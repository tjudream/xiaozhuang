package com.wellknown.xiaozhuang.service.impl;

import com.wellknown.xiaozhuang.service.LoggingService;

public class LoggingServiceImpl implements LoggingService {
	private String logid;
	private String prelog;
	
	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getPrelog() {
		return prelog;
	}

	public void setPrelog(String prelog) {
		this.prelog = prelog;
	}
	
	
}
