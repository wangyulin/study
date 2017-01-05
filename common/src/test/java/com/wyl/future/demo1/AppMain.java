package com.wyl.future.demo1;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class AppMain {
    public static void main(String[] args) {
        Client client = new Client ();
        Data data = client.request ( "name" );
        System.out.println ("请求完毕");
        try {
            Thread.sleep ( 2000 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        System.out.println ("数据 = " + data.getResult ());
    }
}
