package com.example.hackeru.class_ontouchevent;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.example.hackeru.class_ontouchevent.GameXO.draw_XO_Game;
import com.example.hackeru.class_ontouchevent.GameXO.draw_XO_Game_vsComp;

public class MainActivity_onTouch extends AppCompatActivity {
    Bundle savedInstanceState;
    int width,height;
  public static  int flag=1;
    draw_XO_Game_vsComp XO_Game;
      @Override
  public void onBackPressed() {
        super.onBackPressed();
          XO_Game.XO_back.cancel(true);

          Log.d("debugTag", "   onBackPressed()  called   "+ this.getClass() + "  line num   " + Thread.currentThread().getStackTrace()[2].getLineNumber());

          this.finish();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("debugTag", "   onPostResume()  called   " + this.getClass() + "  line num   " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        XO_Game=new draw_XO_Game_vsComp(this,width, height);
        setContentView(XO_Game);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            this.savedInstanceState=savedInstanceState;
            Log.d("debugTag", "   onCreate()  called   " + this.getClass() + "   line num   " + Thread.currentThread().getStackTrace()[2].getLineNumber());

            Display ds1=getWindowManager().getDefaultDisplay();
            Point size=new Point();
            ds1.getSize(size);
            this.width=size.x;
            this.height=size.y;

/*            XO_Game=new draw_XO_Game_vsComp(this,width,height);
            setContentView(XO_Game);*/
        } catch (Exception e) {
            Log.d("debugTag", this.getClass() + " line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "  Msg: " + e.getMessage());
        }
    }
}
