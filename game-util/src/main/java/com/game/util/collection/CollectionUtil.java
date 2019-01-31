package com.game.util.collection;

import java.util.*;

import com.game.util.math.MathUtil;

/**
 * 〈${DESCRIPTION}〉
 *
 * @author caochaojie
 * @create 2018/5/18
 * @since 1.0.0
 */
public class CollectionUtil {

    /**
     * 是否元素全相等
     *
     * @param coll1 不能为空
     * @param coll2 不能为空
     * @return boolean
     */
    public static boolean equalsAll(final List<?> coll1, final List<?> coll2) {
        if (coll1 == null || coll2 == null || coll1.size() != coll2.size()) {
            return false;
        } else {
            Iterator<?> it = coll1.iterator();
            while (it.hasNext()) {
                if (!coll2.contains(it.next())) {
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
        Map<String, Object> map = new HashMap();
        for (int i = 0; i < args.length; i += 2) {
            map.put((String) args[i], args[i + 1]);
        }
        return map;
    }

    public static <T> Map<T, Integer> createMapByIntegerValueList(
            List<T> keyList, List<Integer> valueList) {
        if (keyList == null || valueList == null || keyList.size() != valueList.size()) {
            return null;
        }
        int size = keyList.size();
        Map<T, Integer> map = new HashMap();
        for (int i = 0; i < size; i++) {
            T key = keyList.get(i);
            Integer val = valueList.get(i);
            Integer oldValue = map.getOrDefault(key, 0);
            map.put(key, oldValue + val);
        }
        return map;
    }

    public static <T> Map<T, Long> createMapByLongValueList(List<T> keyList,
                                                            List<Long> valueList) {
        if (keyList == null || valueList == null || keyList.size() != valueList.size()) {
            return null;
        }
        int size = keyList.size();
        Map<T, Long> map = new HashMap();
        for (int i = 0; i < size; i++) {
            T key = keyList.get(i);
            Long val = valueList.get(i);
            Long oldValue = map.getOrDefault(key, 0l);
            map.put(key, oldValue + val);
        }
        return map;
    }

    public static <T> List<T> createList(List<T> args) {
        List<T> list = new ArrayList<T>();
        for (T arg : args) {
            list.add(arg);
        }
        return list;
    }

    public static List<Integer> createList(List<Integer> args, Integer mul) {
        List<Integer> list = new ArrayList<Integer>();
        for (Integer arg : args) {
            list.add(MathUtil.adjustNumberInRange(arg * mul, 0, Integer.MAX_VALUE, true));
        }
        return list;
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
            map1.put(key, MathUtil.adjustNumberInRange(oldValue + value, 0, Integer.MAX_VALUE, true));
        }
    }

}
