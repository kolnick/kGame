package com.game.scene.meta;

public class Point {
    
    private final static byte BLOCK_FLAG = 0b00000001;
    
    private final static byte SAFE_FLAG = 0b00000010;
    
    public int x;
    
    public int y;
    
    /**
     * 阻挡,安全区等统一保存
     */
    private byte flag;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(int id) {
        this.x = id >>> 16;
        this.y = id & 0xFFFF;
    }
}
