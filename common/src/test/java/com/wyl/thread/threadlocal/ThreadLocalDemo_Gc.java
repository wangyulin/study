package com.wyl.thread.threadlocal;

import org.omg.SendingContext.RunTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 03/01/2017.
 */
public class ThreadLocalDemo_Gc {

    //private static final SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );

    static volatile ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat> () {
        @Override
        protected void finalize() throws Throwable {
            System.out.println (this.toString () + " is gc 111");
        }
    };
    static volatile CountDownLatch cd = new CountDownLatch ( 10000 );

    public static class ParseDate implements Runnable {
        int i = 0;
        public ParseDate (int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if(t1.get () == null) {
                    t1.set ( new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" ) {
                        protected void finalize() throws Throwable {
                            System.out.println (this.toString () + "is gc 222");
                        }
                    });
                    System.out.println (Thread.currentThread ().getId () + " : create SimpleDateFormat");
                }
                Date t = t1.get ().parse ( "2017-02-03 14:08:" + i % 60);
                //Date t = sdf.parse ( "2017-02-03 14:08:" + i % 60);
            } catch (ParseException e) {
                e.printStackTrace ();
            } finally {
                cd.countDown ();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool (10);
        for (int i = 0; i < 10000; i++) {
            es.execute ( new ParseDate ( i ) );
        }
        cd.await ();
        System.out.println ("mission complete !!!");
        t1 = null;
        System.gc ();
        System.out.println ("First GC complete");
        //在设置Threadlocal的时候，会清除ThreadlocalMap中的无效对象
        t1 = new ThreadLocal<SimpleDateFormat> ();
        cd = new CountDownLatch ( 10000 );
        for (int i = 0; i < 10000; i++) {
            es.execute ( new ParseDate ( i ) );
        }
        cd.await ();
        Thread.sleep ( 1000 );
        System.gc ();
        System.out.println ("Second GC complete!!!");
        es.shutdown ();
    }

}
