package com.wyl.other.wyl.hadoop.configuration;

import org.apache.hadoop.conf.Configuration;

/**
 * Created by wangyulin on 19/02/2017.
 */
public class ConfigurationDemo {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        conf.addResource("configuration-2.xml");
        System.setProperty("size", "100");
        System.out.println(conf.get("size-weight"));
        //System.out.println(conf.get("weight"));
        //System.setProperty("length", "2");
        //System.out.println(conf.get("length"));

    }

}
