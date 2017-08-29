package com.wyl.dp.decorator;

public abstract class Beverage {
	
	String description = "Unkown Beverage";
	
	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
	
}
