package com.wellknown.xiaozhuang.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellknown.xiaozhuang.utils.ObjectMapperFactory;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by my on 2015/3/27.
 */

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return ObjectMapperFactory.get();
    }
}
