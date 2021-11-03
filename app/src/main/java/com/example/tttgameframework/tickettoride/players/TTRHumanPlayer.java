package com.example.tttgameframework.tickettoride.players;

import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameHumanPlayer;
import com.example.tttgameframework.R;
import com.example.tttgameframework.tickettoride.TTRMainActivity;

public class TTRHumanPlayer extends GameHumanPlayer implements View.OnTouchListener{
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

    }
}
