package com.wyl.ds;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> res = solution.letterCombinations("12");
        System.out.println(res);
    }

    char[][] a = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0){
            return new ArrayList<String>();
        }
        if(digits.length() == 1){
            List<String> result = new ArrayList<String>();
            int i = Integer.parseInt(digits);
            for(char c : a[i]){
                result.add(Character.toString(c));
            }
            return result;
        }
        int j = Integer.parseInt(digits.substring(0,1));
        List<String> res = new ArrayList<String>();
        for(char c : a[j]){
            for(String ss : letterCombinations(digits.substring(1))){
                res.add(c+ss);
            }
        }
        return res;
    }

    /*public List<String> letterCombinations(String digits) {
        String[] ans = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<String>();
        if (digits == "" || digits.length() == 0)
            return result;
        result.add("");
        int[] num = new int[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            num[i] = digits.charAt(i) - '0';
        }
        for (int k = 0; k < digits.length(); k++) {
            if (num[k] < 2)
                continue;
            List<String> midans = new ArrayList<String>();
            String s1 = ans[num[k]];
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < s1.length(); j++) {
                    midans.add(result.get(i) + s1.charAt(j));
                }
            }
            result = midans;
        }
        return result;
    }*/

    /*public List<String> letterCombinations(String digits) {
        //把table上的数字对应的字母列出来，当输入为2是，digits[2]就是2所对应的"abc"
        String[] table = new String[]
                {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> list = new ArrayList<String>();
        //index从0开始，即digits的第一个数字
        letterCombinations(list,digits,"",0,table);
        return list;
    }



    private void letterCombinations (List<String> list, String digits,
                                     String curr, int index,String[] table) {
        //最后一层退出条件
        if (index == digits.length()) {
            if(curr.length() != 0) list.add(curr);
            return;
        }

        //找到数字对应的字符串
        String temp = table[digits.charAt(index) - '0'];
        for (int i = 0; i < temp.length(); i++) {
            //每次循环把不同字符串加到当前curr之后
            String next = curr + temp.charAt(i);
            //进入下一层
            letterCombinations(list,digits,next,index+1,table);
        }
    }*/
}
