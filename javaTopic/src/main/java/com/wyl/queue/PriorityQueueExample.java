package com.wyl.queue;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PriorityQueueExample {

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		PriorityQueue<Integer> qi = new PriorityQueue<>(3);

        Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor(new Class<?>[0]);
        constructor.setAccessible(true);
        Unsafe UNSAFE = constructor.newInstance(new Object[0]);

        int a = UNSAFE.getAndAddInt(2,2,2);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(1,2 );

        qi.add(9);
        qi.add(3);
        qi.add(7);
        qi.add(6);
        qi.add(5);
        qi.add(1);
        qi.add(10);
        qi.add(2);

 
        while (!qi.isEmpty()) {
            System.out.print(qi.poll() + ",");
        }
        System.out.println();
        System.out.println("-----------------------------");
        //自定义的比较器，可以让我们自由定义比较的顺序cmp;
        Comparator cmp = (Comparator<Integer>) (e1, e2) -> e2 - e1;
        PriorityQueue<Integer> q2 = new PriorityQueue<Integer>(5, cmp);
        q2.add(2);
        q2.add(8);
        q2.add(9);
        q2.add(1);
        while (!q2.isEmpty()) {
            System.out.print(q2.poll() + ",");
        }
	}

}
