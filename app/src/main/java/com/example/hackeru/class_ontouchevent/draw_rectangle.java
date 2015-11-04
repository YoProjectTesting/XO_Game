package com.example.hackeru.class_ontouchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackeru on 01/11/2015.
 */
public class draw_rectangle extends View {

    public draw_rectangle(Context context) {
        super(context);
    }
    int length;
    float cx1,cy1,cx2,cy2;
    List<my_point> list_of_points=new ArrayList<my_point>();
    List<Paint> list_of_Paint=new ArrayList<Paint>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        length=list_of_points.size()-(list_of_points.size()%2);

        if(list_of_points.size()%4==0){
            Paint newP=new Paint();
            newP.setColor(Color.RED);
            newP.setStrokeWidth(10);
            list_of_Paint.add(newP);
        }


        for (int i = 0; i < length; i++) {
           if(i%4==3){

               canvas.drawLine(list_of_points.get(i).getX(), list_of_points.get(i).getY(), list_of_points.get(i - 3).getX(), list_of_points.get(i - 3).getY(), list_of_Paint.get((i - 3) / 4));
               canvas.drawLine(list_of_points.get(i - 3).getX(), list_of_points.get(i - 3).getY(), list_of_points.get(i - 2).getX(), list_of_points.get(i - 2).getY(), list_of_Paint.get((i - 3) / 4));
               canvas.drawLine(list_of_points.get(i - 2).getX(), list_of_points.get(i - 2).getY(), list_of_points.get(i-1).getX(), list_of_points.get(i-1).getY(), list_of_Paint.get((i - 3) /4));
               canvas.drawLine(list_of_points.get(i - 1).getX(), list_of_points.get(i - 1).getY(), list_of_points.get(i).getX(), list_of_points.get(i).getY(), list_of_Paint.get((i - 3) /4));

           }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                cx1 = event.getX();
                cy1 = event.getY();
                my_point mp1=new my_point(cx1,cy1);
                list_of_points.add(mp1);

                break;

            case MotionEvent.ACTION_MOVE:


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
