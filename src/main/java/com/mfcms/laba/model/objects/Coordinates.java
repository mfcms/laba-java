package com.mfcms.laba.model.objects;

public class Coordinates {
    private float x;
    private Float y;

    private Coordinates() {}
    /**
     * 
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public Coordinates(float x, Float y) {
        assert y != null && y <= 808;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        assert y != null && y <= 808;
        this.y = y;
    }
}