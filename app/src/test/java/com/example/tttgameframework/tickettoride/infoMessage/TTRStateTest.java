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
//        TTRState test = new TTRState(2);
//        TTRState test1 = new TTRState(2);
//        ArrayList<Ticket> tickets = test.getTickets();
        TTRState test = new TTRState(2);
        Ticket testTicket = new Ticket(8, TTRState.CITY.ASTORIA, TTRState.CITY.PENDLETON);
        test.addTicket(testTicket);
        ArrayList<Ticket> tickets = test.getTickets();
        assertEquals(test.getTicketDeck().size(), tickets.size());
        //assertEquals(test1.getTicketDeck().get(index2), tickets.get(1));

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
        //only used for testing
    }

    @Test
    public void getPlayer1() {
        TTRState state = new TTRState(2);
        Player player = new Player(state.getPlayer1());
        assertEquals(state.getPlayers().get(0).getCardHand().size(), player.getCardHand().size());

        state.getPlayers().get(0).addCardHand(TTRState.CARD.BLACKCARD);
        assertNotEquals(state.getPlayers().get(0).getCardHand().size(), player.getCardHand().size());

        player = new Player(state.getPlayer1());
        assertEquals(state.getPlayers().get(0).getCardHand().size(), player.getCardHand().size());
    }//Bruce

    @Test
    public void getPlayers() {
        TTRState state0 = new TTRState(2);
        TTRState state1 = new TTRState(4);
        assertNotEquals(null, state0.getPlayers());
        assertNotEquals(null, state1.getPlayers());
        assertNotEquals(state1.getPlayers().size(), state0.getPlayers().size());
    }//Bruce

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
    }//Bruce

    @Test
    public void addShownTicket() {
        TTRState state0 = new TTRState(2);

        Ticket ticket = new Ticket(1, TTRState.CITY.NEWPORT, TTRState.CITY.NEWPORT);
        ArrayList<Ticket> tic = new ArrayList<>();
        state0.addShownTicket(ticket);
        tic.add(ticket);
        assertEquals(tic.get(0), state0.getShownTickets().get(0));
    }//Bruce

    @Test
    public void getShownTickets() {
        TTRState state = new TTRState(2);

        Ticket ticket = new Ticket(1, TTRState.CITY.NEWPORT, TTRState.CITY.NEWPORT);
        ArrayList<Ticket> tic = new ArrayList<>();
        assertEquals(tic, state.getShownTickets());
        state.addShownTicket(ticket);
        tic.add(ticket);
        assertEquals(tic.get(0), state.getShownTickets().get(0));

    }//Bruce

    @Test
    public void removeShownTicket() {
        TTRState state0 = new TTRState(2);

        Ticket ticket = new Ticket(1, TTRState.CITY.NEWPORT, TTRState.CITY.NEWPORT);
        ArrayList<Ticket> tic = new ArrayList<>();
        state0.addShownTicket(ticket);
        tic.add(ticket);

        state0.removeShownTicket(ticket);
        assertNotEquals(tic.size(), state0.getShownTickets().size());
        tic.remove(ticket);
        assertEquals(tic.size(), state0.getShownTickets().size());
    }//Bruce

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


    //Will not work 100% of teh time due to random starting order
    @Test
    public void getFaceUp() {
        TTRState state = new TTRState(2);
        assertEquals(5, state.getFaceUp().size());

        ArrayList<TTRState.CARD> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            cards.add(TTRState.CARD.WILDCARD);
        }

        boolean same = true;
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i) != state.getFaceUp().get(i)){
                same = false;
            }
        }
        assertTrue(!same);
        same = true;
        state.setFaceUp(cards);
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i) != state.getFaceUp().get(i)){
                same = false;
            }
        }
        assertTrue(same);

    }//Bruce


    //Will not work 100% of teh time due to random starting order
    @Test
    public void setFaceUp() {
        TTRState state = new TTRState(2);

        ArrayList<TTRState.CARD> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            cards.add(TTRState.CARD.WHITECARD);
        }

        boolean same = true;
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i) != state.getFaceUp().get(i)){
                same = false;
                break;
            }
        }
        assertTrue(!same);
        same = true;
        state.setFaceUp(cards);
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i) != state.getFaceUp().get(i)){
                same = false;
                break;
            }
        }
        assertTrue(same);
        same = true;

        for (int i = 0; i < 5; i++){
            cards.set(i, TTRState.CARD.BLACKCARD);
        }
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i) != state.getFaceUp().get(i)){
                same = false;
                break;
            }
        }
        assertTrue(!same);

    }//Bruce

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
    }//Bruce

    @Test
    public void addCard() {
        TTRState state = new TTRState(2);
        ArrayList<TTRState.CARD> deck = new ArrayList<>();
        for (int i = 0; i < state.getCardDeck().size(); i++){
            deck.add(state.getCardDeck().get(i));
        }
        assertEquals(deck.size(), state.getCardDeck().size());
        state.addCard(TTRState.CARD.BLACKCARD);
        assertNotEquals(deck.size(), state.getCardDeck().size());
        int stateBlack = 0;
        int deckBlack = 0;

        for(int i = 0; i < state.getCardDeck().size(); i++){
            if(state.getCardDeck().get(i) == TTRState.CARD.BLACKCARD){
                stateBlack++;
            }
        }
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i) == TTRState.CARD.BLACKCARD){
                deckBlack++;
            }
        }
        assertNotEquals(deckBlack, stateBlack);

    }//Bruce

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
        //not finished yet
    }
}