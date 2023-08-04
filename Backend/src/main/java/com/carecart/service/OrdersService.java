package com.carecart.service;

import com.carecart.exception.OrderDetailsException;
import com.carecart.exception.OrderException;
import com.carecart.exception.PaymentException;
import com.carecart.exception.UserException;
import com.carecart.models.Orders;

public interface OrdersService {
	Orders createOrder(long userId,String promo) throws OrderException,OrderDetailsException,UserException;
	Orders toogleOrderStatus(long orderid) throws OrderException,PaymentException;
	
}
