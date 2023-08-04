package com.carecart.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	@ManyToOne
	private Users userId;
	@OneToMany(mappedBy = "orderId")
	private Set<OrderDetails> orderDetails;
	private String promoCode;
	private LocalDate orderDate;
	private LocalDate deliverDate;
	private double totalOrderAmount;
	@OneToOne(mappedBy = "orderId")
	private Payment paymentId;
	private boolean isDelivered;
	private boolean isPaymentAttached;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return orderId == other.orderId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}
	
	
}
