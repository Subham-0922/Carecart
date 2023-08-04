package com.carecart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carecart.exception.CategoryException;
import com.carecart.exception.ProductException;
import com.carecart.exception.UserException;
import com.carecart.models.Category;
import com.carecart.models.Products;
import com.carecart.models.Users;
import com.carecart.service.CategoryService;
import com.carecart.service.ProductService;
import com.carecart.service.UserService;

@RestController
@RequestMapping("/guest")
public class GuestController {
	@Autowired
	private UserService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	@PostMapping("/signup")
	public ResponseEntity<Users> signUp(@RequestBody Users user,@RequestParam(required = false) String secretKey) throws UserException{
		return ResponseEntity.ok(service.addUser(user, secretKey));
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllCategory() throws CategoryException{
		return new ResponseEntity<List<Category>>(categoryService.showAllCategory(),HttpStatus.OK);
	}
	@GetMapping("/products")
	public ResponseEntity<List<Products>> getProducts() throws ProductException{
		return new ResponseEntity<List<Products>>(productService.displayProducts(), HttpStatus.ACCEPTED);
	}
	@GetMapping("/products/category")
	public ResponseEntity<List<Products>> getProductsByCategory(@RequestParam long categoryId) throws ProductException, CategoryException{
		return new ResponseEntity<List<Products>>(productService.displayProductByCategory(categoryId), HttpStatus.OK);
	}
	@GetMapping("/products/search")
	public ResponseEntity<List<Products>> searchProducts(@RequestParam String query) throws ProductException, CategoryException{
		return new ResponseEntity<List<Products>>(productService.searchProducts(query), HttpStatus.OK);
	}
	
	
}
