package com.game.util.collection;

import com.game.util.math.MathUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caochaojie
 * @create 2018/5/18
 * @since 1.0.0
 */
public class CollectionUtil {
    
    /**
     * coll1 是否元素和coll2相同
     *
     * @param coll1
     * @param coll2
     * @return
     */
    public static boolean isEqualAll(final Collection<?> coll1, final Collection<?> coll2) {
        if (coll1 == null || coll2 == null || coll1.size() != coll2.size()) {
            return false;
        } else {
            for (Object next : coll2) {
                if (!coll1.contains(next)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    /**
     * coll1 是否包含 coll2
     *
     * @param coll1
     * @param coll2
     * @return
     */
    public static boolean isContainsAll(final Collection<?> coll1, final Collection<?> coll2) {
        if (coll1 == null || coll2 == null) {
            return false;
        } else {
            for (Object next : coll2) {
                if (!coll1.contains(next)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    /**
     * 从键值序列的参数列表生成映射表
     *
     * @param args
     * @return
     */
    public static Map<String, Object> createMap(Object... args) {
        if (args == null || args.length <= 0 || args.length % 2 > 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put((String) args[i], args[i + 1]);
        }
        return map;
    }
    
    /**
     * 将map2 添加到map1
     *
     * @param map1
     * @param map2
     * @Author cao chaojie
     */
    public static <T> void addMap(Map<T, Integer> map1, Map<T, Integer> map2) {
        if (map1 == null || map2 == null || map2.isEmpty()) {
            return;
        }
        for (Map.Entry<T, Integer> map2Entry : map2.entrySet()) {
            Integer value = map2Entry.getValue();
            T key = map2Entry.getKey();
            Integer oldValue = map1.getOrDefault(key, 0);
            map1.put(key, MathUtil.increaseNumberInRange(oldValue + value, 0, Integer.MAX_VALUE));
            
        }
    }
    
}
