package com.wyl.dp.strategy;

public class Demo {

	public static void main(String[] args) {
		testModelDuck();
	}
	
	public static void testModelDuck() {
		Duck model = new ModelDuck();
		model.performFly();
		model.performQuack();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
	}
	
	public static void testMallardDuck() {
		Duck mallard = new MallardDuck();
		mallard.performQuack();
		mallard.performFly();
	}

}
