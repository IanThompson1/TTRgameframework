package com.example.tttgameframework.tickettoride.infoMessage;

import java.io.Serializable;
import java.util.HashMap;

/**
 * CityNode: class to hold a City and a hashmap containing all their neighbors.
 *              Used for any type of graph traversals done in the game.
 *
 * @author Jennifer, Ian, Trent, Bruce
 * @version 6 November 2021
 */
public class CityNode implements Serializable {
    private static final long serialVersionUID = 7542321013422624386L;
    //instance variables
    private TTRState.CITY city;
    private HashMap<TTRState.CITY, Path> neighbors;

    /**
     * constructor
     *
     * @param c     CITY name being held in the node
     */
    public CityNode(TTRState.CITY c){
        city = c;
        neighbors = new HashMap<>(); //init empty hashmap
    }

    /**
     * copy constructor
     *
     * @param n     CityNode being copied.
     */
    public CityNode(CityNode n){
        this.city = n.city;

        neighbors = new HashMap<>(); //init empty hashmap

        //add the neighbors from old CityNode
        for(TTRState.CITY c: n.neighbors.keySet()){
            this.neighbors.put(c, n.neighbors.get(c));
        }
    }

    /** addNeighbor
     *
     * Description: adds a neighbor of the city to the neighbors hashmap.
     *
     * @param p     path representing the edge between the two cities.
     */
    public void addNeighbor(Path p){
        //check which node on the path matches the current node.
        if(p.getNode0() == city){
            neighbors.put(p.getNode1(), p);
        }
        else if(p.getNode1() == city){
            neighbors.put(p.getNode0(), p);
        }
        else{
            return;
        }
    } //addNeighbor

    //getter method for city
    public TTRState.CITY getCity(){
        return city;
    }

    //getter method for neighbors
    public HashMap<TTRState.CITY, Path> getNeighbors(){
        return neighbors;
    }



}
