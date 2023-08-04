package com.carecart.service;

import com.carecart.exception.PromoException;
import com.carecart.models.Promo;

public interface PromoService {
	
	Promo addPromo(Promo promo)throws PromoException;
	Promo deletePromo(long promoId)throws PromoException;
	
}
