package com.wyl.pool.test1;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 * Created by wangyulin on 8/17/16.
 */
public class TestPoolableFactory implements PoolableObjectFactory {

    /** 重新初始化实例返回池 */
    @Override
    public void activateObject(Object arg0) throws Exception {
        ((MyBaseObject)arg0).setActive(true);
    }

    /** 销毁被破坏的实例 */
    @Override
    public void destroyObject(Object arg0) throws Exception {
        arg0 = null;
    }

    /** 创建一个实例到对象池 */
    @Override
    public Object makeObject() throws Exception {
        MyBaseObject bo = new MyBaseObject();
        return bo;
    }

    /** 取消初始化实例返回到空闲对象池 */
    @Override
    public void passivateObject(Object arg0) throws Exception {
        ((MyBaseObject)arg0).setActive(false);
    }

    /** 验证该实例是否安全 */
    @Override
    public boolean validateObject(Object arg0) {
        if(((MyBaseObject)arg0).isActive())
            return true;
        else
            return false;
    }

}
