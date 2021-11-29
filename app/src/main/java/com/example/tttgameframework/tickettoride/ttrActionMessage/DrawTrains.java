package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.io.Serializable;
import java.util.ArrayList;

public class DrawTrains extends GameAction implements Serializable {
    private static final long serialVersionUID = 7542321013488624333L;
    private ArrayList<Boolean> SelectedTrains;
    //selected trains is an arraylist of 7 0's, the first 2 denoting how many random cards selected,
    //and the last 5 denoting which of the other cards are selected
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DrawTrains(GamePlayer player,ArrayList<Boolean> selectedTrains) {
        super(player);
        SelectedTrains = new ArrayList<Boolean>();
        for (Boolean current:selectedTrains) {
            SelectedTrains.add(current);
        }
    }

    public void setSelectedTrains(ArrayList<Boolean> selectedTrains) {
        SelectedTrains = selectedTrains;
    }

    public ArrayList<Boolean> getSelectedTrains() {
        return SelectedTrains;
    }

    public void resetSelectedTrains(){
        SelectedTrains = null;
    }
}
