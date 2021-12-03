package com.example.tttgameframework.tickettoride.players;

import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameComputerPlayer;
import com.example.tttgameframework.tickettoride.TTRLocalGame;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.Player;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;

import java.util.ArrayList;
import java.util.Random;

public class TTRComputerPlayer0 extends GameComputerPlayer {

    private static final Random rand = new Random();
    private int firstTurn;
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public TTRComputerPlayer0(String name) {

        super(name);
        firstTurn = 1;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof TTRState)){return;}

        TTRState state = (TTRState) info;

    //if its my turn take a turn
        if (state.getTurn() != playerNum){  //not sure
            return;
        }
        sleep(2);
        if (firstTurn == 1){
            firstTurn = 0;
            game.sendAction(new DrawTickets(this, null));
            return;
        }

        //check if it is start of game and player has no tickets
        //use all players and find this computer player
        ArrayList<Player> players = state.getPlayers();
        //should be reset in the for loop
        Player self = players.get(1);
        for (Player play: players) {
            if (play.getName() == playerNum){
                self = play;
                break;
            }
        }
        if (self.getTickets().size() == 0){
            ArrayList<Integer> tickets = new ArrayList<Integer>(2);
            //pick the first ticket
            tickets.add(1);
            tickets.add(0);
            game.sendAction(new DrawTickets(this, tickets));
        }

        else {
            //----place train----
            if (rand.nextInt(2) == 1){
                //create int arraylist to send and set them to 0 (not selected)
                ArrayList<Integer> paths = new ArrayList<>(state.getAllPaths().size());

                //chooses a random path
                Path path = state.getAllPaths().get((rand.nextInt(state.getAllPaths().size())));

                /* placed this code above
                //use all players and find this computer player
                ArrayList<Player> players = state.getPlayers();
                //should be reset in the for loop
                Player self = players.get(1);
                for (Player play: players) {
                    if (play.getName() == playerNum){
                        self = play;
                        break;
                    }
                }*/

                //color to be used
                TTRState.CARD color;

                //find number of each type of card
                int numWhite = self.getWhiteCards();
                int numBlack = self.getBlackCards();
                int numPink = self.getPinkCards();
                int numOrange = self.getOrangeCards();
                int numWild = self.getWildCards();

                //handles wildnumber. wild number is the amount wilds that the player want to use, not their total # of wilds
                if(path.getLength() < numWild){
                    numWild = path.getLength();
                }


                //check color of path
                if (path.getPathColor() == Path.COLOR.GREYPATH){
                    //check which color it has the most of (simple max finder)
                    int max = numWhite;
                    if (numBlack > max){
                        max = numBlack;
                    }
                    if (numPink > max){
                        max = numPink;
                    }
                    if (numOrange > max){
                        max = numOrange;
                    }

                    //assign color to be used as max
                    if (max == numWhite){
                        color = TTRState.CARD.WHITECARD;
                    }
                    else if (max == numBlack){
                        color = TTRState.CARD.BLACKCARD;
                    }
                    else if (max == numPink){
                        color = TTRState.CARD.PINKCARD;
                    }
                    else {
                        color = TTRState.CARD.ORANGECARD;
                    }
                }

                //if the path is a set color
                else if (path.getPathColor() == Path.COLOR.WHITEPATH){
                    color = TTRState.CARD.WHITECARD;
                }
                else if (path.getPathColor() == Path.COLOR.BLACKPATH){
                    color = TTRState.CARD.BLACKCARD;
                }
                else if (path.getPathColor() == Path.COLOR.PINKPATH){
                    color = TTRState.CARD.PINKCARD;
                }
                //must be orange
                else{
                    color = TTRState.CARD.ORANGECARD;
                }

                game.sendAction(new PlaceTrains(this, path, numWild, color , state.getAllPaths().indexOf(path)));
                //need to select all wilds and what color to use
            }

            //-----draw cards---
            else{
                ArrayList<Boolean> selectedCards = new ArrayList<Boolean>();
                for (int i = 0; i < 7; i++){
                    selectedCards.add(false);
                }

                //do card draw
                if (rand.nextInt(2) == 1){
                    //draw face up cards
                    int card1 = rand.nextInt(5) + 2;
                    int card2 = rand.nextInt(5) + 2;

                    //check for duplicates
                    while (card1 == card2){
                        card2 = rand.nextInt(5) + 2;
                    }

                    //set which cards to draw
                    for (int i = 0; i < 7; i++){
                        if (i == card1 || i == card2){
                            selectedCards.set(i, true);
                        }
                    }
                }

                else{
                    //take two random cards
                    selectedCards.set(0, true);
                    selectedCards.set(1, true);
                }
                game.sendAction(new DrawTrains(this, selectedCards));
            }
        }
    }
}
