package com.example.tttgameframework.tickettoride.ttrActionMessage;

import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;

import java.io.Serializable;
import java.util.ArrayList;

public class PlaceTrains extends GameAction implements Serializable {
    private static final long serialVersionUID = 7542321013488624322L;
    private Path selectedPath;
    private int numberOfWilds;
    private TTRState.CARD color;
    private int pathNum;
    //this is an array list of integers containing all 0's except for one 1 depicting which path is selected
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlaceTrains(GamePlayer player,Path SelectedPath, int numWilds,TTRState.CARD col, int pathNum) {
        super(player);
        this.selectedPath = new Path(SelectedPath);
        numberOfWilds = numWilds;
        color = col;
        this.pathNum = pathNum;
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

    public TTRState.CARD getColor() {
        return color;
    }

    public int getNumberOfWilds() {
        return numberOfWilds;
    }

    public void setColor(TTRState.CARD color) {
        this.color = color;
    }

    public void setNumberOfWilds(int numberOfWilds) {
        this.numberOfWilds = numberOfWilds;
    }

    public int getPathNum() {
        return pathNum;
    }
}
