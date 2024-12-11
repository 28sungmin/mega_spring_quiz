package com.quiz.lesson01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lesson01/quiz02")
@RestController
public class Lesson01Quiz02RestController {
	
	// http://localhost:8080/lesson01/quiz02/1
	@RequestMapping("/1")
	public List<Map<String, Object>> quiz02_1() {
	    List<Map<String, Object>> list = new ArrayList<>();
	    Map<String, Object> map = new HashMap<>();
		map.put("rate", 15);
		map.put("director", "봉준호");
		map.put("time", 131);
		map.put("title", "기생충");
	    list.add(map);
	    
	    map = new HashMap<>() {
	    	{
	    		put("rate", 0);
	    		put("director", "로베르토 베니니");
	    		put("time", 116);
	    		put("title", "인생은 아름다워");
	    	}
	    };	
	    list.add(map);
	    map = new HashMap<>() {
	    	{
	    		put("rate", 12);
	    		put("director", "크리스토퍼 놀란");
	    		put("time", 147);
	    		put("title", "인셉션");
	    	}
	    };	
	    list.add(map);
	    map = new HashMap<>() {
	    	{
	    		put("rate", 19);
	    		put("director", "윤종빈");
	    		put("time", 133);
	    		put("title", "범죄와의 전쟁 : 나쁜놈들 전성시대");
	    	}
	    };	
	    list.add(map);
	    map = new HashMap<>() {
	    	{
	    		put("rate", 15);
	    		put("director", "프란시스 로렌스");
	    		put("time", 137);
	    		put("title", "헝거게임");
	    	}
	    };	
	    list.add(map);
	    
	    return list;
	}
	
	// http://localhost:8080/lesson01/quiz02/2
	@RequestMapping("/2")
	public List<Quiz02Data> quiz02_2() {
		// @ResponseBody + return String => (HttpMessageCoverterer) => HTML
		// @ResponseBody + return 객체(Map, List, Class) => (HttpMessageCoverterer) => (Jackson 라이브러리) => JSON
		
		List<Quiz02Data> list = new ArrayList<>();
		
		Quiz02Data data = new Quiz02Data();
		data.setTitle("안녕하세요 가입인사 드립니다.");
		data.setUser("marobiana");
		data.setContent("안녕하세요. 가입했어요. 앞으로 잘 부탁 드립니다. 활동 열심히 하겠습니다.");
		list.add(data);
		
		data = new Quiz02Data();
		data.setTitle("헐 대박");
		data.setUser("bada");
		data.setContent("오늘 목요일이었어... 금요일인줄");
		list.add(data);
		
		data = new Quiz02Data();
		data.setTitle("오늘 데이트 한 이야기 해드릴게요");
		data.setUser("dulumary");
		data.setContent("...");
		list.add(data);
		
		return list;
	}
	
	// 요청 URL: http://localhost:8080/lesson01/quiz02/3
	@RequestMapping("/3")
	public ResponseEntity<Quiz02Data> quiz02_3() {
		Quiz02Data data = new Quiz02Data();
		data.setTitle("안녕하세요 가입인사 드립니다.");
		data.setUser("marobiana");
		data.setContent("안녕하세요. 가입했어요. 앞으로 잘 부탁 드립니다. 활동 열심히 하겠습니다.");
		
		return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
}
