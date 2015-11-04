package com.example.hackeru.class_ontouchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 28/10/2015.
 */
public class my_draw extends View {

    public my_draw(Context context) {
        super(context);


    }

    float cx = 0, cy = 0;
    List<my_point> list_of_points = new ArrayList<my_point>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p1 = new Paint();
        p1.setColor(Color.RED);
        for (my_point mp1 : list_of_points) {
            cx = mp1.getX();
            cy = mp1.getY();
            canvas.drawCircle(cx, cy, 50, p1);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //mGestureDetector.onTouchEvent(event);

        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                cx = event.getX();
                cy = event.getY();
                my_point mp1 = new my_point(cx, cy);
                list_of_points.add(mp1);

                break;

            case MotionEvent.ACTION_UP:

                break;

            case MotionEvent.ACTION_CANCEL:

                break;

            case MotionEvent.ACTION_OUTSIDE:

                break;
        }
        invalidate();
        return true;
    }

}
