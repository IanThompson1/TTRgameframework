package com.example.tttgameframework.tickettoride.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.EditText;

import com.example.tttgameframework.R;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.Player;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.infoMessage.Ticket;

import java.util.ArrayList;

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
    private Paint emptyPaint;
    private Paint highlightPaint;
    private Paint buttonHighlight;
    private float Xratio;
    private float Yratio;
    private float Rratio;
    private Paint higlighter;
    private Paint numCardsPaint;
    private Paint lightgreen;

    //instance variables that hold the players train counts
    private int player0Trains;
    private int player1Trains;
    private int player2Trains;
    private int player3Trains;

    //instance variables that hold the players cards in hand
    private int player0Cards;
    private int player1Cards;
    private int player2Cards;
    private int player3Cards;

    //instance variables that hold the players tickets held
    private int player0Tickets;
    private int player1Tickets;
    private int player2Tickets;
    private int player3Tickets;

    //holds the current players tickets
    private ArrayList<Ticket> curTickets;

    private ArrayList<Path> allPaths;

    private ArrayList<Boolean> selected;

    private ArrayList<Integer> selectedTickets;

    //holds the tickets shown to the player
    private ArrayList<Ticket> shownTickets;

    //holds selected path
    private Path curPath;

    //holds the TTRstate
    protected TTRState state;

    //holds the current player
    private Player curPlayer;

    //holds the selected card color
    private TTRState.CARD selectedCardColor;

    //holds the edit text view
    private EditText text;

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
        emptyPaint = new Paint();
        highlightPaint = new Paint();
        higlighter = new Paint();
        buttonHighlight = new Paint();
        numCardsPaint = new Paint();
        lightgreen = new Paint();
        lightgreen.setARGB(125,0,255,0);
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
        emptyPaint.setARGB(0 , 255,255,255);
        highlightPaint.setARGB(155, 173, 216, 230);
        higlighter.setARGB(255,173,216,230);
        numCardsPaint.setColor(Color.BLACK);
        screenHeight = getMeasuredHeight();
        screenWidth = getMeasuredWidth();
        ticketTextPaint.setTextSize(27);
        textPaint.setTextSize(30);
        trainCountPaint.setTextSize(50);
        otherTrainCountPaint.setTextSize(25);
        numCardsPaint.setTextSize(50);
        trainCountPaint.setFakeBoldText(true);
        otherTrainCountPaint.setFakeBoldText(true);
        textPaint.setFakeBoldText(true);
        ticketTextPaint.setFakeBoldText(true);
        numCardsPaint.setFakeBoldText(true);
        otherInfoPaint = new Paint(otherTrainCountPaint);
        otherInfoPaint.setARGB(255,255,255,255);
        buttonHighlight.setColor(Color.GREEN);

        Xratio = (float) (25.0/32.0);
        Yratio = (float) (2.0/3.0);
        Rratio = Yratio;

    }

    protected void onDraw(Canvas canvas){
        Xratio = (float) (25.0/32.0);
        Yratio = (float) (2.0/3.0);
        Rratio = Yratio;

        if(state == null){
            return;
        }

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.oregon_map);
        //following code was taken from stack overflow
        float maxSize =2000;
        float ratio = Math.min((float)maxSize/image.getWidth(),(float)maxSize/image.getHeight());
        int width = Math.round((float)ratio*image.getWidth());
        Bitmap newImage = Bitmap.createScaledBitmap(image,Math.round(width*Yratio),Math.round(1300*Xratio),true);

        //background
        canvas.drawBitmap(newImage, 150.f*Xratio, 0.f*Yratio, redPaint);





        //current player
        canvas.drawCircle( 2225*Xratio, 1525*Yratio,75*Rratio,white);
        canvas.drawCircle( 2400*Xratio, 1525*Yratio,100*Rratio,greenPaint);
        canvas.drawText(String.valueOf(curPlayer.getNumTrains()), 2190*Xratio, 1550*Yratio, trainCountPaint);

        //player 2
        canvas.drawCircle(600*Xratio,100*Yratio,70*Rratio,redPaint);
        canvas.drawRect(600*Xratio, 60*Yratio, 900*Xratio, 170*Yratio,redPaint);
        canvas.drawCircle(660*Xratio,140*Yratio,27*Rratio,white);
        canvas.drawText(String.valueOf(player1Trains), 645*Xratio, 150*Yratio, otherTrainCountPaint);
        canvas.drawRect(720*Xratio,70*Yratio, 770*Xratio, 110*Yratio, drawTrainPaint);
        canvas.drawText(String.valueOf(player1Cards), 790*Xratio, 100*Yratio, otherInfoPaint);
        canvas.drawRect(720*Xratio,120*Yratio, 770*Xratio, 160*Yratio, ticketPaint);
        canvas.drawText(String.valueOf(player1Tickets), 790*Xratio, 150*Yratio, otherInfoPaint);
        //player 3
        canvas.drawCircle(  1025*Xratio,100*Yratio,70*Rratio,bluePaint);
        canvas.drawRect(1025*Xratio, 60*Yratio, 1325*Xratio, 170*Yratio,bluePaint);
        canvas.drawCircle(1085*Xratio,140*Yratio,27*Rratio,white);
        canvas.drawText(String.valueOf(player2Trains), 1070*Xratio, 150*Yratio, otherTrainCountPaint);
        canvas.drawRect(1145*Xratio,70*Yratio, 1195*Xratio, 110*Yratio, drawTrainPaint);
        canvas.drawText(String.valueOf(player2Cards), 1215*Xratio, 100*Yratio, otherInfoPaint);
        canvas.drawRect(1145*Xratio,120*Yratio, 1195*Xratio, 160*Yratio, ticketPaint);
        canvas.drawText(String.valueOf(player2Tickets), 1215*Xratio, 150*Yratio, otherInfoPaint);

        //player 4
        canvas.drawCircle(1450*Xratio,100*Yratio,70*Rratio,yellowPaint);
        canvas.drawRect(1450*Xratio, 60*Yratio, 1750*Xratio, 170*Yratio,yellowPaint);
        canvas.drawCircle(1510*Xratio,140*Yratio,27*Rratio,white);
        canvas.drawText(String.valueOf(player3Trains), 1495*Xratio, 150*Yratio, otherTrainCountPaint);
        canvas.drawRect(1570*Xratio,70*Yratio, 1620*Xratio, 110*Yratio, drawTrainPaint);
        canvas.drawText(String.valueOf(player3Cards), 1640*Xratio, 100*Yratio, otherInfoPaint);
        canvas.drawRect(1570*Xratio,120*Yratio, 1620*Xratio, 160*Yratio, ticketPaint);
        canvas.drawText(String.valueOf(player3Tickets), 1640*Xratio, 150*Yratio, otherInfoPaint);

        /**
         * Board drawings
         *
         *
         * Cities Dots and their names
         */

        //Astoria
        canvas.drawCircle(400*Xratio,120*Yratio,25*Rratio,white);
        canvas.drawText("Astoria", 270*Xratio, 130*Yratio, textPaint);

        //Tillamook
        canvas.drawCircle(390*Xratio, 300*Yratio, 25*Rratio, white);
        canvas.drawText("Tillamook", 235*Xratio, 310*Yratio,textPaint);

        //Newport
        canvas.drawCircle(360*Xratio, 500*Yratio, 25*Rratio, white);
        canvas.drawText("Newport", 220*Xratio, 510*Yratio, textPaint);

        //Coos Bay
        canvas.drawCircle(320*Xratio, 880*Yratio  , 25*Rratio,white);
        canvas.drawText("Coos Bay", 165*Xratio, 890*Yratio, textPaint);

        //Portland
        canvas.drawCircle(650*Xratio,280*Yratio, 25*Rratio, white);
        canvas.drawText("Portland", 660*Xratio, 225*Yratio, textPaint);

        //Salem
        canvas.drawCircle(600*Xratio,420*Yratio,25*Rratio, white);
        canvas.drawText("Salem", 640*Xratio, 430*Yratio, textPaint);


        //Eugene
        canvas.drawCircle(570*Xratio,670*Yratio,25*Rratio,white);
        canvas.drawText("Eugene", 600*Xratio, 710*Yratio, textPaint);


        //RoseBurg
        canvas.drawCircle(530*Xratio,920*Yratio,25*Rratio,white);
        canvas.drawText("RoseBurg", 560*Xratio, 930*Yratio, textPaint);

        //Grants Pass
        canvas.drawCircle(520*Xratio,1125*Yratio,25*Rratio,white);
        canvas.drawText("Grants Pass", 450*Xratio, 1180*Yratio, textPaint);

        //The Dalles
        canvas.drawCircle(1000*Xratio,270*Yratio, 25*Rratio, white);
        canvas.drawText("The Dalles", 980*Xratio, 230*Yratio, textPaint);

        //Bend
        canvas.drawCircle(1000*Xratio,670*Yratio, 25*Rratio, white);
        canvas.drawText("Bend", 1050*Xratio, 670*Yratio, textPaint);

        //K Falls
        canvas.drawCircle(880*Xratio,1195*Yratio,25*Rratio, white);
        canvas.drawText("K Falls", 920*Xratio, 1160*Yratio, textPaint);

        //Pendelton
        canvas.drawCircle(1560*Xratio, 260*Yratio, 25*Rratio, white);
        canvas.drawText("Pendleton", 1500*Xratio, 230*Yratio, textPaint);

        //La Grand
        canvas.drawCircle(1750*Xratio, 350*Yratio,25*Rratio, white);
        canvas.drawText("La Grand", 1780*Xratio, 360*Yratio, textPaint );

        //Burns
        canvas.drawCircle(1500*Xratio, 820*Yratio,25*Rratio, white);
        canvas.drawText("Burns", 1530*Xratio, 830*Yratio, textPaint);


        //LakeView
        canvas.drawCircle(1250*Xratio,1195*Yratio,25*Rratio,white);
        canvas.drawText("Lakeview",1280*Xratio, 1205*Yratio, textPaint);


        /**
         * Paths
         */

        canvas.save();

        //Astoria-Tillamook
        canvas.drawRect(360*Xratio,155*Yratio, 390*Xratio, 265*Yratio, white);

        //draws if path is selected
        canvas.drawRect(360*Xratio,155*Yratio, 390*Xratio, 265*Yratio, hPaint(18));
        //draws if someone owns the path
        System.out.println(ownerPaint(allPaths.get(18)).toString());
        canvas.drawRect(370*Xratio,155*Yratio, 380*Xratio, 265*Yratio, ownerPaint(allPaths.get(18)));
        canvas.drawRect(395*Xratio,155*Yratio, 425*Xratio, 265*Yratio, orangePaint);
        canvas.drawRect(395*Xratio,155*Yratio, 425*Xratio, 265*Yratio, hPaint(0));
        canvas.drawRect(405*Xratio,155*Yratio, 415*Xratio, 265*Yratio, ownerPaint(allPaths.get(0)));


        //Astoria-Portland
        canvas.rotate(125, 400*Xratio,120*Yratio);
        canvas.drawRect(355*Xratio, -25*Yratio,385*Xratio,85*Yratio,greyPaint);
        canvas.drawRect(355*Xratio,-140*Yratio,385*Xratio,-30*Yratio,greyPaint);
        canvas.drawRect(365*Xratio,-140*Yratio, 375*Xratio, 85*Yratio, ownerPaint(allPaths.get(24)));
        canvas.drawRect(355*Xratio, -25*Yratio,385*Xratio,85*Yratio,hPaint(24));
        canvas.drawRect(355*Xratio, -140*Yratio,385*Xratio,-30*Yratio,hPaint(24));
        canvas.drawRect(390*Xratio, -25*Yratio,420*Xratio,85*Yratio,greyPaint);
        canvas.drawRect(390*Xratio, -140*Yratio,420*Xratio,-30*Yratio,greyPaint);
        canvas.drawRect(400*Xratio,-140*Yratio, 410*Xratio, 85*Yratio, ownerPaint(allPaths.get(25)));
        canvas.drawRect(390*Xratio, -25*Yratio,420*Xratio,85*Yratio,hPaint(25));
        canvas.drawRect(390*Xratio, -140*Yratio,420*Xratio,-30*Yratio,hPaint(25));
        canvas.restore();
        //found how to rotate on stack overflow

        //Tillamook-Portland
        canvas.save();
        canvas.rotate(87, 390*Xratio,300*Yratio);
        canvas.drawRect(350*Xratio,135*Yratio, 380*Xratio, 265*Yratio,pinkPaint);
        canvas.drawRect(350*Xratio,135*Yratio, 380*Xratio, 265*Yratio,hPaint(6));
        canvas.drawRect(360*Xratio,135*Yratio, 370*Xratio, 265*Yratio, ownerPaint(allPaths.get(6)));
        canvas.drawRect(385*Xratio,135*Yratio, 415*Xratio, 265*Yratio,blackPaint );
        canvas.drawRect(385*Xratio,135*Yratio, 415*Xratio, 265*Yratio,hPaint(12) );
        canvas.drawRect(395*Xratio,135*Yratio, 405*Xratio, 265*Yratio,ownerPaint(allPaths.get(12)));

        canvas.restore();

        //Tillamook-Newport
        canvas.save();
        canvas.rotate(5,390*Xratio,300*Yratio);
        canvas.drawRect(345*Xratio, 335*Yratio,375*Xratio,465*Yratio, greyPaint);
        canvas.drawRect(345*Xratio, 335*Yratio,375*Xratio,465*Yratio, hPaint(30));
        canvas.drawRect(355*Xratio, 334*Yratio, 365*Xratio,465*Yratio, ownerPaint(allPaths.get(30)));
        canvas.drawRect(380*Xratio, 335*Yratio,410*Xratio,465*Yratio, greyPaint);
        canvas.drawRect(380*Xratio, 335*Yratio,410*Xratio,465*Yratio, hPaint(31));
        canvas.drawRect(390*Xratio, 334*Yratio, 400*Xratio,465*Yratio, ownerPaint(allPaths.get(31)));

        canvas.restore();

        //Portland-The Dalles
        canvas.save();
        canvas.rotate(89, 650*Xratio,280*Yratio);
        canvas.drawRect(610*Xratio,110*Yratio, 640*Xratio, 235*Yratio,orangePaint );
        canvas.drawRect(610*Xratio,  -20*Yratio, 640*Xratio, 105*Yratio,orangePaint );
        canvas.drawRect(610*Xratio,110*Yratio, 640*Xratio, 235*Yratio,hPaint(1) );
        canvas.drawRect(610*Xratio,  -20*Yratio, 640*Xratio, 105*Yratio,hPaint(1) );
        canvas.drawRect(620*Xratio, -20*Yratio, 630*Xratio,235*Yratio, ownerPaint(allPaths.get(1)));
        canvas.drawRect(645*Xratio,110*Yratio, 675*Xratio, 235*Yratio,pinkPaint );
        canvas.drawRect(645*Xratio,-20*Yratio, 675*Xratio, 105*Yratio,pinkPaint );
        canvas.drawRect(645*Xratio,110*Yratio, 675*Xratio, 235*Yratio,hPaint(7) );
        canvas.drawRect(645*Xratio,-20*Yratio, 675*Xratio, 105*Yratio,hPaint(7) );
        canvas.drawRect(655*Xratio, -20*Yratio, 665*Xratio,235*Yratio, ownerPaint(allPaths.get(7)));
        canvas.restore();

        //Portland-Salem
        canvas.save();
        canvas.rotate(15,650*Xratio,280*Yratio);
        canvas.drawRect(615*Xratio, 315*Yratio, 645*Xratio,410*Yratio, greyPaint);
        canvas.drawRect(615*Xratio, 315*Yratio, 645*Xratio,410*Yratio, hPaint(26));
        canvas.drawRect(625*Xratio, 315*Yratio, 635*Xratio,410*Yratio, ownerPaint(allPaths.get(26)));
        canvas.drawRect(650*Xratio, 315*Yratio, 680*Xratio,410*Yratio, greyPaint);
        canvas.drawRect(650*Xratio, 315*Yratio, 680*Xratio,410*Yratio, hPaint(27));
        canvas.drawRect(660*Xratio, 315*Yratio, 670*Xratio,410*Yratio, ownerPaint(allPaths.get(27)));



        canvas.restore();

        //The Dalles- Pendleton
        canvas.save();
        canvas.rotate(270,1000*Xratio,270*Yratio);
        canvas.drawRect(965*Xratio, 315*Yratio, 995*Xratio, 440*Yratio, blackPaint);
        canvas.drawRect(965*Xratio, 445*Yratio, 995*Xratio, 570*Yratio, blackPaint);
        canvas.drawRect(965*Xratio, 575*Yratio, 995*Xratio, 700*Yratio, blackPaint);
        canvas.drawRect(965*Xratio, 315*Yratio, 995*Xratio, 440*Yratio, hPaint(13));
        canvas.drawRect(965*Xratio, 445*Yratio, 995*Xratio, 570*Yratio, hPaint(13));
        canvas.drawRect(965*Xratio, 575*Yratio, 995*Xratio, 700*Yratio, hPaint(13));
        canvas.drawRect(975*Xratio, 315*Yratio, 985*Xratio,700*Yratio, ownerPaint(allPaths.get(13)));
        canvas.drawRect(1000*Xratio, 315*Yratio, 1030*Xratio, 440*Yratio, white);
        canvas.drawRect(1000*Xratio, 445*Yratio, 1030*Xratio, 570*Yratio, white);
        canvas.drawRect(1000*Xratio, 575*Yratio, 1030*Xratio, 700*Yratio, white);
        canvas.drawRect(1000*Xratio, 315*Yratio, 1030*Xratio, 440*Yratio, hPaint(19));
        canvas.drawRect(1000*Xratio, 445*Yratio, 1030*Xratio, 570*Yratio, hPaint(19));
        canvas.drawRect(1000*Xratio, 575*Yratio, 1030*Xratio, 700*Yratio, hPaint(19));
        canvas.drawRect(1010*Xratio, 315*Yratio, 1020*Xratio,700*Yratio, ownerPaint(allPaths.get(19)));
        canvas.restore();

        //The Dalles- Bend
        canvas.drawRect(965*Xratio, 305*Yratio,995*Xratio,415*Yratio, greyPaint);
        canvas.drawRect(965*Xratio, 420*Yratio,995*Xratio,530*Yratio, greyPaint);
        canvas.drawRect(965*Xratio, 535*Yratio,995*Xratio,645*Yratio, greyPaint);
        canvas.drawRect(965*Xratio, 305*Yratio,995*Xratio,415*Yratio, hPaint(28));
        canvas.drawRect(965*Xratio, 420*Yratio,995*Xratio,530*Yratio, hPaint(28));
        canvas.drawRect(965*Xratio, 535*Yratio,995*Xratio,645*Yratio, hPaint(28));
        canvas.drawRect(975*Xratio, 305*Yratio, 985*Xratio,645*Yratio, ownerPaint(allPaths.get(28)));
        canvas.drawRect(1000*Xratio, 305*Yratio,1030*Xratio,415*Yratio, greyPaint);
        canvas.drawRect(1000*Xratio, 420*Yratio,1030*Xratio,530*Yratio, greyPaint);
        canvas.drawRect(1000*Xratio, 535*Yratio,1030*Xratio,645*Yratio, greyPaint);
        canvas.drawRect(1000*Xratio, 305*Yratio,1030*Xratio,415*Yratio, hPaint(29));
        canvas.drawRect(1000*Xratio, 420*Yratio,1030*Xratio,530*Yratio, hPaint(29));
        canvas.drawRect(1000*Xratio, 535*Yratio,1030*Xratio,645*Yratio, hPaint(29));
        canvas.drawRect(1010*Xratio, 305*Yratio, 1020*Xratio,645*Yratio, ownerPaint(allPaths.get(29)));


        //Pendleton-Bend
        canvas.save();
        canvas.rotate(53, 1560*Xratio,260*Yratio);
        canvas.drawRect( 1545*Xratio, 300*Yratio, 1575*Xratio, 440*Yratio, pinkPaint);
        canvas.drawRect( 1545*Xratio, 445*Yratio, 1575*Xratio, 585*Yratio, pinkPaint);
        canvas.drawRect( 1545*Xratio, 590*Yratio, 1575*Xratio, 730*Yratio, pinkPaint);
        canvas.drawRect( 1545*Xratio, 735*Yratio, 1575*Xratio, 875*Yratio, pinkPaint);
        canvas.drawRect( 1545*Xratio, 300*Yratio, 1575*Xratio, 440*Yratio, hPaint(10));
        canvas.drawRect( 1545*Xratio, 445*Yratio, 1575*Xratio, 585*Yratio, hPaint(10));
        canvas.drawRect( 1545*Xratio, 590*Yratio, 1575*Xratio, 730*Yratio, hPaint(10));
        canvas.drawRect( 1545*Xratio, 735*Yratio, 1575*Xratio, 875*Yratio, hPaint(10));
        canvas.drawRect(1555*Xratio, 300*Yratio, 1565*Xratio,875*Yratio, ownerPaint(allPaths.get(10)));


        canvas.restore();

        //Pendleton-La Grand
        canvas.save();
        canvas.rotate(-65,1560*Xratio,260*Yratio);
        canvas.drawRect(1515*Xratio, 295*Yratio, 1545*Xratio, 420*Yratio,orangePaint);
        canvas.drawRect(1515*Xratio, 295*Yratio, 1545*Xratio, 420*Yratio,hPaint(3));
        canvas.drawRect(1525*Xratio, 295*Yratio, 1535*Xratio,420*Yratio, ownerPaint(allPaths.get(3)));
        canvas.drawRect(1550*Xratio, 295*Yratio, 1580*Xratio, 420*Yratio, blackPaint);
        canvas.drawRect(1550*Xratio, 295*Yratio, 1580*Xratio, 420*Yratio, hPaint(14));
        canvas.drawRect(1560*Xratio, 295*Yratio, 1570*Xratio,420*Yratio, ownerPaint(allPaths.get(14)));
        canvas.restore();

        //Newport-Salem
        canvas.save();
        canvas.rotate(-108,360*Xratio,500*Yratio);
        canvas.drawRect(345*Xratio,535*Yratio,375*Xratio,630*Yratio, greyPaint);
        canvas.drawRect(345*Xratio,635*Yratio,375*Xratio,730*Yratio, greyPaint);
        canvas.drawRect(345*Xratio,535*Yratio,375*Xratio,630*Yratio, hPaint(37));
        canvas.drawRect(345*Xratio,635*Yratio,375*Xratio,730*Yratio, hPaint(37));
        canvas.drawRect(355*Xratio, 535*Yratio, 365*Xratio,730*Yratio, ownerPaint(allPaths.get(37)));

        canvas.restore();

        //Newport-CoosBoy
        canvas.save();
        canvas.rotate(7,360*Xratio,500*Yratio);
        canvas.drawRect(345*Xratio,535*Yratio,375*Xratio,635*Yratio,pinkPaint);
        canvas.drawRect(345*Xratio,640*Yratio,375*Xratio,740*Yratio,pinkPaint);
        canvas.drawRect(345*Xratio,745*Yratio,375*Xratio,845*Yratio,pinkPaint);
        canvas.drawRect(345*Xratio,535*Yratio,375*Xratio,635*Yratio,hPaint(8));
        canvas.drawRect(345*Xratio,640*Yratio,375*Xratio,740*Yratio,hPaint(8));
        canvas.drawRect(345*Xratio,745*Yratio,375*Xratio,845*Yratio,hPaint(8));
        canvas.drawRect(355*Xratio, 535*Yratio, 365*Xratio,845*Yratio, ownerPaint(allPaths.get(8)));
        canvas.restore();

        //Newport-Eugene
        canvas.save();
        canvas.rotate(-52,360*Xratio,500*Yratio);
        canvas.drawRect(345*Xratio,535*Yratio,375*Xratio,630*Yratio,white);
        canvas.drawRect(345*Xratio,635*Yratio,375*Xratio,730*Yratio,white);
        canvas.drawRect(345*Xratio,535*Yratio,375*Xratio,630*Yratio,hPaint(21));
        canvas.drawRect(345*Xratio,635*Yratio,375*Xratio,730*Yratio,hPaint(21));
        canvas.drawRect(355*Xratio, 535*Yratio, 365*Xratio,730*Yratio, ownerPaint(allPaths.get(21)));

        canvas.restore();

        //Salem-Eugene
        canvas.save();
        canvas.rotate(5,600*Xratio,420*Yratio);
        canvas.drawRect(565*Xratio,455*Yratio, 595*Xratio,550*Yratio,blackPaint);
        canvas.drawRect(565*Xratio,555*Yratio, 595*Xratio,650*Yratio, blackPaint);
        canvas.drawRect(565*Xratio,455*Yratio, 595*Xratio,550*Yratio,hPaint(15));
        canvas.drawRect(565*Xratio,555*Yratio, 595*Xratio,650*Yratio, hPaint(15));
        canvas.drawRect(575*Xratio, 455*Yratio, 585*Xratio,650*Yratio, ownerPaint(allPaths.get(15)));
        canvas.drawRect(600*Xratio,455*Yratio, 630*Xratio,550*Yratio, orangePaint);
        canvas.drawRect(600*Xratio,555*Yratio, 630*Xratio,650*Yratio, orangePaint);
        canvas.drawRect(600*Xratio,455*Yratio, 630*Xratio,550*Yratio, hPaint(2));
        canvas.drawRect(600*Xratio,555*Yratio, 630*Xratio,650*Yratio, hPaint(2));
        canvas.drawRect(610*Xratio, 455*Yratio, 620*Xratio,650*Yratio, ownerPaint(allPaths.get(2)));

        canvas.restore();

        //Salem-Bend
        canvas.save();
        canvas.rotate(-57,600*Xratio,420*Yratio);
        canvas.drawRect(585*Xratio,455*Yratio, 615*Xratio,580*Yratio, white);
        canvas.drawRect(585*Xratio,585*Yratio, 615*Xratio,710*Yratio, white);
        canvas.drawRect(585*Xratio,715*Yratio, 615*Xratio,840*Yratio, white);
        canvas.drawRect(585*Xratio,455*Yratio, 615*Xratio,580*Yratio, hPaint(20));
        canvas.drawRect(585*Xratio,585*Yratio, 615*Xratio,710*Yratio, hPaint(20));
        canvas.drawRect(585*Xratio,715*Yratio, 615*Xratio,840*Yratio, hPaint(20));
        canvas.drawRect(595*Xratio, 455*Yratio, 605*Xratio,840*Yratio, ownerPaint(allPaths.get(20)));
        canvas.restore();

        //LaGrand-Burns
        canvas.save();
        canvas.rotate(28,1750*Xratio,350*Yratio);
        canvas.drawRect(1735*Xratio, 385*Yratio, 1765*Xratio, 495*Yratio, orangePaint);
        canvas.drawRect(1735*Xratio, 500*Yratio, 1765*Xratio, 610*Yratio, orangePaint);
        canvas.drawRect(1735*Xratio, 615*Yratio, 1765*Xratio, 725*Yratio, orangePaint);
        canvas.drawRect(1735*Xratio, 730*Yratio, 1765*Xratio, 840*Yratio, orangePaint);
        canvas.drawRect(1735*Xratio, 385*Yratio, 1765*Xratio, 495*Yratio, hPaint(4));
        canvas.drawRect(1735*Xratio, 500*Yratio, 1765*Xratio, 610*Yratio, hPaint(4));
        canvas.drawRect(1735*Xratio, 615*Yratio, 1765*Xratio, 725*Yratio, hPaint(4));
        canvas.drawRect(1735*Xratio, 730*Yratio, 1765*Xratio, 840*Yratio, hPaint(4));
        canvas.drawRect(1745*Xratio, 385*Yratio, 1755*Xratio,840*Yratio, ownerPaint(allPaths.get(4)));
        canvas.restore();

        //Euguene-Bend
        canvas.save();
        canvas.rotate(-89,570*Xratio,670*Yratio);
        canvas.drawRect(555*Xratio, 720*Yratio, 585*Xratio, 885*Yratio, pinkPaint);
        canvas.drawRect(555*Xratio, 890*Yratio, 585*Xratio, 1050*Yratio,pinkPaint);
        canvas.drawRect(555*Xratio, 720*Yratio, 585*Xratio, 885*Yratio, hPaint(9));
        canvas.drawRect(555*Xratio, 890*Yratio, 585*Xratio, 1050*Yratio,hPaint(9));
        canvas.drawRect(565*Xratio, 720*Yratio, 575*Xratio,1050*Yratio, ownerPaint(allPaths.get(9)));
        canvas.restore();

        //Eugene-Roseburg
        canvas.save();
        canvas.rotate(7,570*Xratio,670*Yratio);
        canvas.drawRect(530*Xratio, 705*Yratio, 560*Xratio, 800*Yratio, greyPaint);
        canvas.drawRect(530*Xratio, 805*Yratio, 560*Xratio, 900*Yratio, greyPaint);
        canvas.drawRect(530*Xratio, 705*Yratio, 560*Xratio, 800*Yratio, hPaint(32));
        canvas.drawRect(530*Xratio, 805*Yratio, 560*Xratio, 900*Yratio, hPaint(32));
        canvas.drawRect(540*Xratio, 705*Yratio, 550*Xratio,900*Yratio, ownerPaint(allPaths.get(32)));
        canvas.drawRect(565*Xratio, 705*Yratio, 595*Xratio, 800*Yratio, greyPaint);
        canvas.drawRect(565*Xratio, 805*Yratio, 595*Xratio, 900*Yratio, greyPaint);
        canvas.drawRect(565*Xratio, 705*Yratio, 595*Xratio, 800*Yratio, hPaint(33));
        canvas.drawRect(565*Xratio, 805*Yratio, 595*Xratio, 900*Yratio, hPaint(33));
        canvas.drawRect(575*Xratio, 705*Yratio, 585*Xratio,900*Yratio, ownerPaint(allPaths.get(33)));
        canvas.restore();

        //Bend-KFalls
        canvas.save();
        canvas.rotate(12,1000*Xratio,670*Yratio);
        canvas.drawRect(985*Xratio, 705*Yratio, 1015*Xratio, 820*Yratio,orangePaint);
        canvas.drawRect(985*Xratio, 825*Yratio, 1015*Xratio, 940*Yratio,orangePaint);
        canvas.drawRect(985*Xratio, 945*Yratio, 1015*Xratio, 1060*Yratio,orangePaint);
        canvas.drawRect(985*Xratio, 1065*Yratio, 1015*Xratio, 1180*Yratio,orangePaint);
        canvas.drawRect(985*Xratio, 705*Yratio, 1015*Xratio, 820*Yratio,hPaint(38));
        canvas.drawRect(985*Xratio, 825*Yratio, 1015*Xratio, 940*Yratio,hPaint(38));
        canvas.drawRect(985*Xratio, 945*Yratio, 1015*Xratio, 1060*Yratio,hPaint(38));
        canvas.drawRect(985*Xratio, 1065*Yratio, 1015*Xratio, 1180*Yratio,hPaint(38));
        canvas.drawRect(995*Xratio, 705*Yratio, 1005*Xratio,1180*Yratio, ownerPaint(allPaths.get(38)));

        canvas.restore();

        //Bend-Burns
        canvas.save();
        canvas.rotate(-73, 1000*Xratio,670*Yratio);
        canvas.drawRect(985*Xratio, 710*Yratio, 1015*Xratio, 845*Yratio,greyPaint);
        canvas.drawRect(985*Xratio, 850*Yratio, 1015*Xratio, 985*Yratio,greyPaint);
        canvas.drawRect(985*Xratio, 990*Yratio, 1015*Xratio, 1120*Yratio,greyPaint);
        canvas.drawRect(985*Xratio, 710*Yratio, 1015*Xratio, 845*Yratio,hPaint(36));
        canvas.drawRect(985*Xratio, 850*Yratio, 1015*Xratio, 985*Yratio,hPaint(36));
        canvas.drawRect(985*Xratio, 990*Yratio, 1015*Xratio, 1120*Yratio,hPaint(36));
        canvas.drawRect(995*Xratio, 710*Yratio, 1005*Xratio,1120*Yratio, ownerPaint(allPaths.get(36)));
        canvas.restore();

        //Burns-Lakeview
        canvas.save();
        canvas.rotate(33,1500*Xratio, 820*Yratio);
        canvas.drawRect(1485*Xratio, 855*Yratio, 1515*Xratio, 975*Yratio,pinkPaint);
        canvas.drawRect(1485*Xratio, 980*Yratio, 1515*Xratio, 1100*Yratio,pinkPaint);
        canvas.drawRect(1485*Xratio, 1105*Yratio, 1515*Xratio, 1225*Yratio,pinkPaint);
        canvas.drawRect(1485*Xratio, 855*Yratio, 1515*Xratio, 975*Yratio,hPaint(11));
        canvas.drawRect(1485*Xratio, 980*Yratio, 1515*Xratio, 1100*Yratio,hPaint(11));
        canvas.drawRect(1485*Xratio, 1105*Yratio, 1515*Xratio, 1225*Yratio,hPaint(11));
        canvas.drawRect(1495*Xratio, 855*Yratio, 1505*Xratio,1225*Yratio, ownerPaint(allPaths.get(11)));
        canvas.restore();

        //CoosBoy-Roseburg
        canvas.save();
        canvas.rotate(-80,320*Xratio,880*Yratio);
        canvas.drawRect(305*Xratio, 915*Yratio,335*Xratio, 1050*Yratio, white);
        canvas.drawRect(305*Xratio, 915*Yratio,335*Xratio, 1050*Yratio, hPaint(22));
        canvas.drawRect(315*Xratio, 915*Yratio, 325*Xratio,1050*Yratio, ownerPaint(allPaths.get(22)));
        canvas.restore();

        //CoosBoy-Grants Pass
        canvas.save();
        canvas.rotate(-38, 320*Xratio,880*Yratio);
        canvas.drawRect(305*Xratio,915*Yratio, 335*Xratio, 1035*Yratio,blackPaint);
        canvas.drawRect(305*Xratio,1040*Yratio, 335*Xratio, 1160*Yratio,blackPaint);
        canvas.drawRect(305*Xratio,915*Yratio, 335*Xratio, 1035*Yratio,hPaint(17));
        canvas.drawRect(305*Xratio,1040*Yratio, 335*Xratio, 1160*Yratio,hPaint(17));
        canvas.drawRect(315*Xratio, 915*Yratio, 325*Xratio,1160*Yratio, ownerPaint(allPaths.get(17)));
        canvas.restore();

        //Roseburg-Grants Pass
        canvas.save();
        canvas.rotate(3,530*Xratio,920*Yratio);
        canvas.drawRect(515*Xratio, 955*Yratio, 545*Xratio, 1080*Yratio, greyPaint);
        canvas.drawRect(515*Xratio, 955*Yratio, 545*Xratio, 1080*Yratio, hPaint(34));
        canvas.drawRect(525*Xratio, 955*Yratio, 535*Xratio,1080*Yratio, ownerPaint(allPaths.get(34)));
        canvas.restore();

        //Roseburg-KFalls
        canvas.save();
        canvas.rotate(-52,530*Xratio,920*Yratio);
        canvas.drawRect(515*Xratio, 955*Yratio, 545*Xratio, 1075*Yratio, greyPaint);
        canvas.drawRect(515*Xratio, 1080*Yratio, 545*Xratio, 1200*Yratio, greyPaint);
        canvas.drawRect(515*Xratio, 1205*Yratio, 545*Xratio, 1325*Yratio, greyPaint);
        canvas.drawRect(515*Xratio, 955*Yratio, 545*Xratio, 1075*Yratio, hPaint(35));
        canvas.drawRect(515*Xratio, 1080*Yratio, 545*Xratio, 1200*Yratio, hPaint(35));
        canvas.drawRect(515*Xratio, 1205*Yratio, 545*Xratio, 1325*Yratio, hPaint(35));
        canvas.drawRect(525*Xratio, 955*Yratio, 535*Xratio,1325*Yratio, ownerPaint(allPaths.get(35)));
        canvas.restore();

        //Grants Pass-KFalls
        canvas.save();
        canvas.rotate(-77,520*Xratio,1125*Yratio);
        canvas.drawRect(505*Xratio,1170*Yratio,535*Xratio, 1295*Yratio, orangePaint);
        canvas.drawRect(505*Xratio,1300*Yratio,535*Xratio, 1425*Yratio, orangePaint);
        canvas.drawRect(505*Xratio,1170*Yratio,535*Xratio, 1295*Yratio, hPaint(5));
        canvas.drawRect(505*Xratio,1300*Yratio,535*Xratio, 1425*Yratio, hPaint(5));
        canvas.drawRect(515*Xratio, 1170*Yratio, 525*Xratio,1425*Yratio, ownerPaint(allPaths.get(5)));
        canvas.restore();

        //KFalls - LakeView
        canvas.save();
        canvas.rotate(-90, 880*Xratio,1195*Yratio);
        canvas.drawRect(865*Xratio,1230*Yratio,895*Xratio, 1365*Yratio,white );
        canvas.drawRect(865*Xratio,1370*Yratio,895*Xratio, 1505*Yratio,white );
        canvas.drawRect(865*Xratio,1230*Yratio,895*Xratio, 1365*Yratio,hPaint(23) );
        canvas.drawRect(865*Xratio,1370*Yratio,895*Xratio, 1505*Yratio,hPaint(23) );
        canvas.drawRect(875*Xratio, 1230*Yratio, 885*Xratio,1505*Yratio, ownerPaint(allPaths.get(23)));
        canvas.restore();


        //draws the tickets


