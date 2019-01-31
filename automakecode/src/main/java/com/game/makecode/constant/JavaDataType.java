package com.game.makecode.constant;

import java.util.HashMap;
import java.util.Map;

public class JavaDataType {
    private static Map<String, Class> DATA_TYPE = new HashMap<>();

    static {
        DATA_TYPE.put("int", Integer.class);
        DATA_TYPE.put("Integer", Integer.class);
        DATA_TYPE.put("i", Integer.class);
        DATA_TYPE.put("I", Integer.class);

        DATA_TYPE.put("s", String.class);
        DATA_TYPE.put("S", String.class);
        DATA_TYPE.put("String", String.class);
        DATA_TYPE.put("string", String.class);

        DATA_TYPE.put("F", Float.class);
        DATA_TYPE.put("f", Float.class);
        DATA_TYPE.put("Float", Float.class);
        DATA_TYPE.put("float", Float.class);

        DATA_TYPE.put("d", Double.class);
        DATA_TYPE.put("D", Double.class);
        DATA_TYPE.put("double", Double.class);
        DATA_TYPE.put("Double", Double.class);

        DATA_TYPE.put("b", boolean.class);
        DATA_TYPE.put("B", boolean.class);
        DATA_TYPE.put("boolean", boolean.class);
        DATA_TYPE.put("Boolean", boolean.class);
    }

    public static boolean containsDataType(String dataType) {
        if (dataType == null || dataType.isEmpty()) {
            return false;
        }
        return DATA_TYPE.containsKey(dataType);
    }

    public static Class getDataType(String dataType) {
        return DATA_TYPE.get(dataType);
    }

}
