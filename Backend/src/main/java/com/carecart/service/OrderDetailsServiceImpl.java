package com.carecart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecart.exception.OrderDetailsException;
import com.carecart.exception.UserException;
import com.carecart.models.OrderDetails;
import com.carecart.models.Users;
import com.carecart.repository.CustomerRepository;
import com.carecart.repository.OrderDetailsRepository;
import com.carecart.repository.ProductRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderDetailsRepository detailsRepository;
	@Override
	public OrderDetails addOrderDatails(long userId, long productId)
			throws OrderDetailsException, UserException {
		// TODO Auto-generated method stub
		OrderDetails od=new OrderDetails();
		od.setDelivered(false);
		od.setOrdered(false);
		od.setProductId(productRepository.findById(productId).orElseThrow(()->new OrderDetailsException("Product not found")));
		od.setQuantity(1);
		od.setUser(customerRepository.findById(userId).orElseThrow(()->new UserException("User not found")));
		Users user=od.getUser();
		List<OrderDetails> list=user.getOrdersDetails();
		list.add(od);
		user.setOrdersDetails(list);
		customerRepository.save(user);
		return od;
	}

	@Override
	public OrderDetails updateQuantity(long orderDetailsId,int quantity) throws OrderDetailsException {
		// TODO Auto-generated method stub
		OrderDetails od=detailsRepository.findById(orderDetailsId).orElseThrow(()->new OrderDetailsException("Order details box not found"));
		od.setQuantity(quantity);
		return detailsRepository.save(od);
	}

	@Override
	public OrderDetails deleteOrderDetails(long orderDetailsId) throws OrderDetailsException {
		// TODO Auto-generated method stub
		OrderDetails od=detailsRepository.findById(orderDetailsId).orElseThrow(()->new OrderDetailsException("Order details box not found"));
		detailsRepository.delete(od);
		return od;
	}

	@Override
	public List<OrderDetails> getCart(long userId) throws OrderDetailsException, UserException {
		// TODO Auto-generated method stub
		List<OrderDetails> od=detailsRepository.findAll();
		
		return od.stream().filter((a)->!a.isOrdered()).toList();
	}

	@Override
	public List<OrderDetails> getOrderedCart(long userId) throws OrderDetailsException, UserException {
		List<OrderDetails> od=detailsRepository.findAll();
		
		return od.stream().filter((a)->a.isOrdered()).toList();
	}

	@Override
	public List<OrderDetails> getDeliveredOrders(long userId) throws OrderDetailsException, UserException {
		List<OrderDetails> od=detailsRepository.findAll();
		
		return od.stream().filter((a)->a.isDelivered()).toList();
	}

}
