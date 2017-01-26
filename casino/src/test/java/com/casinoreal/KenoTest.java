package com.casinoreal;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by alexandraarmstrong on 1/26/17.
 */
public class KenoTest {
    @Test
    @Ignore
    public void chooseNumberOfSpotsTest(){

    }

    @Test
    public void kenoBallDrawFillTest(){
        Keno keno = new Keno();
        keno.kenoBallDrawFill();
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual = keno.kenoBallDraw;
        assertArrayEquals(expected, actual);
    }
}
