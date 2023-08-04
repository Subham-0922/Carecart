package com.carecart.service;

import org.springframework.stereotype.Service;

import com.carecart.exception.PromoException;
import com.carecart.models.Promo;
import com.carecart.repository.PromoRepository;

@Service
public class PromoServiceImpl implements PromoService {
	
	private PromoRepository promoRepository;

	@Override
	public Promo addPromo(Promo promo) throws PromoException {
		if(promo==null)throw new PromoException("Promo is not valid");
		return promoRepository.save(promo);
	}

	@Override
	public Promo deletePromo(long promoId) throws PromoException {
		Promo promo=promoRepository.findById(promoId).orElseThrow(()-> new PromoException("Promo not found"));
		promoRepository.delete(promo);
		return promo;
	}
	
}
