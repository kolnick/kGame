package com.game.pub.player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.pub.basic.IModel;

public abstract class BasePlayer implements IPlayer
{
	private Map<String, IModel> models = new ConcurrentHashMap<>();

	@Override
	public <T> IModel getModel(Class<? extends IModel> model)
	{
		return models.get(model.getName());
	}

	@Override
	public void registerModel(IModel model)
	{
		models.put(model.getClass().getName(), model);
	}
}
