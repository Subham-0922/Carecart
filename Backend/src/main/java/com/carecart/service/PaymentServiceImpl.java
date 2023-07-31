package com.carecart.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecart.exception.OrderException;
import com.carecart.exception.PaymentException;
import com.carecart.models.Orders;
import com.carecart.models.Payment;
import com.carecart.repository.OrderRepository;
import com.carecart.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Override
	public Payment addPayment(Payment payment, long orderId) throws PaymentException, OrderException {
		// TODO Auto-generated method stub
		Orders existingOrder=orderRepository.findById(orderId).orElseThrow(()->new OrderException("Order not Found"));
		payment.setOrderId(existingOrder);
		payment.setAllowed(true);
		existingOrder.setPaymentAttached(true);
		existingOrder.setPaymentId(payment);
		orderRepository.save(existingOrder);
		return paymentRepository.save(payment);
	}

	@Override
	public Payment makePayment(long paymentId) throws PaymentException, OrderException {
		// TODO Auto-generated method stub
		Payment existingPayment = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentException("Payment history not Found"));
		existingPayment.setComplete(true);
		Orders order=existingPayment.getOrderId();
		
		orderRepository.save(order);
		return paymentRepository.save(existingPayment);
	}

	@Override
	public String toogleAllowance(long paymentId) throws PaymentException {
		// TODO Auto-generated method stub
		Payment existingPayment = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentException("Payment history not Found"));
		existingPayment.setAllowed(!existingPayment.isAllowed());
		return "Now it is "+existingPayment.isAllowed();
	}

}
