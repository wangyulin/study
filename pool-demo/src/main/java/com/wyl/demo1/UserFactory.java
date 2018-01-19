package com.wyl.demo1;

import com.wyl.model.User;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by wangyulin on 01/07/2017.
 */
public class UserFactory implements PooledObjectFactory<User> {

    @Override
    public PooledObject<User> makeObject() throws Exception {
        System.out.println("创建一个新的对象");
        return new DefaultPooledObject<User>(new User());
    }

    @Override
    public void destroyObject(PooledObject<User> p) throws Exception {
        User user = p.getObject();
        System.out.println("销毁对象" + user.toString());
        user = null;
    }

    @Override
    public boolean validateObject(PooledObject<User> p) {
        if(p.getObject() instanceof User){
            System.out.println("是一个合法的对象");
            return true;
        }
        System.out.println("是一个非法的对象");
        return false;
    }

    @Override
    public void activateObject(PooledObject<User> p) throws Exception {
        System.out.println("重新初始化对象");
    }

    @Override
    public void passivateObject(PooledObject<User> p) throws Exception {
        User user = p.getObject();
        System.out.println("对象已经被归还:" + user.toString());
    }
}
