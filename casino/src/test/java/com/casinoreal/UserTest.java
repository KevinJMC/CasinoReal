package com.casinoreal;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andresholland on 1/25/17.
 */

public class UserTest {


    @Test
    public void reUpFirstTime() {
        User user = new User();
        double expected = user.getBalance() + 100;
        user.reUp();
        double actual = user.getBalance();
        Assert.assertEquals("Balance should increase by 100", expected, actual, 0);
    }

    @Test
    public void reUpSecondTime() {
        User user = new User();
        double expected = user.getBalance() + 200;
        user.reUp();
        user.reUp();
        double actual = user.getBalance();
        Assert.assertEquals("Balance should increase by 200", expected, actual, 0);
    }

    @Test
    public void reUpThirdTime() {
        User user = new User();
        double expected = user.getBalance() + 200;
        user.reUp();
        user.reUp();
        user.reUp();
        double actual = user.getBalance();
        Assert.assertEquals("Balance should only increase by 200, as reUp limit has been reached", expected, actual, 0);
    }

}