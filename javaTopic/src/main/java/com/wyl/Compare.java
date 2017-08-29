package com.wyl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.wyl.utils.ConsistentHash;
import com.wyl.utils.RendezvousHash;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangyulin on 12/27/15.
 * For comparing the load differences between consistent hash and HRW
 */

public class Compare {
    private static final HashFunction hfunc = Hashing.murmur3_128();
    private static final Funnel<CharSequence> strFunnel = Funnels.stringFunnel(Charset.defaultCharset());

    public static void main(String[] args) {
        Map<String, AtomicInteger> distribution = Maps.newHashMap();

        System.out.println("======: ConsistentHash :========");
        ConsistentHash<String, String> c = new ConsistentHash(hfunc, strFunnel, strFunnel, getNodes(distribution));
        for(int i = 0 ; i < 10000; i++) {
            distribution.get(c.get(""+i)).incrementAndGet();
        }
        for(Map.Entry<String, AtomicInteger> e : distribution.entrySet()) {
            System.out.println(e.getKey() + "," + e.getValue().get());
            e.getValue().set(0);
        }
        System.out.println("====== remove 2 ========");
        for(int i = 0 ; i < 2; i ++) {
            c.remove("Node"+i);
            distribution.remove("Node"+i);
        }
        for(int i = 0 ; i < 10000; i++) {
            distribution.get(c.get(""+i)).incrementAndGet();
        }
        for(Map.Entry<String, AtomicInteger> e : distribution.entrySet()) {
            System.out.println(e.getKey() + "," + e.getValue().get());
        }

        System.out.println("======: RendezvousHash :========");
        distribution = Maps.newHashMap();
        RendezvousHash<String, String> r = new RendezvousHash(hfunc, strFunnel, strFunnel, getNodes(distribution));

        for(int i = 0 ; i < 10000; i++) {
            distribution.get(r.get(""+i)).incrementAndGet();
        }
        for(Map.Entry<String, AtomicInteger> e : distribution.entrySet()) {
            System.out.println(e.getKey() + "," + e.getValue().get());
            e.getValue().set(0);
        }
        System.out.println("====== remove 2 ========");
        for(int i = 0 ; i < 2; i ++) {
            r.remove("Node"+i);
            distribution.remove("Node"+i);
        }
        for(int i = 0 ; i < 10000; i++) {
            distribution.get(r.get(""+i)).incrementAndGet();
        }
        for(Map.Entry<String, AtomicInteger> e : distribution.entrySet()) {
            System.out.println(e.getKey() + "," + e.getValue().get());
        }
    }

    private static List<String> getNodes(Map<String, AtomicInteger> distribution) {
        List<String> nodes = Lists.newArrayList();
        for(int i = 0 ; i < 5; i ++) {
            nodes.add("Node"+i);
            distribution.put("Node"+i, new AtomicInteger());
        }
        return nodes;
    }
    
    /**
	PROPAGATION_REQUIRED 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
	PROPAGATION_SUPPORTS 支持当前事务，如果当前没有事务，就以非事务方式执行。
	PROPAGATION_MANDATORY 使用当前的事务，如果当前没有事务，就抛出异常。
	PROPAGATION_REQUIRES_NEW 新建事务，如果当前存在事务，把当前事务挂起。
	PROPAGATION_NOT_SUPPORTED 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
	PROPAGATION_NEVER 以非事务方式执行，如果当前存在事务，则抛出异常。
	PROPAGATION_NESTED 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与 PROPAGATION_REQUIRED 类似的操作。
     */

}

