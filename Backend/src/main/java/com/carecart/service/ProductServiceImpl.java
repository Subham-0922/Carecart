package com.carecart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecart.dto.ProductUpdateDto;
import com.carecart.exception.CategoryException;
import com.carecart.exception.ProductException;
import com.carecart.models.Category;
import com.carecart.models.Products;
import com.carecart.repository.CategoryRepository;
import com.carecart.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public Products addProduct(Products product) throws ProductException {
		if(product==null)throw new ProductException("Product is not Valid");
		
		return productRepository.save(product);
	}

	@Override
	public Products deleteProduct(long productId) throws ProductException {
		Products existingProduct = productRepository.findById(productId).orElseThrow(()->new ProductException("Product not found with this id"));
		productRepository.delete(existingProduct);
		return existingProduct;
	}

	@Override
	public Products updateProduct(ProductUpdateDto productDto) throws ProductException {
		// TODO Auto-generated method stub
		Products existingProduct=productRepository.findById(productDto.getProductId()).orElseThrow(()->new ProductException("Product not found"));
		existingProduct.setBrand(productDto.getBrand());
//		existingProduct.setCategoryId(product.getCategoryId());
		existingProduct.setCostPrice(productDto.getCostPrice());
		existingProduct.setProductName(productDto.getProductName());
		existingProduct.setSalePrice(productDto.getSalePrice());
		return productRepository.save(existingProduct);
	}

	@Override
	public List<Products> displayProducts() throws ProductException {
		// TODO Auto-generated method stub
		List<Products> products=productRepository.findAll();
		if (products.isEmpty())throw new ProductException("Product list is empty");
		return products;
	}

	@Override
	public List<Products> displayProductByCategory(long categoryid) throws ProductException,CategoryException {
		// TODO Auto-generated method stub
		Category category=categoryRepository.findById(categoryid).orElseThrow(()->new CategoryException("Category not found"));
		List<Products> products=productRepository.findByCategoryId(category);
		if (products.isEmpty())throw new ProductException("Product list is empty");
		return products;
	}

	@Override
	public Products getProduct(long productId) throws ProductException {
		// TODO Auto-generated method stub
		Products existingProduct = productRepository.findById(productId).orElseThrow(()->new ProductException("Product not found with this id"));
		return existingProduct;
	}

	@Override
	public List<Products> searchProducts(String query) throws ProductException {
		List<Products> products=productRepository.findByProductNameContainingIgnoreCase(query);
		if(products.isEmpty())throw new ProductException("Products not available");
		return products;
	}
	

}
