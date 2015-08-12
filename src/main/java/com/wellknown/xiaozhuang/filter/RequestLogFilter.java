package com.wellknown.xiaozhuang.filter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.wellknown.xiaozhuang.service.LoggingService;
import com.wellknown.xiaozhuang.utils.AppContext;
import com.wellknown.xiaozhuang.utils.Logging;
import com.wellknown.xiaozhuang.utils.Util;

@Provider
@Priority(1)
@PreMatching
public class RequestLogFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		Logging.debug(" ===== Enter RequestLogFilter =====");
		LoggingService loggingService = AppContext.getBean("loggingService");
		loggingService.setLogid("logid_" + System.currentTimeMillis()/1000 + "_" + Util.genRandom(10));
		loggingService.setPrelog("");
		String method = requestContext.getMethod();
		Logging.debug("method = " + method);
		String path = requestContext.getUriInfo().getPath();
		Logging.debug(path);
		Logging.debug("============Headers===============");
		int contentLength = 0;
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		Iterator<String> iterheader = headers.keySet().iterator();
		while (iterheader.hasNext()) {
			String key = iterheader.next();
			Logging.debug(key + " : " + headers.getFirst(key));
			if (key.equalsIgnoreCase("content-length")) {
				contentLength = Integer.parseInt(headers.getFirst(key));
			}
		}
		
		Logging.debug("============QueryParameters===============");
		//获取QueryParameters
		List<String> queryKeys = new ArrayList<String>();
		MultivaluedMap<String, String> queryPars = requestContext.getUriInfo().getQueryParameters();
		Iterator<String> iter = queryPars.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			queryKeys.add(key);
			//queryParameters += key + "=" + queryPars.getFirst(key) + "&"; //参数中不含数组
		}
		Collections.sort(queryKeys);
		
		String queryParameters = "";
		
		for (int i=0;i<queryKeys.size();i++) {
			String key = queryKeys.get(i);
			queryParameters += key + "=" + queryPars.getFirst(key) + "&"; //参数中不含数组
		}
		if (queryParameters.length() > 0) 
			queryParameters = queryParameters.substring(0, queryParameters.length() - 1);
		Logging.debug("queryParameters = " + queryParameters);
		
		Logging.debug("============PostParameters===============");
		//获取PostParameters
		requestContext.setEntityStream(new BufferedInputStream(requestContext.getEntityStream()));
		List<String> postKeys = new ArrayList<String>();
		String postParameterStrs = this.inputStreamToString(requestContext.getEntityStream(), contentLength);
		if (postParameterStrs != null && postParameterStrs.length() > 0) {
			String[] postParameters = postParameterStrs.split("&");
			for (int i=0;i<postParameters.length;i++) {
				postKeys.add(postParameters[i]);
			}
		}
		Collections.sort(postKeys);
		String postParameters = "";
		for (String para: postKeys) {
			postParameters += para + "&";
		}
		if (postParameters.length() > 0)
			postParameters = postParameters.substring(0, postParameters.length() - 1);
		Logging.debug("postParameters = " + postParameters);
		
	}
	
	public String inputStreamToString(InputStream in, int contentLen) throws IOException {
		if (contentLen == 0) {
			return "";
		}
		StringBuffer out = new StringBuffer();
        int len = contentLen;
        while (len < Integer.MAX_VALUE && len > 0) {
        	byte[] b = new byte[len];
        	in.mark(len);
        	if (in.read(b) <= len) {
        		in.reset();
        		break;
        	}
        	in.reset();
        	len <<= 1;
        }
        
        in.mark(len);
        byte[] b = new byte[len];
        int n;
        for ( n = 0; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        in.reset();
        Logging.debug("the len = " + len + "; b = " + b.length + "; n = " + n);
        return out.toString();
	}

}
