package com.game.service.message;

import com.game.common.meta.Result;

public interface MessageService
{
	Result sendMessage(int type, int model, String content);
}
