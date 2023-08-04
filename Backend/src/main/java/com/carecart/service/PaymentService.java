package com.carecart.service;

import com.carecart.exception.OrderException;
import com.carecart.exception.PaymentException;
import com.carecart.models.Payment;

public interface PaymentService {
	
	Payment addPayment(Payment payment,long orderId)throws PaymentException,OrderException;
	Payment makePayment(long paymentId) throws PaymentException,OrderException;
//	String toogleAllowance(long paymentId) throws PaymentException;
}
