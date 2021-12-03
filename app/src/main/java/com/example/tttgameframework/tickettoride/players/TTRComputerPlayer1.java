package com.example.tttgameframework.tickettoride.players;

import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.tttgameframework.GameFramework.players.GameComputerPlayer;
import com.example.tttgameframework.tickettoride.infoMessage.Path;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.infoMessage.Ticket;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTickets;
import com.example.tttgameframework.tickettoride.ttrActionMessage.DrawTrains;

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

        //draw tickets on first turn
        if (firstTurn){//unsure
            firstTurn = false;
            game.sendAction(new DrawTickets(this, null));
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
            if (t.getIsComplete() == false){
                ArrayList<Path> visited = new ArrayList<>();
                TTRState.CITY startCity = t.getNode0();
                TTRState.CITY endCity = t.getNode1();

                TTRState.CITY midCity = findOwnedPath(state, visited, startCity, endCity, playerNum);

                //if the path is complete
                if (midCity == endCity){
                    t.setIsComplete();
                }
                //path is not yet completed
                else {

                    //sendInfo();
                    return;
                }
            }
        }

    }


    public TTRState.CITY findOwnedPath(TTRState state, ArrayList<Path> visited, TTRState.CITY start, TTRState.CITY end, int owner) {
        ArrayList<Path> allPaths = state.getAllPaths();

        //if found complete path
        if (end.equals(start)) { //base case
            return start;
        }




        //search for a connected path
        for (Path p : allPaths) {
            //if it is owned by player and haven't been already
            if (p.getPathOwner() == owner && !visited.contains(p)) {
                visited.add(p);

                //if it is a valid path (random but should always be the same order) search from it
                if (p.getNode0().equals(start)) {
                    return findOwnedPath(state, visited, p.getNode1(), end, owner);
                }
                else if (p.getNode1().equals(start)) {
                    //visited.add(p);
                    return findOwnedPath(state, visited, p.getNode0(), end, owner);
                }
            }
        }
        //if there is no valid path
        return start;
    }





    public TTRState.CITY findBestPath(TTRState state, ArrayList<Path> visited, TTRState.CITY start, TTRState.CITY end, int value, int owner) {
        ArrayList<Path> allPaths = state.getAllPaths();
        //trying to find path with lowest value
        Hashtable<Path, Integer> table = new Hashtable<>();




        //search for a connected path
        for (Path p : allPaths) {
            //if it is owned by player and haven't been already
            if (p.getPathOwner() == owner && !visited.contains(p)) {
                visited.add(p);

                //if it is a valid path (random but should always be the same order) search from it
                if (p.getNode0().equals(start)) {
                    findBestPath(state, visited, p.getNode1(), end, p.getLength(), owner);
                    table.put(p, value);
                }
                else if (p.getNode1().equals(start)) {
                    //visited.add(p);
                    return findBestPath(state, visited, p.getNode0(), end, p.getLength(), owner);
                }
            }
        }

        //go through every key in it
        Enumeration e = table.keys();
        while (e.hasMoreElements()){
            e.nextElement();
        }


        return start;//dummy

    }
}
