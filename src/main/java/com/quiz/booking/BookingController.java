package com.quiz.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.booking.bo.BookingBO;
import com.quiz.booking.domain.Booking;

@RequestMapping("/booking")
@Controller
public class BookingController {
	
	@Autowired
	private BookingBO bookingBO;
	
	// checkBooking 화면
	@GetMapping("/check-booking-view")
	public String checkBookingView() {
		return "booking/checkBooking";
	}
	
	// bookingList 화면
	@GetMapping("/booking-list-view")
	public String bookingListView(Model model) {
		
		// db select
		List<Booking> bookingList = bookingBO.getBookingList();
		
		// Model 담기
		model.addAttribute("bookingList", bookingList);
		
		return "booking/bookingList";
	}
	
	// makeBooking 화면
	@GetMapping("/make-booking-view")
	public String makeBookingView() {
		return "booking/makeBooking";
	}
	
	// AJAX 요청
	// booking 삭제
	@ResponseBody
	@DeleteMapping("/remove-booking")
	public Map<String, Object> removeBooking(
			@RequestParam("id") int id) {
		
		// db delete
		int rowCount = bookingBO.removeBookingById(id);
		
		// 응답
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
}
