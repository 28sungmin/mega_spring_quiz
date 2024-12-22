package com.quiz.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.lesson04.bo.SellerBO;
import com.quiz.lesson04.domain.Seller;

@RequestMapping("/lesson04/quiz01")
@Controller
public class Lesson04Quiz01Controller {
	
	@Autowired
	private SellerBO sellerBO;
	
	// http://localhost:8080/lesson04/quiz01/add-seller-view
	@GetMapping("/add-seller-view")
	public String addSellerView() {
		return "lesson04/addSeller";
	}
	
	// 판매자 추가 API
	// http://localhost:8080/lesson04/quiz01/add-seller
	@PostMapping("/add-seller")
	public String addSeller(
			@RequestParam("nickname") String nickname,
			@RequestParam(value = "profileImageUrl", required = false) String profileImageUrl,
			@RequestParam(value = "temperature", required = false) Double temperature) {
		
		// DB insert
		sellerBO.addSeller(nickname, profileImageUrl, temperature);
		
		// response 화면
		return "lesson04/afterAddSeller";
	}
	
	// 최근에 가입된 한명의 판매자 화면
	// http://localhost:8080/lesson04/quiz01/seller-info-view
	// http://localhost:8080/lesson04/quiz01/seller-info-view?id=3
	@GetMapping("seller-info-view")
	public String sellerInfoView(
			@RequestParam(value = "id", required = false) Integer id,
			Model model) {
		
		// DB select
		Seller seller = sellerBO.getSeller(id);
		
		// model에 데이터 담기 => HTML이 꺼내서 씀
		model.addAttribute("result", seller);
		model.addAttribute("title", "판매자 정보");
		
		// 결과 html
		return "lesson04/latestSeller";
	}
}
