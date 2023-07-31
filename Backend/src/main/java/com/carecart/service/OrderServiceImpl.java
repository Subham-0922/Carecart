package com.carecart.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carecart.exception.OrderDetailsException;
import com.carecart.exception.OrderException;
import com.carecart.exception.PaymentException;
import com.carecart.exception.UserException;
import com.carecart.models.OrderDetails;
import com.carecart.models.Orders;

@Service
public class OrderServiceImpl implements OrdersService {

	@Override
	public Orders createOrder(List<OrderDetails> cart, long userId, String promo)
			throws OrderException, OrderDetailsException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders toogleOrderStatus(long orderid) throws OrderException, PaymentException {
		// TODO Auto-generated method stub
		return null;
	}

}
