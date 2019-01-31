package com.game.common.meta;

import com.game.pub.GameObject;

public class Result extends GameObject
{
	private int code; // 状态码
	private Object[] obj; // 数据

	public Result()
	{
	}

	public Result(int code)
	{
		this.code = code;
	}

	public static Result getResult(int code)
	{
		return new Result(code);
	}

	public Result(int code, Object... obj)
	{
		this.code = code;
		this.obj = obj;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public Object[] getObj()
	{
		return obj;
	}

	public void setObj(Object[] obj)
	{
		this.obj = obj;
	}

	public boolean isSuccess()
	{
		return this.code == StatusCode.SUCCESS ? true : false;
	}

}
