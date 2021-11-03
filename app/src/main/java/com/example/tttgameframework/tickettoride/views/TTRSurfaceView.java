package com.example.tttgameframework.tickettoride.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.tttgameframework.R;

public class TTRSurfaceView extends SurfaceView {

    private int screenHeight;
    private int screenWidth;
    private Paint redPaint;
    private Paint greenPaint;
    private Paint bluePaint;
    private Paint yellowPaint;
    private Paint orangePaint;
    private Paint white;
    private Paint greyPaint;
    private Paint pinkPaint;
    private Paint blackPaint;
    private Paint ticketPaint;
    private Paint textPaint;
    private Paint ticketTextPaint;
    private Paint trainCountPaint;
    private Paint otherTrainCountPaint;
    private Paint drawTrainPaint;
    private Paint otherInfoPaint;

    public TTRSurfaceView(Context context, AttributeSet atr) {
        super(context, atr);
        //enable drawing
        setWillNotDraw(false);

        //make paint
        white = new Paint();
        redPaint = new Paint();
        greenPaint = new Paint();
        bluePaint = new Paint();
        yellowPaint = new Paint();
        orangePaint = new Paint();
        greyPaint = new Paint();
        pinkPaint = new Paint();
        blackPaint = new Paint();
        ticketPaint =  new Paint();
        textPaint = new Paint();
        ticketTextPaint = new Paint();
        trainCountPaint = new Paint();
        otherTrainCountPaint = new Paint();
        drawTrainPaint = new Paint();
        white.setARGB(255,255,255,255);
        redPaint.setARGB(255,255,0,0);
        greenPaint.setARGB(255,0,255,0);
        bluePaint.setARGB(255,0,0,255);
        yellowPaint.setARGB(255,223,189,0);
        orangePaint.setARGB(255,255,165,0);
        greyPaint.setARGB(255,128,128,128);
        pinkPaint.setARGB(255, 255,192,203);
        blackPaint.setARGB(255,0 ,0,0);
        ticketPaint.setARGB(255,246,208,120);
        textPaint.setARGB(255,0 ,0,0);
        ticketTextPaint.setARGB(255,0,0,0);
        trainCountPaint.setARGB(255,0,0,0);
        otherTrainCountPaint.setARGB(255,0,0,0);
        drawTrainPaint.setARGB(255,173,216, 230);
        screenHeight = getMeasuredHeight();
        screenWidth = getMeasuredWidth();
        ticketTextPaint.setTextSize(27);
        textPaint.setTextSize(30);
        trainCountPaint.setTextSize(50);
        otherTrainCountPaint.setTextSize(25);
        trainCountPaint.setFakeBoldText(true);
        otherTrainCountPaint.setFakeBoldText(true);
        textPaint.setFakeBoldText(true);
        ticketTextPaint.setFakeBoldText(true);
        otherInfoPaint = new Paint(otherTrainCountPaint);
        otherInfoPaint.setARGB(255,255,255,255);



    }

    protected void onDraw(Canvas canvas){


        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.oregon_map);
        //following code was taken from stack overflow
        float maxSize =2000;
        float ratio = Math.min((float)maxSize/image.getWidth(),(float)maxSize/image.getHeight());
        int width = Math.round((float)ratio*image.getWidth());
        Bitmap newImage = Bitmap.createScaledBitmap(image,width,1300,true);

        //background
        canvas.drawBitmap(newImage, 150.f, 0.f, redPaint);



        //current player
        canvas.drawCircle( 2225, 1425,75,white);
        canvas.drawCircle( 2400, 1425,100,greenPaint);
        canvas.drawText("44", 2190, 1450, trainCountPaint);

        //player 2
        canvas.drawCircle(600,100,70,redPaint);
        canvas.drawRect(600, 60, 900, 170,redPaint);
        canvas.drawCircle(660,140,27,white);
        canvas.drawText("44", 645, 150, otherTrainCountPaint);
        canvas.drawRect(720,70, 770, 110, drawTrainPaint);
        canvas.drawText("3", 790, 100, otherInfoPaint);
        canvas.drawRect(720,120, 770, 160, ticketPaint);
        canvas.drawText("3", 790, 150, otherInfoPaint);
        //player 3
        canvas.drawCircle(  1025,100,70,bluePaint);
        canvas.drawRect(1025, 60, 1325, 170,bluePaint);
        canvas.drawCircle(1085,140,27,white);
        canvas.drawText("45", 1070, 150, otherTrainCountPaint);
        canvas.drawRect(1145,70, 1195, 110, drawTrainPaint);
        canvas.drawText("3", 1215, 100, otherInfoPaint);
        canvas.drawRect(1145,120, 1195, 160, ticketPaint);
        canvas.drawText("3", 1215, 150, otherInfoPaint);

