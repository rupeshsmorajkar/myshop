package com.myshop.products.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.products.model.Products;
import com.myshop.products.model.ServiceResponse;
import com.myshop.products.service.ProductService;

@RestController
@RequestMapping(path="/product")
public class ProductController {
	public static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productServiceImpl;

	@RequestMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public ServiceResponse getProduct(@PathVariable String id) {
		LOGGER.info("entering getProduct");

		ServiceResponse sr = new ServiceResponse();
		sr.setStatus("success");
		sr.setData(productServiceImpl.fetchProduct(id));
		return sr;
	}

	@RequestMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.DELETE)
	public ServiceResponse deleteProduct(@PathVariable String id) {
		LOGGER.info("entering deleteProduct");
		ServiceResponse sr = new ServiceResponse();
		sr.setStatus("success");
		sr.setData(productServiceImpl.deleteProduct(id));
		return sr;
	}
	
	@RequestMapping(path="/", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.PUT)
	public ServiceResponse updateProduct(@RequestBody List<Products> products) {
		LOGGER.info("entering updateProduct");

		ServiceResponse sr = new ServiceResponse();
		sr.setStatus("success");
		sr.setData(productServiceImpl.updateProduct(products));
		return sr;
	}
	
	@RequestMapping(path="/", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public ServiceResponse createProduct(@RequestBody List<Products> products) {
		LOGGER.info("entering createProduct");
		
		ServiceResponse sr = new ServiceResponse();
		sr.setStatus("success");
		sr.setData(productServiceImpl.createProduct(products));
		return sr;
	}
}
