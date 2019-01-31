package com.game.service.message;

import com.game.common.meta.Result;

public class MessageServiceImpl implements MessageService
{
	private static MessageServiceImpl INSTANCE = new MessageServiceImpl();

	private MessageServiceImpl()
	{

	}

	public static MessageServiceImpl getInstance()
	{
		return INSTANCE;
	}

	@Override
	public Result sendMessage(int type, int model, String content)
	{
		return null;
	}
}
