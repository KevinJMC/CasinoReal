package com.casinoreal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeriahhumphrey on 1/27/17.
 */
public class TexasHoldemTest {
    TexasHoldem game;


    @Before
    public void setUp(){
        game = new TexasHoldem();
    }



    @Test
    public void flopTurnRiverTest(){
        int expected = 5;
        game.flopTurnRiver();
        int actual = game.turnHand.cards.size();
        assertEquals("I expect the size of turnhand to be 5 cards", expected, actual);
    }

    @Test
    public void dealAddsTwoCardsToPlayerTest(){
        int expected=2;
        game.deal();
        int actual= game.playerHand.cards.size();
        assertEquals("T expect the amount of playerCards to be 2", expected, actual);
    }

    @Test
    public void combineHandadds5CardsToPlayerTest(){
        game.deal();
        int oldsize = game.playerHand.cards.size();
        int expected = 5;
        game.flopTurnRiver();
        game.CombineHand();
        int actual = game.playerHand.cards.size()- oldsize;
        assertEquals("I expect the playerhand size to increase by 5", expected, actual);
    }

    @Test
    public void CompareTestWin(){
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.KING);
        Card c6 = new Card(Suit.SPADE, Rank.ACE);
        Card c3 = new Card(Suit.SPADE, Rank.TEN);
        Card c7 = new Card(Suit.HEART, Rank.SEVEN);
        Card c4 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c5 = new Card(Suit.SPADE, Rank.JACK);
        game.playerHand = new PokerHand();
        game.dealerHand = new PokerHand();
        game.playerHand.addCard(c1);
        game.playerHand.addCard(c2);
        game.playerHand.addCard(c3);
        game.playerHand.addCard(c4);
        game.playerHand.addCard(c5);
        game.playerHand.addCard(c6);
        game.playerHand.addCard(c7);
        Card d1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card d2 = new Card(Suit.SPADE, Rank.KING);
        Card d6 = new Card(Suit.CLUB, Rank.FIVE);
        Card d3 = new Card(Suit.DIAMOND, Rank.TEN);
        Card d7 = new Card(Suit.HEART, Rank.SEVEN);
        Card d4 = new Card(Suit.HEART, Rank.SEVEN);
        Card d5 = new Card(Suit.SPADE, Rank.JACK);
        game.dealerHand.addCard(d1);
        game.dealerHand.addCard(d2);
        game.dealerHand.addCard(d3);
        game.dealerHand.addCard(d4);
        game.dealerHand.addCard(d5);
        game.dealerHand.addCard(d6);
        game.dealerHand.addCard(d7);
        game.compare();
        String expected = "Yes";
        String actual = game.winner;
        assertEquals("I expect winner to read yes", expected, actual);
    }

    @Test
    public void CompareTestLose(){
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.KING);
        Card c6 = new Card(Suit.CLUB, Rank.ACE);
        Card c3 = new Card(Suit.DIAMOND, Rank.FIVE);
        Card c7 = new Card(Suit.HEART, Rank.EIGHT);
        Card c4 = new Card(Suit.SPADE, Rank.THREE);
        Card c5 = new Card(Suit.SPADE, Rank.JACK);
        game.playerHand = new PokerHand();
        game.dealerHand = new PokerHand();
        game.playerHand.addCard(c1);
        game.playerHand.addCard(c2);
        game.playerHand.addCard(c3);
        game.playerHand.addCard(c4);
        game.playerHand.addCard(c5);
        game.playerHand.addCard(c6);
        game.playerHand.addCard(c7);
        Card d1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card d2 = new Card(Suit.SPADE, Rank.KING);
        Card d6 = new Card(Suit.CLUB, Rank.KING);
        Card d3 = new Card(Suit.DIAMOND, Rank.TEN);
        Card d7 = new Card(Suit.HEART, Rank.SEVEN);
        Card d4 = new Card(Suit.HEART, Rank.SEVEN);
        Card d5 = new Card(Suit.SPADE, Rank.JACK);
        game.dealerHand.addCard(d1);
        game.dealerHand.addCard(d2);
        game.dealerHand.addCard(d3);
        game.dealerHand.addCard(d4);
        game.dealerHand.addCard(d5);
        game.dealerHand.addCard(d6);
        game.dealerHand.addCard(d7);
        game.compare();
        String expected = "No";
        String actual = game.winner;
        assertEquals("I expect winner to read yes", expected, actual);
    }
}
