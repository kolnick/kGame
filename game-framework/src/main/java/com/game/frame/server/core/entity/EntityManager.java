package com.game.frame.server.core.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityManager {

    private static EntityManager INSTANCE = new EntityManager();
    private final Map<String, GameEntity> entityMap = new ConcurrentHashMap<>();        // 游戏中缓存对象


    private EntityManager() {

    }

    public static EntityManager getInstance() {
        return INSTANCE;
    }

    public void addEntity(GameEntity gameEntity) {
        String entityId = gameEntity.getEntityId();
        if (!entityMap.containsKey(entityId)) {
            entityMap.put(entityId, gameEntity);
        }
    }

    public GameEntity getGameEntity(String entity) {
        return entityMap.get(entity);
    }

}
