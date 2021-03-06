package com.wyl.java7concurrency.forkjoin.demo1;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by wangyulin on 30/08/2017.
 */
public class Task extends RecursiveAction {

    private static final long serialVersionUID = 1L;

    private List<Product> products;

    private int first;
    private int last;

    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if(last - first < 10) {
            updatePrices();
        } else {
            int middle = (last - first) / 2;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            //invokeAll(t1, t2);
            t1.fork();
            t2.compute();
        }
    }


    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
            //System.out.println("------->>>>>>>>");
        }
    }
}
