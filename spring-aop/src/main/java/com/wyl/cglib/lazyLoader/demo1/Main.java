package com.wyl.cglib.lazyLoader.demo1;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class Main {

    public static void main(String[] args) {

        LoaderBean loader = new LoaderBean();
        loader.getPropertyBean();
        loader.getPropertyBean();
        loader.getPropertyBean();

        System.out.println(loader.getLoaderName());
        System.out.println(loader.getLoaderValue());

        PropertyBean propertyBean = loader.getPropertyBean();//访问延迟加载对象

        System.out.println("---------------------");
        System.out.println(propertyBean.getPropertyName());
        System.out.println(propertyBean.getPropertyValue());

        System.out.println("after...");
        //当再次访问延迟加载对象时,就不会再执行回调了
        System.out.println(propertyBean.getPropertyName());
    }

}
