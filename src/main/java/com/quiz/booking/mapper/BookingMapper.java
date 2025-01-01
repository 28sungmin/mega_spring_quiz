package com.quiz.booking.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.quiz.booking.domain.Booking;

@Mapper
public interface BookingMapper {

	public List<Booking> selectBookingList();
	
	public int deleteBookingById(int id);
	
	public int insertBooking(
			@Param("name") String name, 
			@Param("date") LocalDate date, 
			@Param("day") int day, 
			@Param("headcount") int headcount, 
			@Param("phoneNumber") String phoneNumber);
	
	// i: name, phoneNumber
	// o: Booking or 한 사람이 또 예약을 할 수 있으므로 List<Booking> 
	// => [] or [Booking] or [Booking, Booking, ...]
	public List<Booking> selectBookingByNamePhoneNumber(
			@Param("name") String name, 
			@Param("phoneNumber") String phoneNumber);
}
