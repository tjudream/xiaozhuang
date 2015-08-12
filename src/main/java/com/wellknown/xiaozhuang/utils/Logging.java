package com.wellknown.xiaozhuang.utils;

import com.wellknown.xiaozhuang.service.LoggingService;

public class Logging {
    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger("com.wellknown.haoyun");
    private static final boolean ISDEBUG = false;

    private static LoggingService getLoggingService() {
        try {
            return AppContext.getBean("loggingService");
        } catch (Exception e) {
            return null;
        }

    }

    public static void debug(Object s){
    	if (ISDEBUG) {
    		System.out.println(s);
    	} else {
    		LoggingService loggingService = getLoggingService();
            if (loggingService == null)
                logger.debug(s.toString());
            else
    		    logger.debug(loggingService.getLogid() + "  " + loggingService.getPrelog() + "  " + s.toString());
    	}
    }

    public static void info(Object s){
    	if (ISDEBUG) {
    		System.out.println(s);
    	} else {
    		LoggingService loggingService = getLoggingService();
            if (loggingService == null)
                logger.debug(s.toString());
            else
    		    logger.debug(loggingService.getLogid() + "  " + loggingService.getPrelog() + "  " + s.toString());
    	}
    }

    public static void warning(Object s){
    	LoggingService loggingService = getLoggingService();
        if (loggingService == null)
            logger.warn(s.toString());
        else
            logger.warn(loggingService.getLogid() + "  " + loggingService.getPrelog() + "  " + s.toString());
    }

    public static void error(Object s){
    	LoggingService loggingService = getLoggingService();
        logger.error(loggingService.getLogid() + "  " + loggingService.getPrelog() + "  " + s.toString());
    }

}
