package com.example.tttgameframework.tickettoride.players;

import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.tttgameframework.GameFramework.players.GameComputerPlayer;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.Player;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.infoMessage.Ticket;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;
import com.example.tttgameframework.tickettoride.ttrActionMessage.PlaceTrains;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class TTRComputerPlayer1 extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */

    private boolean draw;
    private boolean firstTurn;
    private int smallestIndex;
    private ArrayList<Integer> lastIndex;
    private boolean ticketsComplete;

    //instate
    /*
    public static final boolean[][] adj = {
            {false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true},
            {false, false, true, false, true, false, true, false, false, false, true, false, false, true, true, false},
            {false, true, false, false, false, false, false, true, true, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, true, false, false, true, false, false, false},
            {false, true, false, false, false, false, false, false, false, true, false, false, true, true, false, false},
            {false, false, false, true, false, false, true, false, false, false, false, false, true, false, false, false},
            {false, true, false, false, false, true, false, false, true, false, false, false, true, false, false, false},
            {false, false, true, false, false, false, false, false, false, false, true, false, false, false, false, false},
            {false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false},//is lake
            {false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, true},
            {false, true, false, false, false, false, false, true, false, false, false, false, false, false, true, false},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false},//is portland
            {false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false},
            {false, true, false, false, true, false, false, false, false, true, false, true, false, false, false, false},
            {false, true, false, false, false, false, false, false, false, false, true, true, false, false, false, false},
            {true, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false}
    };*/

    public static final int[][] dist = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
            {0, 0, 3, 0, 2, 0, 4, 0, 0, 0, 4, 0, 0, 3, 3, 0},
            {0, 3, 0, 0, 0, 0, 0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 1, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 2, 0, 0},//eugene
            {0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 4, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 3, 0, 0, 0},//kfalls
            {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lakeview
            {0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1},
            {0, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 0},//pendelton
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1},
            {0, 0, 0, 1, 2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},//roseburg
            {0, 3, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0},
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0},//the dalles
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}
    };

    /*
    public static final TTRState.CITY[] order = {TTRState.CITY.ASTORIA, TTRState.CITY.BEND, TTRState.CITY.BURNS,
            TTRState.CITY.COOSBAY, TTRState.CITY.EUGENE, TTRState.CITY.GPASS, TTRState.CITY.KFALLS, TTRState.CITY.LAGRANDE,
            TTRState.CITY.LAKEVIEW, TTRState.CITY.NEWPORT, TTRState.CITY.PENDLETON, TTRState.CITY.PORTLAND,
            TTRState.CITY.ROSEBURG, TTRState.CITY.SALEM, TTRState.CITY.THEDALLES, TTRState.CITY.TILLAMOOK};
    */


    public TTRComputerPlayer1(String name) {
        super(name);
        draw = false;
        firstTurn = true;
        smallestIndex = 100;
        lastIndex = new ArrayList<>();
        ticketsComplete = false;

    }

    @Override
    protected void receiveInfo(GameInfo info) {

        //If last move did not work
        if (info instanceof IllegalMoveInfo){
            draw = true;
            return;
        }

        //if It's a state
        if(!(info instanceof TTRState)){
            return;
        }

        TTRState state = (TTRState) info;

        //if its my turn take a turn
        if (state.getTurn() != playerNum){  //not sure
            return;
        }

        if (firstTurn == true){
            firstTurn = false;
            game.sendAction(new DrawTickets(this, null));
            return;
        }

        ArrayList<Player> players = state.getPlayers();
        //should be reset in loop
        Player self = players.get(1);
        for (Player play: players) {
            if (play.getName() == playerNum){
                self = play;
                break;
            }
        }


        if (state.getShownTickets().size() != 0){
            ArrayList<Integer> tickets = new ArrayList<Integer>(2);
            //pick the first ticket
            tickets.add(1);
            tickets.add(0);
            game.sendAction(new DrawTickets(this, tickets));
            return;
        }


        //if you are supposed to draw
        if(draw){
            ArrayList<Boolean> selectedCards = new ArrayList<Boolean>();
            for (int i = 0; i < 7; i++){
                selectedCards.add(false);
            }
            //draw from the face down pile
            selectedCards.set(0, true);
            selectedCards.set(1, true);

            draw = false;
            game.sendAction(new DrawTrains(this, selectedCards));
            return;
        }


        ArrayList<Ticket> tickets = state.getPlayers().get(playerNum).getTickets();
        //check for the desired path
        for (Ticket t : tickets){
            //check if the ticket is completed
            if (state.ticket_completed(t.getNode0(), t.getNode1(), playerNum)){
                t.setIsComplete();
            }

            if (!t.getIsComplete()){
                //ArrayList<Path> visited = new ArrayList<>();
                TTRState.CITY startCity = t.getNode0();
                TTRState.CITY endCity = t.getNode1();

                //TTRState.CITY midCity = findOwnedPath(state, visited, startCity, endCity, playerNum);
/*
                //if the path is complete
                if (midCity == endCity){
                    t.setIsComplete();
                }*/
                //path is not yet completed
                //else {
                boolean[] visit = new boolean[16];
                for(int i = 0; i < 16; i++){
                    visit[i] = false;
                }


                findBestPath(state, visit, startCity, endCity, playerNum);
                Path path = findPath(state, startCity, TTRState.order[smallestIndex]);
                //visit[smallestIndex] = true;

                int cityNum = -1;
                for (int i = 0; i < TTRState.order.length; i++){
                    if (TTRState.order[i] == startCity){
                        cityNum = i;
                    }
                }
                lastIndex.add(cityNum);

                while(path.getPathOwner() == playerNum){
                    for(int i = 0; i < 16; i++){
                        visit[i] = false;
                    }
                    System.out.println("Got to the loop");
                    lastIndex.add(smallestIndex);

                    //need to change to multiple owned paths
                    for (Integer i : lastIndex){
                        visit[i] = true;
                    }
                    findBestPath(state, visit, TTRState.order[smallestIndex], endCity, playerNum);
                    path = findPath(state, TTRState.order[lastIndex.get(lastIndex.size() - 1)], TTRState.order[smallestIndex]);
                }


                //Rest is copied from dumb computer player

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

                //need to find num
                game.sendAction(new PlaceTrains(this, path, numWild, color, state.getAllPaths().indexOf(path)));

                return;
                //}
            }
        }
        firstTurn = true;
        return;

        //System.out.println("IF you are reading this string right here you are breaking the ticket completed function");
    }





    public int findBestPath(TTRState state, boolean[] visited, TTRState.CITY start, TTRState.CITY end, int owner) {
        //found the end
        if (start == end){
            return 0;
        }

        int cityNum = -1;
        int[] values = new int[16];

        for (int i = 0; i < TTRState.order.length; i++){
            if (TTRState.order[i] == start){
                cityNum = i;
            }
        }

        //add to visited so this one "branch" will not go back
        visited[cityNum] = true;


        //go through and find each connected node
        for (int i = 0; i < TTRState.order.length; i++) {
            Path path = findPath(state, TTRState.order[i], start);

            //already visited
            if (visited[i]){
                values[i] = 1000;
            }

            //a path exists
            else if(dist[cityNum][i] != 0) {
                //if it is owned by the player add 0 to the total should not happen
                if (path == null) {
                    System.out.println("" + cityNum + " --- " + i);
                }

                //already owned by self
                else if (path.getPathOwner() == playerNum) {
                    values[i] = findBestPath(state, visited, TTRState.order[i], end, playerNum);
                }

                //if it is not connected OR if it is not the same city then add it to the array
                else if (path.getPathOwner() == -1){
                    values[i] = dist[cityNum][i] + findBestPath(state, visited, TTRState.order[i], end, playerNum);
                }
            }
        }



        //find the shortest path
        int smallest = 100;
        for (int i = 0; i < values.length; i++) {
            System.out.println("" + i + " ; " + values[i]);
            if (values[i] < smallest && values[i] > 0){
                smallest = values[i];

                //----------THE ACTUAL VALUE USED IN RECEIVE INFO----------
                smallestIndex = i;
            }
        }
        return smallest;

    }

    //find path
    public Path findPath(TTRState state, TTRState.CITY c0, TTRState.CITY c1){
        ArrayList<Path> allPaths = state.getAllPaths();

        for (Path p : allPaths){
            //if it has the same cities
            if (
                    (p.getNode0() == c0 && p.getNode1() == c1)
            || (p.getNode0() == c1 && p.getNode1() == c0)
            ) {
                return p;
            }
        }
        //path DNE
        return null;
    }
}
