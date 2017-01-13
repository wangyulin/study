package com.wyl.spring.demo.soundsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangyulin on 13/01/2017.
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayConfig.class)
public class CDPlayTest {

    @Autowired
	private CompactDisc cd;

    @Test
    public void cdshouldBeNull() {
    	System.out.println("asd");
        assert(cd != null);
    }
	
}
