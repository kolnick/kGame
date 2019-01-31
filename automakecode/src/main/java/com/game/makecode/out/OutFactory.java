package com.game.makecode.out;

import com.game.makecode.constant.OutType;
import com.game.makecode.out.handler.CsvOutHandler;
import com.game.makecode.out.handler.IOutHandler;
import com.game.makecode.out.handler.JavaOutHandler;

import java.util.HashMap;
import java.util.Map;

public class OutFactory {

    private static Map<Integer, IOutHandler> outMap = new HashMap<>();

    static {
        outMap.put(OutType.CSV, new CsvOutHandler());
        outMap.put(OutType.JAVA, new JavaOutHandler());

    }
}
