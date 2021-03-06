package com.wyl.utils.eventbus;

import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangyulin on 28/02/2017.
 * 消息接受类
 */
@Service
public class EventListener {

    @Autowired
    public EventListener(){

    }

    public int lastMessage = 0;

    @Subscribe
    public void listen(DataEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message : " + lastMessage);
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
