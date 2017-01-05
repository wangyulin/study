package com.wyl.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangyulin on 05/01/2017.
 */
public class DateDemo {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
        Date date = sdf.parse( "2017-01-05" );
        Date d1 = new Date(date.getTime () - 1000 * 60 * 60 * 24);

        Date d2 = new Date(d1.getTime () - 1000 * 60 * 60 * 24 * 7);

        System.out.println (date);
        System.out.println (d1);
        System.out.println (d2);
    }

}
