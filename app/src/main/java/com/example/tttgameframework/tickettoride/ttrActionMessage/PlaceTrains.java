package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.util.ArrayList;

public class PlaceTrains extends GameAction {
    private ArrayList<Integer> SelectedPath;
    //this is an array list of integers containing all 0's except for one 1 depicting which path is selected
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlaceTrains(GamePlayer player,ArrayList<Integer> selectedPath) {
        super(player);
        SelectedPath = selectedPath;
    }

    public void setSelectedPath(ArrayList<Integer> selectedPath) {
        SelectedPath = selectedPath;
    }

    public ArrayList<Integer> getSelectedPath() {
        return SelectedPath;
    }

    public void resetSelectedPath(){
        SelectedPath = null;
    }

}
