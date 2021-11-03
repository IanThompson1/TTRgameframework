package com.example.tttgameframework.tickettoride.players;

import android.view.MotionEvent;
import android.view.View;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameHumanPlayer;

public class TTRHumanPlayer extends GameHumanPlayer implements View.OnTouchListener{
    private GameMainActivity myActivtiy;
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
        return null;
    }
//impolement as TTT
    @Override
    public void receiveInfo(GameInfo info) {
    //if game info is gamestate then use gamestate to update what is being drawn
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
//impolement as TTT
    }
}
