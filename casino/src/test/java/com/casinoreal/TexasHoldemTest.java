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
}