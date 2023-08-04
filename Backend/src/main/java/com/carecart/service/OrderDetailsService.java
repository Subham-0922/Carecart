package com.carecart.service;

import java.util.List;

import com.carecart.exception.OrderDetailsException;
import com.carecart.exception.UserException;
import com.carecart.models.OrderDetails;

public interface OrderDetailsService {
	OrderDetails addOrderDatails(long userId,long productId) throws OrderDetailsException,UserException;
	OrderDetails updateQuantity(long orderDetailsId,int quantity) throws OrderDetailsException;
	OrderDetails deleteOrderDetails(long orderDetailsId) throws OrderDetailsException;
	List<OrderDetails> getCart(long userId) throws OrderDetailsException,UserException;
	List<OrderDetails> getOrderedCart(long userId) throws OrderDetailsException,UserException;
	List<OrderDetails> getDeliveredOrders(long userId) throws OrderDetailsException,UserException;
	
}
