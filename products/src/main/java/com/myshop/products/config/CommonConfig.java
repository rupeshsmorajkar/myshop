package com.myshop.products.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.myshop.products.common.logging.LoggingFilter;

@Configuration
@EnableCircuitBreaker
@ServletComponentScan(basePackages="com.myshop.products")
@ComponentScan(basePackages= { "com.myshop.products" })
public class CommonConfig {
	
	/*@Bean
	public ServletContextInitializer mycompanyInitializer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.setInitParameter("LOG_CONFIG_FILE_NAME", "/resources");
			}
		};
	}*/
	
	@Bean
	public FilterRegistrationBean loggerfilter() {
		FilterRegistrationBean fb = new FilterRegistrationBean();
		
		fb.setFilter(new LoggingFilter());
		fb.setOrder(2);
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/*");
		fb.setUrlPatterns(urlPatterns);
		
		return fb;
	}
	
}
