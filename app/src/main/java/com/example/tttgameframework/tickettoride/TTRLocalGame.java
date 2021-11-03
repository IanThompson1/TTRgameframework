package com.example.tttgameframework.tickettoride;

import com.example.tttgameframework.GameFramework.LocalGame;
import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.Player;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.infoMessage.Ticket;

import java.util.ArrayList;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;

public class TTRLocalGame extends LocalGame {
    private TTRState state;
    public TTRLocalGame(){
        state = new TTRState(this.players.length);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

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

        //get ArrayList of players
        ArrayList<Player> players = getPlayers();

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
            theseTickets = state.getTickets();

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
        return winnerID + " wins with a score of: " + maxScore;

        //return null; //dummy
    } //checkIfGameOver()


    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof DrawTickets){
            if(((DrawTickets) action).getSelected().isEmpty()){
                //show tickets

            }else{
                //
            }

            return true;
        }else if(action instanceof DrawTrains){



            return true;
        }else if(action instanceof PlaceTrains){




            return true;
        }else{
            return false;
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
        if(gameOver.equals(playerNames[0]+" is the winner.")){
            return 0;
        }
        else if(gameOver.equals(playerNames[1]+" is the winner.")){
            return 1;
        }
        else if(gameOver.equals(playerNames[2]+" is the winner.")){
            return 2;
        }
        else if(gameOver.equals(playerNames[3]+" is the winner.")){
            return 3;
        }

        return -1;
    }
}
