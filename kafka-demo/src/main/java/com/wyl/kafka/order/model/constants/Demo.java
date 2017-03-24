package com.wyl.kafka.order.model.constants;

/**
 * Created by wangyulin on 15/03/2017.
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println(OrderStatus.SUCCESS_PAID);
        System.out.println(OrderStatus.SUCCESS_PAID.name());
        System.out.println(OrderStatus.SUCCESS_PAID.ordinal());
        System.out.println(OrderStatus.valueOf("SUCCESS_PAID"));
    }

}
