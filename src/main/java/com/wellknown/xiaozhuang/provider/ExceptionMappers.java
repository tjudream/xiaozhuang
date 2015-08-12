package com.wellknown.xiaozhuang.provider;

import org.springframework.dao.EmptyResultDataAccessException;

import com.wellknown.xiaozhuang.exception.ErrorRet;
import com.wellknown.xiaozhuang.exception.Exceptions.ApiException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by my on 2015/3/25.
 */
@Provider
public class ExceptionMappers {

    @Provider
    public static class EmptyResultDataAccessMapper implements ExceptionMapper<EmptyResultDataAccessException> {

        @Override
        public Response toResponse(EmptyResultDataAccessException exception) {
            ErrorRet ret = new ErrorRet();
            ret.setMessage("资源不存在");
            ret.setStatusCode(Response.Status.NOT_FOUND.getStatusCode());
            exception.printStackTrace();
            return Response.status(ret.getStatusCode()).entity(ret).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @Provider
    public static class ApiExceptionMapper implements ExceptionMapper<ApiException> {

        @Override
        public Response toResponse(ApiException exception) {
            ErrorRet ret = new ErrorRet();
            ret.setMessage(exception.getMessage());
            ret.setStatusCode(exception.getStatsCode());
            return Response.status(ret.getStatusCode()).entity(ret).type(MediaType.APPLICATION_JSON).build();
        }
    }

//    @Provider
//    public static class NormalMapper implements ExceptionMapper<Exception> {
//
//        @Override
//        public Response toResponse(Exception exception) {
//            ErrorRet ret = new ErrorRet();
//            ret.setMessage("默认异常");
//            ret.setStatusCode(400);
//            return Response.status(400).entity(ret).type(MediaType.APPLICATION_JSON).build();
//        }
//    }

}
