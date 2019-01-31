package com.game.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.game.server.config.ServerConfig;

public class ServerOption
{

	public void build(String path)
	{
		// 加载配置文件
		Properties properties = new Properties();
		try
		{
			properties.load(new FileInputStream(path));
			ServerConfig.SERVER_ID = Integer.parseInt(properties.getProperty("serverId"));
			ServerConfig.GAME_SERVER_PORT = Integer.parseInt(properties.getProperty("gameServerPort"));
			ServerConfig.LOG_SERVER_PORT = Integer.parseInt(properties.getProperty("logServerPort"));
			ServerConfig.SERVER_TYPE = Integer.parseInt(properties.getProperty("serverType"));
			ServerConfig.API_MD5 = properties.getProperty("apiMd5");
			ServerConfig.CONFIG_DATA_PATH = properties.getProperty("configDataPath");
			ServerConfig.VERSION = properties.getProperty("version");
		}
		catch (IOException e)
		{
			throw new RuntimeException("服务器初始配置文件读取错误，启动失败......", e);
		}

	}
}
