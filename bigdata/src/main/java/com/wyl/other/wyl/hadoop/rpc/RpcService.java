package com.wyl.other.wyl.hadoop.rpc;

/**
 * Created by wangyulin on 12/03/2017.
 */
public interface RpcService {
    public static final long versionID=100L;
    public String login(String username, String password);
}
