package com.wyl.sortmap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.wyl.utils.ConsistentHash;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangyulin on 12/27/15.
 */
public class ConsistentHashTest {

    private static final HashFunction hfunc = Hashing.murmur3_128();
    private static final Funnel<CharSequence> strFunnel = Funnels.stringFunnel(Charset.defaultCharset());

    @Test
    public void testConsistentHash() {
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

        System.out.println("======: ConsistentHash :========");
        c.remove("Node0");
        c.remove("Node2");

    }

    private static List<String> getNodes(Map<String, AtomicInteger> distribution) {
        List<String> nodes = Lists.newArrayList();
        for(int i = 0 ; i < 5; i ++) {
            nodes.add("Node"+i);
            distribution.put("Node"+i, new AtomicInteger());
        }
        return nodes;
    }

}
