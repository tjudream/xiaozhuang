package com.wellknown.xiaozhuang.service;

import com.wellknown.xiaozhuang.model.MobileAndChannelId;
import com.wellknown.xiaozhuang.model.ModelList;

public interface MobileAndChannelIdService {
	ModelList getMobileAndChannelIdList(String mobilePhone, int page, int pageNum);
	MobileAndChannelId createMobileAndChannelId(MobileAndChannelId mobileAndChannelId);
	void updateMobileAndChannelIdById(MobileAndChannelId mobileAndChannelId);
}
