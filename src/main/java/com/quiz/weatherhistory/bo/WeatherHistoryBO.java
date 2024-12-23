package com.quiz.weatherhistory.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.weatherhistory.domain.WeatherHistory;
import com.quiz.weatherhistory.mapper.WeatherHistoryMapper;

@Service
public class WeatherHistoryBO {
	
	@Autowired
	private WeatherHistoryMapper weatherHistoryMapper;
	
	public List<WeatherHistory> getWeatherHistoryList() {
		return weatherHistoryMapper.selectWeatherHistoryList();
	}
	
	public void addWeatherHistory(WeatherHistory weatherHistory) {
		weatherHistoryMapper.insertWeatherHistory(weatherHistory);
	}
}
