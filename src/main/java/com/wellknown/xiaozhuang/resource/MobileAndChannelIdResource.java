package com.wellknown.xiaozhuang.resource;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.wellknown.xiaozhuang.model.MobileAndChannelId;
import com.wellknown.xiaozhuang.service.MobileAndChannelIdService;
import com.wellknown.xiaozhuang.utils.AppContext;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/mobileandchannelid")
@Api(value = "/mobileandchannelid", position = 1, description="手机号和ChannelId")
@Produces(MediaType.APPLICATION_JSON)
public class MobileAndChannelIdResource {
	
	@Context
    private UriInfo uri;
	private MobileAndChannelIdService mobileAndChannelIdService = AppContext.getBean("mobileAndChannelIdService");
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "创建手机号和channelid的对应关系", produces = MediaType.APPLICATION_JSON)
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MobileAndChannelId.class)})
    public Response createMobileAndChannelId(
    		@BeanParam MobileAndChannelId mobileAndChannelId
    		) {
        return Response.ok(mobileAndChannelIdService.createMobileAndChannelId(mobileAndChannelId)).build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "根据Id修改手机号和channelid的对应关系", produces = MediaType.APPLICATION_JSON)
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MobileAndChannelId.class)})
    public Response updateMobileAndChannelIdById(
    		@BeanParam MobileAndChannelId mobileAndChannelId
    		) {
    	mobileAndChannelIdService.updateMobileAndChannelIdById(mobileAndChannelId);
    	return Response.created(uri.getAbsolutePath()).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取手机号和channelid对应关系的列表", produces = MediaType.APPLICATION_JSON)
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MobileAndChannelId.class)})
    public Response getMobileAndChannelIdList(
    		@ApiParam(value = "手机号", defaultValue = "") @QueryParam("mobilePhone") String mobilePhone,
    		@ApiParam(value = "页数, 从0开始", defaultValue = "0") @QueryParam("page") int page,
            @ApiParam(value = "每页记录数", defaultValue = "10") @QueryParam("pageNum") int pageNum
    		) {
        return Response.ok(mobileAndChannelIdService.getMobileAndChannelIdList(mobilePhone, page, pageNum)).build();
    }
    
    
}
