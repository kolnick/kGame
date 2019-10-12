package com.game.engine.common.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 类实用工具
 *
 * @author caochaojie
 * 2019/9/7
 */
public class ClassUtil {


    /**
     * 类默认构造器
     */
    private ClassUtil() {

    }


    /**
     * 判断 A 是否为 B 的派生类
     *
     * @param clazzA
     * @param clazzB
     * @return
     */
    public static boolean isAssignableFrom(Class<?> clazzA, Class<?> clazzB) {
        if (clazzA == null || clazzB == null) {
            return false;
        } else if (clazzA.equals(clazzB)) {
            // 类自己不能作为自己的派生类,
            return false;
        } else {
            // 判断 A 是否为 B 的派生类
            return clazzB.isAssignableFrom(clazzA);
        }
    }

    /**
     * 获取类的所有字段, 包括从父类继承来的字段
     *
     * @param fromClazz 类
     */
    public static List<Field> listField(Class<?> fromClazz) {
        if (fromClazz == null) {
            // 如果参数对象为空,
            // 则直接退出!
            return null;
        }

        // 类集成关系堆栈
        LinkedList<Class<?>> clazzStack = new LinkedList<>();
        // 当前类
        Class<?> currClazz;

        for (currClazz = fromClazz; currClazz != null; currClazz = currClazz.getSuperclass()) {
            // 将当前类压入堆栈
            clazzStack.offerFirst(currClazz);
        }

        // 创建字段表
        List<Field> fl = new ArrayList<>();

        while ((currClazz = clazzStack.pollFirst()) != null) {
            // 获取方法数组
            Field[] fArr = currClazz.getDeclaredFields();

            Collections.addAll(fl, fArr);
        }

        return fl;
    }



    /**
     * 列表类方法, 包括从父类继承来的方法
     *
     * @param fromClazz
     * @return
     */
    static public List<Method> listMethod(Class<?> fromClazz) {
        if (fromClazz == null) {
            // 如果参数对象为空,
            // 则直接退出!
            return null;
        }

        // 类集成关系堆栈
        LinkedList<Class<?>> clazzStack = new LinkedList<>();
        // 当前类
        Class<?> currClazz;

        for (currClazz = fromClazz;
             currClazz != null;
             currClazz = currClazz.getSuperclass()) {
            // 将当前类压入堆栈
            clazzStack.offerFirst(currClazz);
        }

        // 创建方法列表
        List<Method> ml = new ArrayList<>();

        while ((currClazz = clazzStack.pollFirst()) != null) {
            // 获取方法数组
            Method[] mArr = currClazz.getDeclaredMethods();
            Collections.addAll(ml, mArr);
        }

        return ml;
    }

    /**
     * 列表 get 方法
     *
     * @param fromClazz
     * @return
     */
    static public <T extends Annotation> List<Method> listGetterMethod(Class<?> fromClazz) {
        return listMethod(fromClazz, (m) -> {
            return (m != null && (
                    m.getName().startsWith("get") ||
                            m.getName().startsWith("is")
            ));
        });
    }

    /**
     * 从指定类中获得满足条件的方法列表
     *
     * @param fromClazz
     * @return
     */
    static public List<Method> listMethod(Class<?> fromClazz, Predicate<Method> pred) {
        if (fromClazz == null) {
            // 如果参数对象为空,
            // 则直接退出!
            return null;
        }

        List<Method> ml = listMethod(fromClazz);

        if (ml == null ||
                ml.isEmpty()) {
            return null;
        }

        if (pred == null) {
            // 如果条件为空,
            // 则直接返回!
            return ml;
        } else {
            // 过滤字段列表
            return ml.stream().filter(pred).collect(Collectors.toList());
        }
    }





}
