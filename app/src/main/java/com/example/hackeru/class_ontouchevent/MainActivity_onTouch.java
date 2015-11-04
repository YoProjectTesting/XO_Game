package com.example.hackeru.class_ontouchevent;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.example.hackeru.class_ontouchevent.GameXO.draw_XO_Game;
import com.example.hackeru.class_ontouchevent.GameXO.draw_XO_Game_vsComp;

public class MainActivity_onTouch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            int width,height;
            Display ds1=getWindowManager().getDefaultDisplay();
            Point size=new Point();
            ds1.getSize(size);
            width=size.x;
            height=size.y;
//            my_draw m1=new my_draw(this);
//            draw_line m1=new draw_line(this);
//            draw_rectangle m1=new draw_rectangle(this);
//            draw_continue_lines m1=new draw_continue_lines(this);
//            setContentView(m1);


//            draw_XO_Game XO_Game=new draw_XO_Game(this,width,height);
            draw_XO_Game_vsComp XO_Game=new draw_XO_Game_vsComp(this,width,height);
            setContentView(XO_Game);
        } catch (Exception e) {
            Log.d("debugTag", this.getClass() + " line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "  Msg: " + e.getMessage());
        }
    }
}
