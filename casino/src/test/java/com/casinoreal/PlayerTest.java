package com.casinoreal;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andresholland on 1/25/17.
 */

public class PlayerTest {


    @Test
    public void updateBalanceWith0() {
        Player player = new Player();
        double expected = player.getBalance();
        player.updateBalance(0);
        double actual = player.getBalance();
        Assert.assertEquals("Balance should not increase", expected, actual, 0);
    }

    @Test
    public void updateBalanceWithPositive() {
        Player player = new Player();
        double expected = player.getBalance() + 10;
        player.updateBalance(10);
        double actual = player.getBalance();
        Assert.assertEquals("Balance should increase by 10", expected, actual, 0);
    }

    @Test
    public void updateBalanceWithNegative() {
        Player player = new Player();
        double expected = player.getBalance() - 10;
        player.updateBalance(-10);
        double actual = player.getBalance();
        Assert.assertEquals("Balance should decrease by 10", expected, actual, 0);
    }


}