package com.wyl.java8.optional;

import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

public class Demo {

    public static void main(String[] args) {
        U u = new U();
        u.setName("xyz");
        Optional<U> optionalU = Optional.ofNullable(u);

        V v = new V();
        v.setU(optionalU);

        W w = new W();
        w.setV(Optional.ofNullable(v));

        Optional<W> optionalW = Optional.ofNullable(w);

        System.out.println(optionalW);

        Optional<V> v1 = optionalW.flatMap(W::getV);

        //Optional<String> name = optionalW.map(W::getV).map(V::getU).map(U::getName);
        Optional<String> name = optionalW.flatMap(W::getV).flatMap(V::getU).map(U::getName);
        System.out.println(name.orElseGet(() -> ""));
    }

    @Data
    static class U {
        private String name;
    }

    @Data
    static class V {
        private Optional<U> u;
    }

    @Data
    static class W implements Serializable {
        private Optional<V> v;
    }
}
