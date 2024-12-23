package com.quiz.weatherhistory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.quiz.weatherhistory.domain.WeatherHistory;

@Mapper
public interface WeatherHistoryMapper {
	
	// i: X
	// o: List<WeatherHistory>
	public List<WeatherHistory> selectWeatherHistoryList();
	
	// 성공한 행의 개수 리턴하든 안하든 여기서는 상관 없음
	public void insertWeatherHistory(WeatherHistory weatherHistory);
}
