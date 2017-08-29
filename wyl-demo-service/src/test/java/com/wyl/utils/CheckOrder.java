package com.wyl.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyulin on 06/03/2017.
 */
public class CheckOrder {

    public static void main(String[] args) throws IOException {
        Map<String, Long> mibiStore = new HashMap<>();

        String fileName = "/Users/wangyulin/Desktop/结算异常设计师信息/消费记录2017-02-01-2017-02-28.csv";
        String themeOrder = "/Users/wangyulin/Desktop/结算异常设计师信息/2017-02月份订单ID.csv";

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
            for (String line : lines) {
                String[] array = line.split(",");
                String orderid = array[7];
                if(orderid.startsWith("=") && orderid.length() > 20) {
                    mibiStore.put(orderid.substring(2,24), 0L);
                }
            }

            List<String> orderIds = Files.readAllLines(Paths.get(themeOrder), Charset.defaultCharset());
            for (String item : orderIds) {
                String orderId = item.trim();
                if(mibiStore.containsKey(orderId)) {

                } else {
                    System.out.println(orderId);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
