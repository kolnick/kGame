package com.game.redis.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;

public class FastJsonSerialize implements ISerialize
{
	@Override
	public byte[] serialize(Object obj)
	{
		return JSON.toJSONBytes(obj, SerializeConfig.getGlobalInstance());
	}

	@Override
	public <T> Object deSerialize(byte[] bytes, Class<T> t)
	{
		return JSON.parseObject(bytes, t, null);
	}
}
