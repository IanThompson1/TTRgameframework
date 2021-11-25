package com.example.tttgameframework.tickettoride.infoMessage;

import java.io.Serializable;

public class Path implements Serializable {
    private static final long serialVersionUID = 7542921013488624386L;
    //ENUM representing the possible path colors
    public enum COLOR{
        PINKPATH,
        ORANGEPATH,
        BLACKPATH,
        WHITEPATH,
        GREYPATH,
    }



    private int length;
    private TTRState.CITY node0;
    private TTRState.CITY node1;
    private COLOR pathColor;
    private int pathOwner;//NUMBER


    //Path constructor
    public Path(int length, TTRState.CITY node0, TTRState.CITY node1, COLOR col, int owner){
        this.length = length;
        this.node0 = node0;
        this.node1 = node1;
        pathColor = col;
        pathOwner = owner;

    }

    //Path copy constructor
    public Path(Path p){
        this.length = p.length;
        this.node0 = p.node0;
        this.node1 = p.node1;
        this.pathColor = p.pathColor;
        this.pathOwner = p.pathOwner;
    }

    //method to get the length of a path
    public int getLength(){
        return length;
    }

    //method to get the first city on the path
    public TTRState.CITY getNode0(){
        return node0;
    }

    //method to get the second city on the path
    public TTRState.CITY getNode1(){
        return node1;
    }

    //method to get the color of the path
    public COLOR getPathColor(){
        return pathColor;
    }

    //method to get the name of the player that owns the path
    public int getPathOwner(){
        return pathOwner;
    }

    //method to set the pathOwner
    public void setPathOwner(int owner){
        pathOwner = owner;
    }

}
