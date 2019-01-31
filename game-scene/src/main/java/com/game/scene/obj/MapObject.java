package com.game.scene.obj;

import com.sun.javafx.geom.Vec3f;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public abstract class MapObject implements IMapObject {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MapObject.class);
    
    /**
     * 实例ID
     */
    protected long id;
    
    /**
     * 配表ID
     */
    protected int configId;
    
    /**
     * 类型
     */
    protected int type;
    
    /**
     * 是否可见
     */
    protected boolean visible = true;
    
    /**
     * 所在坐标点
     */
    protected Vec3f point;
    
    /**
     * 地图ID
     */
    protected int mapId;
    
    /**
     * 地图分线ID
     */
    protected int line = 1;
    
    /**
     * 对象方向
     */
    protected float dir;
    
    /**
     * 名字
     */
    protected String name = "";
    
    @Override
    public boolean penetrate(IMapObject obj) {
        return false;
    }
    
    @Override
    public boolean overlying(IMapObject obj) {
        return false;
    }
    
    @Override
    public boolean isEnemy(IMapObject obj) {
        return false;
    }
    
    @Override
    public boolean isFriend(IMapObject obj) {
        return false;
    }
}
