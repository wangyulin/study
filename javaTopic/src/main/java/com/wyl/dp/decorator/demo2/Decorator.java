package com.wyl.dp.decorator.demo2;

public abstract class Decorator implements Person {

    protected Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void eat() {
        person.eat();
    }

}
