package com.casinoreal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by jeriahhumphrey on 1/25/17.
 */
public class PokerHandTest {

    PokerHand hand;

    @Before
    public void setUp() {
        hand = new PokerHand();
        ArrayList<Card> cards = new ArrayList<Card>();
    }


    @Test
    public void addCardsTest() {
        int expected = 4;
        int actual = hand.addCards(4);

        assertEquals("I expect the card list to increase by 4", expected, actual);

    }
    @Test
    public void CardCountWontExceed7Test() {
        int expected = 7;
        int actual = hand.addCards(2000000);

        assertEquals("I expect the card list to increase by 4", expected, actual);

    }

    @Test
    public void flushTestRandom(){
        hand.addCards(5);
        hand.getHand();
        boolean expected = false;
        boolean actual = hand.isAFlush();
        assertEquals("I expect it to be false", expected, actual);
    }

    @Test
    public void flushTestDeliberate(){
        Card c1 = new Card(Suit.SPADE, Rank.EIGHT);
        Card c2 = new Card(Suit.SPADE, Rank.FIVE);
        Card c3 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c4 = new Card(Suit.SPADE, Rank.SIX);
        Card c5 = new Card(Suit.SPADE, Rank.JACK);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAFlush();
        assertEquals("I expect it to be true", expected, actual);
    }





}