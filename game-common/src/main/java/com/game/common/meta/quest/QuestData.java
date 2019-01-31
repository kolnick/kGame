package com.game.common.meta.quest;

import java.util.Map;

/**
 * @author caochaojie
 * 2018/12/18 10:07
 */
public class QuestData {
    // 任务ID-进度
    private Map<Integer, Integer> data;
    // 是否已完成
    private boolean complete;

    public Map<Integer, Integer> getData() {
        return data;
    }

    public void setData(Map<Integer, Integer> data) {
        this.data = data;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
