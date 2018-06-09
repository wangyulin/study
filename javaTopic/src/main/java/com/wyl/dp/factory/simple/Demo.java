package com.wyl.dp.factory.simple;

import com.wyl.dp.factory.simple.product.Human;

public class Demo {

    public static void main(String[] args) {

        //Class.forName("org.apache.hive.jdbc.HiveDriver");

        Human human = SampleFactory.makeHumanV2("com.wyl.dp.factory.simple.product.Man");
        human.say();

        human = SampleFactory.makeHumanV2("com.wyl.dp.factory.simple.product.Woman");
        human.say();

        human = SampleFactory.makeHumanV3("man");
        human.say();
    }

}
