package com.wellknown.xiaozhuang.exception;

import com.wellknown.xiaozhuang.utils.Logging;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.beans.Transient;

/**
 * Created by my on 2015/3/25.
 */
public class Exceptions {

    public static class ApiException extends RuntimeException{

        private int statsCode;

        public ApiException(){
            super("服务器内部错误");
            this.statsCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }

        public ApiException(int statusCode, String message) {
            super(message);
            this.statsCode = statusCode;
        }

        public ApiException(Response.Status status, String message) {
            super(message);
            this.statsCode = status.getStatusCode();
        }

        public int getStatsCode() {
            return statsCode;
        }

    }

    public static class DataValidationFailedException extends ApiException {
        public DataValidationFailedException(String message) {
            super(400, message);
            Logging.info(message);
        }
    }
    
    public static class UnauthorizedException extends ApiException {
    	public UnauthorizedException(String message) {
    		super(401, message);
    	}
    }
    
    public static class ForbiddenException extends ApiException {
    	public ForbiddenException(String message) {
    		super(403, message);
    	}
    }
    
    public static class DataNotFoundException extends ApiException {
        public DataNotFoundException(String message) {
            super(404, message);
        }
    }

    public static class DataConflictedException extends ApiException {
        public DataConflictedException(String message) {
            super(409, message);
        }
    }
}
