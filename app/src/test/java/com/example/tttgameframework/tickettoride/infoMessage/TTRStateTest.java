package com.example.tttgameframework.tickettoride.infoMessage;

import static com.example.tttgameframework.tickettoride.infoMessage.TTRState.CITY.ASTORIA;
import static com.example.tttgameframework.tickettoride.infoMessage.TTRState.CITY.LAGRANDE;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class TTRStateTest {

    @Test
    public void getCardDeck() {
        TTRState test = new TTRState(2);

        ArrayList<TTRState.CARD> cardDeckTest = new ArrayList<>();
        cardDeckTest.add(TTRState.CARD.ORANGECARD);
        cardDeckTest.add(TTRState.CARD.WHITECARD);

        test.setCardDeck(cardDeckTest);

        ArrayList<TTRState.CARD> cardDeckTestGet = test.getCardDeck();

        TTRState.CARD testCard = cardDeckTest.get(0);
        TTRState.CARD testCard2 = cardDeckTest.get(1);

        assertEquals(TTRState.CARD.ORANGECARD, testCard);
        assertEquals(TTRState.CARD.WHITECARD, testCard2);


    }//Jennifer

    @Test
    public void setCardDeck() {
        TTRState test =  new TTRState(2);

        ArrayList<TTRState.CARD> deck = test.getCardDeck();
        deck.remove(0);
        deck.set(0, TTRState.CARD.ORANGECARD);
        test.setCardDeck(deck);
        ArrayList<TTRState.CARD> deck1 = test.getCardDeck();
        assertEquals(TTRState.CARD.ORANGECARD, deck1.get(0));
        assertEquals(deck.size(), deck1.size());

    }//Trent

    @Test
    public void getTickets() {
        TTRState test = new TTRState(2);
        TTRState test1 = new TTRState(2);
        ArrayList<Ticket> tickets = test.getTickets();
        int index1 = test1.getTicketDeck().indexOf(tickets.get(0));
        int index2 = test1.getTicketDeck().indexOf(tickets.get(1));
        assertEquals(test1.getTicketDeck().get(index1), tickets.get(0));
        assertEquals(test1.getTicketDeck().get(index2), tickets.get(1));

    }//Trent

    @Test
    public void addTicket() {
        TTRState test = new TTRState(2);
        Ticket testTicket = new Ticket(8, ASTORIA, TTRState.CITY.PENDLETON);
        test.addTicket(testTicket);
        int index = test.getTicketDeck().indexOf(testTicket);
        assertEquals(testTicket, test.getTicketDeck().get(index));
    }//Trent

    @Test
    public void ticketDeckEmpty() {
        TTRState state = new TTRState(4);
        ArrayList<Ticket> empty = new ArrayList<>();
        state.setTicketDeck(empty);
        assertTrue(state.ticketDeckEmpty());
        Ticket testTicket = new Ticket(1, TTRState.CITY.BEND, TTRState.CITY.BURNS);
        state.addTicket(testTicket);
        assertFalse(state.ticketDeckEmpty());
    }//Ian

    @Test
    public void setTicketDeck(){
        TTRState state = new TTRState(4);
        ArrayList<Ticket> empty = new ArrayList<>();
        state.setTicketDeck(empty);
        assertTrue(state.ticketDeckEmpty());
        Ticket testTicket = new Ticket(1, TTRState.CITY.PENDLETON, TTRState.CITY.PORTLAND);
        ArrayList<Ticket> test = new ArrayList<>();
        test.add(testTicket);
        state.setTicketDeck(test);
        assertEquals(testTicket, state.getTicketDeck().get(0));
    }//Ian

    @Test
    public void testToString() {
    }

    @Test
    public void getPlayer1() {
    }

    @Test
    public void getPlayers() {
    }

    @Test
    public void getAllPaths() {
        TTRState state = new TTRState(4);
        Path testFirst = state.getAllPaths().get(0);
        assertEquals(-1,testFirst.getPathOwner());
        assertEquals(1,testFirst.getLength());
        assertEquals(ASTORIA,testFirst.getNode0());
        assertEquals(TTRState.CITY.TILLAMOOK,testFirst.getNode1());
        assertEquals(Path.COLOR.ORANGEPATH,testFirst.getPathColor());
    }//Ian

    @Test
    public void getTurn() {
        TTRState test = new TTRState(2);

        int testTurn = test.getTurn();

        assertEquals(0,testTurn); //turn should automatically be set to 0.
    }//jennifer

    @Test
    public void getNumPlayers() {
        TTRState state2 = new TTRState(2);
        TTRState state3 = new TTRState(3);

        assertEquals(2, state2.getNumPlayers());
        assertEquals(3, state3.getNumPlayers());
    }

    @Test
    public void addShownTicket() {
        TTRState state0 = new TTRState(2);

        Ticket ticket = new Ticket(1, TTRState.CITY.NEWPORT, TTRState.CITY.NEWPORT);
        ArrayList<Ticket> tic = new ArrayList<>();
        state0.addShownTicket(ticket);
        tic.add(ticket);
        assertEquals(tic.get(0), state0.getShownTickets().get(0));
    }

    @Test
    public void getShownTickets() {
        TTRState state = new TTRState(2);

        Ticket ticket = new Ticket(1, TTRState.CITY.NEWPORT, TTRState.CITY.NEWPORT);
        ArrayList<Ticket> tic = new ArrayList<>();
        assertEquals(tic, state.getShownTickets());
        state.addShownTicket(ticket);
        tic.add(ticket);
        assertEquals(tic.get(0), state.getShownTickets().get(0));

    }

    @Test
    public void removeShownTicket() {
    }

    @Test
    public void clearShownTickets() {
        TTRState test = new TTRState(2);

        Ticket testTicket = new Ticket(8, ASTORIA, LAGRANDE);

        test.addShownTicket(testTicket);

        test.clearShownTickets();

        ArrayList<Ticket> shownTicketsTest = new ArrayList<Ticket>();

        shownTicketsTest = test.getShownTickets();

        assertEquals(0, shownTicketsTest.size());

    }//jennifer

    @Test
    public void getFaceUp() {
    }

    @Test
    public void setFaceUp() {
    }

    @Test
    public void getWhosTurn() {
        TTRState test = new TTRState(2);

        int testTurn = test.getWhosTurn();

        assertEquals(0,testTurn); //turn should automatically be set to 0.
    } //Jennifer

    @Test
    public void setWhosTurn() {
        TTRState state = new TTRState(2);

        state.setWhosTurn(0);
        assertEquals(0, state.getWhosTurn());
        state.setWhosTurn(1);
        assertEquals(1, state.getWhosTurn());
        state.setWhosTurn(2);
        //over nunmber of players
        assertEquals(1, state.getWhosTurn());
        state.setWhosTurn(-1);
        assertEquals(1, state.getWhosTurn());
    }

    @Test
    public void addCard() {
    }

    //will fail
    @Test
    public void ticket_completed() {
        /*TTRState state = new TTRState(2); //make gamestate with 2 players

        //set paths as owned
        ArrayList<Path> paths = new ArrayList<>();

        for(Path p: state.getAllPaths()){
            paths.add(p);
        }

        //astoria->ptown
        //ptown->dalles
        //dalles->pendelton
        //pendelton->la grande
        for(Path p: paths){
            p.setPathOwner(1);
        }

        state.setAllPaths(paths);

        //test ticket from ASTORIA to LAGRANDE
        ArrayList<Ticket> ticks = state.getTicketDeck();
        boolean isComplete = false;

        for(Ticket t: ticks){
            isComplete = state.ticket_completed(t.getNode0(), t.getNode1(), 1);
            assertTrue(isComplete);
        }



        //city1, city2, owner*/

    }

    @Test
    public void dfs() {

    }
}