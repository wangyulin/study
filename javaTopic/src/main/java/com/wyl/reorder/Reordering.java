package com.wyl.reorder;

/**
 * @Auther: wangyulin
 * @Date: 2018/6/26 14:08
 * @Description:
 */
public class Reordering {

    public static void main(String[] args) {
        int x, y;
        x = 1;
        try {
            x = 2;
            y = 0 / 0;
        } catch (Exception e) {
            System.out.println("x = " + x);
        } finally {

        }
    }

}
