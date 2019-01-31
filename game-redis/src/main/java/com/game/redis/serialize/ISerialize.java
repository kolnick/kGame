package com.game.redis.serialize;

public interface ISerialize
{
	byte[] serialize(Object obj);

	<T> Object deSerialize(byte[] bytes, Class<T> t);

}
