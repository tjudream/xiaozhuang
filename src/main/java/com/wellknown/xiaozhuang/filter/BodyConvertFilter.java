package com.wellknown.xiaozhuang.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellknown.xiaozhuang.utils.Logging;
import com.wellknown.xiaozhuang.utils.ObjectMapperFactory;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by my on 2015/4/14.
 */
@Provider
@Priority(3)
@PreMatching
public class BodyConvertFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext rc) throws IOException {

    	Logging.debug("======== Enter BodyConvertFilter =======");
    	
        String contentType = rc.getHeaderString("content-type");
        Logging.debug("contentType = {" + contentType + "}");
//        if (!rc.getMethod().equalsIgnoreCase("POST") && !rc.getMethod().equalsIgnoreCase("PUT")) {
//        	Logging.debug("======== not post or put method skip formCovertFilter =======");
//            return;
//        }
        if (!"application/json".equals(contentType)) {
        	//Logging.debug("======== not a application/json skip formCovertFilter =======");
        	return;
        }

        InputStream is = rc.getEntityStream();
        byte[] bts = new byte[is.available()];
        is.read(bts);

        if (new String(bts).length() == 0)
            return;

        StringBuffer sb = new StringBuffer();

        ObjectMapper mapper = ObjectMapperFactory.get();

        JsonNode jn = mapper.readTree(bts);
        Iterator<Map.Entry<String, JsonNode>> it = jn.fields();
        while (it.hasNext()) {
            Map.Entry<String, JsonNode> next = it.next();
            sb.append(next.getKey() + "=" + next.getValue().asText() + "&");
        }

        int idx = sb.lastIndexOf("&");
        String body = sb.toString();
        if (idx > 0)
            body = sb.toString().substring(0, idx);

        rc.setEntityStream(new ByteArrayInputStream(body.getBytes()));
        rc.getHeaders().putSingle("content-type", "application/x-www-form-urlencoded");
        is.close();
    }
}
