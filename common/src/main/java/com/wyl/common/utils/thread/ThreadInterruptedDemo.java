package com.wyl.common.utils.thread;

/**
 * Created by wangyulin on 21/06/2017.
 */
public class ThreadInterruptedDemo {

    public static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new T1());
        t.start();
        Thread.sleep(3000);
        t.interrupt();
        //stop = true;
    }

}

class T1 implements Runnable {

    @Override
    public void run() {
        try{
            while(!ThreadInterruptedDemo.stop) {
                long now = System.currentTimeMillis();
                while(System.currentTimeMillis()-now<1000){
                    // 为了避免Thread.sleep()而需要捕获InterruptedException而带来的理解上的困惑,
                    // 此处用这种方法空转1秒
                }
                System.out.println("线程T1 执行中...");
                if(Thread.interrupted()) {
                    System.out.println("线程T1 被执行中断操作了");
                    throw new InterruptedException();
                }
                /*if(ThreadInterruptedDemo.stop) {

                }*/
            }
        } catch (InterruptedException e){
            System.out.println("catch interrupted exception");
            e.printStackTrace();
        }
    }
}
