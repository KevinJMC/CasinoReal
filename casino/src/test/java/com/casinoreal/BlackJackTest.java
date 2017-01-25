package com.casinoreal;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJackTest {

    BlackJack blackJack = new BlackJack();

    @Test
    public void dealToPlayersTest(){
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.playerHand.size();
        Assert.assertEquals( expected, actual);
    }

}
