package com.wangkun.thread.completablefuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class App {
    private static List<Shop> shops = Arrays.asList(new Shop("BeiJing"),
            new Shop("ShanDong"),new Shop("ShangHai"),
            new Shop("JiLin"),new Shop("GanSu"));

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Object> shopsInfo = shops.stream().map(shop -> {
           return  shop.getName() + "-" + shop.getPrice();
        }).collect(Collectors.toList());
        System.out.println(shopsInfo);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));

        long startTime1 = System.currentTimeMillis();
        List<Object> shopsInfo1 = shops.parallelStream().map(shop -> {
            return  shop.getName() + "-" + shop.getPrice();
        }).collect(Collectors.toList());
        System.out.println(shopsInfo1);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime1));

        long startTime2 = System.currentTimeMillis();
        List<CompletableFuture<String>> collect = shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> {
                    return shop.getName() + "-" + shop.getPrice();
                })
        ).collect(Collectors.toList());
        List<String> collect1 = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime2));

        long startTime3 = System.currentTimeMillis();
        List<String> collect2 = shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> {
                    return shop.getName() + "-" + shop.getPrice();
                })
        ).map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(collect2);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime3));

    }
}
