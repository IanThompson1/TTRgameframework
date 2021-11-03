package com.example.tttgameframework.tickettoride.players;

import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameComputerPlayer;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;

import java.util.Random;

public class TTRComputerPlayer0 extends GameComputerPlayer {

    private static final Random rand = new Random();
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public TTRComputerPlayer0(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        TTRState state = (TTRState) info;

    //if its my turn take a turn
        if (state.getTurn() != playerNum){  //not sure
            return;
        }
        else {
            //if picking orignal ticket

            if (rand.nextInt(2) == 1){
                game.sendAction(new PlaceTrains(this));
                //send: state.getAllPaths().get(rand.nextInt(state.getAllPaths().size))
                //select all wilds and what color to use
            }
            else{
                //do
            }
        }
    }
}
