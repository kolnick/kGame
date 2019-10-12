package com.game.engine.common.util.math;

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
        return args.stream().mapToInt(Integer::intValue).sum();
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

    public static int increaseNumberInRange(int v, int min, int max) {
        return adjustNumberInRange(v, min, max, true);
    }

    public static int decreaseNumberInRange(int v, int min, int max) {
        return adjustNumberInRange(v, min, max, false);
    }

    private static int adjustNumberInRange(int v, int min, int max, boolean add) {
        return add ? Math.min(Math.max(v, min), max) : Math.min(Math.max(v, min), max);
    }

    public static long increaseNumberInRange(long v, long min, long max) {
        return adjustNumberInRange(v, min, max, true);
    }

    public static long decreaseNumberInRange(long v, long min, long max) {
        return adjustNumberInRange(v, min, max, false);
    }

    private static long adjustNumberInRange(long v, long min, long max, boolean add) {
        return add ? Math.min(Math.max(v, max), max) : Math.min(Math.max(v, min), max);
    }

    public static float increaseNumberInRange(float v, float min, float max) {
        return adjustNumberInRange(v, min, max, true);
    }

    public static float decreaseNumberInRange(float v, float min, float max) {
        return adjustNumberInRange(v, min, max, false);
    }

    private static float adjustNumberInRange(float v, float min, float max, boolean add) {
        return add ? Math.min(Math.max(v, max), max) : Math.min(Math.max(v, min), max);
    }

    public static double increaseNumberInRange(double v, double min, double max) {
        return adjustNumberInRange(v, min, max, true);
    }

    public static double decreaseNumberInRange(double v, double min, double max) {
        return adjustNumberInRange(v, min, max, false);
    }

    private static double adjustNumberInRange(double v, double min, double max, boolean add) {
        return add ? Math.min(Math.max(v, max), max) : Math.min(Math.max(v, min), max);
    }

    public static double halfUpScaleToDouble(double val, int scale) {
        if (scale < 0) {
            return val;
        }
        double number = Math.pow(10, scale);
        return Math.floor(val * number) / (number * 1);
    }

    public static long halfUpScaleToLong(double val) {
        return new Double(halfUpScaleToDouble(val, 0)).longValue();
    }

    public static long halfRoundScaleToLong(long val) {
        return new Double(halfRoundToDouble(val, 0)).longValue();
    }

    public static int halfUpScaleToInt(double val) {
        return new Double(halfUpScaleToDouble(val, 0)).intValue();
    }

    public static int halfUpScaleToInt(double val, int scale) {
        return new Double(halfUpScaleToDouble(val, scale)).intValue();
    }

    public static int halfRoundScaleToInt(double val) {
        return new Double(halfRoundToDouble(val, 0)).intValue();
    }

    public static int halfRoundScaleToInt(double val, int scale) {
        return new Double(halfRoundToDouble(val, scale)).intValue();
    }

    public static float halfUpScaleToFloat(double val, int scale) {
        return new Double(halfUpScaleToDouble(val, scale)).floatValue();
    }

    public static float halfRoundScaleToFloat(double val, int scale) {
        return new Double(halfRoundToDouble(val, scale)).floatValue();
    }

    public static double halfDownScaleToDouble(double val, int scale) {
        if (scale < 0) {
            return val;
        }
        double number = Math.pow(10, scale);
        return (Math.ceil(val * number) / (number * 1));
    }


    public static long halfDownScaleToLong(double val) {
        return new Double(halfDownScaleToDouble(val, 0)).longValue();
    }

    public static int halfDownScaleToInt(double val) {
        return new Double(halfDownScaleToDouble(val, 0)).intValue();
    }

    public static int halfDownScaleToInt(double val, int scale) {
        return new Double(halfDownScaleToDouble(val, scale)).intValue();
    }

    public static float halfDownScaleToFloat(double val, int scale) {
        return new Double(halfDownScaleToDouble(val, scale)).floatValue();
    }

    private static float toFloat(int num) {
        return (float) (num * 1.0);
    }

    /**
     * 转换百分比
     *
     * @param num 数字
     */
    public static float toBaiPercentage(int num) {
        return halfUpScaleToFloat(toFloat(num) / 100, 2);
    }

    /**
     * 转换百分比
     *
     * @param num 数字
     */
    public static float toBaiPercentage(float num) {
        return halfUpScaleToFloat(num, 2);
    }

    /**
     * 转换成千分比
     *
     * @param baseNum
     * @param reduceNum
     * @return
     */
    public static int toQianPercentageReduce(float baseNum, float reduceNum) {
        return reduceNum >= 1000 ? 0 : halfRoundScaleToInt(baseNum * (1000 * 1.0 - reduceNum) / 1000);
    }

    /**
     * 转换万分比
     *
     * @param num 数字
     */
    public static float toQianPercentage(int num) {
        return halfUpScaleToFloat(toFloat(num) / 1000, 3);
    }

    public static float toQianPercen(float num) {
        return halfUpScaleToFloat(num, 3);
    }

    public static float toQianPercen(double num) {
        return halfUpScaleToFloat(num, 3);
    }

    public static float toQianPercentage(double num) {
        return halfUpScaleToFloat(num / 1000, 3);
    }

    public static float toQianPercentageAdd(float num, float addNum) {
        return halfUpScaleToFloat(num * (1000 * 1.0 + addNum) / 1000, 3);
    }

    public static double halfRoundToDouble(double val, int scale) {
        if (scale < 0) {
            return val;
        } else {
            double number = Math.pow(10, (double) scale);
            return Math.round(val * number) / (number * 1);
        }
    }


    /**
     * 转换成分比
     *
     * @param baseNum 基础值
     * @param addNum  增加的万分比
     * @return
     */
    public static int toQianPercentage(int baseNum, int addNum) {
        return halfUpScaleToInt(baseNum * (1000 * 1.0 + addNum) / 1000);
    }

    /**
     * 转换万分比
     *
     * @param num 数字
     */
    public static float toWanPercentage(int num) {
        return halfUpScaleToFloat(toFloat(num) / 10000, 3);
    }


    public static float toWanPercentage(float num) {
        return halfUpScaleToFloat(num / 10000, 3);
    }

    public static float toWanPercentage(float num, float addNum) {
        return halfUpScaleToFloat(num * (10000 * 1.0 + addNum) / 10000, 3);
    }

    /**
     * 转换成万分比
     *
     * @param baseNum 基础值
     * @param addNum  增加的万分比
     * @return
     */
    public static int toWanPercentage(int baseNum, int addNum) {
        return halfUpScaleToInt(baseNum * (10000 * 1.0 + addNum) / 10000);
    }

    /**
     * 转换成万分比
     *
     * @param baseNum
     * @param reduceNum
     * @return
     */
    public static int toWanPercentageReduce(int baseNum, int reduceNum) {
        return reduceNum >= 10000 ? 0 : halfUpScaleToInt(baseNum * (10000 * 1.0 - reduceNum) / 10000);
    }

    /**
     * 转换成万分比
     *
     * @param baseNum
     * @param reduceNum
     * @return
     */
    public static int toWanPercentageReduce(int baseNum, float reduceNum) {
        return reduceNum >= 10000 ? 0 : halfUpScaleToInt(baseNum * (10000 * 1.0 - reduceNum) / 10000);
    }

    /**
     * 转换成万分比
     *
     * @param baseNum 基础值
     * @param addNum  增加的万分比
     * @return
     */
    public static int toWanPercentage(int baseNum, float addNum) {
        return halfUpScaleToInt((baseNum * (10000 * 1.0 + addNum) / 10000), 3);
    }
}
