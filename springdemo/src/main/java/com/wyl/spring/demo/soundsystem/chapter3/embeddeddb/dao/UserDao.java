package com.wyl.spring.demo.soundsystem.chapter3.embeddeddb.dao;


import com.wyl.spring.demo.soundsystem.chapter3.embeddeddb.model.User;

import java.util.List;

/**
 * Created by wangyulin on 14/01/2017.
 */
public interface UserDao {

    User findByName(String name);

    List<User> findAll();

}