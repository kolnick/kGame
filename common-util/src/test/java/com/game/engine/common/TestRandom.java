package com.game.engine.common;

import com.game.engine.common.util.random.RandomUtil;
import org.junit.Test;

/**
 * @author caochaojie
 * @date 2019/10/08
 */
public class TestRandom {

    @Test
    public void test() {
        String s = RandomUtil.randomString(RandomUtil.BASE_NUMBER + RandomUtil.LOWER_CASE + RandomUtil.UPPER_CASE, 5);
        System.out.println(s);
    }
}
