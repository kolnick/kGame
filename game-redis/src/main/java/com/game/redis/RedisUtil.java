package com.game.redis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.redis.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil
{
	private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

	private static JedisPool jedisPool = null;

	private static String redisFilePath = "redis.properties";

	static
	{
		loadConfig();
		Set<String> sentinels = new HashSet<String>();
		String addrs = RedisConfig.IP;
		for (String addr : addrs.split(","))
		{
			sentinels.add(addr);
		}

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(RedisConfig.MAX_ACTIVE);
		config.setMaxIdle(RedisConfig.MAX_IDLE);
		config.setMaxWaitMillis(RedisConfig.MAX_WAIT);
		config.setTestOnBorrow(RedisConfig.TEST_ON_BORROW);
		jedisPool = new JedisPool(config, RedisConfig.IP, RedisConfig.PORT, RedisConfig.TIMEOUT, null);
	}

	private static void loadConfig()
	{
		Properties properties = new Properties();
		try
		{
			properties.load(RedisUtil.class.getClassLoader().getResourceAsStream("redis.properties"));
			String ip = properties.getProperty("ip");
			String port = properties.getProperty("port");
			String timeout = properties.getProperty("timeout");
			String serialize = properties.getProperty("serialize");
			String masterName = properties.getProperty("masterName");
			String max_active = properties.getProperty("max_active");
			String max_idle = properties.getProperty("max_idle");
			String max_wait = properties.getProperty("max_wait");
			String test_on_borrow = properties.getProperty("test_on_borrow");
			String password = properties.getProperty("password");

			RedisConfig.IP = ip;
			RedisConfig.PORT = Integer.parseInt(port);
			RedisConfig.TIMEOUT = Integer.parseInt(timeout);
			RedisConfig.SERIALIZE = serialize;
			RedisConfig.MASTERNAME = masterName;
			RedisConfig.MAX_ACTIVE = Integer.valueOf(max_active);
			RedisConfig.MAX_IDLE = Integer.valueOf(max_idle);
			RedisConfig.MAX_WAIT = Integer.valueOf(max_wait);
			RedisConfig.TEST_ON_BORROW = Boolean.valueOf(test_on_borrow);
			RedisConfig.PASSWORD = password;

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis()
	{
		Jedis resource = null;
		try
		{
			if (jedisPool != null)
			{
				resource = jedisPool.getResource();
				return resource;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			log.error("获取Jedis实例失败:" + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	private static void returnResource(final Jedis jedis)
	{
		if (jedis != null)
		{
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 向redis里添加元素： key-value
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            要写入的内容
	 */
	public static void set(String key, String value)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.set(key, value);
		}
		catch (Throwable e)
		{
			log.error("set数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 向redis里添加元素： key-value
	 * 
	 * @param key
	 *            键值
	 * @param map
	 *            要写入的内容
	 */
	public static void set(String key, Map<String, String> map)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.hmset(key, map);
		}
		catch (Throwable e)
		{
			log.error("set Map数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 在key 对应 list的头部添加字符串元素 [如果key不存在，则新建list]
	 * 
	 * @param key
	 *            键值 要写入的内容
	 */
	public static void add(String key, String[] str)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.lpush(key, str);
		}
		catch (Throwable e)
		{
			log.error("set List数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 在指定key的value里追加数据，只有当这个列表已经存在
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            要追加的内容
	 */
	public static void append(String key, String value)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.append(key, value);
		}
		catch (Throwable e)
		{
			log.error("追加数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 在key 对应 list 的尾部添加字符串元素 [如果key不存在，则新建list]
	 * 
	 * @param key
	 *            键值
	 */
	public static void append(String key, String[] value)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.rpush(key, value);
		}
		catch (Throwable e)
		{
			log.error("追加List数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 获取key对应元素的value值
	 * 
	 * @param key
	 * @return String
	 */
	public static String get(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return "";
		}
		String value = "";
		try
		{
			value = jedis.get(key);
		}
		catch (Throwable e)
		{
			log.error("获取数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取key对应Map所有数据
	 * 
	 * @param key
	 * @return Map
	 */
	public static Map<String, String> getMap(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		Map<String, String> map = null;
		try
		{
			map = jedis.hgetAll(key);
		}
		catch (Throwable e)
		{
			log.error("获取Map数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return map;
	}

	/**
	 * 获取map里的所有key
	 * 
	 * @param key
	 *            map对应的键值
	 * @return Set
	 */
	public static Set<String> getMapKeys(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		Set<String> keys = null;
		try
		{
			keys = jedis.hkeys(key);
		}
		catch (Throwable e)
		{
			log.error("获取map里的所有key数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return keys;
	}

	/**
	 * 获取Map里对应filed列的值
	 * 
	 * @param key
	 * @param filed
	 * @return Map
	 */
	public static String getMapValue(String key, String filed)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		String value = "";
		try
		{
			value = jedis.hget(key, filed);
		}
		catch (Throwable e)
		{
			log.error("获取Map里对应filed列数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取key对应List列表中的start~end区间的元素，start起始值为：0，end 为-1时 表示取list列表所有数据
	 * 
	 * @param key
	 *            键值
	 * @param start
	 * @param end
	 * @return list
	 */
	public static List<String> getList(String key, int start, int end)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		List<String> list = null;
		try
		{
			list = jedis.lrange(key, start, end);
		}
		catch (Throwable e)
		{
			log.error("获取List列表数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return list;
	}

	/**
	 * 获取并移除列表中第一个元素
	 * 
	 * @param key
	 * @return List
	 */
	public static List<String> getListFirst(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		List<String> list = null;
		try
		{
			// timeout --> 10 s [阻塞时间]
			list = jedis.blpop(10, key);
		}
		catch (Throwable e)
		{
			log.error("获取并移除列表数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return list;
	}

	public static boolean hset(String key, String field, String value)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return false;
		}

		try
		{
			return jedis.hset(key, field, value) > 0;
		}
		catch (Throwable e)
		{
			log.error("设置数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return false;
	}

	/**
	 * 获取并移除列表中的最后一个元素
	 * 
	 * @param key
	 * @return List
	 */
	public static List<String> getListLast(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		List<String> list = null;
		try
		{
			// timeout --> 10 s [阻塞时间]
			list = jedis.brpop(10, key);
		}
		catch (Throwable e)
		{
			log.error("获取并移除列表数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return list;
	}

	/**
	 * 获取所有的key: " " return set空集 "*" return set集合 "h?llo" will match hello
	 * hallo hhllo 【单字符匹配】 "h*llo" will match hllo heeeello 【多字符匹配】 "h[ae]llo"
	 * will match hello and hallo, but not hillo 【好像是通过发音匹配】 预测返回数据量很大的情况下 不建议使用
	 * 
	 * @return Set
	 */
	public static Set<String> getKeys(String pattern)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		Set<String> set = null;
		try
		{
			set = jedis.keys(pattern);
		}
		catch (Throwable e)
		{
			log.error("获取所有的key:数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
		return set;
	}

	/**
	 * 删除指定key的元素，可删除多个[默认删除的数据库index为0]
	 * 
	 * @param keys
	 *            键值
	 */
	public static void del(String... keys)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.del(keys);
		}
		catch (Throwable e)
		{
			log.error("删除指定key数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 删除指定数据库的指定key的元素，可删除多个
	 * 
	 * @param index
	 *            数据库索引
	 * @param keys
	 *            键值
	 */
	public static void del(int index, String... keys)
	{
		Jedis jedis = select(index);
		try
		{
			jedis.del(keys);
		}
		catch (Throwable e)
		{
			log.error("删除指定数据库 key数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 选择redis数据库
	 * 
	 * @param index
	 *            数据库索引 return Jedis
	 */
	private static Jedis select(int index)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		try
		{
			jedis.select(index);
		}
		catch (Throwable e)
		{
			log.error("指定数据库失败：" + e.getMessage(), e);
		}
		return jedis;
	}

	/**
	 * 移除指定数据库的所有数据
	 * 
	 * @param index
	 *            数据库索引
	 */
	public static void removeData(int index)
	{
		Jedis jedis = select(index);
		try
		{
			jedis.flushDB();
		}
		catch (Throwable e)
		{
			log.error("移除指定数据库数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 移除redis服务下所有数据库的数据
	 */
	public static void removeData()
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.flushAll();
		}
		catch (Throwable e)
		{
			log.error("移除数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 指定key的值加1,如果key不存在，新加key 并且value为1
	 */
	public static void incr(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			String value = jedis.get(key);
			if (value != null && value.length() != 0)
			{
				jedis.incr(key);
			}
			else
			{
				jedis.set(key, "1");
			}

		}
		catch (Throwable e)
		{
			log.error("移除数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * @param key
	 * @param inrcValue
	 *            将指定的key 的值加上指定的增量值,key不存在默认为0
	 */
	public static void Incrby(String key, long inrcValue)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			String value = jedis.get(key);
			if (value != null && value.length() != 0)
			{
				jedis.incrBy(key, inrcValue);
			}
			else
			{
				jedis.set(key, inrcValue + "");
			}

		}
		catch (Throwable e)
		{
			log.error("移除数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 指定key的减1,如果key不存在，新加key 并且value为0
	 */
	public static void decr(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			String value = jedis.get(key);
			if (value != null && value.length() != 0)
			{
				jedis.decr(key);
			}
			else
			{
				jedis.set(key, "0");
			}

		}
		catch (Throwable e)
		{
			log.error("移除数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * @param key
	 * @param decrValue
	 *            将指定的key 的值加上指定的减量值,key不存在默认为0
	 */
	public static void decrBy(String key, long decrValue)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			String value = jedis.get(key);
			if (value != null && value.length() != 0)
			{
				jedis.decrBy(key, decrValue);
			}
			else
			{
				jedis.set(key, "0");
			}

		}
		catch (Throwable e)
		{
			log.error("移除数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	/**
	 * 向set集合中添加数据
	 * 
	 * @param key
	 * @param members
	 */
	public static void sadd(String key, String[] members)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return;
		}
		try
		{
			jedis.sadd(key, members);

		}
		catch (Throwable e)
		{
			log.error("移除数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}
	}

	public static Set<String> smembers(String key)
	{
		Jedis jedis = RedisUtil.getJedis();
		if (jedis == null)
		{
			log.warn("获取jedis实例失败");
			return null;
		}
		Set<String> set = null;
		try
		{
			set = jedis.smembers(key);

		}
		catch (Throwable e)
		{
			log.error("移除数据失败：" + e.getMessage(), e);
		}
		finally
		{
			returnResource(jedis);
		}

		return set;
	}

}
