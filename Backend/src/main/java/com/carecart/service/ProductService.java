package com.carecart.service;

import java.util.List;

import com.carecart.dto.ProductUpdateDto;
import com.carecart.exception.CategoryException;
import com.carecart.exception.ProductException;
import com.carecart.models.Products;

public interface ProductService {
	
	Products addProduct(Products product)throws ProductException;
	Products deleteProduct(long productId) throws ProductException;
	Products updateProduct(ProductUpdateDto product)throws ProductException;
	List<Products> displayProducts()throws ProductException;
	List<Products> displayProductByCategory(long categoryid) throws ProductException,CategoryException;
	Products getProduct(long productId) throws ProductException;
	List<Products> searchProducts(String query) throws ProductException;
	
}
