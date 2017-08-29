package com.wyl.ds;

import java.util.Stack;

/**
 * Created by wangyulin on 4/13/16.
 */
public class StackDemo {

    public static void main(String[] args) {
        Stack<String> st = new Stack<String>();
        st.push("A");
        st.push("B");
        st.push("C");

        st.addElement("F");

        System.out.println(st.pop());


    }

}
