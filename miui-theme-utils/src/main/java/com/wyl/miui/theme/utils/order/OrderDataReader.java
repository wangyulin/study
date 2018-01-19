package com.wyl.miui.theme.utils.order;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by wangyulin on 28/04/2017.
 */
public class OrderDataReader {

    public static final String INT_SEG = "INT";
    public static final String STRING_SEG = "STRING";
    public static final char COMMA_SEPARATOR = ',';
    public static final char SINGLE_QUOTATION_MARK = '\'';

    public static void main(String[] args) throws IOException {
        //String filePath = "/Users/wangyulin/work/order_2017_04_22";
        String filePath = "/Users/wangyulin/work/order_2014_03_19";
        List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());

        int counter = 0;
        for(String line : lines) {
            List<String> cols = null;
            try {
                cols = patternMatching(line);
            } catch (Exception e) {
                System.out.println("Error Data : " + line);
            }
            Object[] objectList = cols.toArray();
            String[] stringArray =  Arrays.copyOf(objectList,objectList.length,String[].class);
            if(stringArray.length == 18) {

            } else {
                //System.out.println("Error Data : " + cols);
            }
            //createOrderInfo(stringArray, cols);

            /*if(cols.size() == 23) {
                //System.err.println(cols.size());
                //System.err.println(cols);
                String cols_1 = cols.get(0);
                try {
                    Long id = Long.valueOf(cols_1);
                    System.out.println(id);
                } catch (Exception e) {
                    System.err.println(cols);
                }
            }*/
/*
            counter++;
            if(counter == 10) {
                break;
            }*/
        }
    }

    public static void createOrderInfo(String[] columns, List<String> cols) {
        try {
            System.out.print(Integer.valueOf(columns[0]) + "  ### ");
            System.out.print(columns[1] + "  ### ");
            System.out.print(Integer.valueOf(columns[2]) + "  ### ");
            System.out.print(columns[3] + "  ### ");
            System.out.print(columns[4] + "  ### ");
            System.out.print(Long.valueOf(columns[5]) + "  ### ");
            System.out.print(columns[6] + "  ### ");
            System.out.print(Long.valueOf(columns[7]) + "  ### ");
            System.out.print(Long.valueOf(columns[8]) + "  ### ");
            System.out.print(Long.valueOf(columns[9]) + "  ### ");
            System.out.print(Long.valueOf(columns[10]) + "  ### ");
            System.out.print(Long.valueOf(columns[11]) + "  ### ");
            System.out.print(Long.valueOf(columns[12]) + "  ### ");
            System.out.print(Long.valueOf(columns[13]) + "  ### ");
            System.out.print(Long.valueOf(columns[14]) + "  ### ");
            System.out.print(Long.valueOf(columns[15]) + "  ### ");
            System.out.print(Long.valueOf(columns[16]) + "  ### ");
            System.out.print(Integer.valueOf(columns[17]) + "  ### ");
            System.out.print(Integer.valueOf(columns[18]) + "  ### ");
            System.out.print(Integer.valueOf(columns[19]) + "  ### ");
            System.out.print(columns[20] + "  ### ");
            System.out.print(columns[21] + "  ### ");
            System.out.print(Integer.valueOf(columns[22]) + "  ### ");
            if (columns.length == 23) {
                System.out.println("" + "  ### ");
            } else {
                System.out.println(columns[23] + "  ### ");
            }
        } catch (Exception e) {
            System.err.println("Error Data : " + cols);
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

    }

    public static List<String> patternMatching(String str) {
        Stack<String> sk = new Stack<>();
        List<String> result = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char item =  str.charAt(i);

            if(i == 0) {
                if(item == SINGLE_QUOTATION_MARK) {
                    sk.push(STRING_SEG);
                    sb.append(item);
                    continue;
                } else {
                    sk.push(INT_SEG);
                    sb.append(item);
                    continue;
                }
            }

            if(item != COMMA_SEPARATOR && item != SINGLE_QUOTATION_MARK) {
                if(str.charAt(i - 1) == COMMA_SEPARATOR && sk.empty()) {
                    sk.push(INT_SEG);
                    sb.append(item);
                    if((i == (str.length() - 1)) && sk.peek().equals(INT_SEG)) {
                        sk.pop();
                        result.add(sb.toString());
                    }
                    continue;
                } else {
                    sb.append(item);
                    continue;
                }
            } else if(item == COMMA_SEPARATOR) {
                if(sk.peek().equals(INT_SEG)){
                    sk.pop();
                    result.add(sb.toString());
                    sb = new StringBuffer();
                    continue;
                } else if((str.charAt(i - 1) == SINGLE_QUOTATION_MARK) && sk.peek().equals(STRING_SEG)) {
                    sk.pop();
                    String res = sb.toString().replaceAll("^\'", "").replaceAll("\'$", "");
                    result.add(res);
                    sb = new StringBuffer();
                    continue;
                }
            }

            if(item == SINGLE_QUOTATION_MARK) {
                if(str.charAt(i - 1) == COMMA_SEPARATOR && sk.empty()) {
                    sk.push(STRING_SEG);
                    sb.append(item);
                    continue;
                } else if((i == str.length() - 1) && sk.peek().equals(STRING_SEG)) {
                    sb.append(item);
                    result.add(sb.toString());
                    sb = new StringBuffer();
                    continue;
                }
            }

            sb.append(item);
        }
        return result;
    }
}
