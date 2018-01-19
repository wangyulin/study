package com.wyl.java7concurrency.test.demo3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Main {

    public static void main(String[] args) {
        Logger logger = MyLogger.getLogger("Core");
        logger.entering("Core", "main()", args);

        Thread threads[] = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            logger.log(Level.INFO, "Launching thread: " + i);
            Task task = new Task();
            threads[i] = new Thread(task);
            logger.log(Level.INFO, "Thread created: " + threads[i].getName());
            threads[i].start();
        }

        logger.log(Level.INFO, "Ten Threads created." + "Waiting for its finalization");

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
                logger.log(Level.INFO, "Thread has finished its exection", threads[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.exiting("Core", "main()");
    }

}
