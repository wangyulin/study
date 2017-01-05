package com.wyl.parallel;

/**
 * Created by wangyulin on 05/01/2017.
 */
public class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 100L * 10L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong ();
        }
    }

    public FalseSharing (final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis ();
        runTest ();
        System.out.println ("duration = " + (System.currentTimeMillis () - start));
    }

    public static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for(int i = 0; i < threads.length; i++ ) {
            threads[i] = new Thread ( new FalseSharing ( i ) );
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start ();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join ();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while(0 != --i) {
            longs[arrayIndex].value = i;
            //System.out.println (Thread.currentThread ().getName () + " " + i);
        }
    }

    public final static class VolatileLong {
        public volatile long value = 0L;
        public long p1,p2,p3,p4,p5,p6,p7;
    }
}
