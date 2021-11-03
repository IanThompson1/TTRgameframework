package com.example.tttgameframework.tickettoride.players;

import android.graphics.Color;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameHumanPlayer;
import com.example.tttgameframework.R;
import com.example.tttgameframework.tickettoride.TTRMainActivity;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;

import java.util.ArrayList;

public class TTRHumanPlayer extends GameHumanPlayer implements View.OnTouchListener, View.OnClickListener{
    /* instance variables */
    //these variables will reference widgets that will be modified during play
    private ImageButton blackTrainHandButton = null;
    private ImageButton orangeTrainHandButton = null;
    private ImageButton pinkTrainHandButton = null;
    private ImageButton whiteTrainHandButton = null;
    private ImageButton wildTrainHandButton = null;
    private Button confirmButton = null;
    private Button cancelButton = null;
    private ImageButton drawTicketsButton = null;
    private ImageButton faceup1Button = null;
    private ImageButton faceup2Button = null;
    private ImageButton faceup3Button = null;
    private ImageButton faceup4Button = null;
    private ImageButton faceup5Button = null;
    private ImageButton drawTrainButton = null;

    //this variable will hold the actions wishes to be taken the turn off.
    private ArrayList<GameAction> turnActions;

    private GameMainActivity myActivity; //android activity that we are running.

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public TTRHumanPlayer(String name) {
        super(name);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
        //this is where we will send actions to the game
    @Override
    public View getTopView() {
        //give id of our xml layout for G
        return myActivity.findViewById(R.id.mainView2);
    }
//impolement as TTT
    @Override
    public void receiveInfo(GameInfo info) {
    //if game info is gamestate then use gamestate to update what is being drawn
    if(!(info instanceof TTRState)){
        flash(Color.RED, 20);
        return;
    }

    TTRState state = new TTRState((TTRState) info);

    }


    @Override
    public void setAsGui(GameMainActivity activity) {
        //set the myActivity to remember activity
        myActivity = activity;

        //set the layout as the layout resource
        activity.setContentView(R.layout.activity_main);

        //init the widget reference member variables
        this.blackTrainHandButton = (ImageButton)activity.findViewById(R.id.blackTrainHand);
        this.orangeTrainHandButton = (ImageButton)activity.findViewById(R.id.orangeTrainHand);
        this.pinkTrainHandButton = (ImageButton)activity.findViewById(R.id.pinkTrainHand);
        this.whiteTrainHandButton = (ImageButton)activity.findViewById(R.id.whiteTrainHand);
        this.wildTrainHandButton = (ImageButton)activity.findViewById(R.id.wildTrainHand);
        this.confirmButton = (Button)activity.findViewById(R.id.ConfirmButton);
        this.cancelButton = (Button)activity.findViewById(R.id.CancelButton);
        this.drawTicketsButton = (ImageButton)activity.findViewById(R.id.DrawTicketButton);
        this.faceup1Button = (ImageButton)activity.findViewById(R.id.FaceUp1Button);
        this.faceup2Button = (ImageButton)activity.findViewById(R.id.FaceUp2Button);
        this.faceup3Button = (ImageButton)activity.findViewById(R.id.FaceUp3Button);
        this.faceup4Button = (ImageButton)activity.findViewById(R.id.FaceUp4Button);
        this.faceup5Button = (ImageButton)activity.findViewById(R.id.FaceUp5Button);
        this.drawTrainButton = (ImageButton)activity.findViewById(R.id.DrawTrainButton);

        //set listeners for button presses
        blackTrainHandButton.setOnClickListener(this);
        orangeTrainHandButton.setOnClickListener(this);
        pinkTrainHandButton.setOnClickListener(this);
        whiteTrainHandButton.setOnClickListener(this);
        wildTrainHandButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        drawTicketsButton.setOnClickListener(this);
        faceup1Button.setOnClickListener(this);
        faceup2Button.setOnClickListener(this);
        faceup3Button.setOnClickListener(this);
        faceup4Button.setOnClickListener(this);
        faceup5Button.setOnClickListener(this);
        drawTrainButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View button) {
        //if the player clicks draw train then send the action draw train
        if(button.getId() == R.id.DrawTrainButton){
            //asks if the user already has chosen 2 actions already
            if(turnActions.size() < 2){
                //if hasn't chosen 2 actions then add another action
                turnActions.add(new DrawTrains(this));
            } else {
                flash(Color.RED, 20);
            }

        //else if the player clicks on draw ticket then send the action draw ticket
        } else if(button.getId() == R.id.DrawTicketButton){
            game.sendAction(new DrawTickets(this));

        //else if the player clicks on a face up card
        } else if(button.getId() == R.id.FaceUp1Button) {
            if()
            if (turnActions.size() < 2) {
                turnActions.add(new DrawTrains(this));
            } else {
                flash(Color.RED, 20);
            }
        }

    }
}
