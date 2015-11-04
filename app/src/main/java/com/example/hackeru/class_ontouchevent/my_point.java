package com.example.hackeru.class_ontouchevent;

/**
 * Created by hackeru on 01/11/2015.
 */
public class my_point {
    private float x;
    private float y;

    public my_point(float x, float y) {
        this.y = y;
        this.x = x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {

        return y;
    }

    public float getX() {
        return x;
    }
}
