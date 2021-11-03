package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.util.ArrayList;

public class DrawTrains extends GameAction {
    private ArrayList<Integer> SelectedTrains;
    //selected trains is an arraylist of 7 0's, the first 2 denoting how many random cards selected,
    //and the last 5 denoting which of the other cards are selected
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DrawTrains(GamePlayer player) {
        super(player);
    }

    public void setSelectedTrains(ArrayList<Integer> selectedTrains) {
        SelectedTrains = selectedTrains;
    }

    public ArrayList<Integer> getSelectedTrains() {
        return SelectedTrains;
    }
}
