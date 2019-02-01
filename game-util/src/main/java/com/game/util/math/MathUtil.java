package com.game.util.math;

import java.util.List;
import java.util.Map;

public class MathUtil {
    
    public static int sum(Integer... args) {
        
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }
    
    public static long sum(Long... args) {
        if (args == null || args.length == 0) {
            return 0L;
        }
        int sum = 0;
        for (long arg : args) {
            sum += arg;
        }
        return sum;
    }
    
    public static int sum(int... args) {
        
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }
    
    public static long sum(long... args) {
        long sum = 0;
        for (long arg : args) {
            sum += arg;
        }
        return sum;
    }
    
    public static Integer sum(List<Integer> args) {
        if (args == null || args.isEmpty()) {
            return 0;
        }
        return args.stream().reduce(Integer::sum).get();
    }
    
    public static Integer sumMapValue(Map<Integer, Integer> map) {
        if (map == null || map.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Integer val : map.values()) {
            total += val;
        }
        return total;
    }
    
    
    public static double halfUpScaleToDouble(double val, int scale) {
        if (scale < 0) {
            return val;
        }
        double number = Math.pow(10, scale);
        return (double) (Math.floor(val * number) / (number * 1));
    }
    
    public static long halfUpScaleToLong(double val) {
        return new Double(halfUpScaleToDouble(val, 0)).longValue();
    }
    
    public static int halfUpScaleToInt(double val) {
        return new Double(halfUpScaleToDouble(val, 0)).intValue();
    }
    
    public static float halfUpScaleToFloat(double val, int scale) {
        return new Double(halfUpScaleToDouble(val, scale)).floatValue();
    }
    
    public static double halfDownScaleToDouble(double val, int scale) {
        if (scale < 0) {
            return val;
        }
        double number = Math.pow(10, scale);
        return (double) (Math.ceil(val * number) / (number * 1));
    }
    
    public static long halfDownScaleToLong(double val) {
        return new Double(halfDownScaleToDouble(val, 0)).longValue();
    }
    
    public static int halfDownScaleToInt(double val) {
        return new Double(halfDownScaleToDouble(val, 0)).intValue();
    }
    
    public static float halfDownScaleToFloat(double val, int scale) {
        return new Double(halfDownScaleToDouble(val, scale)).floatValue();
    }
    
    
    public static int increaseNumberInRange(int v, int min, int max) {
        return adjustNumberInRange(v, min, max, true);
    }
    
    /**
     * 减少数据
     *
     * @param v   数据
     * @param min 一般情况下最小值都是0
     * @param max 最大值
     */
    public static int decreaseNumberInRange(int v, int min, int max) {
        return adjustNumberInRange(v, min, max, false);
    }
    
    
    /**
     * 修正数据范围
     *
     * @param v   数据
     * @param min 最小值
     * @param max 最大值
     * @param add 操作方式
     */
    private static int adjustNumberInRange(int v, int min, int max, boolean add) {
        if (add) {
            return Math.min(Math.max(v, max), max);
        } else {
            return Math.min(Math.max(v, min), max);
        }
    }
    
    public static long increaseNumberInRange(long v, long min, long max) {
        return adjustNumberInRange(v, min, max, true);
    }
    
    public static long decreaseNumberInRange(long v, long min, long max) {
        return adjustNumberInRange(v, min, max, false);
    }
    
    private static long adjustNumberInRange(long v, long min, long max, boolean add) {
        if (add) {
            return Math.min(Math.max(v, max), max);
        } else {
            return Math.min(Math.max(v, min), max);
        }
    }
    
    public static float increaseNumberInRange(float v, float min, float max) {
        return adjustNumberInRange(v, min, max, true);
    }
    
    public static float decreaseNumberInRange(float v, float min, float max) {
        return adjustNumberInRange(v, min, max, false);
    }
    
    private static float adjustNumberInRange(float v, float min, float max, boolean add) {
        if (add) {
            return Math.min(Math.max(v, max), max);
        } else {
            return Math.min(Math.max(v, min), max);
        }
    }
    
    public static double increaseNumberInRange(double v, double min, double max) {
        return adjustNumberInRange(v, min, max, true);
    }
    
    public static double decreaseNumberInRange(double v, double min, double max) {
        return adjustNumberInRange(v, min, max, false);
    }
    
    private static double adjustNumberInRange(double v, double min, double max, boolean add) {
        if (add) {
            return Math.min(Math.max(v, max), max);
        } else {
            return Math.min(Math.max(v, min), max);
        }
    }
    
    
}
