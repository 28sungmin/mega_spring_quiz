package com.quiz.lesson06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.BookmarkBO;
import com.quiz.lesson06.domain.Bookmark;

@RequestMapping("/lesson06/quiz01")
@Controller
public class Lesson06Quiz01Controller {
	
	@Autowired
	private BookmarkBO bookmarkBO;
	
	// 즐겨찾기 추가 화면
	@GetMapping("/add-bookmark-view")
	public String addBookmarkView() {
		return "lesson06/addBookmark";
	}
	
	// AJAX 요청
	// 즐겨찾기 추가
	@ResponseBody
	@PostMapping("/add-bookmark")
	public Map<String, Object> addBookmark(
			@RequestParam("name") String name,
			@RequestParam("url") String url) {
		
		// db insert
		bookmarkBO.addBookmarkAsField(name, url);
		
		// 성공 응답
		// {"code":200, "result":"성공"}
		// {"code":500, "error_message":"에러 원인"}
		// => 이렇게 하기로 우리끼리 약속한 거임
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result; // json string 응답
	}
	
	// AJAX가 하는 요청
	// 중복 확인
	@ResponseBody
	@PostMapping("/is-duplicate-url")
	public Map<String, Object> isDuplicateUrl(
			@RequestParam("url") String url){
		
		// db select => db url로 조회
		boolean isDuplicate = bookmarkBO.isDuplicateUrl(url);
		
		// 응답
		// {"code":200, "is_duplicate_url":true} => 중복
 		// {"code":200, "is_duplicate_url":false} => 중복 아님
 		// {"code":500, "error_message":"에러 원인"} => 로직 에러 상황
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("is_duplicate_url", isDuplicate);
		return result;
	}
	
	// AJAX 요청
	// id로 하나의 bookmark 삭제
	@ResponseBody
	@DeleteMapping("/remove-bookmark") // Delete는 Post 계열
	public Map<String, Object> removeBookmark(
			@RequestParam("id") int id){
		
		// db delete
		int rowCount = bookmarkBO.removeBookmarkAsField(id);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (rowCount == 1) {
			// 삭제 성공
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			// 삭제 실패
			result.put("code", 300);
			result.put("error_message", "삭제할 데이터가 없습니다.");
		}
		
		return result;
	}
	
	// 즐겨찾기 목록 화면
	@GetMapping("/after-add-bookmark-view")
	public String afterAddBookmark(Model model) {
		
		// db select
		List<Bookmark> bookmarkList = bookmarkBO.getBookmarkList();
		
		// model 담기
		model.addAttribute("bookmarkList", bookmarkList);
		
		// 화면 이동
		return "lesson06/afterAddBookmark";
	}
}
