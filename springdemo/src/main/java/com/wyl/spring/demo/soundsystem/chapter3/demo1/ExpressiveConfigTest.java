package com.wyl.spring.demo.soundsystem.chapter3.demo1;

import com.wyl.spring.demo.soundsystem.demo1.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangyulin on 14/01/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=ExpressConfig.class)
@ContextConfiguration( locations = {"classpath:applicationContext3.xml"})
public class ExpressiveConfigTest {

    @Autowired
    CompactDisc compactDisc;

    @Test
    public void test1() {
        compactDisc.play ();
    }

    @Test
    public void test2() {
        compactDisc.play ();
    }

}
