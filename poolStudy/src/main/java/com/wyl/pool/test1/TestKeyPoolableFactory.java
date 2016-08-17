package com.wyl.pool.test1;

import org.apache.commons.pool.KeyedPoolableObjectFactory;

/**
 * Created by wangyulin on 8/17/16.
 */
public class TestKeyPoolableFactory implements KeyedPoolableObjectFactory<String, MyBaseObject> {

    /** 重新初始化实例返回池 */
    public void activateObject(String arg0, MyBaseObject arg1) throws Exception {
        arg1.setActive(true);
    }

    /** 销毁被破坏的实例 */
    public void destroyObject(String arg0, MyBaseObject arg1) throws Exception {
        arg1 = null;
    }

    /** 创建一个实例到对象池 */
    public MyBaseObject makeObject(String arg0) throws Exception {
        //这里从数据库里查询出使用次数最少的配置
        MyBaseObject bo = new MyBaseObject();
        bo.setNum(((int)(Math.random() * 1000)));
        return bo;
    }

    /** 取消初始化实例返回到空闲对象池 */
    public void passivateObject(String arg0, MyBaseObject arg1) throws Exception {
        arg1.setActive(false);
    }

    /** 验证该实例是否安全 true:正在使用 */
    public boolean validateObject(String arg0, MyBaseObject arg1) {
        //这里可以判断实例状态是否可用
        if(arg1.isActive())
            return true;
        else
            return false;
    }
}