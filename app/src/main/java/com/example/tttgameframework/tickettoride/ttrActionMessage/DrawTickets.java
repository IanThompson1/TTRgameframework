package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

public class DrawTickets extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DrawTickets(GamePlayer player) {
        super(player);
    }
}
