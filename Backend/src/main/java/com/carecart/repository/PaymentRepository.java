package com.carecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carecart.models.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
