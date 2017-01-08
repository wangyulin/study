package com.wyl.future.demo3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by wangyulin on 08/01/2017.
 */
public class AskThread implements Runnable {

    CompletableFuture<Integer> re = null;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*final CompletableFuture<Integer> future = new CompletableFuture<> ();
        new Thread(new AskThread ( future )).start ();
        Thread.sleep ( 1000 );
        future.complete ( 60 );*/

        CompletableFuture<Void> fu = CompletableFuture.supplyAsync ( () -> calc ( 50 ) )
                .exceptionally ( ex -> {
                    System.out.println (ex.toString ());
                    return 0;
                } )
                .thenCompose ( (i) -> CompletableFuture.supplyAsync ( () -> calc ( i ) ) )
                .thenApply ( (i) -> Integer.toString ( i ) )
                .thenApply ( (str) -> "\"" + str + "\"" )
                .thenAccept ( System.out::println );
        fu.get ();
    }


    public AskThread(CompletableFuture<Integer> re) {
        this.re = re;
    }

    public static Integer calc(Integer param) {
        try {
            Thread.sleep ( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        //return param * param;
        return param * param;
    }

    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get () * re.get ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (ExecutionException e) {
            e.printStackTrace ();
        }
        System.out.println (myRe);
    }


}
