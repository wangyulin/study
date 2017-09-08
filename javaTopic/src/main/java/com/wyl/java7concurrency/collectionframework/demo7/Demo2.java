package com.wyl.java7concurrency.collectionframework.demo7;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Demo2 {

    private static final long COUNT = 10000000;
    private static final int THREADNUM = 512;

    public static void main(String[] args) {
        AtomicInteger atomicInteger;
        //System.out.println( "Shared Random" );
        //testRandom(THREADNUM, COUNT);

        //System.out.println("ThreadLocal<Random>");
        //testTL_Random(THREADNUM, COUNT);

        System.out.println("ThreadLocalRandom");
        testTLRandom(THREADNUM, COUNT);

        //System.out.println("Shared Random[] with no padding");
        //testRandomArray(THREADNUM, COUNT, 1);

        //System.out.println("Shared Random[] with padding");
        //testRandomArray(THREADNUM, COUNT, 2);
    }

    //runner for all tests
    private static class RandomTask implements Runnable {
        private final Random rnd;
        protected final int id;
        private final long cnt;

        private RandomTask(Random rnd, int id, long cnt) {
            this.rnd = rnd;
            this.id = id;
            this.cnt = cnt;
        }

        protected Random getRandom() {
            return rnd;
        }

        @Override
        public void run() {
            final Random random = getRandom();
            final long start = System.currentTimeMillis();
            int sum = 0;
            for ( long j = 0; j < cnt; ++j ) {
                sum += random.nextInt();
            }
            final long time = System.currentTimeMillis() - start;
            System.out.println( "Thread #" + id + " Time = " + time / 1000.0 + " sec, sum = " + sum );
        }
    }

    private static void testRandom( final int threadNum, final long cnt ) {
        final Random random = new Random( 100 );
        Thread threads[] = new Thread[threadNum];
        for ( int i = 0; i < threadNum; ++i ) {
            threads[i] = new Thread( new RandomTask( random, i, cnt ) );
            threads[i].start();
        }

        for ( int i = 0; i < threadNum; ++i ) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testRandomArray( final int threadNum, final long cnt, final int padding ) {
        final Random[] rnd = new Random[threadNum * padding];
        Thread threads[] = new Thread[threadNum];

        for ( int i = 0; i < threadNum * padding; ++i ) //allocate together
            rnd[ i ] = new Random( 100 );

        for ( int i = 0; i < threadNum; ++i ) {
            threads[i] = new Thread( new RandomTask( rnd[ i * padding ], i, cnt ) );
            threads[i].start();
        }

        for ( int i = 0; i < threadNum; ++i ) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testTLRandom( final int threadNum, final long cnt ) {
        Thread threads[] = new Thread[threadNum];
        for ( int i = 0; i < threadNum; ++i ) {
            threads[i] = new Thread( new RandomTask( null, i, cnt ) {
                @Override
                protected Random getRandom() {
                    return ThreadLocalRandom.current();
                }
            } );
            threads[i].start();
        }

        for ( int i = 0; i < threadNum; ++i ) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testTL_Random( final int threadNum, final long cnt ) {
        final ThreadLocal<Random> rnd = new ThreadLocal<Random>() {
            @Override
            protected Random initialValue() {
                return new Random( 100 );
            }
        };

        Thread threads[] = new Thread[threadNum];

        for ( int i = 0; i < threadNum; ++i ) {
            threads[i] = new Thread( new RandomTask( null, i, cnt ) {
                @Override
                protected Random getRandom() {
                    return rnd.get();
                }
            } );
            threads[i].start();
        }

        for ( int i = 0; i < threadNum; ++i ) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
