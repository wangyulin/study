package com.wyl.cglib.interfaces;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class ISomeServiceImpl implements ISomeService {

    @Override
    public String Litigate() {
        return "自己打官司，输了";
    }

    @Override
    public String eat() {
        return "自己吃饭";
    }

}
