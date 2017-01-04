package com.wyl.thread.atomic;

import com.wyl.thread.problem.MapMultiThread;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by wangyulin on 03/01/2017.
 */
public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger (  );

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i.incrementAndGet ();
                e = e + 1;
            }
        }
    }

    static Integer e = new Integer ( 10 );
    public static void main(String[] args) throws InterruptedException {
        //Unsafe unsafe = Unsafe.getUnsafe();
        //unsafe.compareAndSwapInt(new AtomicInteger (  ), 1, 1, 1);
        //unsafe.getAndAddInt(this, valueOffset, 1) + 1;
        AtomicReference<Long> temp1;
        AtomicStampedReference<Long> temp2;
        Thread[] ts = new Thread[10];
        for (int j = 0; j < 10; j++) {
            ts[j] = new Thread ( new AddThread ( ) );
        }

        for (int k = 0; k < 10; k++) {
            ts[k] .start ();
        }

        for (int j = 0; j < 10; j++) {
            ts[j].join ();
        }
        System.out.println (i);
        System.out.println (e);
    }

}
