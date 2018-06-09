package com.wyl.java8.completablefuture;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Demo1 {

    public static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("A"),
            //new Shop("B"),
            //new Shop("C"),
            //new Shop("D"),
            new Shop("E"));

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public static void main(String[] args) {
        /*Shop shop = new Shop();
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invacationTime = ((System.nanoTime() - start) / 1000000);

        doSomeThingElse();

        try {
            double price = futurePrice.get();
            System.out.println(price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration);

    }

    public static List<String> findPrices(String product) {
        /*return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());*/

        List<CompletableFuture<String>> completableFutureList = shops.parallelStream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
                .collect(toList());

        return completableFutureList.parallelStream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public static void doSomeThingElse() {
        try {
            System.out.println("doSomeThingElse() starting ...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("doSomeThingElse() end ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Data
    static class Shop{

        private String name;

        public Shop(String name) {
            this.name = name;
        }

        public double getPrice(String product) {
            /*System.out.println("getPriceAsync() starting ...");
            CompletableFuture<Double> completableFuture = new CompletableFuture<>();
            new Thread(() -> {
                try {
                    double price = calculatePrice(product);
                    int x = 1/0;
                    completableFuture.complete(price);
                    System.out.println("getPriceAsync() end ...");
                    System.out.println(x);
                }catch (Exception ex) {
                    completableFuture.completeExceptionally(ex);
                }
            }).start();
            return completableFuture;*/
            System.out.println(product);
            return calculatePrice(product);
        }

        public Future<Double> getPriceAsync(String product) {
            /*System.out.println("getPriceAsync() starting ...");
            CompletableFuture<Double> completableFuture = new CompletableFuture<>();
            new Thread(() -> {
                try {
                    double price = calculatePrice(product);
                    int x = 1/0;
                    completableFuture.complete(price);
                    System.out.println("getPriceAsync() end ...");
                    System.out.println(x);
                }catch (Exception ex) {
                    completableFuture.completeExceptionally(ex);
                }
            }).start();
            return completableFuture;*/

            return CompletableFuture.supplyAsync(() -> calculatePrice(product));
        }

        private double calculatePrice(String product) {
            delay();
            //int x = 1/0;
            //System.out.println(x);
            return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
        }

        public static void delay() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
