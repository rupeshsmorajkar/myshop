package com.myshop.products.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myshop.products.model.ServiceResponse;

@ControllerAdvice
public class MyShopControllerAdvice {

	@ExceptionHandler(DataAccessException.class)
	public ServiceResponse handleDataAccessException(HttpServletRequest request, Exception ex) {
		return getErrorresponse("Sarvice failed while processing data");
	}

	@ExceptionHandler(Exception.class)
	public ServiceResponse handleException(HttpServletRequest request, Exception ex) {
		return getErrorresponse("General error occured while processing request");
	}

	private ServiceResponse getErrorresponse(String message) {
		ServiceResponse sr = new ServiceResponse();
		sr.setStatus("failed");
		sr.setData(message);

		return sr;
	}
}
