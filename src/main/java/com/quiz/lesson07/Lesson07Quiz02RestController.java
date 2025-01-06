package com.quiz.lesson07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.lesson07.entity.RecruitEntity;
import com.quiz.lesson07.repository.RecruitRepository;

@RequestMapping("/lesson07/quiz02")
@RestController
public class Lesson07Quiz02RestController {

	@Autowired
	private RecruitRepository recruitRepository;
	
	@GetMapping("/1")
	public RecruitEntity select1() {
		return recruitRepository.findById(8).orElse(null);
	}
	
	@GetMapping("/2")
	public List<RecruitEntity> select2(
			@RequestParam("company_id") int companyId) { // company_id는 주소에 치는 것. 물론 카멜케이스로 해도 된다.
		return recruitRepository.findByCompanyId(companyId);
	}
	
	@GetMapping("/3")
	public List<RecruitEntity> select3() {
		return recruitRepository.findByPositionAndType("웹 back-end 개발자", "정규직");
	}
	
	@GetMapping("/4")
	public List<RecruitEntity> select4() {
		return recruitRepository.findByTypeOrSalaryGreaterThanEqual("정규직", 9000);
	}
	
	@GetMapping("/5")
	public List<RecruitEntity> select5() {
		return recruitRepository.findTop3ByTypeOrderBySalaryDesc("계약직");
	}
	
	@GetMapping("/6")
	public List<RecruitEntity> select6() {
		return recruitRepository.findByRegionAndSalaryBetween("성남시 분당구", 7000, 8500);
	}
	
	@GetMapping("/7")
	public List<RecruitEntity> select7() {
		// 마감일이 2026-04-10 이후이고 연봉이 8100 이상인 정규직 공고를 연봉 내림차순으로 조회하세요.
		return recruitRepository.findByDeadlineAndType("2026-04-10", 8100, "정규직");
	}
}
