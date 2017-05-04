package com.wyl.common.utils.future;

import java.util.concurrent.Callable;

/**
 * Created by wangyulin on 21/04/2017.
 */
class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        //Thread.sleep(1000);
        int sum = 0;
        for(int i=0;i<100000;i++) {
            if(i % 100 == 0)
                System.out.println("累计 : " + i);
            sum += i;
        }
        return sum;
    }
}
