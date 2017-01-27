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


    public void clearHand(PokerHand p ){
        for (int i = 0; i <p.cards.size();i++){
            p.cards.remove(i);

        }
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
    public void flushTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.TEN);
        Card c6 = new Card(Suit.SPADE, Rank.FIVE);
        Card c3 = new Card(Suit.CLUB, Rank.SEVEN);
        Card c7 = new Card(Suit.HEART, Rank.EIGHT);
        Card c4 = new Card(Suit.SPADE, Rank.SIX);
        Card c5 = new Card(Suit.SPADE, Rank.JACK);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAFlush();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void straightTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c2 = new Card(Suit.SPADE, Rank.EIGHT);
        Card c3 = new Card(Suit.SPADE, Rank.FIVE);
        Card c4 = new Card(Suit.SPADE, Rank.SIX);
        Card c5 = new Card(Suit.SPADE, Rank.FOUR);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAStraight();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void fourOfAKindTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.TEN);
        Card c6 = new Card(Suit.DIAMOND, Rank.TEN);
        Card c3 = new Card(Suit.CLUB, Rank.TEN);
        Card c7 = new Card(Suit.HEART, Rank.TEN);
        Card c4 = new Card(Suit.SPADE, Rank.SIX);
        Card c5 = new Card(Suit.SPADE, Rank.JACK);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAFourOfAKind();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void threeOfAKindTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.TEN);
        Card c6 = new Card(Suit.DIAMOND, Rank.FIVE);
        Card c3 = new Card(Suit.CLUB, Rank.SEVEN);
        Card c7 = new Card(Suit.HEART, Rank.SEVEN);
        Card c4 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c5 = new Card(Suit.DIAMOND, Rank.JACK);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAThreeOfAKind();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void PairTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.TEN);
        Card c5 = new Card(Suit.DIAMOND, Rank.TEN);
        Card c3 = new Card(Suit.CLUB, Rank.SEVEN);
        Card c4 = new Card(Suit.HEART, Rank.EIGHT);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAPair();
        assertEquals("I expect it to be true", expected, actual);
    }


    @Test
    public void twoPairTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.EIGHT);
        Card c2 = new Card(Suit.SPADE, Rank.TEN);
        Card c5 = new Card(Suit.DIAMOND, Rank.TEN);
        Card c3 = new Card(Suit.CLUB, Rank.SEVEN);
        Card c4 = new Card(Suit.HEART, Rank.SEVEN);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isTwoPair();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void straightFlushTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.TEN);
        Card c6 = new Card(Suit.DIAMOND, Rank.FIVE);
        Card c3 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c7 = new Card(Suit.SPADE, Rank.EIGHT);
        Card c4 = new Card(Suit.SPADE, Rank.SIX);
        Card c5 = new Card(Suit.SPADE, Rank.NINE);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAStraightFlush();
        assertEquals("I expect it to be true", expected, actual);


    }
    @Test
    public void fullHouseTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.FIVE);
        Card c6 = new Card(Suit.DIAMOND, Rank.FIVE);
        Card c3 = new Card(Suit.CLUB, Rank.SEVEN);
        Card c7 = new Card(Suit.HEART, Rank.SEVEN);
        Card c4 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c5 = new Card(Suit.DIAMOND, Rank.JACK);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isAFullHouse();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void royalFlushTestDeliberate() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.KING);
        Card c6 = new Card(Suit.SPADE, Rank.ACE);
        Card c3 = new Card(Suit.SPADE, Rank.TEN);
        Card c7 = new Card(Suit.HEART, Rank.SEVEN);
        Card c4 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c5 = new Card(Suit.SPADE, Rank.JACK);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        boolean expected = true;
        boolean actual = hand.isARoyalFlush();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void cardRankTest() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c6 = new Card(Suit.HEART, Rank.NINE);
        Card c3 = new Card(Suit.DIAMOND, Rank.NINE);
        Card c7 = new Card(Suit.SPADE, Rank.JACK);
        Card c4 = new Card(Suit.SPADE, Rank.JACK);
        Card c5 = new Card(Suit.CLUB, Rank.NINE);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        String expected = "Full House";
        String actual = hand.rankHand();
        assertEquals("I expect it to be true", expected, actual);
    }

    @Test
    public void getRankTest() {
        Card c1 = new Card(Suit.SPADE, Rank.QUEEN);
        Card c2 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c6 = new Card(Suit.HEART, Rank.NINE);
        Card c3 = new Card(Suit.DIAMOND, Rank.NINE);
        Card c7 = new Card(Suit.SPADE, Rank.JACK);
        Card c4 = new Card(Suit.SPADE, Rank.JACK);
        Card c5 = new Card(Suit.CLUB, Rank.NINE);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        hand.addCard(c6);
        hand.addCard(c7);
        hand.getHand();
        int expected = 7;
        hand.rankHand();
        int actual = hand.getRank();
        assertEquals("I expect it to be true", expected, actual);
    }







}