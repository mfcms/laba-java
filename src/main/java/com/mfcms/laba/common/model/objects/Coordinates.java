package com.mfcms.laba.common.model.objects;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private float x;
    private Float y;

    // Required for JAXB
    @SuppressWarnings("unused")
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