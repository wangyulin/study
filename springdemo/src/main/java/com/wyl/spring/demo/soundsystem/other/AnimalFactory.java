package com.wyl.spring.demo.soundsystem.other;

public class AnimalFactory {

    public /*static*/ Animal getAnimal(String type) {
        if ("cat".equalsIgnoreCase(type)) {
            return new Cat();
        } else {
            return new Dog();
        }
    }
}
