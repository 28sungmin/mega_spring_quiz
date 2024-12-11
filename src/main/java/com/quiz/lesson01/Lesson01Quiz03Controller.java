package com.quiz.lesson01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Lesson01Quiz03Controller {
	// 요청 URL : http://localhost:8080/lesson01/quiz03/1
	@RequestMapping("/lesson01/quiz03/1")
	public String quiz03() {
		// html 파일 경로: templates/lesson01/quiz03.html
		// @ResponseBody가 없는 상태로 return String 하면
		// ViewResolver에 의해 리턴된 HTML 경로를 찾아 화면이 구성된다.
		return "lesson01/quiz03"; // html view 경로
	}
}