        //player 4
        canvas.drawCircle(1450,100,70,yellowPaint);
        canvas.drawRect(1450, 60, 1750, 170,yellowPaint);
        canvas.drawCircle(1510,140,27,white);
        canvas.drawText("41", 1495, 150, otherTrainCountPaint);
        canvas.drawRect(1570,70, 1620, 110, drawTrainPaint);
        canvas.drawText("3", 1640, 100, otherInfoPaint);
        canvas.drawRect(1570,120, 1620, 160, ticketPaint);
        canvas.drawText("3", 1640, 150, otherInfoPaint);

        /**
         * Board drawings
         *
         *
         * Cities Dots and their names
         */

        //Astoria
        canvas.drawCircle(400,120,25,white);
        canvas.drawText("Astoria", 270, 130, textPaint);

        //Tillamook
        canvas.drawCircle(390, 300, 25, white);
        canvas.drawText("Tillamook", 235, 310,textPaint);

        //Newport
        canvas.drawCircle(360, 500, 25, white);
        canvas.drawText("Newport", 220, 510, textPaint);

        //Coos Bay
        canvas.drawCircle(320, 880  , 25,white);
        canvas.drawText("Coos Bay", 165, 890, textPaint);

        //Portland
        canvas.drawCircle(650,280, 25, white);
        canvas.drawText("Portland", 660, 225, textPaint);

        //Salem
        canvas.drawCircle(600,420,25, white);
        canvas.drawText("Salem", 640, 430, textPaint);


        //Eugene
        canvas.drawCircle(570,670,25,white);
        canvas.drawText("Eugene", 600, 710, textPaint);


        //RoseBurg
        canvas.drawCircle(530,920,25,white);
        canvas.drawText("RoseBurg", 560, 930, textPaint);

        //Grants Pass
        canvas.drawCircle(520,1125,25,white);
        canvas.drawText("Grants Pass", 450, 1180, textPaint);

        //The Dalles
        canvas.drawCircle(1000,270, 25, white);
        canvas.drawText("The Dalles", 980, 230, textPaint);

        //Bend
        canvas.drawCircle(1000,670, 25, white);
        canvas.drawText("Bend", 1050, 670, textPaint);

        //K Falls
        canvas.drawCircle(880,1195,25, white);
        canvas.drawText("K Falls", 920, 1160, textPaint);

        //Pendelton
        canvas.drawCircle(1560, 260, 25, white);
        canvas.drawText("Pendleton", 1500, 230, textPaint);

        //La Grand
        canvas.drawCircle(1750, 350,25, white);
        canvas.drawText("La Grand", 1780, 360, textPaint );

        //Burns
        canvas.drawCircle(1500, 820,25, white);
        canvas.drawText("Burns", 1530, 830, textPaint);


        //LakeView
        canvas.drawCircle(1250,1195,25,white);
        canvas.drawText("Lakeview",1280, 1205, textPaint);


        /**
         * Paths
         */

        canvas.save();

        //Astoria-Tillamook
        canvas.drawRect(360,155, 390, 265, white);
        canvas.drawRect(395,155, 425, 265, orangePaint);

        //Astoria-Portland
        canvas.rotate(125, 400,120);
        canvas.drawRect(355, -25,385,85,greyPaint);
        canvas.drawRect(355, -140,385,-30,greyPaint);
        canvas.drawRect(390, -25,420,85,greyPaint);
        canvas.drawRect(390, -140,420,-30,greyPaint);
        canvas.restore();
        //found how to rotate on stack overflow

        //Tillamook-Portland
        canvas.save();
        canvas.rotate(87, 390,300);
        canvas.drawRect(350,135, 380, 265,pinkPaint );
        canvas.drawRect(385,135, 415, 265,blackPaint );
        canvas.drawRect(395,135, 405, 265,greenPaint);

        canvas.restore();

