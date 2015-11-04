package com.example.hackeru.class_ontouchevent;

import android.graphics.Paint;

/**
 * Created by hackeru on 01/11/2015.
 */
public class my_point_XO {
    private float x;
    private float y;
    private Paint p;

    public my_point_XO(float x, float y, Paint p) {
        this.y = y;
        this.x = x;

        this.p = p;
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

    public Paint getP() {
        return p;
    }
}
