package com.carecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carecart.models.Promo;

public interface PromoRepository extends JpaRepository<Promo, Long> {
	Promo findByPromoCode(String promoCode);
}
