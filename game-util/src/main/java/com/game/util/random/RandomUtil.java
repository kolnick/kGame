package com.game.util.random;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;

import com.game.util.math.MathUtil;
import com.game.util.string.StringUtil;

/**
 * @author wangguoyun
 * @description 随机工具类
 * @date 2017年11月25日 上午11:12:46
 */
public class RandomUtil
{

	private RandomUtil()
	{

	}

	private final static ThreadLocal<Random> thead_local_random = new ThreadLocal<Random>()
	{
		@Override
		protected Random initialValue()
		{
			return new Random(Thread.currentThread().getId());
		}
	};

	private static char[] numbersAndLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final static String STRING_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * 获取随机数
	 *
	 * @param min
	 * @param max
	 * @return Integer, null: when max < min
	 */
	public static final Integer rand(int min, int max)
	{
		int tmp = max - min;
		if (tmp < 0)
		{
			return null;
		}
		else if (tmp == 0)
		{
			return min;
		}
		else
		{
			return thead_local_random.get().nextInt(tmp + 1) + min;
		}
	}

	/**
	 * 获取随机数
	 *
	 * @param min
	 * @param max
	 * @return Integer, null: when max < min
	 */
	public static final Long rand(long min, long max)
	{
		int tmp = Math.abs((int) (max - min));
		if (tmp < 0)
		{
			return null;
		}
		else if (tmp == 0)
		{
			return min;
		}
		else
		{
			return thead_local_random.get().nextInt(tmp + 1) + min;
		}
	}

	/**
	 * 获取随机数(double)
	 *
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param round
	 *            保留多少位小数（不能太大）
	 * @return Double, null: when max < min
	 */
	public static final Double rand(double min, double max, int round)
	{
		double dRound = Math.pow(10, round);
		int iMin = (int) (min * dRound);
		int iMax = (int) (max * dRound);
		Integer r = rand(iMin, iMax);
		if (r == null)
		{
			return null;
		}
		return r / dRound;
	}

