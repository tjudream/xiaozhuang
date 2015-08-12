package com.wellknown.xiaozhuang.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

@XmlRootElement
@ApiModel(value = "手机号和频道Id信息")
public class MobileAndChannelId {
	@ApiModelProperty(value = "Id")
	private Integer id;
	
	@ApiModelProperty(value = "手机号")
	@ApiParam(value = "手机号")
	@FormParam("mobilePhone")
	private String mobilePhone;
	
	@ApiModelProperty(value = "频道Id")
	@ApiParam(value = "频道Id")
	@FormParam("channelId")
	private String channelId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}
