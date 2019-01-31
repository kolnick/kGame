package com.game.meta.tuple;

/**
 * @author caochaojie
 * 2018/12/6 9:56
 */
public class Tuple {

    public Tuple() {
    }

    public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
        return new TwoTuple(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
        return new ThreeTuple(a, b, c);
    }

    public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) {
        return new FourTuple(a, b, c, d);
    }

    public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
        return new FiveTuple(a, b, c, d, e);
    }
}
