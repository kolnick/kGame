package com.game.engine.common.util.collection.map;

import com.ym.server.engine.common.util.math.MathUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caochaojie
 * @date 2019/08/16
 */
public class MapUtil {

    /**
     * Map是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }


    /**
     * Map是否为非空
     *
     * @param map 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return null != map && !map.isEmpty();
    }


    /**
     * 从键值序列的参数列表生成映射表
     *
     * @param args
     * @return
     */
    public static Map<String, Object> createHashMap(Object... args) {
        Map<String, Object> map = new HashMap<>();
        int length = args.length;
        for (int i = 0; i < length; i += 2) {
            map.put((String) args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * 将map2 添加到map1
     *
     * @param map1
     * @param map2
     */
    public static <T> void addMap(Map<T, Integer> map1, Map<T, Integer> map2) {
        addMap(map1, map2, 1);
    }


    public static <T> void addMap(Map<T, Integer> map1, Map<T, Integer> map2, int mulCount) {
        if (map1 == null || map2 == null || map2.isEmpty()) {
            return;
        }
        for (Map.Entry<T, Integer> map2Entry : map2.entrySet()) {
            Integer value = map2Entry.getValue();
            T key = map2Entry.getKey();
            Integer oldValue = map1.getOrDefault(key, 0);
            map1.put(key, MathUtil.increaseNumberInRange((oldValue + value) * mulCount, 0, Integer.MAX_VALUE));
        }
    }


}
