package com.carecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carecart.models.OrderDetails;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
