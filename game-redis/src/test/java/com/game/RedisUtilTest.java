package com.game;

import org.junit.Test;

import com.game.redis.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.List;

public class RedisUtilTest
{
	@Test
	public void testHset()
	{
		boolean hset = RedisUtil.hset("kolnick", "name", "11");
		System.out.println(hset);
	}

	@Test
	public void testCount() throws IOException
	{
		Jedis jedis = RedisUtil.getJedis();
		long l = System.currentTimeMillis();
		Pipeline pipelined = jedis.pipelined();
		for (int i = 0; i < 10000; i++)
		{
			Long mailId = jedis.incr("mailId");
		}
		pipelined.sync();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - l);
		pipelined.close();
		jedis.close();
	}

	@Test
	public void testCount2()
	{
		Jedis jedis = RedisUtil.getJedis();
		for (int i = 0; i < 4; i++)
		{
			jedis.lpush("dd", String.valueOf(i));
		}
		// Long member1 = jedis.llen("member");
		// List<String> member2 = jedis.lrange("member", 0, -1);
		// System.out.println(member2);
	}
}
