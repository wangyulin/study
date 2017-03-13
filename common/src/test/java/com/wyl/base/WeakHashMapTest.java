package com.wyl.base;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by wangyulin on 13/03/2017.
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WeakHashMap<AA, People1> weakMap1 = new WeakHashMap<AA, People1>();
        String b = new String("louhang1");
        AA a = new AA(b);
        BB bb = new BB(a);
        People1 p1 = new People1(bb);
        weakMap1.put(p1.getB().getAA(), p1);
        p1.getB().setAA(null);// 去除对象a的强引用
        a = null;// 去除对象a的强引用,并让垃圾收集器回收AA对象在堆中的内存
        System.gc();
        Iterator i = weakMap1.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry en = (Map.Entry) i.next();
            System.out.println("weakMap:" + en.getKey() + ":" + en.getValue());
        }
    }
}

class AA {
    private String a;

    public AA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}

class BB {
    private AA a;

    public BB(AA a) {
        this.a = a;
    }

    public AA getAA() {
        return a;
    }

    public void setAA(AA a) {
        this.a = a;
    }
}

class People1 {
    private BB b;

    public People1(BB b) {
        this.b = b;
    }

    public BB getB() {
        return b;
    }

    public void setB(BB b) {
        this.b = b;
    }
}
