package com.wyl.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wangyulin on 04/01/2017.
 */
public class AtomicReferenceDemo {

    static AtomicReference<Integer> money = new AtomicReference<Integer> ( 19 );

    public static void main(String[] args) {
        System.out.println (money.get ());
        for (int i = 0; i < 3; i++) {
            new Thread (  ) {
                @Override
                public void run() {
                    while(true) {
                        while (true) {
                            Integer m = money.get ();
                            if (m < 20) {
                                if(money.compareAndSet ( m, m + 20 )) {
                                    System.out.println ( "余额小于20远，充值成功，余额：" + money.get () + "元" );
                                    try {
                                        Thread.sleep ( 1000 );
                                    } catch (InterruptedException e) {
                                        e.printStackTrace ();
                                    }
                                }
                                break;
                            } else {
                                System.out.println ( "余额大于20元，无须充值" );
                                try {
                                    Thread.sleep ( 1000 );
                                } catch (InterruptedException e) {
                                    e.printStackTrace ();
                                }
                                break;
                            }
                        }
                    }

                }
            }.start ();
        }

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while(true) {
                        Integer m = money.get ();
                        if(m > 10) {
                            System.out.println ("大于10元");
                            if(money.compareAndSet ( m, m - 10 )) {
                                System.out.println ("成功消费10元，余额:" + money.get ());
                                break;
                            }
                        } else {
                            System.out.println ("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep ( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
            }
        }.start ();
    }

}
