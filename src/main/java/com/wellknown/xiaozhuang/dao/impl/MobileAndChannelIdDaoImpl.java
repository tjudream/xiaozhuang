package com.wellknown.xiaozhuang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wellknown.xiaozhuang.dao.MobileAndChannelIdDao;
import com.wellknown.xiaozhuang.model.MobileAndChannelId;
import com.wellknown.xiaozhuang.model.ModelList;

public class MobileAndChannelIdDaoImpl extends JdbcDaoSupport implements MobileAndChannelIdDao  {

	private static final String TABLE = "mobile_channelid";

	public static final RowMapper<MobileAndChannelId> sRowMapper = new RowMapper<MobileAndChannelId>() {
        @Override
        public MobileAndChannelId mapRow(ResultSet rs, int rowNum) throws SQLException {
        	MobileAndChannelId ret = new MobileAndChannelId();
            ret.setId(rs.getInt("id"));
            ret.setMobilePhone(rs.getString("mobile_phone"));
            ret.setChannelId(rs.getString("channel_id"));
            return ret;
        }
    };

	@Override
	public ModelList getMobileAndChannelIdList(String mobilePhone, int page,
			int pageNum) {
		ModelList ml = new ModelList();
		String sql = "select * from " + TABLE + " where is_deleted=0 order by create_time desc limit " + page + "," + pageNum;
		String countSql = "select count(1) from " + TABLE + " where is_deleted=0";
		List<MobileAndChannelId> mcList = getJdbcTemplate().query(sql, sRowMapper);
		int total = getJdbcTemplate().queryForObject(countSql, Integer.class);
		ml.setObjList(mcList);
		ml.setTotalcount(total);
		return ml;
	}

	@Override
	public MobileAndChannelId createMobileAndChannelId(
			final MobileAndChannelId mobileAndChannelId) {
		MobileAndChannelId ret = getJdbcTemplate().execute(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "insert into " + TABLE + " (mobile_phone,channel_id) values(?,?)";
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, mobileAndChannelId.getMobilePhone());
                ps.setString(2, mobileAndChannelId.getChannelId());
                return ps;
            }
        }, new PreparedStatementCallback<MobileAndChannelId>() {
            @Override
            public MobileAndChannelId doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                MobileAndChannelId c = new MobileAndChannelId();
                c.setId(rs.getInt(1));
                return c;
            }
        });
		
        return ret;
	}

	@Override
	public int updateMobileAndChannelIdById(
			MobileAndChannelId mobileAndChannelId) {
		String sql = "update " + TABLE + " set channel_id=? where mobile_phone = ? ";
		Object[] var = {
				mobileAndChannelId.getChannelId(),
				mobileAndChannelId.getMobilePhone()
		};
		return getJdbcTemplate().update(sql, var);
	}

	@Override
	public String getChannelIdByMobile(String mobilePhone) {
		String sql = "select channel_id from " + TABLE + " where mobile_phone = ?";
		return getJdbcTemplate().queryForObject(sql, String.class, mobilePhone);
	}

}
