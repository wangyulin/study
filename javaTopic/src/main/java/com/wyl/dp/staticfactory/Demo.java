package com.wyl.dp.staticfactory;

public class Demo {

	public static void main(String[] args) {
		PizzaStore nystore = new NYPizzaStore();
		Pizza pizza = nystore.orderPizza("cheese");
		System.out.println(pizza.name);
	}

}
