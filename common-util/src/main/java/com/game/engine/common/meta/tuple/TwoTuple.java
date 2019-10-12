package com.game.engine.common.meta.tuple;

import java.util.Objects;

/**
 * @Author: caochaojie
 * @Date: 2019/4/16
 */
public class TwoTuple<A, B> {
    public A first;
    public B second;

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public void setSecond(B second) {
        this.second = second;
    }


    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TwoTuple<?, ?> twoTuple = (TwoTuple<?, ?>) o;
        return Objects.equals(first, twoTuple.first) &&
                Objects.equals(second, twoTuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
