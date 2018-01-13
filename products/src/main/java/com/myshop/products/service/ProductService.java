package com.myshop.products.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myshop.products.model.Products;

public interface ProductService {
	/**
	 * get product by id.
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<Products> fetchProduct(String id) throws DataAccessException;
	
	/**
	 * create product 
	 * 
	 * @param products
	 * @return
	 * @throws DataAccessException
	 */
	public boolean createProduct(List<Products> products) throws DataAccessException;
	
	/**
	 * update product.
	 * 
	 * @param products
	 * @return
	 * @throws DataAccessException
	 */
	public boolean updateProduct(List<Products> products) throws DataAccessException;
	
	/**
	 * delete product
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public boolean deleteProduct(String id) throws DataAccessException;
}
