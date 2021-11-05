package com.example.tttgameframework.tickettoride.players;

import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameComputerPlayer;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;

import java.util.ArrayList;
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
                ArrayList<Boolean> selectedCards = new ArrayList<Boolean>();
                for (int i = 0; i < 7; i++){
                    selectedCards.add(false);
                }

                //do card draw
                if (rand.nextInt(2) == 1){
                    //draw face up cards
                    int card1 = rand.nextInt(5) + 2;
                    int card2 = rand.nextInt(5) + 2;

                    //check for duplicates
                    while (card1 == card2){
                        card2 = rand.nextInt(5) + 2;
                    }

                    //set which cards to draw
                    for (int i = 0; i < 7; i++){
                        if (i == card1 || i == card2){
                            selectedCards.set(i, true);
                        }
                    }
                }

                else{
                    //take two random cards
                    selectedCards.set(0, true);
                    selectedCards.set(1, true);
                }
                game.sendAction(new DrawTrains(this, selectedCards));
            }
        }
    }
}
