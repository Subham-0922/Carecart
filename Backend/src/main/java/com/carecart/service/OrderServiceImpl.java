package com.carecart.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecart.exception.OrderDetailsException;
import com.carecart.exception.OrderException;
import com.carecart.exception.PaymentException;
import com.carecart.exception.UserException;
import com.carecart.models.OrderDetails;
import com.carecart.models.Orders;
import com.carecart.models.Promo;
import com.carecart.models.Users;
import com.carecart.repository.CustomerRepository;
import com.carecart.repository.OrderDetailsRepository;
import com.carecart.repository.OrderRepository;
import com.carecart.repository.PromoRepository;

@Service
public class OrderServiceImpl implements OrdersService {
	@Autowired
	private PromoRepository promoRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailsRepository detailsRepository;

	@Override
	public Orders createOrder( long userId, String key)
			throws OrderException, OrderDetailsException, UserException {
		// TODO Auto-generated method stub
		Users user=customerRepository.findById(userId).orElseThrow(()->new UserException("User not Found for this Id"));
		double totalAmount=0.00;
		List<OrderDetails>cart=user.getOrdersDetails().stream().filter((a)->{
			return (a.isOrdered()==false && a.isDelivered()==false);
		}).toList();
		
		for(OrderDetails od:cart) {
			totalAmount+=(double)od.getQuantity()*od.getProductId().getSalePrice();
		}
		Orders order=new Orders();
		if(key!=null||key!="") {
			Promo promo=promoRepository.findByPromoCode(key);
			
			if(promo==null|| !promo.isActive())throw new OrderException("Invalid Promo Code");
			totalAmount-=totalAmount*promo.getDiscount()/100.00;
			order.setPromoCode(key);
		}
		order.setTotalOrderAmount(totalAmount);
		order.setDelivered(false);
		order.setPaymentAttached(false);
		order.setOrderDate(LocalDate.now());
		order.setUserId(user);
		Orders newOrder=orderRepository.save(order);
		cart.forEach((s)->{
			s.setOrderId(newOrder);
			s.setOrdered(true);
			
		});
		
		
		return newOrder;
	}

	@Override
	public Orders toogleOrderStatus(long orderid) throws OrderException, PaymentException {
		// TODO Auto-generated method stub
		Orders order=orderRepository.findById(orderid).orElseThrow(()->new OrderException("Order not found"));
		if(order.getPaymentId().isComplete()==false) {
			throw new PaymentException("Payment is Due , Can't Complete the order");
		}
		order.getOrderDetails().forEach((od)->{
			od.setDeliveryDate(LocalDate.now());
			od.setDelivered(true);
			detailsRepository.save(od);
		});
		order.setDeliverDate(LocalDate.now());
		order.setDelivered(true);
		return order;
	}

}
