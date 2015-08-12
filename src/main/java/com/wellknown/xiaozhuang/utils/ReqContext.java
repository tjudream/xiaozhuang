package com.wellknown.xiaozhuang.utils;

import org.glassfish.jersey.server.Uri;

import javax.ws.rs.core.UriInfo;

/**
 * Created by Administrator on 2015/4/13.
 */
public class ReqContext {

    private UriInfo uri;

    public static ReqContext get() {
        return AppContext.getBean("requestContext");
    }

    public UriInfo getUri() {
        return uri;
    }

    public void setUri(UriInfo uri) {
        this.uri = uri;
    }
}
