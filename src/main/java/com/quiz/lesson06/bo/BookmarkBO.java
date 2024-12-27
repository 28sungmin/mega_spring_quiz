package com.quiz.lesson06.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson06.domain.Bookmark;
import com.quiz.lesson06.mapper.BookmarkMapper;

@Service
public class BookmarkBO {

	@Autowired
	private BookmarkMapper bookmarkMapper;
	
	// i: name, url
	// o: X
	public void addBookmarkAsField(String name, String url) {
		bookmarkMapper.insertBookmarkAsField(name, url);
	}
	
	public List<Bookmark> getBookmarkList() {
		return bookmarkMapper.selectBookmarkList();
	}
	
	// i: url
	// o: boolean => 중복일 때 true, 중복이 아닐 때(사용 가능일 때) false로 준다.
	public boolean isDuplicateUrl(String url) {
		List<Bookmark> bookmarkList = bookmarkMapper.selectBookmarkByUrl(url);
		// [] => false 사용 가능
		// [O] [O, O] => true 중복
		// 표현 방법1)
//		if (bookmarkList.isEmpty()) {
//			return false;
//		}
//		return true;
		
		// 표현 방법2) 삼항연산자로 표현 가능
//		return bookmarkList.isEmpty() ? false : true;
		
		// 표현 방법3) 한 번에 표현 가능
		return !bookmarkList.isEmpty();
	}
	
	public int removeBookmarkAsField(int id) {
		return bookmarkMapper.removeBookmarkAsField(id);
	}
}
