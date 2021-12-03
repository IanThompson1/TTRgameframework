package com.example.tttgameframework.tickettoride;

import android.media.MediaPlayer;

import com.example.tttgameframework.GameFramework.LocalGame;
import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;
import com.example.tttgameframework.R;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.Player;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.infoMessage.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.players.TTRHumanPlayer;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;

public class TTRLocalGame extends LocalGame {
    private TTRState state;
    public TTRLocalGame(){
        super();
    }

    public void start(GamePlayer[] players){
        super.start(players);
        super.state = new TTRState(players.length);
        state = (TTRState) super.state;
    }
    /** sendUpdatedStateTo
     *
     * Description: Notify the player that their state has changed. Send a game info object.
     *
     * @param p the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new TTRState(((TTRState) state)));
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
        ArrayList<Player> players;
        try {
            players = state.getPlayers();
        }catch (Exception e){
            return "";
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
                if(state.ticket_completed(t.getNode0(), t.getNode1(), p.getName())){
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
        String scoreBoard = playerToColor(winnerID) + " is the winner with a score of "+ scores[winnerID]+"\n";
        int secondPlace = 0;
        int secondScore = -9001;
        for(int i = 0; i < scores.length; i++){
            if(scores[i] > secondScore && i != winnerID){
                secondScore = scores[i];
                secondPlace = i;
            }
        }
        //check if 2 players
        scoreBoard += playerToColor(secondPlace) + " came in second with a score of: "+scores[secondPlace]+"\n";
        if(state.getNumPlayers() == 2){
            return scoreBoard;
        }

        int thirdPlace = 0;
        int thirdScore = -9001;
        for(int i = 0; i < scores.length; i++){
            if(scores[i] > thirdScore && i != winnerID && i != secondPlace){
                thirdScore = scores[i];
                thirdPlace = i;
            }
        }
        //check if 3 players
        scoreBoard += playerToColor(thirdPlace) + " came in third with a score of: "+scores[thirdPlace]+"\n";
        if(state.getNumPlayers() == 3){
            return scoreBoard;
        }

        int fourthPlace = 0;
        int fourthScore = -9001;
        for(int i = 0; i < scores.length; i++){
            if(i != winnerID && i != secondPlace && i != thirdPlace){
                fourthPlace = i;
            }
        }
        scoreBoard += playerToColor(fourthPlace) + " came in last :( with a score of: "+scores[fourthPlace]+"\n";
        return scoreBoard;
    } //checkIfGameOver()


    /** checkTicketComplete
     *
     * @param t the ticket being checked
     * @param p the player being checked
     *
     * @return  if the ticket is completed by the player, return true
     */
    public boolean checkTicketComplete(Ticket t, Player p){
        //return state.ticket_completed(t.getNode0(), t.getNode1(), p.getName());
        return false;
    }


