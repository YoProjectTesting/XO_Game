package com.example.hackeru.class_ontouchevent.GameXO;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.hackeru.class_ontouchevent.my_point_XO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hackeru on 01/11/2015.
 */
public class draw_XO_Game extends View {


    float cx, cy ,fixedX,fixedY;
    int[][] sheet = new int[3][3];
    int width, height, x, y;
    List<my_point_XO> list_of_points = new ArrayList<my_point_XO>();
    Paint[] paintPlayer = new Paint[2];
    Random r = new Random();
    int nextPlay = r.nextInt(1);
    int firstPlay=nextPlay;

    public draw_XO_Game(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;

        paintPlayer[0] = new Paint();
        paintPlayer[0].setColor(Color.RED);
        paintPlayer[1] = new Paint();
        paintPlayer[1].setColor(Color.BLUE);
      //Game Sheet Initialization
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sheet[i][j]=0;
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//sheet of Game

        Paint gameSheet = new Paint();
        gameSheet.setColor(Color.RED);
        gameSheet.setStrokeWidth(10);
        //external
        canvas.drawLine((float) 0.05 * width, (float) 0.15 * height, (float) 0.05 * width, (float) 0.75 * height, gameSheet);
        canvas.drawLine((float) 0.05 * width, (float) 0.15 * height, (float) 0.95 * width, (float) 0.15 * height, gameSheet);
        canvas.drawLine((float) 0.95 * width, (float) 0.15 * height, (float) 0.95 * width, (float) 0.75 * height, gameSheet);
        canvas.drawLine((float) 0.05 * width, (float) 0.75 * height, (float) 0.95 * width, (float) 0.75 * height, gameSheet);
        //internal
        canvas.drawLine((float) 0.35 * width, (float) 0.15 * height, (float) 0.35 * width, (float) 0.75 * height, gameSheet);
        canvas.drawLine((float) 0.65 * width, (float) 0.15 * height, (float) 0.65 * width, (float) 0.75 * height, gameSheet);
        canvas.drawLine((float) 0.05 * width, (float) 0.35 * height, (float) 0.95 * width, (float) 0.35 * height, gameSheet);
        canvas.drawLine((float) 0.05 * width, (float) 0.55 * height, (float) 0.95 * width, (float) 0.55 * height, gameSheet);


//filled sells

        for (int i = 0; i < list_of_points.size(); i++) {
            float centerX = list_of_points.get(i).getX();
            float centerY = list_of_points.get(i).getY();
            canvas.drawCircle(centerX, centerY, 100, list_of_points.get(i).getP());
        }
        if(GamePlayerVsPlayerEngine.checkWinner(sheet, getContext())){
        /*  try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            GamePlayerVsPlayerEngine.clearGame(list_of_points, sheet,firstPlay);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                cx = event.getX();
                cy = event.getY();
                x = (int) ((cx - (0.05 * width)) / (0.30 * width));
                y = (int) ((cy - (0.15 * height)) / (0.20 * height));
                if(sheet[x][y]==0){
                    sheet[x][y]=(nextPlay ==0) ? 1 : 5;

                    fixedX=(float)(x*(0.30 * width)+(0.20 * width));
                    fixedY=(float)(y*(0.20 * height)+(0.25 * height));
                    my_point_XO mp1 = new my_point_XO(fixedX, fixedY, paintPlayer[nextPlay]);
                    list_of_points.add(mp1);
                    //change player
                    nextPlay = (nextPlay == 1) ? 0 : 1;
                }
                Log.d("debugTag", ""+sheet[0][0]+sheet[1][0]+sheet[2][0]);
                Log.d("debugTag", ""+sheet[0][1]+sheet[1][1]+sheet[2][1]);
                Log.d("debugTag", ""+sheet[0][2]+sheet[1][2]+sheet[2][2]);


                Log.d("debugTag", "line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " nextPlay " + nextPlay );
                Log.d("debugTag", "line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " x " + x + "   y " + y);

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