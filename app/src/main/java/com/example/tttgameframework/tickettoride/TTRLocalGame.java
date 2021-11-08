package com.example.tttgameframework.tickettoride;

import com.example.tttgameframework.GameFramework.LocalGame;
import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.Player;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.infoMessage.Ticket;

import java.util.ArrayList;
import java.util.Collections;

import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;

public class TTRLocalGame extends LocalGame {
    private TTRState state;
    public TTRLocalGame(){
        super();
        super.state = new TTRState(2);
    }

    /** sendUpdatedStateTo
     *
     * Description: Notify the player that their state has changed. Send a game info object.
     *
     * @param p the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new TTRState(((TTRState) super.state)));
    }

    /** canMove()
     *
     * Description: determine if the player can make a move
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return boolean representing if the player ID passed is the current player
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((TTRState)state).getTurn();
    }


    /** checkIfGameOver()
     *
     * Description: Check if the game is over. If it is over, return who won the game.
     *              If not, return null.
     *
     * @return String that tells who has won the game, or null if the game is not over.
     */
    @Override
    protected String checkIfGameOver() {
        boolean gameOver = false;

        //get players of players
        Player[] playersArr = (Player[]) getPlayers();
        ArrayList<Player> players = new ArrayList<>();
        for(Player p: playersArr){
            players.add(p);
        }

        //1. Check if game is over.
        //      Game is over if any 1 player runs out of trains.
        for(Player p: players){
            if(p.getNumTrains() <= 0){ //if player has no trains left
                //mark game is over
                gameOver = true;
                break;
            }
        }

        //return if the game isn't over.
        if(!gameOver){
            return null;
        }

        //2. Compute the scores of all players
        //      Compute by adding the value of all the tickets completed by a player and
        //      subtracting the value of all the tickets a player holds but did not complete.
        //      longest continuous path gets 10 points ***BETA***
        //      path lengths gives you points. path L = 1 --> 1 pt
        //      path L = 2 --> 2 pts
        //      path L = 3 --> 4
        //      path L = 4 --> 7

        //create array for the scores of the players
        int scores[] = new int[((TTRState)state).getNumPlayers()];

        //2a. compute scores from tickets
        for(Player p: players){
            //get list of tickets they have
            ArrayList<Ticket> theseTickets = new ArrayList<Ticket>();
            theseTickets = p.getTickets();

            //compute now if the tickets are completed or not

            //loop through tickets and add to the players score.
            for(Ticket t: theseTickets){
                if(t.getIsComplete()){
                    scores[p.getName()] += t.getPointValue(); //add if completed
                }
                else{
                    scores[p.getName()] -= t.getPointValue(); //subtract if not completed
                }
            }
        }

        //2b. compute scores from building paths
        ArrayList<Path> thesePaths = state.getAllPaths();
        for(Path p: thesePaths){ //loop through all the paths
            if(p.getPathOwner() != -1){
                switch(p.getLength()){
                    case 1: //path L = 1 --> 1 pt
                        scores[p.getPathOwner()] += 1;
                        break;
                    case 2: //path L = 2 --> 2 pt2
                        scores[p.getPathOwner()] += 2;
                        break;
                    case 3: //path L = 3 --> 4 pt2
                        scores[p.getPathOwner()] += 4;
                        break;
                    case 4: //path L = 4 --> 7 pt2
                        scores[p.getPathOwner()] += 7;
                        break;
                }
            }
        }

        //2c. find the longest continuous path made ***BETA***


        //3. Compare scores to determine winner.
        int winnerID = 0;
        int maxScore = scores[0];
        for(int i = 1; i < scores.length; i++){
            if(scores[i] > maxScore){
                maxScore = scores[i];
                winnerID = i;
            }
        }

        //Player winner = players.get(winnerID);

        //4. Generate and return message of who the winner is.
        return winnerID + " is the winner.";

        //return null; //dummy
    } //checkIfGameOver()


    /** checkTicketComplete
     *
     * @param t the ticket being checked
     * @param p the player being checked
     *
     * @return  if the ticket is completed by the player, return true
     */
    public boolean checkTicketComplete(Ticket t, Player p){
        return state.ticket_completed(t.getNode0(), t.getNode1(), p.getName());
    }


