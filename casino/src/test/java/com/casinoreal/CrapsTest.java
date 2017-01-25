package com.casinoreal;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinmccann on 1/25/17.
 */
public class CrapsTest {

    @Test
    public void getDiceRoll() throws Exception {
        Craps craps = new Craps();
        int actual = craps.getDiceRoll();
        assertTrue(actual <= 12);
    }

}