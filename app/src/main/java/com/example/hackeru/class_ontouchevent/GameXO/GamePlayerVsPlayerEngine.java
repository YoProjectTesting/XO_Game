package com.example.hackeru.class_ontouchevent.GameXO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hackeru.class_ontouchevent.MainActivity_onTouch;
import com.example.hackeru.class_ontouchevent.my_point_XO;

import java.util.List;

/**
 * Created by Yoel on 03.11.2015.
 */
public class GamePlayerVsPlayerEngine {

    public static String checkWinner(int[][] sheet) {
        int counter=0;
        int[] sumArr = new int[8];
        sumArr[0] = sheet[0][0] + sheet[0][1] + sheet[0][2];
        sumArr[1] = sheet[1][0] + sheet[1][1] + sheet[1][2];
        sumArr[2] = sheet[2][0] + sheet[2][1] + sheet[2][2];
        sumArr[3] = sheet[0][0] + sheet[1][0] + sheet[2][0];
        sumArr[4] = sheet[0][1] + sheet[1][1] + sheet[2][1];
        sumArr[5] = sheet[0][2] + sheet[1][2] + sheet[2][2];
        sumArr[6] = sheet[0][0] + sheet[1][1] + sheet[2][2];
        sumArr[7] = sheet[2][0] + sheet[1][1] + sheet[0][2];
        for (int i = 0; i < sumArr.length; i++) {
            if (sumArr[i] == 3) return "RED";
            if (sumArr[i] == 15) return "BLUE";
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(sheet[i][j]==0)counter++;
            }
        }
        if (counter==0) return "TEKO"; return "false";
    }

    public static void clearGame(List<my_point_XO> list_of_points,int[][] sheet) {
        Log.d("debugTag", "line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " CLEARED ");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sheet[i][j]=0;
            }
        }
        list_of_points.removeAll(list_of_points);
        draw_XO_Game_vsComp.firstPlay=(draw_XO_Game_vsComp.firstPlay==0)?1:0;

    }


}


