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
	
	public boolean isDuplicateByUrl(String url) {
		return bookmarkMapper.isDuplicateByUrl(url);
	}
	
	public void removeBookmarkAsField(int id) {
		bookmarkMapper.removeBookmarkAsField(id);
	}
}
