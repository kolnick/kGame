package com.game.util.random;

import com.game.util.math.MathUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author wangguoyun
 * @description 随机工具类
 * @date 2017年11月25日 上午11:12:46
 */
public class RandomUtil {
    
    private RandomUtil() {
    
    }
    
    private final static ThreadLocal<Random> THREAD_LOCAL_RANDOM = ThreadLocal.withInitial(() -> new Random(Thread.currentThread().getId()));
    
    private static char[] numbersAndLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final static String STRING_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    /**
     * 获取随机数
     *
     * @param min
     * @param max
     * @return Integer, null: when max < min
     */
    public static final Integer randomNumber(int min, int max) {
        int tmp = max - min;
        if (tmp < 0) {
            return null;
        } else if (tmp == 0) {
            return min;
        } else {
            return THREAD_LOCAL_RANDOM.get().nextInt(tmp + 1) + min;
        }
    }
    
    /**
     * 获取概率事件
     *
     * @param wildsArr 概率数组长度
     * @param num      返回结果个数
     * @return 选中结果（会有重复的）
     */
    public static final int[] randPropEvent(int[] wildsArr, int num) {
        int[] ret = new int[num];
        int len = wildsArr.length;
        for (int i = 0; i < num; i++) {
            int luckNum = THREAD_LOCAL_RANDOM.get().nextInt(len);
            System.arraycopy(wildsArr, luckNum, ret, i, 1);
        }
        return ret;
    }
    
    /**
     * 从[0-9] 和 [A-Z] 中随机定长的字符串
     *
     * @param length 生成字符串长度
     * @return
     */
    public static String randomString(int length) {
        if (length <= 0) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[THREAD_LOCAL_RANDOM.get().nextInt(numbersAndLetters.length - 1)];
        }
        return new String(randBuffer);
    }
    
    public static <T> T randomList(List<T> srcList, List<Integer> probList) {
        int total = MathUtil.sum(probList);
        if (CollectionUtils.isEmpty(srcList) || total <= 0) {
            return null;
        }
        return srcList.get(randomHitIndex(probList, total));
    }
    
    /**
     * 返回 圆桌概率(存在返回-1） 击中的下标索引位置
     *
     * @param probList 概率列表
     * @param total    总概率
     */
    public static int randomHitIndex(List<Integer> probList, int total) {
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
     * 返回0-max之间的随机数,包括0，不包括MAX
     *
     * @param max
     * @return
     */
    public final static int randomNumber(final int max) {
        if (max == 0) {
            return 0;
        }
        return THREAD_LOCAL_RANDOM.get().nextInt(max);
    }
    
    public static <T> List<T> randomList(List<T> srcList, List<Integer> probList,
                                         int count) {
        if (srcList == null || probList == null || srcList.size() != probList.size()) {
            return Collections.emptyList();
        }
        List<T> retList = new ArrayList<T>();
        int total = MathUtil.sum(probList);
        if (CollectionUtils.isEmpty(srcList) || count < 1 || total <= 0) {
            return retList;
        }
        for (int i = 0; i < count; ++i) {
            retList.add(srcList.get(randomHitIndex(probList, total)));
        }
        return retList;
    }
    
    public static Short randomShortSet(Set<Short> set) {
        if (set != null && !set.isEmpty()) {
            Short[] array = set.toArray(new Short[set.size()]);
            return array[randomNumber(set.size())];
        }
        return null;
    }
    
    /**
     * 随机返回set中的一个
     *
     * @param set
     * @return
     * @author kolnick
     */
    public static Integer randomIntegerSet(Set<Integer> set) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        Integer[] array = set.toArray(new Integer[set.size()]);
        return array[randomNumber(set.size())];
    }
    
    /**
     * 随机List
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T randomList(List<T> list) {
        if (list == null == list.isEmpty()) {
            return null;
        }
        return list.get(randomNumber(list.size()));
    }
    
    /**
     * 随机布尔值
     */
    public static boolean randomBoolean() {
        return THREAD_LOCAL_RANDOM.get().nextBoolean();
    }
    
    /**
     * 根据给定的字符串 生成指定长度的字符串
     *
     * @param str 给定字符串字符串
     * @param num 生成几个字符
     */
    public static String getRandomString(String str, int num) {
        if (str == null || str.length() < num) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int randIndex = randomNumber(str.length());
            char ch = str.charAt(randIndex);
            sb.append(ch);
        }
        return sb.toString();
    }
    
}
