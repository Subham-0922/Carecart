package com.carecart.models;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDetailsId;
	@OneToOne
	private Products productId;
	
	private int quantity;
	@ManyToOne
	private Orders orderId;
	@ManyToOne
	private Users user;
	private LocalDate deliveryDate;
	private boolean isDelivered;
	private boolean isOrdered;//false=cart   |||     true=ordered
	@Override
	public int hashCode() {
		return Objects.hash(orderDetailsId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetails other = (OrderDetails) obj;
		return orderDetailsId == other.orderDetailsId;
	}
	
	
}
