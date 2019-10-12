package com.game.engine.common.util.array;

import javax.swing.text.TabableView;

/**
 * 数组实用工具类
 *
 * @Author: caochaojie
 * @Date: 2019/9/7
 */
public class ArrayUtil {

    /**
     * 类默认构造器
     */
    private ArrayUtil() {

    }

    /**
     * 是否为空数组
     *
     * @param intValArray int 数组
     * @return true = 空数组, false = 非空数组
     */
    public static boolean isNullOrEmpty(int[] intValArray) {
        return null == intValArray || intValArray.length <= 0;
    }

    /**
     * 是否为非空数组
     *
     * @param intValArray int 数组
     * @return true = 非空数组, false = 空数组
     */
    public static boolean isNotEmpty(
            int[] intValArray) {
        return !isNullOrEmpty(intValArray);
    }

    /**
     * 是否为空数组
     *
     * @param objArray 数组对象
     * @param <T>      数组元素类型
     * @return true = 空数组, false = 非空数组
     */
    static public <T> boolean isNullOrEmpty(
            T[] objArray) {
        return null == objArray || objArray.length <= 0;
    }

    /**
     * 是否为非空数组
     *
     * @param objArray 数组对象
     * @param <T>      数组元素类型
     * @return true = 非空数组, false = 空数组
     */
    public static <T> boolean isNotEmpty(
            TabableView[] objArray) {
        return !isNullOrEmpty(objArray);
    }

}
