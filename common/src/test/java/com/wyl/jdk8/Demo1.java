package com.wyl.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * Created by wangyulin on 07/01/2017.
 */
public class Demo1 {
    static int[] arr = {1,2,4,5,6,7,8,9,10};

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<User> (  );
        for(int i = 0; i < 10; i ++ ) {
            users.add ( new User(i, "billy" + i) );
        }
        users.stream ().map ( User::getName ).forEach ( System.out::println );
        /*users.stream ().forEach ( new Consumer<User> () {
            @Override
            public void accept(User user) {
                System.out.println (user.getName ());
            }
        } );*/
        List<Double> numbers = new ArrayList<> ();
        for (int i = 0; i < 10; i++) {
            numbers.add ( Double.valueOf ( i ) );
        }
        numbers.stream ().forEach ( System.out::println );
        /*numbers.stream ().forEach ( new Consumer<Double> () {
            @Override
            public void accept(Double aDouble) {
                System.out.println (aDouble);
            }
        } );*/

        IntConsumer output = System.out::println;
        IntConsumer errput = System.err::println;
        Arrays.stream ( arr ).forEach ( output.andThen ( errput ) );
    }

}
