package com.wellknown.xiaozhuang.dao;

import com.wellknown.xiaozhuang.model.MobileAndChannelId;
import com.wellknown.xiaozhuang.model.ModelList;

public interface MobileAndChannelIdDao {
	ModelList getMobileAndChannelIdList(String mobilePhone, int page, int pageNum);
	MobileAndChannelId createMobileAndChannelId(MobileAndChannelId mobileAndChannelId);
	int updateMobileAndChannelIdById(MobileAndChannelId mobileAndChannelId);
	String getChannelIdByMobile(String mobilePhone);
}
