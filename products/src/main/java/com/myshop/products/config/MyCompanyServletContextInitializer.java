package com.myshop.products.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;


public class MyCompanyServletContextInitializer implements ServletContextInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("LOG_CONFIG_FILE_NAME", "/resources");
	}
}
