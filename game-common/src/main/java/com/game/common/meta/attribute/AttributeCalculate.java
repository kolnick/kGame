package com.game.common.meta.attribute;

import com.game.common.meta.attribute.bean.Attribute;
import com.game.pub.player.Player;

public interface AttributeCalculate {

    Attribute calc(Player player);
}
