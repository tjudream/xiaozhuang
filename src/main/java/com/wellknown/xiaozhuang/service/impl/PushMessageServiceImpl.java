package com.wellknown.xiaozhuang.service.impl;

import org.springframework.util.StringUtils;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.wellknown.xiaozhuang.dao.MobileAndChannelIdDao;
import com.wellknown.xiaozhuang.exception.Exceptions;
import com.wellknown.xiaozhuang.service.PushMessageService;
import com.wellknown.xiaozhuang.utils.BaiduPush;

public class PushMessageServiceImpl implements PushMessageService {

	private MobileAndChannelIdDao mobileAndChannelIdDao;
    
    public void setMobileAndChannelIdDao(MobileAndChannelIdDao mobileAndChannelIdDao) {
    	this.mobileAndChannelIdDao = mobileAndChannelIdDao;
    }
    
	@Override
	public void pushMsg(String mobilePhone, String msg) {
		String channelId = mobileAndChannelIdDao.getChannelIdByMobile(mobilePhone);
		if (StringUtils.isEmpty(channelId)) {
			throw new Exceptions.DataNotFoundException("手机号未注册");
		}
		try {
			BaiduPush.pushMsgToSignalIOSDevice(channelId, msg);
		} catch (Exception e) {
			throw new Exceptions.ApiException(500, "百度推送失败，请稍后再试");
		}
	}

}
