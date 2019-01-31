package com.game.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;

/**
 * 〈${DESCRIPTION}〉
 *
 * @author caochaojie
 * @create 2018/4/28
 * @since 1.0.0
 */
public class ClassUtil {

    public static boolean isAbstractClass(Class<?> clazz) {
        if (clazz == null) {
            throw new NullPointerException();
        }
        return Modifier.isAbstract(clazz.getModifiers());
    }

    public static String getName(Class<?> clazz) {
        return clazz.getName();
    }

    public static boolean hasAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        if (clazz == null || annotationClass == null)
            return false;
        return clazz.getDeclaredAnnotation(annotationClass) != null;
    }

}

