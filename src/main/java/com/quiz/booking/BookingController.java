package com.quiz.booking;

import java.time.LocalDate;
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
		
		return result; // json String
	}
	
	// AJAX 요청
	// 예약 추가
	@ResponseBody
	@PostMapping("/make-booking")
	public Map<String, Object> makeBooking(
			@RequestParam("name") String name,
			@RequestParam("date") LocalDate date,
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// db insert
		int rowCount = bookingBO.addBooking(name, date, day, headcount, phoneNumber);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (rowCount > 0) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "예약하는데 실패했습니다. 관리자에게 문의해주세요.");
		}
		return result; // json string
	}
	
	// AJAX 요청
	// 이름, 전화번호 조회
	@ResponseBody // 이것을 사용하면 Model을 사용할 수 없다.
	@PostMapping("/check-booking")
	public Map<String, Object> checkBooking(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// db select
		Booking booking = bookingBO.getLatestBookingByNamePhoneNumber(name, phoneNumber);
		
		// 응답
		// 성공시 json 형태 3가지 => 여기서는 2번째 방식으로 함
		// {"code":200, "result":{"id":3, "name":"강하늘", ...}}
		// {"code":200, "booking":{"id":3, "name":"강하늘", ...}}
		// {"code":200, "result":{"booking":{"id":3, "name":"강하늘", ...}}}
		// 실패시: {"code":400, "error_message":"예약 내역이 없습니다."}
		Map<String, Object> result = new HashMap<>();

		if (booking == null) {
			result.put("code", 400);
			result.put("error_message", "예약 내역이 없습니다.");
		} else {
			result.put("code", 200);
			result.put("booking", booking); // booking 객체를 통째로 담는다.
		}	
		return result;
	}
}
