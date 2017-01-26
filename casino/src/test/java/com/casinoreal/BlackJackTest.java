package com.casinoreal;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJackTest {

    Player randall = new Player();
    BlackJack blackJack = new BlackJack();

    @Test
    public void dealToPlayersPlayersHandTest(){
        blackJack.joinMembersInGame();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.playerHand.size();
        Assert.assertEquals("Expects hand size to be 2", expected, actual);
    }

    @Test
    public void dealToPlayersDealersHandTest(){
        blackJack.joinMembersInGame();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.dealerHand.size();
        Assert.assertEquals("Expects hand size to be 2", expected, actual);
    }

    @Test
    public void hitTest(){
        blackJack.joinMembersInGame();
        blackJack.dealToPlayers();
        blackJack.hit(blackJack.playerHand);
        int expected = 3;
        int actual = blackJack.playerHand.size();
        Assert.assertEquals("Expects hand size to be 3", expected, actual);
    }

    @Test
    public void setWagerTest() {
        randall.updateBalance(1000.00);
        blackJack.setWager(randall, 500);
        double expected = 500.0;
        double actual = randall.getBalance();
        Assert.assertEquals("Expects balance to be 500.0",expected,actual, 0.0);
    }
}
