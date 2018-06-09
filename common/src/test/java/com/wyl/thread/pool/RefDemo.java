package com.wyl.thread.pool;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class RefDemo {

    public static void main(String[] args) throws Exception {
        //soft();

        weak();

        //phantom();
    }

    /**
     * 只有当内存不够的时候，才回收这类内存，因此在内存足够的时候，它们通常不被回收
     *
     * <pre>
     * 无论是否发送GC,执行结果都是:
     * java.lang.Object@f9f9d8
     * null
     * java.lang.Object@f9f9d8
     * null
     * </pre>
     *
     * 可以看到:只有发送了GC,将对于从内存中释放的时候,JVM才会将reference假如引用队列
     */
    public static void soft() throws Exception {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        SoftReference<Object> softRef = new SoftReference<>(obj, refQueue);
        System.out.println("softRef.get() : " + softRef.get()); // java.lang.Object@f9f9d8
        System.out.println("refQueue.poll() : " + refQueue.poll());// null

        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println("softRef.get() : " + softRef.get());

        Thread.sleep(200);
        System.out.println("refQueue.poll() : " + refQueue.poll());

        System.out.println("--------------------------");
        while (true) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("refQueue.poll() : " + refQueue.poll());
        }
    }

    /**
     * 弱引用: 当发生GC的时候,Weak引用对象总是会内回收回收。因此Weak引用对象会更容易、更快被GC回收。
     * Weak引用对象常常用于Map数据结构中，引用占用内存空间较大的对象
     *
     * <pre>
     * 如果不发生垃圾回收：
     * java.lang.Object@f9f9d8
     * null
     * java.lang.Object@f9f9d8
     * null
     *
     * 如果发生垃圾回收:
     * java.lang.Object@f9f9d8
     * null
     * null
     * java.lang.ref.WeakReference@422ede
     *
     * <pre>
     */
    public static void weak() throws Exception {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        WeakReference<Object> weakRef = new WeakReference<>(obj, refQueue);
        System.out.println("weakRef.get() : " + weakRef.get()); // java.lang.Object@f9f9d8
        System.out.println("refQueue.poll() : " + refQueue.poll());// null

        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println("weakRef.get() : " + weakRef.get());

        // 这里特别注意:poll是非阻塞的,remove是阻塞的.
        // JVM将弱引用放入引用队列需要一定的时间,所以这里先睡眠一会儿
        // System.out.println(refQueue.poll());// 这里有可能是null

        Thread.sleep(200);
        System.out.println("refQueue.poll() : " + refQueue.poll());
        System.out.println("refQueue.poll() : " + refQueue.poll());//这里一定是null,因为已经从队列中移除

        //System.out.println(refQueue.remove());
    }

    /**
     * 当GC一但发现了虚引用对象，将会将PhantomReference对象插入ReferenceQueue队列.
     * 而此时PhantomReference所指向的对象并没有被GC回收，而是要等到ReferenceQueue被你真正的处理后才会被回收.
     *
     * <pre>
     * 不发生GC执行结果是:
     * null
     * null
     * null
     * null
     *
     * 发生GC执行结果是:
     * null
     * null
     * null
     * java.lang.ref.PhantomReference@87816d
     * </pre>
     *
     * 虚引用在实现一个对象被回收之前必须做清理操作是很有用的,比finalize()方法更灵活
     */
    public static void phantom() throws Exception {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<>(obj, refQueue);
        System.out.println("phantom.get() : " + phantom.get()); // java.lang.Object@f9f9d8
        System.out.println("refQueue.poll() : " + refQueue.poll());// null

        obj = null;
        System.gc();

        // 调用phanRef.get()不管在什么情况下会一直返回null
        System.out.println("phantom.get() : " + phantom.get());

        // 当GC发现了虚引用，GC会将phanRef插入进我们之前创建时传入的refQueue队列
        // 注意，此时phanRef所引用的obj对象，并没有被GC回收，在我们显式地调用refQueue.poll返回phanRef之后
        // 当GC第二次发现虚引用，而此时JVM将phanRef插入到refQueue会插入失败，此时GC才会对obj进行回收
        Thread.sleep(200);
        System.out.println("refQueue.poll() : " + refQueue.poll());
    }
}
