package com.game.math;

import com.game.util.math.MathUtil;
import org.junit.Test;

public class MathTest {
    @Test
    public void numberInRange() {
        int i = MathUtil.increaseNumberInRange((Integer.MAX_VALUE - 100) + 101, 0, Integer.MAX_VALUE);
        int ssi = MathUtil.increaseNumberInRange(100 + 1, 0, Integer.MAX_VALUE);
        System.out.println(ssi);

        int j = MathUtil.decreaseNumberInRange((0 - 100), 0, Integer.MAX_VALUE);
        int h = MathUtil.decreaseNumberInRange(3 - 1, 0, Integer.MAX_VALUE);

        System.out.println(i);
        System.out.println(j);
        System.out.println(h);

    }
}
