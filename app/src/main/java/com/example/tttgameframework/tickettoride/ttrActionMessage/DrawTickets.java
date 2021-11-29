package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.io.Serializable;
import java.util.ArrayList;

public class DrawTickets extends GameAction implements Serializable {
    private static final long serialVersionUID = 7542321013488624311L;
    private ArrayList<Integer> selectedTickets;// arraylist of length 2, 1 if a ticket is selected, and 0 if it is not
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DrawTickets(GamePlayer player, ArrayList<Integer> tickets) {
        super(player);
        if(tickets == null){
            selectedTickets = null;
        }else {
            selectedTickets = new ArrayList<Integer>();
            for (Integer current : tickets) {
                selectedTickets.add(current);
            }
        }
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
