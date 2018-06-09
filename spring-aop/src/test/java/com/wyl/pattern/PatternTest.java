package com.wyl.pattern;

import com.wyl.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangyulin on 10/02/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class PatternTest {

    @Test
    public void testPatternClient() {
        Subject subject = new Proxy(new RealSubject());
        subject.hello();
    }

}
