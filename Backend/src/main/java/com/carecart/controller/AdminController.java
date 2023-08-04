package com.carecart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carecart.dto.ProductUpdateDto;
import com.carecart.exception.CategoryException;
import com.carecart.exception.OrderDetailsException;
import com.carecart.exception.OrderException;
import com.carecart.exception.PaymentException;
import com.carecart.exception.ProductException;
import com.carecart.exception.PromoException;
import com.carecart.exception.UserException;
import com.carecart.models.Category;
import com.carecart.models.OrderDetails;
import com.carecart.models.Orders;
import com.carecart.models.Products;
import com.carecart.models.Promo;
import com.carecart.models.Users;
import com.carecart.service.CategoryService;
import com.carecart.service.OrderDetailsService;
import com.carecart.service.OrdersService;
import com.carecart.service.ProductService;
import com.carecart.service.PromoService;
import com.carecart.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderDetailsService detailsService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private PromoService promoService;
	
	@PostMapping("/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) throws CategoryException{
		return new ResponseEntity<Category>(categoryService.addCategory(category),HttpStatus.OK);
	}
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) throws CategoryException{
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>("Category and its products are deleted",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Products> createProduct(@RequestBody Products product) throws ProductException{
		return new ResponseEntity<Products>(productService.addProduct(product),HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Products> deleteProduct(@PathVariable long productId) throws ProductException{
		return new ResponseEntity<Products>(productService.deleteProduct(productId),HttpStatus.OK);
	}
	
	@PutMapping("/products")
	public ResponseEntity<Products> updateProduct(@RequestBody ProductUpdateDto productDto) throws ProductException{
		return new ResponseEntity<Products>(productService.updateProduct(productDto),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers() throws UserException{
		return new ResponseEntity<List<Users>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/user/cart/{userId}")
	public ResponseEntity<List<OrderDetails>> getCartByAdmin(@PathVariable long userId) throws OrderDetailsException, UserException{
		return new ResponseEntity<List<OrderDetails>>(detailsService.getCart(userId),HttpStatus.OK);
	}
	@GetMapping("/user/order/{userId}")
	public ResponseEntity<List<OrderDetails>> getOrdersByAdmin(@PathVariable long userId) throws OrderDetailsException, UserException{
		return new ResponseEntity<List<OrderDetails>>(detailsService.getOrderedCart(userId),HttpStatus.OK);
	}
	@GetMapping("/user/deliver/{userId}")
	public ResponseEntity<List<OrderDetails>> getDeliveredItemsByAdmin(@PathVariable long userId) throws OrderDetailsException, UserException{
		return new ResponseEntity<List<OrderDetails>>(detailsService.getDeliveredOrders(userId),HttpStatus.OK);
	}
	
	@PatchMapping("/orders/{orderId}")
	public ResponseEntity<Orders> orderDeliveredByAdmin(@PathVariable long orderId) throws OrderException, PaymentException{
		return new ResponseEntity<Orders>(ordersService.toogleOrderStatus(orderId),HttpStatus.OK);
	}
	@PostMapping("/promo")
	public ResponseEntity<Promo> createPromo(@RequestBody Promo promo) throws PromoException{
		return ResponseEntity.ok(promoService.addPromo(promo));
	}
	@DeleteMapping("/promo/{promoId}")
	public ResponseEntity<Promo> removePromo(@PathVariable long promoId) throws PromoException{
		return ResponseEntity.ok(promoService.deletePromo(promoId));
	}
	
	
	
	
	
}
