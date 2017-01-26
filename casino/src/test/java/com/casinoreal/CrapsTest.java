package com.casinoreal;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by kevinmccann on 1/25/17.
 */
public class CrapsTest {
    Craps craps;
    Player p;

    @Test
    public void checkSeven() throws Exception {
        craps = new Craps(p);
        assertTrue(craps.checkSeven(7));

    }

   /* @Test
    public void getDiceRoll() throws Exception {
        Craps craps = new Craps();
        int actual = craps.getDiceRoll();
        assertTrue(actual <= 12);
    }*/

}