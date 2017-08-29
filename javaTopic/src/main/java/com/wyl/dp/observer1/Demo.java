package com.wyl.dp.observer1;

import com.wyl.dp.observer1.CurrentConditionsDisplay;
import com.wyl.dp.observer1.WeatherData;

public class Demo {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 30.0f);
	}

}
