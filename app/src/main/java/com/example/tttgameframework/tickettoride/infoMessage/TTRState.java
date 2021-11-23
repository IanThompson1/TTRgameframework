package com.example.tttgameframework.tickettoride.infoMessage;

import com.example.tttgameframework.GameFramework.infoMessage.GameState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class TTRState extends GameState implements Serializable {
    //Tag for logging
    private static final String TAG = "TTTState";
    private static final long serialVersionUID = 7542321013488624386L;
    private ArrayList<Ticket> shownTickets;
    public enum CARD{
        WHITECARD,
        BLACKCARD,
        PINKCARD,
        ORANGECARD,
        WILDCARD,
        NULLCARD
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
    } //16 cities in total

    private int whosTurn;

    private ArrayList<Path> allPaths;
    private ArrayList<Player> allPlayers;

    private ArrayList<CARD> cardDeck;
    private ArrayList<CARD> faceUp;
    private ArrayList<Ticket> ticketDeck;

    private int numPlayers;

    private HashMap<TTRState.CITY, CityNode> cityAdjList;


    public TTRState(int inNumPlayers){

        //set number of players
        numPlayers = inNumPlayers;
        whosTurn = 0;


        //create HashMap for adjacency list
        cityAdjList = new HashMap<>();

        //create CityNodes, add to adj list
        for(CITY c: CITY.values()){
            cityAdjList.put(c, new CityNode(c));
        }


        /**
         * create all paths
         * Path(length, node0, node1, color, owner)
         */
        //paths 0-5
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

        //paths 6-11
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

        //paths 12-17
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

        //paths 18-23
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

        //paths 24-38
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

        //PATHS NEED FIXIN
        allPaths.add(new Path(2, CITY.SALEM, CITY.NEWPORT, Path.COLOR.GREYPATH, -1));
        allPaths.add(new Path(4, CITY.BEND, CITY.KFALLS, Path.COLOR.ORANGEPATH, -1));

        //adding the paths as neighbors in the adjacency list
        for(Path p: allPaths){
            //add node0
            cityAdjList.get(p.getNode0()).addNeighbor(p);

            //add node 1

            cityAdjList.get(p.getNode1()).addNeighbor(p);
        }


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
                System.out.println("all players are created");
                System.out.println("all players are created");
                System.out.println("all players are created");
                System.out.println("all players are created");
                System.out.println("all players are created");

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
        Collections.shuffle(ticketDeck);

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


        shownTickets = new ArrayList<Ticket>();

    }

    /** copy constructor for TTRGameState
     *
     * @param other TTRGameState being copied
     */
    public TTRState(TTRState other){
        //copies everything
        // same number of :new's
        this.whosTurn = other.getWhosTurn();

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


        this.shownTickets = new ArrayList<Ticket>(other.shownTickets.size());

        for(Ticket x: other.shownTickets){
            this.shownTickets.add(new Ticket(x));
        }
    }

    //getter method for cardDeck
    public ArrayList<CARD> getCardDeck() {
        return cardDeck;
    }

    //setter method for cardDeck
    public void setCardDeck(ArrayList<CARD> cardDeck1){

        cardDeck = new ArrayList<CARD>(cardDeck1.size());
        System.out.println(cardDeck1.size());
        cardDeck.addAll(cardDeck1);
    }

    //method that allows players to get tickets.
    public ArrayList<Ticket> getTickets(){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for(int i = 0; i < 2; i++){
            tickets.add(ticketDeck.get(0));
            ticketDeck.remove(0);
        }
        return tickets;
    }

    public void setTicketDeck(ArrayList<Ticket> ticketDeck) {
        /*ticketDeck.clear();
        for (int i = 0; i < ticketDeck.size(); i++){
            this.ticketDeck.set(i, ticketDeck.get(i));
        }*/
        this.ticketDeck = ticketDeck;
    }

    public void addTicket(Ticket tik){
        ticketDeck.add(tik);
        Collections.shuffle(ticketDeck);
    }

    public ArrayList<Ticket> getTicketDeck() {
        return ticketDeck;
    }
/*
    //check if the ticket desk is empty
    public boolean ticketDeckEmpty(){
        if (ticketDeck == null){
            return true;
        }
        else if(ticketDeck.size() == 0){
            return true;
        }
        for (int i = 0; i < ticketDeck.size(); i++){
            if (ticketDeck.get(i) != null){
                return false;
            }
        }
        return true;
        //return ticketDeck.isEmpty();
    }
*/

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

    public void clearShownTickets(){
        shownTickets.clear();
    }

    public ArrayList<CARD> getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(ArrayList<CARD> faceUp) {
        for (int i = 0; i < 5; i++){
            if(faceUp == null){
                this.faceUp = null;
            }
            else {
                this.faceUp.set(i, faceUp.get(i));
            }
        }
    }

    public int getWhosTurn() {
        return whosTurn;
    }

    public void setWhosTurn(int whosTurn) {
        if(whosTurn > -1 && whosTurn < numPlayers) {
            this.whosTurn = whosTurn;
        }
    }

    public void addCard(CARD card){
        cardDeck.add(card);
        Collections.shuffle(cardDeck);
    }

    //comment
    public boolean ticket_completed(TTRState.CITY c0, TTRState.CITY c1, int owner){
        /*HashMap<TTRState.CITY, CityNode> marked = new HashMap<>(); //hashmap to store the marked off nodes


        return dfs(marked, c0, c1, owner);*/

        ArrayList<Path> visited = new ArrayList<>();

        return dfs(visited, c0, c1, owner);
    }

    //comment
    public boolean dfs(ArrayList<Path> visited, TTRState.CITY c0, TTRState.CITY c1, int owner){
        if(c1.equals(c0)){ //base case
            return true;
        }

        for(Path p: allPaths){
            if(p.getPathOwner() == owner && !visited.contains(p)) {
                visited.add(p);
                if (p.getNode0().equals(c0)) {
                    return dfs(visited, p.getNode1(), c1, owner);
                } else if (p.getNode1().equals(c0)) {
                    //visited.add(p);
                    return dfs(visited, p.getNode0(), c1, owner);
                }
            }
        }

        /*marked.put(c0, cityAdjList.get(c0));

        if(c0 == c1){
            return true;
        }

        HashMap<TTRState.CITY, Path> neighbors = Objects.requireNonNull(cityAdjList.get(c0)).getNeighbors();

        HashMap<TTRState.CITY, Boolean> visited = new HashMap<>();

        boolean reached = false;

        for(TTRState.CITY c: neighbors.keySet()){
            if(Objects.requireNonNull(neighbors.get(c)).getPathOwner() == owner){
                reached = dfs(marked, c, c1, owner);
            }

            if(reached){
                return true;
            }
        }


        return reached;*/
        return false; //dummy
    }
}
