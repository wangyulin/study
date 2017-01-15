package com.wyl.spring.demo.soundsystem.demo1;

import com.wyl.spring.demo.soundsystem.demo1.conf.CDPlayConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by wangyulin on 13/01/2017.
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayConfig.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CDPlayTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Autowired
    private CompactDisc cd;

    /*@Autowired
    public void initData(CompactDisc cd) {
        this.cd = cd;
    }*/

    @Test
    public void cdshouldBeNull() {
        System.out.println("Hello Junit !");
        assert(cd != null);
        assertNotNull(cd);
        cd.play ();
    }

}
