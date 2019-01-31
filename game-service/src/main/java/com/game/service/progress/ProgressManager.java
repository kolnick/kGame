package com.game.service.progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.common.meta.progress.bean.Progress;
import com.game.pub.basic.IModel;

public class ProgressManager implements IModel
{
	private Map<Integer, List<Progress>> progressMap = new ConcurrentHashMap<>();

	// 添加队列

	// 删除队列 取消 操作比较少 list 全遍历 map 也是全遍历

	// 获取某个类型空闲队列

	// 获取某个类型队列有几个

	public boolean addProgress(Progress progress)
	{
		int type = progress.getType();
		List<Progress> progressList = progressMap.get(type);
		if (progressList == null)
		{
			progressList = new ArrayList<>();
			progressMap.put(type, progressList);
		}
		progressList.add(progress);
		return true;
	}

	public List<Progress> getProgressByType(int type)
	{
		return progressMap.get(type);
	}

	public int getProgressCountByType(int type)
	{
		List<Progress> progresses = progressMap.get(type);
		if (progresses == null)
			return 0;
		return (int) progresses.stream().filter(s -> !s.isComplete()).count();
	}
}
