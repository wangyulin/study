package com.wyl.springframework.alias.demo;

import org.springframework.core.SimpleAliasRegistry;
import org.springframework.util.Assert;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;

/**
 * Created by wangyulin on 19/11/2017.
 */
public class SimpleAliasDemo {

    public static void main(String[] args) {
        SimpleAliasRegistry aliasRegistry = new SimpleAliasRegistry();

        aliasRegistry.registerAlias("a", "A");
        aliasRegistry.registerAlias("A","b");

        Arrays.asList(aliasRegistry.getAliases("a")).forEach(System.out::println);
        System.out.println(aliasRegistry.isAlias("b"));
        System.out.println(aliasRegistry.hasAlias("a","b"));

        System.out.println(aliasRegistry.canonicalName("a"));
        Assert.notNull("", "Not null");
        Assert.isTrue(false);
        System.out.println("-");
    }

}
