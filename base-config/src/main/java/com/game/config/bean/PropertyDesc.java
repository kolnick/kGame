package com.game.config.bean;

import java.lang.reflect.Method;

public class PropertyDesc
{
	public static final PropertyDesc NULL = new PropertyDesc();

	private String name;

	private Class<?> propertyType;

	private Method writeMethod;

	private Method readMethod;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Class<?> getPropertyType()
	{
		return propertyType;
	}

	public void setPropertyType(Class<?> propertyType)
	{
		this.propertyType = propertyType;
	}

	public Method getWriteMethod()
	{
		return writeMethod;
	}

	public void setWriteMethod(Method writeMethod)
	{
		this.writeMethod = writeMethod;
	}

	public Method getReadMethod()
	{
		return readMethod;
	}

	public void setReadMethod(Method readMethod)
	{
		this.readMethod = readMethod;
	}
}
