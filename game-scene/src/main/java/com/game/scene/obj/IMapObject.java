package com.game.scene.obj;

import com.sun.javafx.geom.Vec3f;

public interface IMapObject {
    
    long getId();
    
    void setId(long id);
    
    int getConfigId();
    
    void setConfigId(int configId);
    
    int getType();
    
    void setType(int type);
    
    boolean isVisible();
    
    void setVisible(boolean visible);
    
    Vec3f getPoint();
    
    void setPoint(Vec3f point);
    
    int getMapId();
    
    void setMapId(int mapId);
    
    int getLine();
    
    void setLine(int line);
    
    float getDir();
    
    void setDir(float dir);
    
    String getName();
    
    void setName(String name);
    
    /**
     * 是否可穿透
     */
    boolean penetrate(IMapObject obj);
    
    /**
     * 是否可重叠
     */
    boolean overlying(IMapObject obj);
    
    /**
     * 是否敌方
     */
    boolean isEnemy(IMapObject obj);
    
    /**
     * 是否友方
     */
    boolean isFriend(IMapObject obj);
    
    
}
