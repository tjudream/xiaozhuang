package com.wellknown.xiaozhuang.filter;

import com.wellknown.xiaozhuang.utils.AppContext;
import com.wellknown.xiaozhuang.utils.Logging;
import com.wellknown.xiaozhuang.utils.ReqContext;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import java.io.IOException;

/**
 * Created by my on 2015/4/13.
 */
@Provider
@Priority(2)
@PreMatching
public class RequestContextFilter implements ContainerRequestFilter {

    @Context
    private UriInfo uri;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Logging.debug(" ===== Enter RequestContextFilter =====");
        ReqContext rc = ReqContext.get();
        rc.setUri(uri);
        // rs.setRequestId('uuid');
    }
}
