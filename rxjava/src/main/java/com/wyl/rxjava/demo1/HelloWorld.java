package com.wyl.rxjava.demo1;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func0;

import java.util.concurrent.TimeUnit;

public class HelloWorld {

    public static String str;

    public static void main(String[] args) {
        //testHelloWorld();
        //testArray();
        testDefer();
    }

    public static void testDefer() {

        Observable observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(str);
            }
        });

        str = "Hello World , Defer !";
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted(): ");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String o) {
                System.out.println("onNext(): " + o);
            }
        });

    }

    public static void testArray() {
        Observable.from(new Integer[]{1,2,3,4,5,6}).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted(): ");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext(): " + integer);
            }
        });
    }

    public static void testHelloWorld() {
        /** 创建被观察者 */
        // .just("Hello World !")
        Observable myObservable = Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("Hello World !");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onCompleted();
            //throw new NullPointerException();
        });

        /** 创建观察者 */
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted()");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError()");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext():" + s);
            }
        };

        myObservable.subscribe(subscriber);

        System.out.println("---");
    }

}
