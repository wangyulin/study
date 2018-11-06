package com.wyl.reorder;

/**
 * @Auther: wangyulin
 * @Date: 2018/6/26 13:32
 * @Description:
 */
public class PossibleReordering {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        do {
            test(count++);
            if(x == 0 && y == 0) {
                break;
            }
        } while(true);
    }

    public static void test(int index) throws InterruptedException {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread other = new Thread(() -> {
            b = 1;
            y = a;
        });

        one.start();
        other.start();

        one.join();
        other.join();

        System.out.println("第" + index + "次 : " + "(" + x + "," + y + ")");
    }
}
