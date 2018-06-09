package com.wyl.other.wyl.hadoop.rpc;

import java.util.Date;

/**
 * Created by wangyulin on 12/03/2017.
 */
public class RpcServiceImpl implements RpcService{

    public String login(String username,String password){
        System.out.println(username + " " + password);
        return "login successlly"+new Date().getTime();
    }

}
