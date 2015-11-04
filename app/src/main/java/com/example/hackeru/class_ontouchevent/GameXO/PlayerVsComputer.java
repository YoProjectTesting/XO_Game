package com.example.hackeru.class_ontouchevent.GameXO;

import android.util.Log;

import java.util.Random;

/**
 * Created by Yoel on 03.11.2015.
 */
public class PlayerVsComputer {

    public static void computerXO(int[][] sheet, int compPlayerNum,int[] XY) {
        if(!checkAbilityToWin(sheet,compPlayerNum,XY))
            if(!protectRisk(sheet,compPlayerNum,XY))
                smartRandom(sheet,XY);
    }

    public static void smartRandom(int[][] sheet,int[] XY){
        if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
            else if(!checkCorners(sheet,XY))checkRest(sheet,XY);

    }

    public static boolean checkCorners(int[][] sheet,int[] XY){
        int counter=0;Random r=new Random();int []temp=new int[4];
        try {
            if(sheet[0][0]==0){temp[counter]=0;counter++;}
            if(sheet[2][0]==0){temp[counter]=1;counter++;}
            if(sheet[0][2]==0){temp[counter]=2;counter++;}
            if(sheet[2][2]==0){temp[counter]=3;counter++;}
            if(counter>0){
                switch(temp[r.nextInt(counter-1)]){
                    case 0:{XY[0]=0;XY[1]=0;}break;
                    case 1:{XY[0]=2;XY[1]=0;}break;
                    case 2:{XY[0]=0;XY[1]=2;}break;
                    case 3:{XY[0]=2;XY[1]=2;}break;
                }
                Log.d("debugTag", "  checkCorners  XY  "+XY[0]+"  " +XY[1]);

                return true;
            }
        } catch (Exception e) {
            Log.d("debugTag", " line num " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "  Msg: " + e.getMessage());

        }
        return false;
    }
    public static boolean checkRest(int[][] sheet,int[] XY){
        int counter=0;Random r=new Random();int []temp=new int[4];
        try {
            if(sheet[0][1]==0){temp[counter]=0;counter++;}
            if(sheet[1][0]==0){temp[counter]=1;counter++;}
            if(sheet[1][2]==0){temp[counter]=2;counter++;}
            if(sheet[2][1]==0){temp[counter]=3;counter++;}
            if(counter>0){
                switch(temp[r.nextInt(counter-1)]){
                    case 0:{XY[0]=0;XY[1]=1;}break;
                    case 1:{XY[0]=1;XY[1]=0;}break;
                    case 2:{XY[0]=1;XY[1]=2;}break;
                    case 3:{XY[0]=2;XY[1]=1;}break;
                }
                Log.d("debugTag", " checkRest  XY  "+XY[0]+"  " +XY[1]);

                return true;
            }
        } catch (Exception e) {
            Log.d("debugTag", " line num " + Thread.currentThread().getStackTrace()[2].getLineNumber()+"  Msg: "+e.getMessage());
        }
        return false;
    }
    public static boolean checkAbilityToWin(int[][] sheet,int compPlayerNum,int[] XY){
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
            if ((sumArr[i] == 10&&compPlayerNum==1)||(sumArr[i] == 2&&compPlayerNum==0)) {
                switch(i){
                    case 0:
                        if(sheet[0][0]==0){XY[0]=0;XY[1]=0;}
                        else if(sheet[0][1]==0){XY[0]=0;XY[1]=1;}
                        else if(sheet[0][2]==0){XY[0]=0;XY[1]=2;}
                        return true ;

                    case 1:
                        if(sheet[1][0]==0){XY[0]=1;XY[1]=0;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[1][2]==0){XY[0]=1;XY[1]=2;}
                        return true ;

                    case 2:
                        if(sheet[2][0]==0){XY[0]=2;XY[1]=0;}
                        else if(sheet[2][1]==0){XY[0]=2;XY[1]=1;}
                        else if(sheet[2][2]==0){XY[0]=2;XY[1]=2;}
                        return true;

                    case 3:
                        if(sheet[0][0]==0){XY[0]=0;XY[1]=0;}
                        else if(sheet[1][0]==0){XY[0]=1;XY[1]=0;}
                        else if(sheet[2][0]==0){XY[0]=2;XY[1]=0;}
                        return true;

                    case 4:
                        if(sheet[0][1]==0){XY[0]=0;XY[1]=1;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[2][1]==0){XY[0]=2;XY[1]=1;}
                        return true;

                    case 5:
                        if(sheet[0][2]==0){XY[0]=0;XY[1]=2;}
                        else if(sheet[1][2]==0){XY[0]=1;XY[1]=2;}
                        else if(sheet[2][2]==0){XY[0]=2;XY[1]=2;}
                        return true;

                    case 6:
                        if(sheet[0][0]==0){XY[0]=0;XY[1]=0;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[2][2]==0){XY[0]=2;XY[1]=2;}
                        return true;

                    case 7:
                        if(sheet[2][0]==0){XY[0]=2;XY[1]=0;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[0][2]==0){XY[0]=0;XY[1]=2;}
                        return true;

                }

            }
        }
        return false;
    }

    public static boolean protectRisk(int[][] sheet,int compPlayerNum,int[] XY){
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
            if ((sumArr[i] == 2&&compPlayerNum==1)||(sumArr[i] == 10&&compPlayerNum==0))
                switch(i){
                    case 0:
                        if(sheet[0][0]==0){XY[0]=0;XY[1]=0;}
                        else if(sheet[0][1]==0){XY[0]=0;XY[1]=1;}
                        else if(sheet[0][2]==0){XY[0]=0;XY[1]=2;}
                        return true ;
                    case 1:
                        if(sheet[1][0]==0){XY[0]=1;XY[1]=0;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[1][2]==0){XY[0]=1;XY[1]=2;}
                        return true ;
                    case 2:
                        if(sheet[2][0]==0){XY[0]=2;XY[1]=0;}
                        else if(sheet[2][1]==0){XY[0]=2;XY[1]=1;}
                        else if(sheet[2][2]==0){XY[0]=2;XY[1]=2;}
                        return true;
                    case 3:
                        if(sheet[0][0]==0){XY[0]=0;XY[1]=0;}
                        else if(sheet[1][0]==0){XY[0]=1;XY[1]=0;}
                        else if(sheet[2][0]==0){XY[0]=2;XY[1]=0;}
                        return true;
                    case 4:
                        if(sheet[0][1]==0){XY[0]=0;XY[1]=1;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[2][1]==0){XY[0]=2;XY[1]=1;}
                        return true;
                    case 5:
                        if(sheet[0][2]==0){XY[0]=0;XY[1]=2;}
                        else if(sheet[1][2]==0){XY[0]=1;XY[1]=2;}
                        else if(sheet[2][2]==0){XY[0]=2;XY[1]=2;}
                        return true;
                    case 6:
                        if(sheet[0][0]==0){XY[0]=0;XY[1]=0;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[2][2]==0){XY[0]=2;XY[1]=2;}
                        return true;
                    case 7:
                        if(sheet[2][0]==0){XY[0]=2;XY[1]=0;}
                        else if(sheet[1][1]==0){XY[0]=1;XY[1]=1;}
                        else if(sheet[0][2]==0){XY[0]=0;XY[1]=2;}
                        return true;
                }
        }
        return false;
    }


/*
    public static boolean ifFirst( int[][] sheet,int compPlayerNum){
        int counterBLUE=0,counterRED=0;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if(sheet[i][j]==1)counterBLUE++;
                if(sheet[i][j]==5)counterRED++;
            }
        }
        if(counterBLUE==counterRED)return true;
        if(compPlayerNum==0&&counterBLUE<counterRED)return false;
        if(compPlayerNum==1&&counterBLUE>counterRED)return false;
        return false;
    }
*/
}
