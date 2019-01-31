package com.game.service.hero;

import com.game.common.meta.BaseService;
import com.game.common.meta.Result;
import com.game.common.meta.StatusCode;
import com.game.common.meta.progress.bean.Progress;
import com.game.common.meta.progress.constant.ProgressConstant;
import com.game.pub.player.Player;
import com.game.service.progress.ProgressManager;
import com.game.service.util.GameUtil;
import com.game.service.util.PlayerCountUtil;

public class HeroServiceImpl extends BaseService implements HeroSerivce
{
	public Result upgradeStar()
	{
		// 检测是否在升星中
		Player player = new Player();
		ProgressManager model = (ProgressManager) player.getModel(ProgressManager.class);
		int progressCountByType = model.getProgressCountByType(ProgressConstant.ProgressType.HERO_UPGRADE_STAR);
		// 检测队列是否超过最大
		if (progressCountByType > PlayerCountUtil.getProgressCount())
		{
			return getResult(StatusCode.ERROR);
		}
		Progress progress = GameUtil.addProgress(ProgressConstant.ProgressType.HERO_UPGRADE_STAR, 1);
		if (model.addProgress(progress))
		{
			return getResult(StatusCode.SUCCESS);
		}
		return getResult(StatusCode.ERROR);
	}

}
