package com.myshop.products.common.logging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class LoggingConfigListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String configLocation = sce.getServletContext().getInitParameter("LOG_CONFIG_FILE_NAME");
		if(configLocation == null){
			configLocation = "src\\main\\resources\\"; 
		}
		
		try{
			new LogBackConfigLoader(configLocation + "logback-dev.xml");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
