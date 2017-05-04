package com.wyl.miui.theme.utils.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangyulin on 03/05/2017.
 */
public class Demo1 {

    public static void main(String[] args) {
        String str = "2,1,\'22\',\'333,22\',\'32,22,22,23\',4,\'6\',5";
        /*StringBuffer newStr= new StringBuffer("");
        List<String> rslt = new ArrayList<String>();
        boolean flg = true;
        for(char c : str.toCharArray()){
            if(c !=',' || !flg){
                newStr.append(c);
            }else {
                rslt.add(newStr.toString());
                newStr.delete(0, newStr.length());
            }
            if(c=='"') {
                flg = !flg;
            }
        }*/
        System.out.println(Arrays.toString(patternMatching(str).toArray())); //rslt.toArray()
    }

    public static List<String> patternMatching(String str) {
        StringBuffer newStr= new StringBuffer("");
        List<String> rslt = new ArrayList<String>();
        boolean flg = true;
        for(char c : str.toCharArray()){
            if(c !=',' || !flg){
                newStr.append(c);
            }else {
                rslt.add(newStr.toString());
                newStr.delete(0, newStr.length());
            }
            if(c=='\'') {
                flg = !flg;
            }
        }
        return rslt;
    }

}
