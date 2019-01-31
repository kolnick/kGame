package com.game.pub.player;

import com.game.pub.basic.IModel;

public interface IPlayer
{
	<T> IModel getModel(Class<? extends IModel> model);

	void registerModel(IModel model);
}
