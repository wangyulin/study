package com.wyl.service;


import com.wyl.model.Name;

/**
 * Created by wangyulin on 27/02/2017.
 */
public interface ExampleService {

    public String findName(String id);

    public boolean addNameInfo(Name name);

}
