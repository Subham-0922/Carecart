package com.carecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carecart.models.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
