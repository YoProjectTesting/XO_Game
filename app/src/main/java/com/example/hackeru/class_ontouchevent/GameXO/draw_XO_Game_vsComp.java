package com.example.hackeru.class_ontouchevent.GameXO;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.hackeru.class_ontouchevent.MainActivity_onTouch;
import com.example.hackeru.class_ontouchevent.my_point_XO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hackeru on 01/11/2015.
 */
public class draw_XO_Game_vsComp extends View {

    public static XO_background_task XO_back;
    float cx, cy ,fixedX,fixedY,centerX,centerY;
    int[][] sheet = new int[3][3]; int[] XY =new int []{-1,-1};
    int width, height, x, y;
    List<my_point_XO> list_of_points = new ArrayList<my_point_XO>();
    my_point_XO mp1;
    Paint[] paintPlayer = new Paint[2];
    static Random r = new Random();
    static int firstPlay ;
    int curentPlay;
    String apearWinner;Boolean WAIT,ToastWait;
    int compPlayerNum = 1;//Player-RED-1   vs   Computer-BLUE-5


    public draw_XO_Game_vsComp(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;

        XY =new int []{-1,-1};
        list_of_points = new ArrayList<my_point_XO>();
        firstPlay = r.nextInt(2);
        int curentPlay=firstPlay;
        apearWinner="false";WAIT=false;
        compPlayerNum = 1;//Player-RED-1   vs   Computer-BLUE-5
        ToastWait=false;

      //Paint Initialization
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
        XO_back=new XO_background_task();
        XO_back.execute();
        Log.d("debugTag", "   draw_XO_Game_vsComp constructor()  called   " + this.getClass() + "  line num  " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//sheet of Game
        {
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
        }

//filled sells
        try {

            for (int i = 0; i < list_of_points.size(); i++) {
                 centerX = list_of_points.get(i).getX();
                 centerY = list_of_points.get(i).getY();
                canvas.drawCircle(centerX, centerY, 100, list_of_points.get(i).getP());
            }


        } catch (Exception e) {
            Log.d("debugTag", this.getClass()+" line num " + Thread.currentThread().getStackTrace()[2].getLineNumber()+"  Msg: "+e.getMessage());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                cx = event.getX();
                cy = event.getY();
                if(!apearWinner.equals("false"))
                    {
                        GamePlayerVsPlayerEngine.clearGame(list_of_points, sheet);
                        apearWinner="false";
                        ToastWait=false;
                    }
                else {
                    if (cx > 0.05 * width && cx < 0.95 * width && cy > 0.15 * height && cy < 0.75 * height)
                        if (!WAIT) {
                            x = (int) ((cx - (0.05 * width)) / (0.30 * width));
                            y = (int) ((cy - (0.15 * height)) / (0.20 * height));
                            if (sheet[x][y] == 0) {
                                sheet[x][y] = (compPlayerNum == 1) ? 1 : 5;
                                Log.d("debugTag", "  PlayerNum  " + ((compPlayerNum == 1) ? 0 : 1) + " PlayerX " + x + "   PlayerY " + y + "    line num " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                                fixedX = (float) (x * (0.30 * width) + (0.20 * width));
                                fixedY = (float) (y * (0.20 * height) + (0.25 * height));
                                my_point_XO mp1 = new my_point_XO(fixedX, fixedY, paintPlayer[(compPlayerNum == 0) ? 1 : 0]);
                                list_of_points.add(mp1);
                                //change player
                                curentPlay = (curentPlay == 1) ? 0 : 1;
                            }
                        }
                }
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

    public class XO_background_task extends AsyncTask<String,String,String> {
        int version=0;

        @Override
        protected String doInBackground(String... params) {
            version++;
            if(true)
                while(!isCancelled()){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {

                        if(!(apearWinner=GamePlayerVsPlayerEngine.checkWinner(sheet)).equals("false")){
                            try {
                                WAIT=true;
                                Thread.sleep(500);
                                WAIT=false;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            publishProgress(apearWinner);
                        }else{

                            if(curentPlay==compPlayerNum){
                                try {
                                    WAIT=true;
                                    Thread.sleep(1000);
                                    WAIT=false;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                PlayerVsComputer.computerXO(sheet,compPlayerNum,XY);
                                sheet[XY[0]][XY[1]]=(compPlayerNum == 0) ? 1 : 5;
                                fixedX=(float)(XY[0]*(0.30 * width)+(0.20 * width));
                                fixedY=(float)(XY[1]*(0.20 * height)+(0.25 * height));
                                mp1 = new my_point_XO(fixedX, fixedY, paintPlayer[compPlayerNum]);
                                list_of_points.add(mp1);
                                Log.d("debugTag", "" + sheet[0][0] + sheet[1][0] + sheet[2][0]);
                                Log.d("debugTag", ""+sheet[0][1]+sheet[1][1]+sheet[2][1]);
                                Log.d("debugTag", ""+sheet[0][2]+sheet[1][2]+sheet[2][2]);
                                Log.d("debugTag", "  compPlayerNum BLUE(5)= " + compPlayerNum +"  firstPlay  " + firstPlay + " XY[0] " + XY[0] + "   XY[1] " +XY[1] +"    line num " + Thread.currentThread().getStackTrace()[2].getLineNumber()  );
                                curentPlay=(curentPlay == 1) ? 0 : 1;
                                publishProgress("false");
                            }
                            if(!(apearWinner=GamePlayerVsPlayerEngine.checkWinner(sheet)).equals("false")){
                                try {
                                    WAIT=true;
                                    Thread.sleep(500);
                                    WAIT=false;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                publishProgress(apearWinner);
                            }
                        }
                    } catch (Exception e) {
                        Log.d("debugTag", "  getMessage   " + e.getMessage()  + "   line num " + Thread.currentThread().getStackTrace()[2].getLineNumber()  );
                        e.printStackTrace();
                    }
                }
//            publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            try {
                if(!values[0].equals("false")&&!ToastWait){
                    switch(values[0]){
                        case "RED":
                            Log.d("debugTag", "line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " The RED player won!!! ");
                            Toast.makeText(getContext(), "The RED player won!!!", Toast.LENGTH_SHORT).show();
                            ToastWait=true;
                            break;
                        case "BLUE":
                            Log.d("debugTag", "line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " The BLUE player won!!! ");
                            Toast.makeText(getContext(), "The BLUE player won!!!", Toast.LENGTH_SHORT).show();
                            ToastWait=true;
                            break;
                        case "TEKO":
                            Log.d("debugTag", "line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " The TEKO!!! ");
                            Toast.makeText(getContext(), "The TEKO !!!", Toast.LENGTH_SHORT).show();
                            ToastWait=true;
                            break;
                    }
                }
                invalidate();
            } catch (Exception e) {
                Log.d("debugTag", "  getMessage   " + e.getMessage() + "   line num " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }


    }
}
