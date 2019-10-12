package com.game.engine.common;


import com.game.engine.common.util.date.TimeUnitType;
import com.game.engine.common.util.date.TimeUtil;
import org.junit.Test;

/**
 * @author caochaojie
 * @date 2019/09/14
 */
public class TesDateUtil {

    @Test
    public void testTimeDuration() {
        long timeDuration = TimeUtil.addTimeUnit(TimeUnitType.MINUTES, System.currentTimeMillis(), 2);
        System.out.println(timeDuration);
    }
}
