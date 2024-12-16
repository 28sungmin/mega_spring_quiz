package com.quiz.lesson03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.quiz.lesson03.domain.RealEstate;

@Mapper
public interface RealEstateMapper {
	// input: id(int)
	// output: RealEstate or null => 단건 데이터를 할 경우, id가 해당 데이터에 없으면 null이 리턴된다.
	public RealEstate selectRealEstateById(int id);
	
	// input: rentPrice(Integer)
	// output: List<RealEstate>
	public List<RealEstate> selectRealEstateListByRentPrice(int rentPrice);
	
	// input: area(int), price(int)
	// output: List<RealEstate>
	public List<RealEstate> selectRealEstateListByAreaAndPrice(
			// 하나의 맵이 된다. @Param이 맵으로 만들어줌
			// @Param의 괄호 안에 있는 이름이 중요한 거임!! -> 이 이름으로 xml이 꺼낸다.
			@Param("area") int area11111, 
			@Param("price") int price11111);
	
	// i: RealEstate
	// o: int
	public int insertRealEstate(RealEstate realEstate);
	
	public int insertRealEstateAsField(
			@Param("realtorId") int realtorId,
			@Param("address") String address,
			@Param("area") int area,
			@Param("type") String type,
			@Param("price") int price,
			@Param("rentPrice") Integer rentPrice);
	
	public int updateRealEstateById(
			@Param("id") int id, 
			@Param("type") String type, 
			@Param("price") int price);
}
