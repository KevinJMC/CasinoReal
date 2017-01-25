package com.casinoreal;

import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by alexandraarmstrong on 1/24/17.
 */
public class Slots {
    @Test
    @Ignore
    public void checkWinMiddleTest(){
        Slots slotGame = new Slots();
        boolean expected = true;
        boolean actual = (((slotGame.checkWinMiddle() > 0d) && (slotGame.checkWinMiddle() <= 400d)));
        assertEquals(expected, actual);
        //assertTrue(((slotGame.checkWinMiddle() > 0d) && (slotGame.checkWinMiddle() <= 400d)));
    }
}
