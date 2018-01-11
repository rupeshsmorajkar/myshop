package com.myshop.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myshop.products.dao.ProductDAO;
import com.myshop.products.model.Products;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAOImpl;
	
	@Override
	public List<Products> fetchProduct(String id) throws DataAccessException {
		return productDAOImpl.fetchProduct(id);
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
