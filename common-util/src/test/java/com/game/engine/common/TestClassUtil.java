package com.game.engine.common;


import com.game.engine.common.util.reflect.ClassUtil;
import org.junit.Test;

public class TestClassUtil {

    @Test
    public void testExtends() {
        boolean extendsClass = ClassUtil.isAssignableFrom(Man.class, Person.class);
        System.out.println(extendsClass);
    }


}
