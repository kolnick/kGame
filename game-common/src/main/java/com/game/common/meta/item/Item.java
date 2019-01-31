package com.game.common.meta.item;

import lombok.Data;

@Data
public class Item
{
	private long rid;

	private int id;

	private int count;

	@Override
	public String toString()
	{
		return "Item{" + "rid=" + rid + ", id=" + id + ", count=" + count + '}';
	}
}
