package com.wyl.java8.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by wangyulin on 17/01/2018.
 */
public class StreamMaxMin {

    public static void main(String[] args) {
        List<Track> tracks = asList(new Track("Bakai", 453),
                new Track("Violets", 478),
                new Track("Time Was", 451));

        Track shortestTrack = tracks.stream()
                .min(Comparator.comparingLong(track -> track.getLength()))
                .get();

        Track longestTrack = tracks.stream()
                .max(Comparator.comparingLong(track -> track.getLength()))
                .get();

        System.out.println("shortestTrack : " + shortestTrack);
        System.out.println("longestTrack  : " + longestTrack);


        List<String> names = tracks.stream().map(t -> t.getName()).collect(toList());
        System.out.println(names);

        System.out.println("////////////////////////////////////////////////////////");

        Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());

        System.out.println("////////////////////////////////////////////////////////");

        Function<Long, Long> chainedFunction =
                ((Function<Long, Long>)(x -> x * x)).andThen(x -> x + 1).andThen(x -> x * x);
        System.out.println(chainedFunction.apply(3L));
    }

    @Data
    @AllArgsConstructor
    public static class Track {
        private String name;
        private long length;
    }

    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable create( Supplier<Defaulable> supplier ) {
            return supplier.get();
        }
    }

}
