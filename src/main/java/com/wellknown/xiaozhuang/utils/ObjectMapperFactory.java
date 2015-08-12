package com.wellknown.xiaozhuang.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * Created by my on 2015/5/4.
 */
public class ObjectMapperFactory {

    private static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JaxbAnnotationModule());
    }

    public static ObjectMapper get() {
        return mapper;
    }

}
