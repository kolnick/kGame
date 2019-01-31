package com.game.config;

public interface IConfigData
{
	/**
	 * 数据加载完毕后的回调函数. 在实际使用中可以再具体的Config类中处理特殊的逻辑
	 */
	void afterLoad();
}
