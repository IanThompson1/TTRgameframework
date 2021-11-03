package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.util.ArrayList;

public class DrawTickets extends GameAction {
    private ArrayList<Integer> selected;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DrawTickets(GamePlayer player) {
        super(player);
    }

    public ArrayList<Integer> getSelected() {
        return selected;
    }

    public void setSelected(ArrayList<Integer> selected) {
        this.selected = selected;
    }
}