    @Override
    protected boolean makeMove(GameAction action) {
        //check if it is the players turn
        if(getPlayerIdx(action.getPlayer()) != state.getWhosTurn()){
            return false;
        }
        if(action instanceof DrawTickets){
            if(((DrawTickets) action).getSelected() == null){
                //draw tickets to part of the screen
                ArrayList<Ticket> temp = state.getTickets();
                state.addShownTicket(temp.get(0));
                state.addShownTicket(temp.get(1));
                //invalidate(); ?
                return true;
            }else{
                //move tickets to hand
                ArrayList<Ticket> shown = state.getShownTickets();
                ArrayList<Integer> selected = ((DrawTickets) action).getSelected();
                Player user = state.getPlayers().get(state.getWhosTurn());
                //the one check is if neither card is selected
                int count =0;
                for(int t=0; t<selected.size(); t++){
                    if(selected.get(t) == 0){
                        count++;
                    }
                }
                if(count == 0 || count > 2){ //count should never be above 2
                    return false;
                }

                //loops through the 2 possible selected tickets
                for(int i=0; i<selected.size(); i++) {
                    //checks if the card is selected
                    if(selected.get(i) == 1) {
                        user.addTicket(shown.get(selected.get(i)));
                    }else{
                        state.addTicket(shown.get(selected.get(i)));//auto reshuffles
                    }
                }

                //stop drawing the tickets
                for(int i=selected.size()-1; i>=0; i--) {
                    state.removeShownTicket(shown.get(selected.get(i)));
                }
                Collections.shuffle(shown);
                //((DrawTickets) action).resetSelectedTickets(); this is a preference thing so might need it.
                //increment who's turn
                changeTurn(state);
            }
            return true;
        }else if(action instanceof DrawTrains){
            ArrayList<Boolean> selected = ((DrawTrains) action).getSelectedTrains();
            ArrayList<TTRState.CARD> random = state.getCardDeck();
            ArrayList<TTRState.CARD> faceUp = state.getFaceUp();
            int counter=0;
            //check if exactly 2 or 1 are selected
            for(int i=0; i<selected.size(); i++){
                if(selected.get(i)){
                    counter++;
                }
            }
            if(counter<1 || counter >2){
                return false;
            }
            //all checks
            for(int i=0; i<selected.size(); i++) {
                if (selected.get(i)){
                    Player wilds = state.getPlayers().get(state.getWhosTurn());
                    if(i < 2) {
                        if(counter ==1){
                            return false;
                        }
                    }else{
                        //face up cards
                        if(counter ==1 && faceUp.get(i) != TTRState.CARD.WILDCARD){
                            return false;
                        }else if(faceUp.get(i) == TTRState.CARD.WILDCARD){
                            return false;
                        }
                    }
                }
            }

            for(int i=0; i<selected.size(); i++) {
                if (selected.get(i)){
                    Player user = state.getPlayers().get(state.getWhosTurn());
                    if(i < 2) {
                        user.addCardHand(random.get(i));
                    }else{
                        //face up cards
                        user.addCardHand(faceUp.get(i));
                    }
                }
            }
            //resetting the card decks
            for(int i=selected.size()-1; i>=0; i--) {
                if (selected.get(i)){
                    if(i < 2) {
                        random.remove(random.get(i));
                    }else{ //i >= 2
                        //face up cards
                        faceUp.remove(faceUp.get(i));
                    }
                }
            }
            state.setCardDeck(random);
            state.setFaceUp(faceUp);//need better logic here for face up cards
            ((DrawTrains) action).resetSelectedTrains();
            changeTurn(state);
            return true;
        }else if(action instanceof PlaceTrains){
            //assuming just pressed confirm action button and already has the details of whats selected(might need another step like draw tickets)
            Player user = state.getPlayers().get(state.getWhosTurn());
            Path thePath = ((PlaceTrains) action).getSelectedPath();
            int wilds = ((PlaceTrains) action).getNumberOfWilds();
            TTRState.CARD color = ((PlaceTrains) action).getColor();///////////////////////////////////////////////////////////////
            int thePathLength = thePath.getLength();
            int thePathOwner = thePath.getPathOwner();
            Path.COLOR thePathColor = thePath.getPathColor();
            //check if owned
            if(thePathOwner != -1){
                return false;
            }
            //check if correct color
            if(thePathColor != Path.COLOR.GREYPATH){
                if(color == TTRState.CARD.WHITECARD){
                    if(thePathColor != Path.COLOR.WHITEPATH) {
                        return false;
                    }
                }else if(color == TTRState.CARD.BLACKCARD){
                    if(thePathColor != Path.COLOR.BLACKPATH) {
                        return false;
                    }
                }else if(color == TTRState.CARD.ORANGECARD){
                    if(thePathColor != Path.COLOR.ORANGEPATH) {
                        return false;
                    }
                }else if(color == TTRState.CARD.PINKCARD){
                    if(thePathColor != Path.COLOR.PINKPATH) {
                        return false;
                    }
                }
            }else{
                //check enough cards
                if(color == TTRState.CARD.WHITECARD){
                    if (user.getWhiteCards() < thePathLength - wilds) {
                        return false;
                    }
                }else if(color == TTRState.CARD.BLACKCARD){
                    if (user.getBlackCards() < thePathLength - wilds) {
                        return false;
                    }
                }else if(color == TTRState.CARD.ORANGECARD){
                    if (user.getOrangeCards() < thePathLength - wilds) {
                        return false;
                    }
                }else if(color == TTRState.CARD.PINKCARD){
                    if (user.getPinkCards() < thePathLength - wilds) {
                        return false;
                    }
                }
            }
            //checks enough wilds
            if(user.getWildCards() < wilds){
                return false;
            }
            //checks too many wilds
            if(wilds > thePathLength){
                return false;
            }
            //end of checks

            //set path owner
            thePath.setPathOwner(state.getWhosTurn());
            //handles the cards
            for(int i=0; i<thePathLength-wilds; i++) {
                //can make a switch statement
                if (color == TTRState.CARD.WHITECARD) {
                    user.removeCardHand(TTRState.CARD.WHITECARD);
                    state.addCard(TTRState.CARD.WHITECARD);
                } else if (color == TTRState.CARD.BLACKCARD) {
                    user.removeCardHand(TTRState.CARD.BLACKCARD);
                    state.addCard(TTRState.CARD.BLACKCARD);
                } else if (color == TTRState.CARD.ORANGECARD) {
                    user.removeCardHand(TTRState.CARD.ORANGECARD);
                    state.addCard(TTRState.CARD.ORANGECARD);
                } else if (color == TTRState.CARD.PINKCARD) {
                    user.removeCardHand(TTRState.CARD.PINKCARD);
                    state.addCard(TTRState.CARD.PINKCARD);
                }
            }
            //removes wild cards
            for(int i=0; i<wilds; i++){
                user.removeCardHand(TTRState.CARD.WILDCARD);
                state.addCard(TTRState.CARD.WILDCARD);
            }
            //((PlaceTrains) action).resetSelectedPath();//again I think this is a preference thing so might need it.
            changeTurn(state);
            return true;
        }else{
            return false;
        }
    }

