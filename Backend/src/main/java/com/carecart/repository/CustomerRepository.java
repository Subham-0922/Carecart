package com.carecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carecart.models.Users;
@Repository
public interface CustomerRepository extends JpaRepository<Users,Long>{
	Users findByEmail(String email);
	
}
