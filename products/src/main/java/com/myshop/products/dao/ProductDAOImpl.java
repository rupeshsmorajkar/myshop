package com.myshop.products.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.myshop.products.model.Products;

@Repository
public class ProductDAOImpl implements ProductDAO {
	public static final String FETCH_BY_ID_QUERY = "select * from products where id = ?";
	public static final String INSERT_QUERY = "INSERT INTO products (id, category, imageUrl, price, title) values (:id, :category, :imageUrl, :price, :title)";
	public static final String UPDATE_QUERY = "UPDATE products SET imageUrl = :imageUrl, price = :price, title = :title WHERE id = :id";
	public static final String DELETE_QUERY = "DELETE FROM products WHERE id = ?";

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate; 
	
	@PostConstruct
	public void init() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Products> fetchProduct(String id) throws DataAccessException {
		return jdbcTemplate.query(FETCH_BY_ID_QUERY, new Object[] { id }, new BeanPropertyRowMapper(Products.class));
	}

	@Override
	public void createProduct(Products products) throws DataAccessException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		template.update(INSERT_QUERY, new BeanPropertySqlParameterSource(products));
	}

	@Override
	public void updateProduct(Products products) throws DataAccessException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		template.update(UPDATE_QUERY, new BeanPropertySqlParameterSource(products));
	}

	@Override
	public void deleteProduct(String id) throws DataAccessException {
		jdbcTemplate.update(DELETE_QUERY, id);
	}

}
