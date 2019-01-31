package com.game.math;

import com.game.util.math.MathUtil;
import org.junit.Test;

public class MathTest {
    @Test
    public void numberInRange() {
        int i = MathUtil.adjustNumberInRange((Integer.MAX_VALUE - 100) + 101, 0, Integer.MAX_VALUE, true);

        int j = MathUtil.adjustNumberInRange((0 - 100), 0, Integer.MAX_VALUE, false);

        System.out.println(i);
        System.out.println(j);
    }
}
