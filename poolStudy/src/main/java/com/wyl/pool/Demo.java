package com.wyl.pool;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;

public class Demo {

	public static void main(String[] args) throws Exception {
		//创建一个对象池 
        GenericKeyedObjectPool<Object, Object> pool =
        		new GenericKeyedObjectPool<Object, Object>(new BaseKeyedPoolableObjectFactory<Object, Object>() {
        	@Override    
        	public Object makeObject(Object o) throws Exception { 
                        return o; 
                }

            /*@Override
            void destroyObject(Object key, Object obj) throws Exception {

            }*/
        }); 

        //添加对象到池，重复的不会重复入池 
        pool.addObject("a"); 
        pool.addObject("a"); 
        pool.addObject("b"); 
        pool.addObject("x"); 

        //清除最早的对象 
        pool.clearOldest(); 

        pool.invalidateObject("","");
        pool.clear();
        //pool.

        //获取并输出对象 
        System.out.println(pool.borrowObject("a")); 
        System.out.println(pool.borrowObject("b")); 
        System.out.println(pool.borrowObject("c")); 
        System.out.println(pool.borrowObject("c")); 
        System.out.println(pool.borrowObject("a")); 

        //输出池状态 
        System.out.println(pool.getMaxIdle()); 
        System.out.println(pool.getMaxActive()); 
	}

}
