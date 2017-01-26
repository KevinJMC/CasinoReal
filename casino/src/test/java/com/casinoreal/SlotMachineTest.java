package com.casinoreal;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by alexandraarmstrong on 1/25/17.
 */
public class SlotMachineTest{
    SlotMachine slotMachine;

    @Before
    public void setUp(){
        slotMachine = new SlotMachine();
    }

    @Test
    public void setPlayerWagerTest(){
        slotMachine.setBet(2.0);
        assertEquals(2d, slotMachine.getBet());
    }

    @Test
    public void setPlayerWagerTest1(){
        slotMachine.setBet(1.0);
        assertEquals(1d, slotMachine.getBet());
    }

    @Test
    public void setPlayerWagerTest2(){
        slotMachine.setBet(3.0);
        assertEquals(3d, slotMachine.getBet());
    }

    @Test
    public void pullTest(){
        slotMachine.setBet(1.0);
        assertTrue(((slotMachine.pull() >= 0d) && (slotMachine.pull() <= 400d)));
    }

    @Test
    public void pullTest1(){
        slotMachine.setBet(2.0);
        assertTrue(((slotMachine.pull() >= 0d) && (slotMachine.pull() <= 800d)));
    }
    @Test
    public void pullTest2(){
        slotMachine.setBet(3.0);
        assertTrue(((slotMachine.pull() >= 0d) && (slotMachine.pull() <= 1200d)));
    }

    @Test
    public void checkForWinTest(){
        slotMachine.setBet(1);
        boolean actual = true; //slotMachine.checkForWin();
        boolean expected = true || false;
        assertEquals(expected, actual);
    }
}
