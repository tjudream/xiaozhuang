package com.wellknown.xiaozhuang.service.impl;


import org.springframework.util.StringUtils;

import com.wellknown.xiaozhuang.dao.MobileAndChannelIdDao;
import com.wellknown.xiaozhuang.exception.Exceptions;
import com.wellknown.xiaozhuang.model.MobileAndChannelId;
import com.wellknown.xiaozhuang.model.ModelList;
import com.wellknown.xiaozhuang.service.MobileAndChannelIdService;

public class MobileAndChannelIdServiceImpl implements MobileAndChannelIdService {

	private MobileAndChannelIdDao mobileAndChannelIdDao;
    
    public void setMobileAndChannelIdDao(MobileAndChannelIdDao mobileAndChannelIdDao) {
    	this.mobileAndChannelIdDao = mobileAndChannelIdDao;
    }

	@Override
	public ModelList getMobileAndChannelIdList(String mobilePhone, int page,
			int pageNum) {
		return mobileAndChannelIdDao.getMobileAndChannelIdList(mobilePhone, page, pageNum);
	}

	@Override
	public MobileAndChannelId createMobileAndChannelId(
			MobileAndChannelId mobileAndChannelId) {
		if (StringUtils.isEmpty(mobileAndChannelId.getMobilePhone())) {
			throw new Exceptions.DataNotFoundException("手机号不能为空");
		}
		if (StringUtils.isEmpty(mobileAndChannelId.getChannelId())) {
			throw new Exceptions.DataNotFoundException("channelId不能为空");
		}
		return mobileAndChannelIdDao.createMobileAndChannelId(mobileAndChannelId);
	}

	@Override
	public void updateMobileAndChannelIdById(
			MobileAndChannelId mobileAndChannelId) {
		if (StringUtils.isEmpty(mobileAndChannelId.getMobilePhone())) {
			throw new Exceptions.DataNotFoundException("手机号不能为空");
		}
		if (StringUtils.isEmpty(mobileAndChannelId.getChannelId())) {
			throw new Exceptions.DataNotFoundException("channelId不能为空");
		}
		if (mobileAndChannelIdDao.updateMobileAndChannelIdById(mobileAndChannelId) <= 0) {
			throw new Exceptions.DataNotFoundException("资源不存在");
		}
	}

}
