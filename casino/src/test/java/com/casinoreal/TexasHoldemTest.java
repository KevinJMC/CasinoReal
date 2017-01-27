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
}