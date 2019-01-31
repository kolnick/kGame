package com.game;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.game.bean.Person;
import org.junit.Test;

public class FastJsonTest
{

	@Test
	public void test()
	{
		Person p = new Person();
		p.setName("dada");
		p.setId(131);
		byte[] bytes = JSON.toJSONBytes(p, SerializeConfig.getGlobalInstance());

		Person o = JSON.parseObject(bytes, Person.class, null);
		System.out.println(o);
	}
}
