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
		existingOrder.setPaymentAttached(true);
		
		existingOrder.setPaymentId(payment);
		if(!payment.getType().equals("COD")) {
			payment.setComplete(true);
		}
		orderRepository.save(existingOrder);
		return paymentRepository.save(payment);
	}

	@Override
	public Payment makePayment(long paymentId) throws PaymentException, OrderException {
		// TODO Auto-generated method stub
		Payment existingPayment = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentException("Payment history not Found"));
		
		existingPayment.setComplete(true);
		return paymentRepository.save(existingPayment);
	}

	

}
