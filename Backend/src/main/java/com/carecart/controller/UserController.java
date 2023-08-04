package com.carecart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carecart.dto.UserUpdateDto;
import com.carecart.exception.OrderDetailsException;
import com.carecart.exception.OrderException;
import com.carecart.exception.PaymentException;
import com.carecart.exception.UserException;
import com.carecart.models.OrderDetails;
import com.carecart.models.Orders;
import com.carecart.models.Payment;
import com.carecart.models.Users;
import com.carecart.service.OrderDetailsService;
import com.carecart.service.OrdersService;
import com.carecart.service.PaymentService;
import com.carecart.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderDetailsService detailsService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;
	
	@PutMapping("/update")
	public ResponseEntity<Users> updateProfile(@RequestBody UserUpdateDto user) throws UserException{
		return ResponseEntity.ok(userService.updateUser(user));
	}
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Users> deleteUser(@PathVariable long userId) throws UserException{
		return ResponseEntity.ok(userService.deleteUser(userId));
	}
	@PostMapping("/cart/{userId}/{productId}")
	public ResponseEntity<OrderDetails> addProductToCart(@PathVariable long userId,@PathVariable long productId) throws OrderDetailsException, UserException{
		return ResponseEntity.ok(detailsService.addOrderDatails(userId, productId));
	}
	@PutMapping("/cart/{productId}/{quantity}")
	public ResponseEntity<OrderDetails> updateProductToCart(@PathVariable long productId,@PathVariable int quantity) throws OrderDetailsException, UserException{
		return ResponseEntity.ok(detailsService.updateQuantity(productId, quantity));
	}
	
	@DeleteMapping("/cart/{productId}")
	public ResponseEntity<OrderDetails> deleteProductToCart(@PathVariable long productId) throws OrderDetailsException, UserException{
		return ResponseEntity.ok(detailsService.deleteOrderDetails(productId));
	}
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<List<OrderDetails>> getCartItem(@PathVariable long userId) throws OrderDetailsException, UserException{
		return ResponseEntity.ok(detailsService.getCart(userId));
	}
	@GetMapping("/order/{userId}")
	public ResponseEntity<List<OrderDetails>> getOrderedItem(@PathVariable long userId) throws OrderDetailsException, UserException{
		return ResponseEntity.ok(detailsService.getOrderedCart(userId));
	}
	@GetMapping("/delivered/{userId}")
	public ResponseEntity<List<OrderDetails>> getDeliveredItem(@PathVariable long userId) throws OrderDetailsException, UserException{
		return ResponseEntity.ok(detailsService.getDeliveredOrders(userId));
	}
	@PostMapping("/order/{userId}")
	public ResponseEntity<Orders> createOrder(@PathVariable long userId,@RequestParam(required = false) String promo) throws OrderException, OrderDetailsException, UserException{
		return ResponseEntity.ok(ordersService.createOrder(userId, promo));
	}
	
	@PostMapping("/payment/{orderId}")
	public ResponseEntity<Payment> CreatePayment(@RequestBody Payment payment,@PathVariable long orderId) throws PaymentException, OrderException{
		return ResponseEntity.ok(paymentService.addPayment(payment, orderId));
	}
	@PutMapping("/payment/{paymentId}")
	public ResponseEntity<Payment> completePayment(@PathVariable long paymentId) throws PaymentException, OrderException{
		return ResponseEntity.ok(paymentService.makePayment(paymentId));
	}
	
	
	
	
}
