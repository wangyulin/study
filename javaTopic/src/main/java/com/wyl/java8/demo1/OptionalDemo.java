package com.wyl.java8.demo1;

import lombok.Data;

import java.util.Optional;

/**
 * Created by wangyulin on 17/01/2018.
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Optional<String> name = Optional.of("wyl");
        String s = name.get();
        System.out.println(s);
        Optional<String> res = name.map(String::toUpperCase);
        System.out.println(res.get());

        Optional<String> upName = name.flatMap(x -> Optional.of(x.toUpperCase()));
        System.out.println(upName.get());

        User user = new User();
        name.ifPresent((value) -> user.setNane(value));
        System.out.println(user);

        Optional<String> test1 = Optional.empty();
        System.out.println(test1.orElse("Default"));
        System.out.println(Optional.empty().orElse("OK"));
        System.out.println(Optional.empty().orElseGet(() -> "Default Value"));
        //System.out.println(Optional.empty().orElseThrow(ValueAbsentException::new));

        Optional.of("www").filter(User::isNameValid)
                .orElseThrow(()->new IllegalArgumentException("Invalid username."));
    }

    @Data
    static class User{
        private String nane;

        public static boolean isNameValid(String name) {
            return name.length() >= 3;
        }
    }

    static class ValueAbsentException extends Throwable {

        public ValueAbsentException() {
            super();
        }

        public ValueAbsentException(String msg) {
            super(msg);
        }

        @Override
        public String getMessage() {
            return "No value present in the Optional instance";
        }
    }
}
