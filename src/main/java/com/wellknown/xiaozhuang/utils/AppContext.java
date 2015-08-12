package com.wellknown.xiaozhuang.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class AppContext implements ApplicationContextAware, ServletContextAware {

    private static ApplicationContext ac;
    private static ServletContext sc;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContext.ac = applicationContext;
    }

    @SuppressWarnings("unchecked")
	public static <T>T getBean(String beanId) {
        if (AppContext.ac == null)
            return null;
        return (T)AppContext.ac.getBean(beanId);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        AppContext.sc = servletContext;
    }

    public static String getProjectPath(){
        return AppContext.sc.getRealPath("/");
    }

}
