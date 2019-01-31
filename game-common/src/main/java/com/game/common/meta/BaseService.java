package com.game.common.meta;

import com.game.util.service.Service;

public abstract class BaseService implements Service
{
	public Result getResult(int code)
	{
		return new Result(code);
	}

	public Result getResult(Result result, int code)
	{
		if (result == null)
		{
			return new Result(code);
		}
		result.setCode(code);
		return result;
	}

	public Result getResult(Result result, int code, Object... objs)
	{
		if (result == null)
		{
			return new Result(code, objs);
		}
		result.setCode(code);
		return result;
	}

	public Result getResult(int code, Object... obj)
	{
		return new Result(code, obj);
	}

	@Override
	public boolean start()
	{
		return false;
	}

	@Override
	public boolean stop()
	{
		return false;
	}
}
