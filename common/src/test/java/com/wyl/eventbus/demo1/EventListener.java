package com.wyl.eventbus.demo1;

import com.google.common.eventbus.Subscribe;

/**
 * Created by wangyulin on 24/03/2017.
 */
public class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }

}
