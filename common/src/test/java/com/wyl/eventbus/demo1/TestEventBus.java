package com.wyl.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * Created by wangyulin on 24/03/2017.
 */
public class TestEventBus {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:" + listener.getLastMessage());
    }

}
