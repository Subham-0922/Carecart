package com.carecart.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(IllegalArgumentException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
	
	 return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		

	}
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(CategoryException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		
		return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		
		
	}
	@ExceptionHandler(OrderDetailsException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(OrderDetailsException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		
		return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		
		
	}
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(OrderException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		
		return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		
		
	}
	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(PaymentException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		
		return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(ProductException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		
		return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		
		
	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(UserException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		
		return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		
		
	}
	@ExceptionHandler(PromoException.class)
	public ResponseEntity<MyExceptionHandler> myExpHandler(PromoException ie,WebRequest req)  {
		System.out.println("inside myHandler method...");
		
		
		return new ResponseEntity<MyExceptionHandler>(new MyExceptionHandler(LocalDateTime.now(),ie.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
		
		
	}
	
}
