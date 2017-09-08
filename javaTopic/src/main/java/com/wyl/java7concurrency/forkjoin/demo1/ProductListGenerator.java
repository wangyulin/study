package com.wyl.java7concurrency.forkjoin.demo1;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyulin on 30/08/2017.
 */
public class ProductListGenerator {

    public List<Product> generate(int size) {
        List<Product> ret = new ArrayList<>();
        ret.iterator();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }

}
