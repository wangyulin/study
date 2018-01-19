package com.wyl.java7concurrency.test.demo3;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + record.getLevel() + "] - ");
        sb.append(new Date(record.getMillis()) + " : ");
        sb.append(record.getSourceClassName() + "." + record.getSourceMethodName() + " : ");
        sb.append(record.getMessage() + "\n");
        return sb.toString();
    }
}
