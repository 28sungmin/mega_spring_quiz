package com.quiz.weatherhistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.weatherhistory.bo.WeatherHistoryBO;
import com.quiz.weatherhistory.domain.WeatherHistory;

@RequestMapping("/weather-history")
@Controller
public class WeatherHistoryController {
	
	@Autowired
	private WeatherHistoryBO weatherHistoryBO;
	
	// 날씨 목록 화면
	@GetMapping("/weather-list-view")
	// String이면 view의 경로가 된다.
	public String weatherListView(Model model) {
		
		// db select
		List<WeatherHistory> weatherHistoryList = weatherHistoryBO.getWeatherHistoryList();
		
		// model 담기
		model.addAttribute("weatherHistoryList", weatherHistoryList);
		
		return "weatherHistory/weatherList";
	}
	
	// 날씨 입력 화면
	@GetMapping("/add-weather-view")
	public String addWeatherView() {
		
		return "weatherHistory/addWeather";
	}
	
	// 날씨 추가 기능
	@PostMapping("/add-weather")
	public String addWeather(
			@ModelAttribute WeatherHistory weatherHistory) {
		
		// db insert
		weatherHistoryBO.addWeatherHistory(weatherHistory);		
		
		// TODO redirect
//		return "weatherHistory/weatherList"; // 200 ok
		return "redirect:/weather-history/weather-list-view";
	}
}
