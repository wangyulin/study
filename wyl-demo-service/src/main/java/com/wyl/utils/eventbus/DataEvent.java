package com.wyl.utils.eventbus;

/**
 * Created by wangyulin on 28/02/2017.
 * 消息封装类
 */
public class DataEvent {

    private final int message;

    public DataEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getMessage() {
        return message;
    }

}
