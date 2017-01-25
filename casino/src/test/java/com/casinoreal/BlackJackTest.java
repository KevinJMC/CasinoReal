package com.casinoreal;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJackTest {


    BlackJack blackJack = new BlackJack();

    @Test
    public void dealToPlayersPlayersHandTest(){
        blackJack.joinMembersInGame();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.playerHand.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dealToPlayersDealersHandTest(){
        blackJack.joinMembersInGame();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.dealerHand.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hitTest(){
        blackJack.joinMembersInGame();
        blackJack.dealToPlayers();
        blackJack.hit(blackJack.playerHand);
        int expected = 3;
        int actual = blackJack.playerHand.size();
        Assert.assertEquals(expected, actual);
    }

    Test
}
