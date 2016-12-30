package com.wyl.thread.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangyulin on 30/12/2016.
 */
public class MapMultiThread {
    //static Map<String, String> map = new HashMap<String, String> ();
    static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String> ();

    public static class AddThread implements Runnable {
        int start = 0;

        public AddThread (int start) {
            this.start = start;
        }

        public void run () {
            for(int i = start; i < 100000; i++) {
                map.put ( Integer.toString ( i ), Integer.toString ( i ) );
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread ( 0 ));
        Thread t2 = new Thread(new AddThread ( 1 ));
        t1.start ();
        t2.start ();

        t1.join ();
        t2.join ();

        System.out.println (map.size ());
    }

}
