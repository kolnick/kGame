package com.game.meta.tuple;

/**
 * @author caochaojie
 * 2018/12/6 9:58
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.third = c;
    }

    public C getThird() {
        return this.third;
    }

    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third + ")";
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (this.third == null ? 0 : this.third.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!super.equals(obj)) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            ThreeTuple other = (ThreeTuple) obj;
            if (this.third == null) {
                if (other.third != null) {
                    return false;
                }
            } else if (!this.third.equals(other.third)) {
                return false;
            }

            return true;
        }
    }
}