	/**
	 * 获取概率事件，几率最多支持3位小数
	 *
	 * @param map
	 *            参数举例: Map(1=>20.1, 2=>29.9, 3=>50), 则20.1%几率返回1, 29.9%返回2,
	 *            50%返回3
	 * @return 返回键值
	 */
	public static final <T> T getRand(Map<T, Double> map)
	{
		int multiple = 1000; // 放大位数

		// 求和
		int sum = 0;
		Iterator<Entry<T, Double>> iter = map.entrySet().iterator();
		while (iter.hasNext())
		{
			Entry<T, Double> entry = iter.next();
			Double v = entry.getValue();
			sum += v * multiple;
		}

		if (sum <= 0)
		{
			return null;
		}

		// 产生0-sum的整数随机
		int luckNum = thead_local_random.get().nextInt(sum) + 1;
		int tmp = 0;
		iter = map.entrySet().iterator();
		while (iter.hasNext())
		{
			Entry<T, Double> entry = iter.next();
			Double v = entry.getValue();
			tmp += v * multiple;
			if (luckNum <= tmp)
			{
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * 获取概率事件，几率最多支持3位小数
	 *
	 * @param map
	 *            参数举例: HashMap(1=>20.1, 2=>29.9, 3=>50), 则20.1%几率返回1, 29.9%返回2,
	 *            50%返回3
	 * @return 返回键值
	 */
	public static final <T> T getRand(Set<T> map)
	{
		int multiple = 1000; // 放大位数
		// 求和
		int sum = map.size() * multiple;

		if (sum <= 0)
		{
			return null;
		}

		// 产生0-sum的整数随机
		int luckNum = thead_local_random.get().nextInt(sum) + 1;
		int tmp = 0;
		for (T one : map)
		{
			tmp += multiple;
			if (luckNum <= tmp)
			{
				return one;
			}
		}
		return null;
	}

	/**
	 * 从集合中随机一个元素
	 *
	 * @param map
	 * @return
	 * @date: 2016年4月12日 下午1:18:08
	 */
	public static final <T> T getRand(Collection<T> map)
	{
		int multiple = 1000; // 放大位数
		// 求和
		int sum = map.size() * multiple;

		if (sum <= 0)
		{
			return null;
		}

		// 产生0-sum的整数随机
		int luckNum = thead_local_random.get().nextInt(sum) + 1;
		int tmp = 0;
		for (T one : map)
		{
			tmp += multiple;
			if (luckNum <= tmp)
			{
				return one;
			}
		}
		return null;
	}

	/**
	 * 获取概率事件
	 *
	 * @param wildsArr
	 *            字符数组
	 * @param num
	 *            返回结果个数
	 * @return 选中结果（会有重复的）
	 */
	public static final int[] getRandByStrArray(int[] wildsArr, int num)
	{
		int[] ret = new int[num];
		int len = wildsArr.length;
		for (int i = 0; i < num; i++)
		{
			int luckNum = thead_local_random.get().nextInt(len);
			System.arraycopy(wildsArr, luckNum, ret, i, 1);
		}
		return ret;
	}

	/**
	 * 获取两个数之间的随机数，得出的值符合正态分布
	 *
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param factor
	 *            调整曲线参数
	 * @return
	 */
	public static final int gaussianRand(int min, int max, int factor)
	{
		if (min > max)
		{
			return 0;
		}
		int middle = (int) Math.ceil((min + max) / 2.0);
		int in = max - middle;
		factor = factor < 0 ? 0 : factor;
		LinkedHashMap<Integer, Double> map = new LinkedHashMap<Integer, Double>();
		for (int i = min; i <= max; i++)
		{
			if (i == middle)
			{
				map.put(i, (double) in);
			}
			else
			{
				double tmp = Math.abs(in - Math.abs(middle - i));
				tmp = tmp <= 0 ? 1 : tmp;
				tmp = factor > 0 ? tmp * (1 + factor / 100.0) : tmp;
				tmp = tmp > in ? in : tmp;
				map.put(i, tmp);
			}
		}
		return RandomUtil.getRand(map);
	}

	/**
	 * 在一组数据里面随机几个值
	 *
	 * @param array
	 *            随机的源数据
	 * @param num
	 *            获取数据个数
	 * @return
	 */
	public static <T> List<T> getRandByArray(T[] array, int num)
	{
		List<T> reslut = new ArrayList<>();
		List<T> temp = new ArrayList<>();
		for (T v : array)
		{
			temp.add(v);
		}

		if (num >= temp.size())
		{
			return temp;
		}

		for (int i = 0; i < num; i++)
		{
			int index = rand(0, temp.size() - 1);
			T value = (T) temp.get(index);
			if (!reslut.contains(value))
			{
				temp.remove(index);
				reslut.add(value);
			}
		}
		return reslut;
	}

	/**
	 * 从[0-9] 和 [A-Z] 中随机定长的字符串
	 *
	 * @param length
	 *            生成字符串长度
	 * @return
	 */
	public static String randomString(int length)
	{
		if (length <= 0)
		{
			return null;
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++)
		{
			randBuffer[i] = numbersAndLetters[thead_local_random.get().nextInt(numbersAndLetters.length - 1)];
		}
		return new String(randBuffer);
	}

	public static <T> T randomList(List<T> srcList, List<Integer> probs)
	{
		int total = MathUtil.sum(probs);
		if (CollectionUtils.isEmpty(srcList) || total <= 0)
		{
			return null;
		}
		return srcList.get(randomRoundTable(probs, total));
	}

	/**
	 * 圆桌概率(存在返回-1）
	 *
	 * @param probs
	 * @param total
	 * @return
	 */
	public static int randomRoundTable(List<Integer> probs, int total)
	{
		int r = randomNumber(total);
		int p = 0;
		int idx = -1;
		for (int i = 0; i < probs.size(); ++i)
		{
			p += probs.get(i);
			if (p >= r)
			{
				idx = i;
				break;
			}
		}
		return idx;
	}

	public final static int randomNumber(final int max)
	{
		if (max == 0)
			return 0;
		return thead_local_random.get().nextInt(max);
	}

	public static <T> List<T> randomList(List<T> srcList, List<Integer> probs,
			int count)
	{
		if (srcList == null || probs == null || srcList.size() != probs.size())
			return null;
		List<T> retList = new ArrayList<T>();
		int total = MathUtil.sum(probs);
		if (CollectionUtils.isEmpty(srcList) || count < 1 || total <= 0)
		{
			return retList;
		}
		for (int i = 0; i < count; ++i)
		{
			retList.add(srcList.get(randomRoundTable(probs, total)));
		}
		return retList;
	}

	/**
	 * 随机字符
	 *
	 * @param str
	 * @return
	 * @author kolnick
	 */
	public static String randomString(String str, int count)
	{
		if (StringUtil.isNullOrEmpty(str))
			return null;
		if (str.length() <= count)
			return str;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < count; i++)
		{
			int pos = random.nextInt(str.length());
			sb.append(str.charAt(pos));
		}
		return sb.toString();
	}

	public static Short randomShortSet(Set<Short> set)
	{
		if (set != null && !set.isEmpty())
		{
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
	public static Integer randomIntegerSet(Set<Integer> set)
	{
		if (set != null && !set.isEmpty())
		{
			Integer[] array = set.toArray(new Integer[set.size()]);
			return array[randomNumber(set.size())];
		}
		return null;
	}

	public static Object randomList(List<?> list)
	{
		if (list != null && !list.isEmpty())
		{
			int number = randomNumber(list.size());
			return list.get(number);
		}
		return null;
	}

	public static <T> Map<Integer, T> randomIntegerMap(Map<Integer, T> map,
			int ct)
	{
		// TODO impl
		return null;
	}

	public static boolean randomBoolean()
	{
		return thead_local_random.get().nextBoolean();
	}

	/**
	 * 根据给定的字符串 生成指定长度的字符串
	 *
	 * @param str
	 * @param num
	 * @return
	 * @author kolnick
	 */
	public static String getRandomString(String str, int num)
	{
		if (str == null || str.length() < num)
		{
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++)
		{
			int rdNum = randomNumber(str.length());
			char ch = str.charAt(rdNum);
			sb.append(ch);
		}
		return sb.toString();
	}

}
