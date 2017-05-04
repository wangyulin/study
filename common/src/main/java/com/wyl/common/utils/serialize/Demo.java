package com.wyl.common.utils.serialize;

import org.apache.commons.lang.SerializationUtils;

/**
 * Created by wangyulin on 25/04/2017.
 */
public class Demo<T> {

    public static void main(String[] args) throws ClassNotFoundException {
        test1();
        User user = new User();
        Class<User> u = User.class;
        user.setName("wyl");
        User u2 = u.cast(user);
        System.out.println(u2.getName());
        Class c = Class.forName("com.wyl.common.utils.serialize.User");
        User u3 = (User) c.cast(u2);

        byte [] message = SerializationUtils.serialize(u2);
        User ooo = (User) SerializationUtils.deserialize(message);
        User u4 = Utils.cast_1(ooo, User.class);
        System.out.println(u4.getName());

        System.out.println((String) Utils.cast("321"));

        System.out.println(u4.getClass().getSimpleName());
        System.out.println((byte)128|2|4);

    }

    public T deserialize(byte[] bytes) throws ClassNotFoundException {
        T obj = (T) SerializationUtils.deserialize(bytes);
        return obj;

    }

    public static void test1() throws ClassNotFoundException {
        Demo<User> demo = new Demo<User>();
        User user = new User();
        user.setId("1");
        user.setName("wangyulin");
        user.setAge(20);
        byte [] message = SerializationUtils.serialize(user);
        User u = demo.deserialize(message);
        System.out.println(u);
    }

}
