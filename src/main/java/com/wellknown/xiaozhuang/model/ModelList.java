package com.wellknown.xiaozhuang.model;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

@XmlRootElement
@ApiModel(value = "列表")
public class ModelList {
	
	@ApiModelProperty(value = "总数")
	@ApiParam(value = "总数")
	@FormParam("totalcount")
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private int totalcount;
	
	@ApiModelProperty(value = "列表")
	@ApiParam(value = "列表")
	@FormParam("objList")
	private List objList;

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public List getObjList() {
		return objList;
	}

	public void setObjList(List objList) {
		this.objList = objList;
	}
	
}
