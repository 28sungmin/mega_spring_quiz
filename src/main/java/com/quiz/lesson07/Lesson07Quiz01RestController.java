package com.quiz.lesson07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.lesson07.bo.CompanyBO;
import com.quiz.lesson07.entity.CompanyEntity;

@RequestMapping("/lesson07/quiz01")
@RestController
public class Lesson07Quiz01RestController {
	
	@Autowired
	private CompanyBO companyBO;

	// create1
	@GetMapping("/save1")
	public CompanyEntity save1() {
		
		return companyBO.addCompany("넥슨", "컨텐츠 게임", "대기업", 3585);
	}
	
	// create2
	@GetMapping("/save2")
	public CompanyEntity save2() {
		
		return companyBO.addCompany("버블팡", "여신 금융업", "대기업", 6834);
	}
	
	// update
	@GetMapping("/update")
	public CompanyEntity update() {
		// id: 8, 중소기업, 34
		return companyBO.updateCompanytById(8, "중소기업", 34);
	}
	
	// delete
	@GetMapping("/delete")
	public String delete() {
		companyBO.deleteCompanyById(8);
		
		return "삭제 완료";
	}
}