    @Override
    protected boolean makeMove(GameAction action) {

        //check if it is the players turn
        if(getPlayerIdx(action.getPlayer()) != state.getWhosTurn()){
            System.out.println("");
            return false;
        }
        if(action instanceof DrawTickets){
            if(((DrawTickets) action).getSelected() == null){
                //check if there are enough tickets in the deck
                System.out.println(state.getTicketDeck().size() + " ticket deck size");
                if(state.getTicketDeck().size() < 2){
                    state.setSound(1);
                    return false;
                }
                //draw tickets to part of the screen
                ArrayList<Ticket> temp = state.getTickets();
                state.addShownTicket(temp.get(0));
                state.addShownTicket(temp.get(1));
                state.setSound(2);
                return true;
            }else{
                //move tickets to hand
                //state = (TTRState) super.state;
                ArrayList<Ticket> shown = state.getShownTickets();
                ArrayList<Integer> selected = ((DrawTickets) action).getSelected();
                Player user = state.getPlayers().get(state.getWhosTurn());
                //the one check is if neither card is selected
                int count =0;
                for(int t=0; t<selected.size(); t++){
                    if(selected.get(t) == 1){
                        count++;
                    }
                }
                System.out.println("how many are selected? "+count);
                System.out.println("whos turn is it? "+state.getWhosTurn());
                if(count == 0 || count > 2){
                    state.setSound(1);
                    return false;
                }

                //loops through the 2 possible selected tickets
                for(int i=0; i<selected.size(); i++) {
                    //checks if the card is selected
                    if(selected.get(i) == 1) {
                        if(!shown.isEmpty()){
                            System.out.println(shown.get(i).toString() + "adding this card to the players hand");
                            user.addTicket(shown.get(i));
                        }else{
                            state.setSound(1);
                            return false;
                        }

                    }else{
                        state.addTicket(shown.get(i));//auto reshuffles
                    }
                }

                //stop drawing the tickets
                state.clearShownTickets();
                Collections.shuffle(shown);
                //increment who's turn
                state.getPlayers().set(state.getWhosTurn(), user);
                changeTurn(state);
            }
            state.setSound(2);
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
                state.setSound(1);
                return false;
            }
            //all checks
            for(int i=0; i<selected.size(); i++) {
                if (selected.get(i)){
                    //check if draw only one card and the one card is from random deck
                    if(i < 2) {
                        if(counter ==1){
                            state.setSound(1);
                            return false;
                        }
                        //check not enough cards
                        if(random.size() == 0){
                            state.setSound(1);
                            return false;
                        }
                        //checks if double draw from random with only 1 card in the random deck
                        if(selected.get(0) && selected.get(1)){
                            if(random.size() < 2){
                                state.setSound(1);
                                return false;
                            }
                        }
                    }

                    else{
                        //if you select only one card that is not a wild
                        if(counter == 1 && faceUp.get(i-2) != TTRState.CARD.WILDCARD){
                            state.setSound(1);
                            return false;
                        }
                        //if you select two cards and one is a wild
                        else if(counter == 2 && faceUp.get(i-2) == TTRState.CARD.WILDCARD){
                            state.setSound(1);
                            return false;
                        }
                    }
                }
            }

            //add cards to the hand
            for(int i=0; i<selected.size(); i++) {
                if (selected.get(i)){
                    Player user = state.getPlayers().get(state.getWhosTurn());
                    if(i < 2) {
                        //random cards
                        user.addCardHand(random.get(i));
                    }else{
                        //face up cards
                        user.addCardHand(faceUp.get(i-2));
                    }
                }
            }
            //resetting the card decks
            for(int i=0; i<selected.size(); i++) {
                if (selected.get(i)){
                    if(i < 2) {
                        //random cards
                        random.remove(random.get(0));
                    }else{ //i >= 2
                        //face up cards
                        if(random.size() != 0) {
                            TTRState.CARD nextCard = random.get(0);
                            random.remove(random.get(0));
                            faceUp.set(i - 2, nextCard);
                        }else{
                            faceUp.set(i - 2, TTRState.CARD.NULLCARD);
                        }
                    }
                }
            }
            System.out.println("random deck size = "+random.size());
            state.setCardDeck(random);
            ((DrawTrains) action).resetSelectedTrains();
            changeTurn(state);
            afterActionChecks();
            state.setSound(2);
            return true;
        }else if(action instanceof PlaceTrains){
            //assuming just pressed confirm action button and already has the details of whats selected(might need another step like draw tickets)
            //creates a bunch of variables to use
            Player user = state.getPlayers().get(state.getWhosTurn());
            Path thePath = ((PlaceTrains) action).getSelectedPath();
            int pathNum = ((PlaceTrains) action).getPathNum();
            int wilds = ((PlaceTrains) action).getNumberOfWilds();
            TTRState.CARD color = ((PlaceTrains) action).getColor();
            int thePathLength = thePath.getLength();
            int thePathOwner = thePath.getPathOwner();
            Path.COLOR thePathColor = thePath.getPathColor();

            System.out.println(thePathLength+ " The path length");
            System.out.println(wilds+ " wilds");


            //check if the path is already owned
            if(thePathOwner != -1){
                state.setSound(1);
                return false;
            }
            //check if the color selected matches the path color (skips if the path is gray)
            if(thePathColor != Path.COLOR.GREYPATH){
                if(color == TTRState.CARD.WHITECARD){
                    if(thePathColor != Path.COLOR.WHITEPATH) {
                        state.setSound(1);
                        return false;
                    }
                }else if(color == TTRState.CARD.BLACKCARD){
                    if(thePathColor != Path.COLOR.BLACKPATH) {
                        state.setSound(1);
                        return false;
                    }
                }else if(color == TTRState.CARD.ORANGECARD){
                    if(thePathColor != Path.COLOR.ORANGEPATH) {
                        state.setSound(1);
                        return false;
                    }
                }else if(color == TTRState.CARD.PINKCARD){
                    if(thePathColor != Path.COLOR.PINKPATH) {
                        state.setSound(1);
                        return false;
                    }
                }
            }
            //checks if you enough cards
            if(color == TTRState.CARD.WHITECARD){
                if (user.getWhiteCards() < thePathLength - wilds) {
                    state.setSound(1);
                    return false;
                }
            }else if(color == TTRState.CARD.BLACKCARD){
                if (user.getBlackCards() < thePathLength - wilds) {
                    state.setSound(1);
                    return false;
                }
            }else if(color == TTRState.CARD.ORANGECARD){
                if (user.getOrangeCards() < thePathLength - wilds) {
                    state.setSound(1);
                    return false;
                }
            }else if(color == TTRState.CARD.PINKCARD){
                if (user.getPinkCards() < thePathLength - wilds) {
                    state.setSound(1);
                    return false;
                }
            }
            //checks enough wilds
            if(user.getWildCards() < wilds){
                state.setSound(1);
                return false;
            }
            //checks too many wilds
            if(wilds > thePathLength){
                state.setSound(1);
                return false;
            }
            //end of checks

            //set path owner
            thePath.setPathOwner(state.getWhosTurn());
            state.getAllPaths().set(pathNum, thePath);
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
            state.getPlayers().get(state.getWhosTurn()).setNumTrains(state.getPlayers().get(state.getWhosTurn()).getNumTrains() - thePathLength);

            //((PlaceTrains) action).resetSelectedPath();//again I think this is a preference thing so might need it.
            changeTurn(state);
            afterActionChecks();
            state.setSound(2);
            return true;
        }else{
            state.setSound(1);
            return false;
        }
    }

    public void changeTurn(TTRState state){
        if(state.getNumPlayers() == 2){//use modulus to make it shorter in the future
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
    public int getWhoWon(TTRState theState){
        TTRState winState = theState;
        boolean gameOver = false;

        //get players of players
        ArrayList<Player> players;
        try {
            players = winState.getPlayers();
        }catch (Exception e){
            System.out.println("no players left!");
            return -1;
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
            System.out.println("game isnt over");
            return -1;
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
        int scores[] = new int[((TTRState)winState).getNumPlayers()];

        //2a. compute scores from tickets
        //not done
        /*for(Player p: players){
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
        }*/

    //2b. compute scores from building paths
    ArrayList<Path> thesePaths = winState.getAllPaths();
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
        return winnerID;
    }

    public void afterActionChecks(){
        //before each move check for the face up cards
        for(int i=0; i<5; i++){
            if(state.getFaceUp().get(i) == TTRState.CARD.NULLCARD){
                if(state.getCardDeck().size() > 0){
                    TTRState.CARD nextCard = state.getCardDeck().get(0);
                    ArrayList<TTRState.CARD> temp = state.getCardDeck();
                    temp.remove(temp.get(0));
                    state.setCardDeck(temp);
                    state.getFaceUp().set(i, nextCard);
                }
            }
        }
        //next check for triple wilds
        int countWild = 0;
        int nonWilds = 0;
        //counts how many wilds and non wilds are in the face up pile
        for(int i=0; i<5; i++){
            if(state.getFaceUp().get(i) == TTRState.CARD.WILDCARD){
                countWild++;
            }else{
                nonWilds++;
            }
        }
        //counts the non wilds in the random deck
        for(int i=0; i<state.getCardDeck().size(); i++){
            if(state.getCardDeck().get(i) != TTRState.CARD.WILDCARD){
                nonWilds++;
            }
        }

        if(countWild > 2 && nonWilds <= 2){
            System.out.println("not enough nonWilds to perform shuffle");
            System.out.println("# of non wilds: "+nonWilds);
        }

        //replaces all face up cards if 3 wilds + enough non wilds until its fixed

        while(countWild > 2 && nonWilds > 2){
            System.out.println("found 3 wilds, resetting deck");

            //resets the 5 face up cards
            ArrayList<TTRState.CARD> faceUpCards = state.getFaceUp();
            //adds removed cards to deck
            for(int i=0; i<5; i++){
                state.addCard(faceUpCards.get(i));
            }
            //removes the five cards
            for(int i=0; i<5; i++){
                faceUpCards.remove(0);
            }
            //replaces with random cards
            for(int i=0; i<5; i++){
                TTRState.CARD nextCard = state.getCardDeck().get(0);
                state.getCardDeck().remove(0);
                faceUpCards.add(nextCard);
            }

            //checks if needs to keep resetting or not
            countWild = 0;
            nonWilds = 0;
            //counts how many wilds and non wilds are in the face up pile
            for(int i=0; i<5; i++){
                if(state.getFaceUp().get(i) == TTRState.CARD.WILDCARD){
                    countWild++;
                }else{
                    nonWilds++;
                }
            }
            //counts the non wilds in the random deck
            for(int i=0; i<state.getCardDeck().size(); i++){
                if(state.getCardDeck().get(i) != TTRState.CARD.WILDCARD){
                    nonWilds++;
                }
            }
        }
        System.out.println("number of non wilds = "+nonWilds +" \nnumber of wilds = "+countWild);
    }

    public String playerToColor(int player){
        if(player == 0){
            return "Green";
        }else if(player == 1){
            return "Red";
        }else if(player == 2){
            return "Blue";
        }else if(player == 3){
            return "Yellow";
        }
        return "bad number";
    }
}
