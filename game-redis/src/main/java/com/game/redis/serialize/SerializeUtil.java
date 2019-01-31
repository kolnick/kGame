package com.game.redis.serialize;

public class SerializeUtil implements ISerialize
{
	public static ISerialize serialize;

	@Override
	public byte[] serialize(Object obj)
	{
		return serialize.serialize(obj);
	}

	@Override
	public <T> Object deSerialize(byte[] bytes, Class<T> t)
	{
		return serialize.deSerialize(bytes, t);
	}
}
