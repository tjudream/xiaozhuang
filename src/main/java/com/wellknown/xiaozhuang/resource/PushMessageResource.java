package com.wellknown.xiaozhuang.resource;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.wellknown.xiaozhuang.model.MobileAndChannelId;
import com.wellknown.xiaozhuang.service.MobileAndChannelIdService;
import com.wellknown.xiaozhuang.service.PushMessageService;
import com.wellknown.xiaozhuang.utils.AppContext;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/pushmsg")
@Api(value = "/pushmsg", position = 1, description="发送消息")
@Produces(MediaType.APPLICATION_JSON)
public class PushMessageResource {
	
	@Context
    private UriInfo uri;
	private PushMessageService pushMessageService = AppContext.getBean("pushMessageService");
    
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "创建手机号和channelid的对应关系", produces = MediaType.APPLICATION_JSON)
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MobileAndChannelId.class)})
    public Response pushMsgToMobile(
    		@ApiParam(value = "手机号", defaultValue = "18612345678") @FormParam("mobilePhone") String mobilePhone,
    		@ApiParam(value = "消息", defaultValue = "这是测试消息") @FormParam("msg") String msg
    		) {
		pushMessageService.pushMsg(mobilePhone,msg);
        //return Response.ok(mobileAndChannelIdService.createMobileAndChannelId(mobileAndChannelId)).build();
        return Response.created(uri.getAbsolutePath()).build();
    }
}
