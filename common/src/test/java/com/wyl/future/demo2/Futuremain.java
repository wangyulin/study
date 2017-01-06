package com.wyl.future.demo2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class Futuremain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<String> ( new RealData ( "a" ) );
        ExecutorService executor = Executors.newFixedThreadPool ( 1 );
        executor.submit ( future );
        System.out.println ("请求完毕");
        try {
            Thread.sleep ( 2000 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        System.out.println ("数据 = " + future.get ());
        executor.shutdown ();
    }

}
