package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;

import java.util.ArrayList;

public class PlaceTrains extends GameAction {
    private Path selectedPath;
    private int numberOfWilds;
    private Path.COLOR color;
    //this is an array list of integers containing all 0's except for one 1 depicting which path is selected
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlaceTrains(GamePlayer player,Path SelectedPath, int numWilds,Path.COLOR col) {
        super(player);
        this.selectedPath = SelectedPath;
        numberOfWilds = numWilds;
        color = col;
    }

    public void setSelectedPath(Path selectedPath) {
        this.selectedPath = selectedPath;
    }

    public Path getSelectedPath() {
        return selectedPath;
    }

    public void resetSelectedPath(){
        selectedPath = null;
    }

}
