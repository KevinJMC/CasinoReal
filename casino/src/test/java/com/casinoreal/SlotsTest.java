package com.casinoreal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by alexandraarmstrong on 1/24/17.
 */
public class SlotsTest {
    Slots slotGame;
    @Before
    public void setUp(){
        slotGame = new Slots();
    }

    @Test
    public void checkWinMiddleTest(){
        assertTrue(((slotGame.checkWinMiddle() >= 0d) && (slotGame.checkWinMiddle() <= 400d)));
    }

    @Test
    public void checkWinTopTest(){
        assertTrue(((slotGame.checkWinTop() >= 0d) && (slotGame.checkWinTop() <= 400d)));
    }

    @Test
    public void checkWinBottomTest(){
        assertTrue(((slotGame.checkWinBottom() >= 0d) && (slotGame.checkWinBottom() <= 400d)));
    }

    @Test
    public void checkWinDiagonalLeftTest(){
        assertTrue(((slotGame.checkWinDiagonalLeft() >= 0d) && (slotGame.checkWinDiagonalLeft() <= 400d)));
    }

    @Test
    public void checkWinDiagonalRightTest(){
        assertTrue(((slotGame.checkWinDiagonalRight() >= 0d) && (slotGame.checkWinDiagonalRight() <= 400d)));
    }
}
