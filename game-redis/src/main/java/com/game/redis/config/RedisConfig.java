package com.game.redis.config;

public class RedisConfig
{
	public static String IP;
	public static int PORT;
	public static int TIMEOUT;
	public static String SERIALIZE;

	public static int MAX_ACTIVE = -1;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	public static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	public static int MAX_WAIT = -1;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	public static boolean TEST_ON_BORROW = true;

	public static String PASSWORD;
	public static String MASTERNAME;
}
