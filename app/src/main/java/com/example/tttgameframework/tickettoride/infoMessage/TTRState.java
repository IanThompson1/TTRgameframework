package com.example.tttgameframework.tickettoride.infoMessage;

import com.example.tttgameframework.GameFramework.infoMessage.GameState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TTRState extends GameState implements Serializable {
    //Tag for logging
    private static final String TAG = "TTTState";
    private static final long serialVersionUID = 7552321013488624386L;
    private ArrayList<Ticket> shownTickets = null;
    public enum CARD{
        WHITECARD,
        BLACKCARD,
        PINKCARD,
        ORANGECARD,
        WILDCARD,
    }

    public enum CITY{
        ASTORIA,
        TILLAMOOK,
        NEWPORT,
        COOSBAY,
        PORTLAND,
        SALEM,
        EUGENE,
        ROSEBURG,
        GPASS,
        THEDALLES,
        BEND,
        KFALLS,
        PENDLETON,
        LAGRANDE,
        BURNS,
        LAKEVIEW
    }

    public int whosTurn;

    private ArrayList<Path> allPaths;
    private ArrayList<Player> allPlayers;

    private ArrayList<CARD> cardDeck;
    private ArrayList<CARD> faceUp;
    private ArrayList<Ticket> ticketDeck;

    private int numPlayers;


    public TTRState(int inNumPlayers){

        //set number of players
        numPlayers = inNumPlayers;
        whosTurn = 0;

        /**
         * create all paths
         * Path(length, node0, node1, color, owner)
         */
        //orange paths
        allPaths = new ArrayList<Path>();
        allPaths.add(new Path(1, CITY.ASTORIA, CITY.TILLAMOOK,
                Path.COLOR.ORANGEPATH, -1));
        allPaths.add(new Path(2, CITY.PORTLAND, CITY.THEDALLES,
                Path.COLOR.ORANGEPATH, -1));
        allPaths.add(new Path(2, CITY.SALEM, CITY.EUGENE,
                Path.COLOR.ORANGEPATH, -1));
        allPaths.add(new Path(1, CITY.PENDLETON, CITY.LAGRANDE,
                Path.COLOR.ORANGEPATH, -1));
        allPaths.add(new Path(4, CITY.LAGRANDE, CITY.BURNS,
                Path.COLOR.ORANGEPATH, -1));
        allPaths.add(new Path(2, CITY.GPASS, CITY.KFALLS,
                Path.COLOR.ORANGEPATH, -1));


        //Pink Paths
        allPaths.add(new Path(1, CITY.TILLAMOOK, CITY.PORTLAND,
                Path.COLOR.PINKPATH, -1));
        allPaths.add(new Path(2, CITY.PORTLAND, CITY.THEDALLES,
                Path.COLOR.PINKPATH, -1));
        allPaths.add(new Path(3, CITY.COOSBAY, CITY.NEWPORT,
                Path.COLOR.PINKPATH, -1));
        allPaths.add(new Path(2, CITY.EUGENE, CITY.BEND,
                Path.COLOR.PINKPATH, -1));
        allPaths.add(new Path(4, CITY.BEND, CITY.PENDLETON,
                Path.COLOR.PINKPATH, -1));
        allPaths.add(new Path(3, CITY.LAKEVIEW, CITY.BURNS,
                Path.COLOR.PINKPATH, -1));


        //Black Paths
        allPaths.add(new Path(1, CITY.TILLAMOOK, CITY.PORTLAND,
                Path.COLOR.BLACKPATH, -1));
        allPaths.add(new Path(3, CITY.THEDALLES, CITY.PENDLETON,
                Path.COLOR.BLACKPATH, -1));
        allPaths.add(new Path(1, CITY.PENDLETON, CITY.LAGRANDE,
                Path.COLOR.BLACKPATH, -1));
        allPaths.add(new Path(2, CITY.SALEM, CITY.EUGENE,
                Path.COLOR.BLACKPATH, -1));
        allPaths.add(new Path(4, CITY.BEND, CITY.KFALLS,
                Path.COLOR.BLACKPATH, -1));
        allPaths.add(new Path(2, CITY.COOSBAY, CITY.GPASS,
                Path.COLOR.BLACKPATH, -1));


        //White Paths
        allPaths.add(new Path(1, CITY.ASTORIA, CITY.TILLAMOOK,
                Path.COLOR.WHITEPATH, -1));
        allPaths.add(new Path(3, CITY.THEDALLES, CITY.PENDLETON,
                Path.COLOR.WHITEPATH, -1));
        allPaths.add(new Path(3, CITY.SALEM, CITY.BEND,
                Path.COLOR.WHITEPATH, -1));
        allPaths.add(new Path(2, CITY.NEWPORT, CITY.EUGENE,
                Path.COLOR.WHITEPATH, -1));
        allPaths.add(new Path(1, CITY.COOSBAY, CITY.ROSEBURG,
                Path.COLOR.WHITEPATH, -1));
        allPaths.add(new Path(2, CITY.KFALLS, CITY.LAKEVIEW,
                Path.COLOR.WHITEPATH, -1));


        //Gray Paths
        allPaths.add(new Path(2, CITY.ASTORIA, CITY.PORTLAND,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(2, CITY.ASTORIA, CITY.PORTLAND,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(1, CITY.SALEM, CITY.PORTLAND,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(1, CITY.SALEM, CITY.PORTLAND,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(3, CITY.THEDALLES, CITY.BEND,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(3, CITY.THEDALLES, CITY.BEND,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(1, CITY.TILLAMOOK, CITY.NEWPORT,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(1, CITY.TILLAMOOK, CITY.NEWPORT,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(2, CITY.EUGENE, CITY.ROSEBURG,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(2, CITY.EUGENE, CITY.ROSEBURG,
                Path.COLOR.GREYPATH, -1));
        //single greys
        allPaths.add(new Path(1, CITY.ROSEBURG, CITY.GPASS,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(3, CITY.ROSEBURG, CITY.KFALLS,
                Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(3, CITY.BEND, CITY.BURNS,
                Path.COLOR.GREYPATH, -1));


        /**
         * Creates all players
         * Player(playerNum)
         */
        allPlayers = new ArrayList<Player>();

        allPlayers.add(new Player(0));
        allPlayers.add(new Player(1));

        if (numPlayers > 2){
            //three players
            allPlayers.add(new Player(2));
            if (numPlayers == 4){
                //four players
                allPlayers.add(new Player(3));
            }
        }


        //create ticket deck
        ticketDeck = new ArrayList<Ticket>();
        ticketDeck.add(new Ticket(8,CITY.ASTORIA,CITY.LAGRANDE));
        ticketDeck.add(new Ticket(5,CITY.ASTORIA,CITY.COOSBAY));
        ticketDeck.add(new Ticket(5,CITY.TILLAMOOK,CITY.BEND));
        ticketDeck.add(new Ticket(7,CITY.TILLAMOOK,CITY.GPASS));
        ticketDeck.add(new Ticket(6,CITY.PENDLETON,CITY.SALEM));
        ticketDeck.add(new Ticket(8,CITY.EUGENE,CITY.LAKEVIEW));
        ticketDeck.add(new Ticket(8,CITY.THEDALLES,CITY.KFALLS));
        ticketDeck.add(new Ticket(9,CITY.NEWPORT,CITY.BURNS));
        ticketDeck.add(new Ticket(5,CITY.PORTLAND,CITY.BEND));
        ticketDeck.add(new Ticket(5,CITY.ROSEBURG,CITY.PORTLAND));
        ticketDeck.add(new Ticket(10,CITY.PENDLETON,CITY.KFALLS));
        ticketDeck.add(new Ticket(13,CITY.LAGRANDE,CITY.GPASS));
        ticketDeck.add(new Ticket(6,CITY.COOSBAY,CITY.BEND));

        /**
         * Card face up and down deck
         *
         */
        cardDeck = new ArrayList<CARD>();
        faceUp = new ArrayList<CARD>();
        for (int i = 0; i < 12; i++) {
            cardDeck.add(CARD.ORANGECARD);
        }
        for (int i = 0; i < 12; i++) {
            cardDeck.add(CARD.PINKCARD);
        }
        for (int i = 0; i < 12; i++) {
            cardDeck.add(CARD.BLACKCARD);
        }
        for (int i = 0; i < 12; i++) {
            cardDeck.add(CARD.WHITECARD);
        }
        for (int i = 0; i < 14; i++) {
            cardDeck.add(CARD.WILDCARD);
        }
        Collections.shuffle(cardDeck);

        //sets up face up card deck
        for (int i = 0; i < 5; i++){
            faceUp.add(cardDeck.get(0));
            cardDeck.remove(0);
        }

    }

    /** copy constructor for TTRGameState
     *
     * @param other TTRGameState being copied
     */
    public TTRState(TTRState other){
        //copies everything
        // same number of :new's
        this.whosTurn = other.whosTurn;

        //creates a new ArrayList for the copied allPaths
        this.allPaths = new ArrayList<Path>(other.allPaths.size());

        //for every path in the old allPaths, create a new path and add it to the new TTRGameState
        for(Path p: other.allPaths){
            this.allPaths.add(new Path(p));
        }

        //creates a new ArrayList for the copied allPlayers
        this.allPlayers = new ArrayList<Player>(other.allPlayers.size());

        //for every player in the old allPlayers, create a new player and add it to the new TTRGameState
        for(Player p: other.allPlayers){
            this.allPlayers.add(new Player(p));
        }

        //creates a new ArrayList for the copied cardDeck
        this.cardDeck = new ArrayList<CARD>(other.cardDeck.size());

        //populates new cardDeck
        this.cardDeck.addAll(other.cardDeck);

        //creates a new ArrayList for the copied faceUp
        this.faceUp = new ArrayList<CARD>(other.faceUp.size());
        this.faceUp.addAll(other.faceUp);

        //creates a new ArrayList for the copied faceUp
        this.ticketDeck = new ArrayList<Ticket>(other.ticketDeck.size());

        //for every ticket in the old ticketDeck, create a new ticket and add it to the new TTRGameState
        for(Ticket t: other.ticketDeck){
            this.ticketDeck.add(new Ticket(t));
        }

        this.numPlayers = other.numPlayers;
    }

    //getter method for cardDeck
    public ArrayList<CARD> getCardDeck() {
        return cardDeck;
    }

    //setter method for cardDeck
    public void setCardDeck(ArrayList<CARD> cardDeck1){
        cardDeck.clear();
        cardDeck.addAll(cardDeck1);
    }

    //method that allows players to get tickets.
    public ArrayList<Ticket> getTickets(){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for(int i = 0; i < 3; i++){
            tickets.add(ticketDeck.get(i));
            ticketDeck.remove(i);
        }
        return tickets;
    }


    //check if the ticket desk is empty
    public boolean ticketDeckEmpty(){
        return ticketDeck.isEmpty();
    }


    //toString method to display TTRGameState information
    public String toString(){
        String output = "";
        //strings for the paths
        output += "length\tnode0\tnode1\tpath color\tpath owner\n";
        for(Path current: allPaths) {
            output += current.getLength() + "\t";
            output += current.getNode0().name() + "\t";
            output += current.getNode1().name() + "\t";
            output += current.getPathColor().name() + "\t";
            output += current.getPathOwner() + "\t\n";
        }

        //strings for the tickets
        output += "is complete\tpoint value\tnode0\tnode1\n";
        for(Ticket current: ticketDeck){
            output += current.getIsComplete() + "\t";
            output += current.getPointValue() + "\t";
            output += current.getNode0().name() + "\t";
            output += current.getNode1().name() + "\t\n";
        }

        //strings for the players
        output += "player name\ttrains left\n";
        for(Player current: allPlayers) {
            output += current.getName() + "\t";
            output += current.getNumTrains() + "\t\n";

            //string for players tickets
            output += "TICKETS:\t";
            for (Ticket tik : current.getTickets()) {
                output += tik.getIsComplete() + "\t";
                output += tik.getPointValue() + "\t";
                output += tik.getNode0().name() + "\t";
                output += tik.getNode1().name() + "\t";
            }
            output += "\n";

            //string for players cards
            output += "CARDS:\t";
            for(CARD card:current.getCardHand()){
                output += card.name() + "\t";
            }
            output += "\n";
        }

        return output;
    }

    //for the actions
    public Player getPlayer1() {
        return allPlayers.get(0);
    }

    //get ArrayList of Players
    public ArrayList<Player> getPlayers(){
        return allPlayers;
    }

    //for the place train action
    public Path getPath(){
        Path path = allPaths.get(1);
        return path;
    }

    public ArrayList<Path> getAllPaths(){
        return allPaths;
    }

    //get the number of the player whose turn it is
    public int getTurn(){
        return whosTurn;
    }

    //get the number of players there are in the game
    public int getNumPlayers(){
        return numPlayers;
    }

    public ArrayList<Ticket> getShownTickets() {
        return shownTickets;
    }

    public void addShownTicket(Ticket tik) {
        shownTickets.add(tik);
    }

    public void removeShownTicket(Ticket tik){
        shownTickets.remove(tik);
    }

    public ArrayList<CARD> getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(ArrayList<CARD> faceUp) {
        this.faceUp = faceUp;
    }
}
