package com.quiz.lesson04.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson04.domain.Seller;
import com.quiz.lesson04.mapper.SellerMapper;

@Service
public class SellerBO {
	
	@Autowired
	private SellerMapper sellerMapper;
	
	// i: nickname, profileImageUrl, temperature
	// o: int or void
	public void addSeller(String nickname, 
			String profileImageUrl, double temperature) {
		sellerMapper.insertSeller(nickname, profileImageUrl, temperature);
	}
	
	public Seller getSeller() {
		return sellerMapper.selectSeller();
	}
}