        //Tillamook-Newport
        canvas.save();
        canvas.rotate(5,390,300);
        canvas.drawRect(345, 335,375,465, greyPaint);
        canvas.drawRect(355, 334, 365,465, redPaint);
        canvas.drawRect(380, 335,410,465, greyPaint);
        canvas.restore();

        //Portland-The Dalles
        canvas.save();
        canvas.rotate(89, 650,280);
        canvas.drawRect(610,110, 640, 235,orangePaint );
        canvas.drawRect(610,  -20, 640, 105,orangePaint );
        canvas.drawRect(645,110, 675, 235,pinkPaint );
        canvas.drawRect(645,-20, 675, 105,pinkPaint );
        canvas.restore();

        //Portland-Salem
        canvas.save();
        canvas.rotate(15,650,280);
        canvas.drawRect(615, 315, 645,410, greyPaint);
        canvas.drawRect(650, 315, 680,410, greyPaint);
        canvas.restore();

        //The Dalles- Pendleton
        canvas.save();
        canvas.rotate(270,1000,270);
        canvas.drawRect(965, 315, 995, 440, blackPaint);
        canvas.drawRect(965, 445, 995, 570, blackPaint);
        canvas.drawRect(965, 575, 995, 700, blackPaint);
        canvas.drawRect(1000, 315, 1030, 440, white);
        canvas.drawRect(1000, 445, 1030, 570, white);
        canvas.drawRect(1000, 575, 1030, 700, white);
        canvas.restore();

        //The Dalles- Bend
        canvas.drawRect(965, 305,995,415, greyPaint);
        canvas.drawRect(965, 420,995,530, greyPaint);
        canvas.drawRect(965, 535,995,645, greyPaint);
        canvas.drawRect(1000, 305,1030,415, greyPaint);
        canvas.drawRect(1000, 420,1030,530, greyPaint);
        canvas.drawRect(1000, 535,1030,645, greyPaint);

        //Pendleton-Bend
        canvas.save();
        canvas.rotate(53, 1560,260);
        canvas.drawRect( 1545, 300, 1575, 440, pinkPaint);
        canvas.drawRect( 1545, 445, 1575, 585, pinkPaint);
        canvas.drawRect( 1545, 590, 1575, 730, pinkPaint);
        canvas.drawRect( 1545, 735, 1575, 875, pinkPaint);
        canvas.drawRect( 1555, 300, 1565, 440, yellowPaint);
        canvas.drawRect( 1555, 445, 1565, 585, yellowPaint);
        canvas.drawRect( 1555, 590, 1565, 730, yellowPaint);
        canvas.drawRect( 1555, 735, 1565, 875, yellowPaint);
        canvas.restore();

        //Pendleton-La Grand
        canvas.save();
        canvas.rotate(-65,1560,260);
        canvas.drawRect(1515, 295, 1545, 420,orangePaint);
        canvas.drawRect(1550, 295, 1580, 420, blackPaint);
        canvas.restore();

        //Newport-Salem
        canvas.save();
        canvas.rotate(-108,360,500);
        canvas.drawRect(345,535,375,630, greyPaint);
        canvas.drawRect(345,635,375,730, greyPaint);
        canvas.restore();

        //Newport-CoosBoy
        canvas.save();
        canvas.rotate(7,360,500);
        canvas.drawRect(345,535,375,635,pinkPaint);
        canvas.drawRect(345,640,375,740,pinkPaint);
        canvas.drawRect(345,745,375,845,pinkPaint);
        canvas.restore();

        //Newport-Eugene
        canvas.save();
        canvas.rotate(-52,360,500);
        canvas.drawRect(345,535,375,630,white);
        canvas.drawRect(345,635,375,730,white);
        canvas.restore();

        //Salem-Eugene
        canvas.save();
        canvas.rotate(5,600,420);
        canvas.drawRect(565,455, 595,550,blackPaint);
        canvas.drawRect(565,555, 595,650, blackPaint);
        canvas.drawRect(600,455, 630,550, orangePaint);
        canvas.drawRect(600,555, 630,650, orangePaint);
        canvas.restore();

        //Salem-Bend
        canvas.save();
        canvas.rotate(-57,600,420);
        canvas.drawRect(585,455, 615,580, white);
        canvas.drawRect(585,585, 615,710, white);
        canvas.drawRect(585,715, 615,840, white);
        canvas.restore();

