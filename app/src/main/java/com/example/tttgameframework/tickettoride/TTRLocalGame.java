package com.example.tttgameframework.tickettoride;

import com.example.tttgameframework.GameFramework.LocalGame;
import com.example.tttgameframework.GameFramework.actionMessage.GameAction;
import com.example.tttgameframework.GameFramework.players.GamePlayer;
import com.example.tttgameframework.tickettoride.infoMessage.Player;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;

import java.util.ArrayList;

public class TTRLocalGame extends LocalGame {
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

        //create array for the scores of the players
        int scores[] = new int[((TTRState)state).getNumPlayers()];

        //3. Compare scores to determine winner.
        //4. Generate and return message of who the winner is.

        return null; //dummy
    }


    @Override
    protected boolean makeMove(GameAction action) {
        return false;
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
