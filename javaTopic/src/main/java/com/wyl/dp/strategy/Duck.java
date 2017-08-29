package com.wyl.dp.strategy;

public abstract class Duck {
	
	public QuackBehavior quackBehavior;
	public FlyBehavior flyBehavior;
	
	public void performQuack() {
		this.quackBehavior.quack();
	}
	
	public void performFly() {
		this.flyBehavior.fly();
	}
	
	public void setFlyBehavior(FlyBehavior fb) {
		flyBehavior = fb;
	}
	
	public void setQuackBehavior(QuackBehavior qb){
		quackBehavior = qb;
	}
	
	public abstract void display(); 
	
}