        //LaGrand-Burns
        canvas.save();
        canvas.rotate(28,1750,350);
        canvas.drawRect(1735, 385, 1765, 495, orangePaint);
        canvas.drawRect(1735, 500, 1765, 610, orangePaint);
        canvas.drawRect(1735, 615, 1765, 725, orangePaint);
        canvas.drawRect(1735, 730, 1765, 840, orangePaint);
        canvas.restore();

        //Euguene-Bend
        canvas.save();
        canvas.rotate(-89,570,670);
        canvas.drawRect(555, 720, 585, 885, pinkPaint);
        canvas.drawRect(555, 890, 585, 1050,pinkPaint);
        canvas.restore();

        //Eugene-Roseburg
        canvas.save();
        canvas.rotate(7,570,670);
        canvas.drawRect(530, 705, 560, 800, greyPaint);
        canvas.drawRect(530, 805, 560, 900, greyPaint);
        canvas.drawRect(565, 705, 595, 800, greyPaint);
        canvas.drawRect(565, 805, 595, 900, greyPaint);
        canvas.restore();

        //Bend-KFalls
        canvas.save();
        canvas.rotate(12,1000,670);
        canvas.drawRect(985, 705, 1015, 820,orangePaint);
        canvas.drawRect(985, 825, 1015, 940,orangePaint);
        canvas.drawRect(985, 945, 1015, 1060,orangePaint);
        canvas.drawRect(985, 1065, 1015, 1180,orangePaint);
        canvas.restore();

        //Bend-Burns
        canvas.save();
        canvas.rotate(-73, 1000,670);
        canvas.drawRect(985, 710, 1015, 845,greyPaint);
        canvas.drawRect(985, 850, 1015, 985,greyPaint);
        canvas.drawRect(985, 990, 1015, 1120,greyPaint);
        canvas.restore();

        //Burns-Lakeview
        canvas.save();
        canvas.rotate(33,1500, 820);
        canvas.drawRect(1485, 855, 1515, 975,pinkPaint);
        canvas.drawRect(1485, 980, 1515, 1100,pinkPaint);
        canvas.drawRect(1485, 1105, 1515, 1225,pinkPaint);
        canvas.restore();

        //CoosBoy-Roseburg
        canvas.save();
        canvas.rotate(-80,320,880);
        canvas.drawRect(305, 915,335, 1050, white);
        canvas.restore();

        //CoosBoy-Grants Pass
        canvas.save();
        canvas.rotate(-38, 320,880);
        canvas.drawRect(305,915, 335, 1035,blackPaint);
        canvas.drawRect(305,1040, 335, 1160,blackPaint);
        canvas.restore();

        //Roseburg-Grants Pass
        canvas.save();
        canvas.rotate(3,530,920);
        canvas.drawRect(515, 955, 545, 1080, greyPaint);
        canvas.restore();

        //Roseburg-KFalls
        canvas.save();
        canvas.rotate(-52,530,920);
        canvas.drawRect(515, 955, 545, 1075, greyPaint);
        canvas.drawRect(515, 1080, 545, 1200, greyPaint);
        canvas.drawRect(515, 1205, 545, 1325, greyPaint);
        canvas.restore();

        //Grants Pass-KFalls
        canvas.save();
        canvas.rotate(-77,520,1125);
        canvas.drawRect(505,1170,535, 1295, orangePaint);
        canvas.drawRect(505,1300,535, 1425, orangePaint);
        canvas.restore();

        //KFalls - LakeView
        canvas.save();
        canvas.rotate(-90, 880,1195);
        canvas.drawRect(865,1230,895, 1365,white );
        canvas.drawRect(865,1370,895, 1505,white );
        canvas.restore();


        //draws the tickets
        canvas.drawRect(0, 1260, 285, 1350, blackPaint);
        canvas.drawRect(10,1270,275 ,1340, ticketPaint);
        canvas.drawRect(0, 1350, 285, 1440, blackPaint);
        canvas.drawRect(10,1360,275 ,1430, ticketPaint);
        canvas.drawRect(0, 1440, 285, 1530, blackPaint);
        canvas.drawRect(10,1450,275 ,1520, ticketPaint);


        canvas.drawText("Portland-Padleton-7", 20.f,1310.f,ticketTextPaint);
        canvas.drawText("Salem-Burns-12", 20.f,1390.f,ticketTextPaint);
        canvas.drawText("Astoria-Lakeview-20", 20.f,1480.f,ticketTextPaint);


















    }
}
