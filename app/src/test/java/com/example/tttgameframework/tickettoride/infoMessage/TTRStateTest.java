package com.example.tttgameframework.tickettoride.infoMessage;

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
    }

    @Test
    public void getTickets() {
    }

    @Test
    public void addTicket() {
    }

    @Test
    public void ticketDeckEmpty() {
    }

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
    public void getPath() {
    }

    @Test
    public void getAllPaths() {
    }

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

        Ticket testTicket = new Ticket(8, TTRState.CITY.ASTORIA, TTRState.CITY.LAGRANDE);

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

    @Test
    public void ticket_completed() {
    }

    @Test
    public void dfs() {
    }
}