package com.wyl.thread.group;

/**
 * Created by wangyulin on 30/12/2016.
 */
public class ThreadGroupName implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup ( "PrintGroup" );
        Thread t1 = new Thread(tg, new ThreadGroupName (), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName (), "T2");

        t1.start ();
        t2.start ();

        System.out.println (tg.activeCount () );
        tg.list ();
        t1.join ();
        t2.join ();
    }

    public void run() {
        String groupAndName = Thread.currentThread ().getThreadGroup ().getName ()
                + "-" + Thread.currentThread ().getName ();
        while (true) {
            System.out.println ("I am " + groupAndName);
            try {
                Thread.sleep ( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }

}