    public void changeTurn(TTRState state){
        if(state.getNumPlayers() == 2){//use modulus to make it shorter
            if(state.getWhosTurn() == 0){
                state.setWhosTurn(1);
            }else{
                state.setWhosTurn(0);
            }
        }else if(state.getNumPlayers() == 3){
            if(state.getWhosTurn() == 0){
                state.setWhosTurn(1);
            }else if(state.getWhosTurn() == 1){
                state.setWhosTurn(2);
            }else{
                state.setWhosTurn(0);
            }
        }else{
            if(state.getWhosTurn() == 0){
                state.setWhosTurn(1);
            }else if(state.getWhosTurn() == 1){
                state.setWhosTurn(2);
            }else if(state.getWhosTurn() == 2){
                state.setWhosTurn(3);
            }else{
                state.setWhosTurn(0);
            }
        }
    }
    /** whoWon()
     *
     * Description: computes the scores of all players and then returns who won.
     *
     * @return  int representing the player who won
     */
    public int whoWon(){

        String gameOver = checkIfGameOver();

        //if the game is NOT over
        if(gameOver == null){
            return -1;
        }

        //check which player won and return the int of that player
        if(gameOver.equals(0+" is the winner.")){
            return 0;
        }
        else if(gameOver.equals(1+" is the winner.")){
            return 1;
        }
        else if(gameOver.equals(2+" is the winner.")){
            return 2;
        }
        else if(gameOver.equals(3+" is the winner.")){
            return 3;
        }

        return -1;
    }
}
