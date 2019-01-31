package com.game.pub.basic;

import java.util.List;

public class ModelManager {

    public final static ModelManager instance = new ModelManager();

    private ModelManager() {
    }

    public static ModelManager getInstance() {
        return instance;
    }

    private List<IModel> modelList;

    public void start() {

        registerModel();
    }

    private void registerModel() {

    }

}
