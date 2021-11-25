package com.example.tttgameframework.tickettoride.infoMessage;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 7542321013418624386L;
    private int PointValue;
    private TTRState.CITY node0;
    private TTRState.CITY node1;
    private boolean isComplete;

    //constructor for Ticket object
    public Ticket(int value, TTRState.CITY n0, TTRState.CITY n1){
        isComplete = false;
        PointValue = value;
        node0 = n0;
        node1 = n1;
    }

    //copy constructor for ticket object
    public Ticket(Ticket t){
        this.isComplete = t.isComplete;
        this.PointValue = t.PointValue;
        this.node0 = t.node0;
        this.node1 = t.node1;
    }

    //method to check if the ticket has been completed by a user
    public boolean getIsComplete(){
        return isComplete;
    }

    //method to return the points value of a ticket
    public int getPointValue(){
        return PointValue;
    }

    //method to get the first city on the ticket
    public TTRState.CITY getNode0(){
        return node0;
    }

    //method to get the second city on the ticket
    public TTRState.CITY getNode1(){
        return node1;
    }

    //to string method for showing tickets

    @Override
    public String toString() {
        return node0.name() + " - " + node1.name() + " " + PointValue;
    }
}
