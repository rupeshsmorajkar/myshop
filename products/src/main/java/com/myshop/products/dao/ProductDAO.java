package com.myshop.products.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myshop.products.model.Products;

public interface ProductDAO {
	public List<Products> fetchProduct(String id) throws DataAccessException;
	
	public void createProduct(Products employee) throws DataAccessException;
	
	public void updateProduct(Products employee) throws DataAccessException;
	
	public void deleteProduct(String id) throws DataAccessException;
}
