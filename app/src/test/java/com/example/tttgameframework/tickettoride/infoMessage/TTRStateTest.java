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
    }

    @Test
    public void getShownTickets() {
    }

    @Test
    public void addShownTicket() {
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
        TTRState test = new TTRState(2);

        test.setWhosTurn(1);

        int testTurn = test.getTurn();

        assertEquals(1,testTurn); //turn should automatically be set to 0.
    }//Jennifer

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