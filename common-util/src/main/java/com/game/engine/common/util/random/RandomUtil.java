package com.game.engine.common.util.random;


import com.ym.server.engine.common.util.collection.CollectionUtil;
import com.ym.server.engine.common.util.math.MathUtil;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    private static SecureRandom R = new SecureRandom();

    /**
     * 用于随机选的数字
     */
    public static final String BASE_NUMBER = "0123456789";
    /**
     * 用于随机选的小写字符a-z
     */
    public static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 用于随机选的大写字符A-Z
     */
    public static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 用于随机选的数字+小写字符
     */
    public static final String CHAR_NUMBER_LOWER_CASE = BASE_NUMBER + LOWER_CASE;

    /**
     * 用于随机选的数字+大写字符
     */
    public static final String CHAR_NUMBER_UPPER_CASE = BASE_NUMBER + UPPER_CASE;


    /**
     * 用于随机选的数字+大和小写字符
     */
    public static final String CHAR_NUMBER = BASE_NUMBER + UPPER_CASE + LOWER_CASE;

    /**
     * 获取随机数生成器对象<br>
     * ThreadLocalRandom是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获得随机数[0, 2^32)
     *
     * @return 随机数
     */
    public static int randomInt() {
        return getRandom().nextInt();
    }

    /**
     * 获取随机数产生器
     *
     * @param isSecure 是否为强随机数生成器 (RNG)
     */
    public static Random getRandom(boolean isSecure) {
        return isSecure ? getSecureRandom() : getRandom();
    }

    public static SecureRandom getSecureRandom() {
        return R;
    }

    /**
     * 获得随机数 in [min, max)
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public static int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(max - min) + min;
    }

    /**
     * 求[Min,Max]区间之间的随机整数。
     *
     * @param min 最小值
     * @param max 最大值
     * @return 一个Min和Max之间的随机整数
     */
    public static int randomNumber(int min, int max) {
        //如果相等，直接返回
        if (min >= max) {
            return min;
        }
        return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
    }


    /**
     * 按概率随机返回其中一个
     *
     * @param <T>
     * @param rewardPool 奖池Map，其中key是返回的具体对象，value是比率
     * @return
     */
    public static <T> T randomReward(Map<T, Integer> rewardPool) {
        int percentTotal = 0;
        for (Integer percent : rewardPool.values()) {
            percentTotal += percent;
        }

        int val = nextInt(0, percentTotal);
        int temp = 0;
        for (Map.Entry<T, Integer> entry : rewardPool.entrySet()) {
            if (val <= temp + entry.getValue()) {
                return entry.getKey();
            }
            temp += entry.getValue();
        }
        return null;
    }

    /**
     * 随机布尔值
     *
     * @return
     */
    public static boolean randomBoolean() {
        return R.nextBoolean();
    }

    /**
     * 获得随机数[0, max)
     *
     * @param max
     * @return
     */
    public static int randomNumber(final int max) {
        if (max == 0) {
            return 0;
        }
        return ThreadLocalRandom.current().nextInt(max);
    }

    /**
     * 圆桌概率(存在返回-1）
     *
     * @param probList
     * @param total
     * @return
     */
    public static int randomRoundTable(List<Integer> probList, int total) {
        int r = randomNumber(total);
        int p = 0;
        int idx = -1;
        for (int i = 0; i < probList.size(); ++i) {
            p += probList.get(i);
            if (p >= r) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    /**
     * 随机List中的元素
     *
     * @param srcList  源数据
     * @param probList 概率
     * @param count    数量
     */
    public static <T> List<T> randomList(List<T> srcList, List<Integer> probList,
                                         int count) {
        if (srcList == null || probList == null || srcList.size() != probList.size()) {
            return null;
        }
        List<T> retList = new ArrayList<>();
        int total = MathUtil.sum(probList);
        if (CollectionUtil.isEmpty(srcList) || count < 1 || total <= 0) {
            return retList;
        }
        for (int i = 0; i < count; ++i) {
            retList.add(srcList.get(randomRoundTable(probList, total)));
        }
        return retList;
    }

    /**
     * 根据每个元素给定的对应的概率 返回其击中的圆桌概率的元素
     *
     * @param srcList  源数据
     * @param probList 概率
     */
    public static <T> T randomList(List<T> srcList, List<Integer> probList) {
        int total = MathUtil.sum(probList);
        if (CollectionUtil.isEmpty(srcList) || total <= 0) {
            return null;
        }
        return srcList.get(randomRoundTable(probList, total));
    }

    /**
     * 随机List中一个元素
     *
     * @param srcList 源数据
     */
    public static <T> T randomList(List<T> srcList) {
        if (srcList != null && !srcList.isEmpty()) {
            int number = randomNumber(srcList.size());
            return srcList.get(number);
        }
        return null;
    }

    /**
     * 随机从数组中获取元素
     *
     * @param arr 数组
     * @param <T> 对象
     * @return 随机元素
     */
    public static <T> T randomArray(T[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        int index = randomNumber(length);
        return arr[index];
    }

    /**
     * 随机从Map中某取随机Key的值
     *
     * @param map Map集合
     * @param <T> Key
     * @param <V> Value
     * @return V
     */
    public static <T, V> V randomMap(Map<T, V> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Set set = map.keySet();
        Object[] objects = set.toArray();
        int i = randomNumber(objects.length);
        Object key = objects[i];
        return map.get(key);
    }

    /**
     * 从集合中随机一个元素
     *
     * @param collection 集合
     * @param <T>        对象
     * @return 随机元素
     */
    public static <T> T randomElement(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("集合不能为空！");
        }
        int index = randomNumber(collection.size());
        Iterator<T> it = collection.iterator();
        for (int i = 0; i <= index && it.hasNext(); i++) {
            T t = it.next();
            if (i == index) {
                return t;
            }
        }
        return null;
    }

    /**
     * 发生百分比概率
     *
     * @param propNumber 百分率
     * @return
     */
    public static boolean isRandomHappen100(int propNumber) {
        return propNumber > randomNumber(100);
    }

    /**
     * 万分比较概率
     *
     * @param propNumber
     * @return
     */
    public static boolean isRandomHappen10000(int propNumber) {
        return propNumber > randomNumber(10000);
    }

    public static String randomString(String str, int strLen) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        while (strLen-- > 0) {
            int i = randomNumber(length);
            char c = str.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String randomString(int length) {
        return randomString(CHAR_NUMBER, length);
    }

}
