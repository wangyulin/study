package com.wyl.java7concurrency.collectionframework.demo6;

import lombok.Data;

/**
 * Created by wangyulin on 01/09/2017.
 */
@Data
public class Contact {

    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

}
