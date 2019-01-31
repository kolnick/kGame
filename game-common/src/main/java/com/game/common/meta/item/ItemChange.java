package com.game.common.meta.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ItemChange
{
	private Map<Integer, Item> addItems = new HashMap<>();

	private Map<Integer, Item> updateItems = new HashMap<>();

	private Set<Long> removeItems = new HashSet<>();

	public void combine(ItemChange change)
	{
		addItems.putAll(change.addItems);
		updateItems.putAll(change.updateItems);
		removeItems.addAll(change.removeItems);
	}

}
