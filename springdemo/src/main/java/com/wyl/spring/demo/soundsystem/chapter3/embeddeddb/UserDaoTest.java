package com.wyl.spring.demo.soundsystem.chapter3.embeddeddb;

import com.wyl.spring.demo.soundsystem.chapter3.embeddeddb.dao.UserDao;
import com.wyl.spring.demo.soundsystem.chapter3.embeddeddb.dao.UserDaoImpl;
import com.wyl.spring.demo.soundsystem.chapter3.embeddeddb.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

/**
 * Created by wangyulin on 14/01/2017.
 */


public class UserDaoTest {

    private EmbeddedDatabase db;

    UserDao userDao;

    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript ( "classpath:script/create-db.sql" )
                .addScript ( "classpath:script/insert-data.sql" )
                .build();
    }

    @Test
    public void testFindByname() {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setNamedParameterJdbcTemplate(template);

        User user = userDao.findByName("wyl");
        System.out.println (user);
        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getId());
        Assert.assertEquals("wyl", user.getName());
        Assert.assertEquals("wyl@gmail.com", user.getEmail());

    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}