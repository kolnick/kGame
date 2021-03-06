package com.game.engine.common.meta.tuple;

/**
 * @Author: caochaojie
 * @Date: 2019/4/16
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
    public final E fifth;

    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        this.fifth = e;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third + ", " + this.fourth + ", " + this.fifth + ")";
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (this.fifth == null ? 0 : this.fifth.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!super.equals(obj)) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            FiveTuple other = (FiveTuple) obj;
            if (this.fifth == null) {
                if (other.fifth != null) {
                    return false;
                }
            } else if (!this.fifth.equals(other.fifth)) {
                return false;
            }

            return true;
        }
    }
}
