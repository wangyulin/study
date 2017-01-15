package com.wyl.spring.demo.soundsystem.demo1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangyulin on 13/01/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CDPlayerXMLTest {
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Autowired
    ApplicationContext ctx;

    @Test
    public void testXML() {
        MediaPlayer player = (MediaPlayer) ctx.getBean("cdPlayer");
        player.play();
        assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());
    }

    @Test
    public void testXML_C() {
        MediaPlayer player = (MediaPlayer) ctx.getBean("cdPlayer01");
        player.play();
        assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());
    }

    @Test
    public void testXML_3() {
        CompactDisc cd = (CompactDisc) ctx.getBean("blankDisc");
        cd.play();
        assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());
    }

    @Test
    public void testXML_4() {
        CompactDisc cd = (CompactDisc) ctx.getBean("blankDisc01");
        cd.play();
        /*assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());*/
    }

    @Test
    public void testXML_5() {
        CompactDisc cd = (CompactDisc) ctx.getBean("blankDisc2");
        cd.play();
        /*assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());*/
    }

    @Test
    public void testXML_6() {
        MediaPlayer player = (MediaPlayer) ctx.getBean("cdPlayer2");
        player.play();
        assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());
    }

    @Test
    public void testXML_7() {
        MediaPlayer player = (MediaPlayer) ctx.getBean("cdPlayer2_01");
        player.play();
        assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());
    }

    @Test
    public void testXML_8() {
        CompactDisc cd = (CompactDisc) ctx.getBean("blankDisc3");
        cd.play();
        /*assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n",
                log.getLog());*/
    }

}