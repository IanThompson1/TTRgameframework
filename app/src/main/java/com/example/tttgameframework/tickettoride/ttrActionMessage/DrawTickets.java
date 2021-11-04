package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.util.ArrayList;

public class DrawTickets extends GameAction {
    private ArrayList<Integer> selectedTickets;// arraylist of length 2, 1 if a ticket is selected, and 0 if it is not
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DrawTickets(GamePlayer player) {
        super(player);
        selectedTickets = null;
    }

    public ArrayList<Integer> getSelected() {
        return selectedTickets;
    }

    public void setSelected(ArrayList<Integer> selected) {
        this.selectedTickets = selected;
    }

    public void resetSelectedTickets(){
        selectedTickets = null;
    }
}
