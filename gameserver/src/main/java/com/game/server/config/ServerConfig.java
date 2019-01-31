package com.game.server.config;

public class ServerConfig
{
	/**
	 * 游戏端口
	 */
	public static int GAME_SERVER_PORT = 9010;

	/**
	 * 服务器ID
	 */
	public static int SERVER_ID = 1;

	/**
	 * 1= 游戏服 2=跨服服务器
	 */
	public static int SERVER_TYPE = 1;

	/**
	 * 日志服务器端口
	 */
	public static int LOG_SERVER_PORT = 9110;
	/**
	 * http服务器端口
	 */
	public static int HTTP_SERVER_PORT = 9110;

	/**
	 * 登录
	 */
	public static String API_MD5;

	/**
	 * 后台登录密钥
	 */
	public static String BACK_LOGIN_SIGN;

	/**
	 * 配置文件路径
	 */
	public static String CONFIG_DATA_PATH;
	/**
	 * 版本
	 */
	public static String VERSION;
	/**
	 * 配置文件目录路径
	 */
	public static String CONFIG_DICT_PATH;


}
