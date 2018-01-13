package com.myshop.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myshop.products.dao.ProductDAO;
import com.myshop.products.model.Products;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAOImpl;
	
	@Override
	@HystrixCommand(
			fallbackMethod = "fallbackProductById",
			threadPoolKey = "fetchProductThreadPool",
			threadPoolProperties = {
					@HystrixProperty(name="coreSize", value = "20"),  
					@HystrixProperty(name="maxQueueSize", value="10")
			}
	)
	public List<Products> fetchProduct(String id) throws DataAccessException {
		return productDAOImpl.fetchProduct(id);
	}
	
	private List<Products> fallbackProductById(String id)  {
		Products prod = new Products();
		prod.setId(id);
		prod.setTitle("fallback product");
		prod.setCategory("dummy category");
		
		List<Products> list = new ArrayList<>();
		list.add(prod);

		return list;
	}

	@Override
	public boolean createProduct(List<Products> products) throws DataAccessException {
		try {
			if(products == null || products.isEmpty())
				return false;
			
			for(Products prods: products) {
				productDAOImpl.createProduct(prods);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateProduct(List<Products> products) throws DataAccessException {
		try {
			if(products == null || products.isEmpty())
				return false;
			
			for(Products prods: products) {
				productDAOImpl.updateProduct(prods);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteProduct(String id) throws DataAccessException {
		productDAOImpl.deleteProduct(id);
		return true;
	}

}
