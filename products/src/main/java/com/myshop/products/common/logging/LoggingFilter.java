package com.myshop.products.common.logging;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LoggingFilter extends OncePerRequestFilter {

	private static final String THREAD = "thread";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		MDC.put("REQUEST_START_TIME", String.valueOf(startTime));
		
		long threadId = Thread.currentThread().getId();
		MDC.put(THREAD, Long.toHexString(threadId));
		
		MDC.put("userId", "myCompanyUser");
		MDC.put("applicationName", "myCompanyProductApp");
		MDC.put("serviceName", "Product_Service");
		
		HttpServletRequest req = new ContentCachingRequestWrapper(request);
		HttpServletResponse resp = new ContentCachingResponseWrapper(response);
		
		filterChain.doFilter(req, resp);
		
		long totaltime = System.currentTimeMillis() - startTime;
		MDC.put("totalTime", Long.toHexString(threadId));
		
	}
	
}
