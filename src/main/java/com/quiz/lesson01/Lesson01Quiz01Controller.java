package com.quiz.lesson01;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/lesson01/quiz01") // 공통되는 path는 앞에 빼줄 수 있다.
@Controller
public class Lesson01Quiz01Controller {
	// http://localhost:8080/lesson01/quiz01/1
	@RequestMapping("/1")
	@ResponseBody
	public String quiz01_1() {
		return "<h1>테스트 프로젝트 완성</h1><h4>해당 프로젝트를 통해서 문제 풀이를 진행됩니다.</h4>";
	}
	
	// 이렇게 아래에 @ResponseBody처럼 할 수도 있지만, 요즘은 위의 방식으로 하니까, 그냥 그런가보다 정도만 알기
	// http://localhost:8080/lesson01/quiz01/2
	@RequestMapping("/2")
	// quiz01_2() => 요청은 /lesson01/quiz01/2 이 주소로 준다. 응답은 Map 형태로 나간다.
	public @ResponseBody Map<String, Integer> quiz01_2() {
		Map<String, Integer> map = new HashMap<>();
		map.put("국어", 80);
		map.put("수학", 90);
		map.put("영어", 85);
		
		return map;
	}
}
