package com.wyl.dp.observer1;

import java.util.Observable;
import java.util.Observer;

import com.wyl.dp.observer.DisplayElement;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

	Observable obervable;
	private float temperature;
	private float humidity;
	
	public CurrentConditionsDisplay(Observable obervable) {
		this.obervable = obervable;
		obervable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("1. Current conditions : " + temperature
				+ "F degrees and " + humidity + "% humidity");
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) o;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}

}
