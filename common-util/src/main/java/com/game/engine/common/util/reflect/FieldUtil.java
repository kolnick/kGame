package com.game.engine.common.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class FieldUtil {
    /**
     * 类默认构造器
     *
     */
    private FieldUtil() {
    }

    /**
     * 获取泛型类型中的真实类型,
     * 例如 List&lt;Integer&gt; 类型, 最终会得到 Integer
     *
     * @param f
     * @return
     *
     */
    static public Type getGenericTypeA(Field f) {
        if (f == null) {
            // 如果参数对象为空,
            // 则直接退出!
            return null;
        }

        if (!(f.getGenericType() instanceof ParameterizedType)) {
            // 如果不是泛型类型,
            // 则直接退出!
            return null;
        }

        // 获取泛型参数
        ParameterizedType tType = (ParameterizedType)f.getGenericType();

        if (tType.getActualTypeArguments().length <= 0) {
            // 如果泛型参数太少,
            // 则直接退出!
            return null;
        }

        return tType.getActualTypeArguments()[0];
    }
}