//        if(curTickets.size() > 0){
//            canvas.drawText(curTickets.get(0).toString(), 20.f*Xratio,1310.f*Yratio,ticketTextPaint);
//            if(curTickets.size() > 1){
//                canvas.drawText(curTickets.get(1).toString(), 20.f*Xratio,1390.f*Yratio,ticketTextPaint);
//            }
//        }
        //sets the players tickets to the ticket list
        text.setText("");
        for(int i = 0; i < curTickets.size(); i++){
            text.append(curTickets.get(i).toString() + "\n");
            text.append("____________");
        }



        //draw the ticket chooser location
        canvas.drawRect(1690*Xratio, 890*Yratio, 2110 *Xratio, 1210*Yratio, blackPaint);
        canvas.drawRect(1700*Xratio, 900*Yratio , 2100*Xratio, 1045*Yratio, ticketPaint);
        canvas.drawRect(1700*Xratio, 1055*Yratio , 2100*Xratio, 1200*Yratio, ticketPaint);


        //shows ticket if there is tickets in shown ticket
        if(shownTickets == null || shownTickets.size() == 0){
            canvas.drawText("????????", 1710.f *Xratio, 980.f*Yratio, ticketTextPaint);
            canvas.drawText("????????", 1710.f *Xratio, 1130.f*Yratio, ticketTextPaint);
        } else {
            canvas.drawText(shownTickets.get(0).toString(), 1710.f*Xratio , 980.f*Yratio, ticketTextPaint);
            canvas.drawText(shownTickets.get(1).toString(), 1710.f*Xratio , 1130.f*Yratio, ticketTextPaint);
        }

        //highlights the ticket selected
        if(selectedTickets.get(0) == 1){
            canvas.drawRect(1690*Xratio, 890*Yratio, 2110*Xratio, 1045*Yratio, highlightPaint);
        }
        if(selectedTickets.get(1) == 1){
            canvas.drawRect(1690*Xratio, 1055*Yratio, 2110*Xratio, 1210*Yratio, highlightPaint);
        }

        //draws the number of cards the player for each card
        canvas.drawText(String.valueOf(curPlayer.getBlackCards()), 440*Xratio, 1300*Yratio, numCardsPaint);
        canvas.drawText(String.valueOf(curPlayer.getOrangeCards()), 720*Xratio, 1300*Yratio, numCardsPaint);
        canvas.drawText(String.valueOf(curPlayer.getPinkCards()), 1055*Xratio, 1300*Yratio, numCardsPaint);
        canvas.drawText(String.valueOf(curPlayer.getWhiteCards()), 1400*Xratio, 1300*Yratio, numCardsPaint);
        canvas.drawText(String.valueOf(curPlayer.getWildCards()), 1740*Xratio, 1300*Yratio, numCardsPaint);

        //highlights the train cards selected
        //random
        for(int i=0; i<2; i++){
            if(selected.get(1)){
                canvas.drawRect(2130*Xratio,(205+(205*5))*Yratio,2560*Xratio,(410+(205*5))*Yratio,buttonHighlight);
            }else if(selected.get(0)){
                canvas.drawRect(2130*Xratio,(205+(205*5))*Yratio,2560*Xratio,(410+(205*5))*Yratio,lightgreen);
            }
        }
        //face up
        for(int i=0; i<5; i++){
            if(selected.get(i+2)){
                canvas.drawRect(2130*Xratio,(205+(205*i))*Yratio,2560*Xratio,(410+(205*i))*Yratio,buttonHighlight);
            }
        }


        //highlights the players cards
        int j = -1;
        if(selectedCardColor == TTRState.CARD.BLACKCARD){
            j=0;
        }else if(selectedCardColor == TTRState.CARD.ORANGECARD){
            j=1;
        }else if(selectedCardColor == TTRState.CARD.PINKCARD){
            j=2;
        }else if(selectedCardColor == TTRState.CARD.WHITECARD){
            j=3;
        }else if(selectedCardColor == TTRState.CARD.WILDCARD){
            j=4;
        }
        if(j != -1) {
            canvas.drawRect((230 + 335 * j) * Xratio, 1340 * Yratio, (575 + 335 * j) * Xratio, 1800 * Yratio, buttonHighlight);
        }
    }
    //function to change the gui given the information given
    public void setState(TTRState state, ArrayList<Boolean> selected, ArrayList<Integer> selectedTickets, Path p) {
        //sets the values of the players hand size, ticket size, train count
        Player player0;
        Player player1;
        Player player2 = new Player(2);
        Player player3 = new Player(3);

        this.state = new TTRState(state);
        ArrayList<Player> players = state.getPlayers();
        int numPlayers = state.getNumPlayers();
        player0 = players.get(0);
        player0Trains = player0.getNumTrains();
        player0Cards = player0.getCardHand().size();
        player0Tickets = player0.getTickets().size();
        player1 = players.get(1);
        player1Trains = player1.getNumTrains();
        player1Cards = player1.getCardHand().size();
        player1Tickets = player1.getTickets().size();
        if (state.getNumPlayers() > 2) {
            player2 = players.get(2);
            player2Trains = player2.getNumTrains();
            player2Cards = player2.getCardHand().size();
            player2Tickets = player2.getTickets().size();
            if (state.getNumPlayers() == 4) {
                player3 = players.get(3);
                player3Trains = player3.getNumTrains();
                player3Cards = player3.getCardHand().size();
                player3Tickets = player3.getTickets().size();
            }
        }
        if (state.getWhosTurn() == 0) {
            curPlayer = player0;
        } else if (state.getWhosTurn() == 1) {
            curPlayer = player1;
        } else if (state.getWhosTurn() == 2) {
            curPlayer = player2;
        } else if (state.getWhosTurn() == 3) {
            curPlayer = player3;
        }

        this.allPaths = state.getAllPaths();
        this.selected = selected;
        this.selectedTickets = selectedTickets;
        if (this.state.getShownTickets() == null) {
            shownTickets = null;
        } else {
            shownTickets = new ArrayList<Ticket>(this.state.getShownTickets());
        }

        curTickets = curPlayer.getTickets();
        curPath = p;
        if (!(shownTickets == null || shownTickets.size() == 0)) {
            System.out.println(shownTickets.get(0).toString());
        }


    }
    //helper function for showing the selected paths
    public void setSelectedView(ArrayList<Boolean> selected){
        this.selected = selected;
    }

    public void setSelectedCardColor(TTRState.CARD col){
        selectedCardColor = col;
    }

    public void resetSelectedCardColor(){
        selectedCardColor = null;
    }

    //sets the edit text to the current one
    public void setText(EditText editText){
        text = editText;
    }

    //helper function to get the paint of the current owner of the path
    private Paint ownerPaint(Path p){
        int owner = p.getPathOwner();
        if(owner == 0){
            return greenPaint;
        } else if(owner == 1){
            return redPaint;
        } else if(owner == 2){
            return bluePaint;
        } else if(owner == 3){
            return yellowPaint;
        } else {
            return emptyPaint;
        }
    }


    //helper function paint if the path is selected
    private Paint hPaint(int path){
        if(curPath == allPaths.get(path)){
            return higlighter;
        } else {
            return emptyPaint;
        }
    }
}
