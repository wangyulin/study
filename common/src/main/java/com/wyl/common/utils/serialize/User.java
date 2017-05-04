package com.wyl.common.utils.serialize;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wangyulin on 25/04/2017.
 */
@Data
public class User implements Serializable{
    private String id;
    private String name;
    private int age;
}
