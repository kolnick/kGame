package com.game.engine.common.util.collection;


import com.ym.server.engine.common.util.collection.map.MapUtil;

import java.util.Collection;
import java.util.Map;

/**
 * 集合实用工具类
 *
 * @Author: caochaojie
 * @Date: 2019/3/18
 */
public class CollectionUtil {
    /**
     * 集合是否为空
     *
     * @param coll 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * Map是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtil.isEmpty(map);
    }

    /**
     * Map是否为非空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return MapUtil.isNotEmpty(map);
    }

    /**
     * 集合是否为非空
     *
     * @param collection 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断指定集合是否包含指定值，如果集合为空（null或者空），返回{@code false}，否则找到元素返回{@code true}
     *
     * @param collection 集合
     * @param value      需要查找的值
     * @return 如果集合为空（null或者空），返回{@code false}，否则找到元素返回{@code true}
     * @since 4.1.10
     */
    public static boolean contains(Collection<?> collection, Object value) {
        return isNotEmpty(collection) && collection.contains(value);
    }


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

}
