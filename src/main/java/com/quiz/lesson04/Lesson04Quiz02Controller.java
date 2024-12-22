package com.quiz.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.lesson04.bo.RealtorBO;
import com.quiz.lesson04.domain.Realtor;

@RequestMapping("/lesson04/quiz02")
@Controller
public class Lesson04Quiz02Controller {
	
	@Autowired
	private RealtorBO realtorBO;
	
	// http://localhost:8080/lesson04/quiz02/add-realtor-view
	@GetMapping("/add-realtor-view")
	public String addRealtorView() {
		return "lesson04/addRealtor";
	}
	
	// http://localhost:8080/lesson04/quiz02/add-realtor
	@PostMapping("/add-realtor")
	public String addRealtor(
			@ModelAttribute Realtor realtor, 
			Model model) {
		
		// DB insert
		realtorBO.addRealtor(realtor); // 여기까지 해도 날짜가 null임.
		
		// DB select 
		realtor = realtorBO.getRealtorById(realtor.getId()); // heap에 realtor가 새로 생기게 된다. => 날짜도 다 채워진 realtor
		
		// model에 담기
		model.addAttribute("realtor", realtor);
		
		// 화면으로
		return "lesson04/afterAddRealtor";
		
	}
}
