package com.game.engine.common.util.print;

import java.util.Arrays;

public class PrintUtil {

    public static void print(int[] events) {
        Arrays.stream(events).forEach(System.out::println);
    }

    public static void print(Integer[] events) {
        Arrays.stream(events).forEach(System.out::println);
    }


}
