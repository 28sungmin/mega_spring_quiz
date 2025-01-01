package com.quiz.booking.bo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.booking.domain.Booking;
import com.quiz.booking.mapper.BookingMapper;

@Service
public class BookingBO {
	
	@Autowired
	private BookingMapper bookingMapper;
	
	// 조회
	public List<Booking> getBookingList() {
		return bookingMapper.selectBookingList();
	}
	
	// 삭제
	public int removeBookingById(int id) {
		return bookingMapper.deleteBookingById(id);
	}
	
	// 삽입
	// i:5개
	// o:성공한 행 개수
	public int addBooking(String name, LocalDate date, 
			int day, int headcount, String phoneNumber) {
		return bookingMapper.insertBooking(name, date, day, headcount, phoneNumber);
	}
	
	// i: name, phoneNumber
	// o: Booking(단건 최신) or null
	public Booking getLatestBookingByNamePhoneNumber(String name, String phoneNumber) {
		List<Booking> bookingList = bookingMapper.selectBookingByNamePhoneNumber(name, phoneNumber);
		// [] or [Booking] or [Booking, Booking, ...]
		// 방법1)
//		if (bookingList.isEmpty()) {
//			return null;
//		}
//		
//		return bookingList.get(bookingList.size() - 1);
		
		// 방법2) 삼항연산자
		return bookingList.isEmpty() ? null : bookingList.get(bookingList.size() - 1);
	}
}
