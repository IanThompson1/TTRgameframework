package com.example.tttgameframework.tickettoride.players;

import android.view.MotionEvent;
import android.view.View;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameHumanPlayer;

public class TTRHumanPlayer1 extends GameHumanPlayer implements View.OnTouchListener{
    /**
     * constructor
     *
     * @param name the name of the player
     */
    public TTRHumanPlayer1(String name) {
        super(name);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
