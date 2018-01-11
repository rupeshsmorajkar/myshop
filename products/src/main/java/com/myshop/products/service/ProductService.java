package com.myshop.products.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myshop.products.model.Products;

public interface ProductService {
	public List<Products> fetchProduct(String id) throws DataAccessException;
	
	public boolean createProduct(List<Products> products) throws DataAccessException;
	
	public boolean updateProduct(List<Products> products) throws DataAccessException;
	
	public boolean deleteProduct(String id) throws DataAccessException;
}
