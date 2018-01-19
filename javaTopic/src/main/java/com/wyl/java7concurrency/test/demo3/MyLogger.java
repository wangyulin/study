package com.wyl.java7concurrency.test.demo3;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class MyLogger {

    private static Handler handler;

    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setLevel(Level.ALL);

        try {
            if (handler == null) {
                handler = new FileHandler("demo3-logger.log");
                Formatter formatter = new MyFormatter();
                handler.setFormatter(formatter);
            }

            if(logger.getHandlers().length == 0) {
                logger.addHandler(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }

}
