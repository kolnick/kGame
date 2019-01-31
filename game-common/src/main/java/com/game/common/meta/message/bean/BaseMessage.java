package com.game.common.meta.message.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class BaseMessage
{
	private Integer sendId;
	protected String content; // 内容
	protected int type;
	/**
	 * 模式 0-正常 1-战报分享 2-侦查战报分享 3-组队发起 4-请求援助 5-坐标分享 6-系统通知 7-喇叭
	 */
	protected int mode;
	protected long sendTime;// 发送时间
}
