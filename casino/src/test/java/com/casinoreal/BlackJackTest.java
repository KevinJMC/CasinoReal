package com.casinoreal;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJackTest {

    Player randall = new Player();
    BlackJackEngine blackJack = new BlackJackEngine(randall);
    Card aceSpade = new Card(Suit.SPADE, Rank.ACE);
    Card kingSpade = new Card(Suit.SPADE, Rank.KING);
    Card sevenSpade = new Card(Suit.SPADE, Rank.SEVEN);
    Card fiveSpade = new Card(Suit.SPADE, Rank.FIVE);
    Card sixSpade = new Card(Suit.SPADE,Rank.SIX);

    @Test
    public void joinMembersInGameTest() {
        blackJack.setTable();
        int expected = 2;
        int actual = blackJack.getMembersInGame().size();
        Assert.assertEquals("Expects number of arrays to be 2", expected, actual);

    }

    @Test
    public void dealToPlayersPlayersHandTest() {
        blackJack.setTable();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.getPlayerHand().size();
        Assert.assertEquals("Expects hand size to be 2", expected, actual);
    }

    @Test
    public void dealToPlayersDealersHandTest() {
        blackJack.setTable();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.getDealerHand().size();
        Assert.assertEquals("Expects hand size to be 2", expected, actual);
    }

    @Test
    public void dealFromShoeTest() {
        blackJack.setTable();
        blackJack.dealToPlayers();
        blackJack.dealFromShoe(blackJack.getPlayerHand());
        int expected = 3;
        int actual = blackJack.getPlayerHand().size();
        Assert.assertEquals("Expects hand size to be 3", expected, actual);
    }

    @Test
    public void setWagerTest() {
        randall.updateBalance(1000.00);
        blackJack.setWager(randall, 500);
        double expected = 500.0;
        double actual = randall.getBalance();
        Assert.assertEquals("Expects balance to be 500.0", expected, actual, 0.0);
    }

    @Test
    public void createHandValuesPlayerTest() {
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.calculatePlayerHandValue();
        int expected = 11;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's hand value is 11", expected, actual);
    }


    @Test
    public void createHandValuesPlayer2CardsTest() {
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.calculatePlayerHandValue();
        int expected = 21;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's hand value is 21", expected, actual);
    }

    @Test
    public void createHandValuesPlayer2AcesTest() {
        blackJack.setTable();
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.calculatePlayerHandValue();
        int expected = 12;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's hand value is = 12", expected, actual);
    }

    @Test
    public void createHandValuesPlayer6A5Test() {
        blackJack.setTable();
        blackJack.getPlayerHand().add(sixSpade);
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.getPlayerHand().add(fiveSpade);
        blackJack.calculatePlayerHandValue();
        int expected = 12;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's hand value is = 12", expected, actual);
    }

    @Test
    public void createHandValuesPlayer2AcesAndKingTest() {
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.calculatePlayerHandValue();
        int expected = 22;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's hand value is > 22", expected, actual);
    }

    @Test
    public void checkForWinWinTest() {
        blackJack.getPlayerHand().add(fiveSpade);
        blackJack.getPlayerHand().add(fiveSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(sevenSpade);
        blackJack.calculateDealerHandValue();
        blackJack.calculatePlayerHandValue();
        boolean expected = true;
        boolean actual = blackJack.checkForWin();
        Assert.assertEquals("Asserts that compare would return true 20 > 17", expected, actual);
    }

    @Test
    public void checkForWinLostTest() {
        blackJack.getPlayerHand().add(fiveSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(sevenSpade);
        blackJack.calculateDealerHandValue();
        blackJack.calculatePlayerHandValue();
        boolean expected = false;
        boolean actual = blackJack.checkForWin();
        Assert.assertEquals("Asserts that compare would return false 15 < 17", expected, actual);
    }

    @Test
    public void checkForWinPlayerBustLost() {
        blackJack.getPlayerHand().add(fiveSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(sevenSpade);
        blackJack.calculateDealerHandValue();
        blackJack.calculatePlayerHandValue();
        boolean expected = false;
        boolean actual = blackJack.checkForWin();
        System.out.println(blackJack.getResults());
        Assert.assertEquals("Asserts that compare would return false 25 Bust", expected, actual);
    }

    @Test
    public void checkForWinDealerBustWin() {
        blackJack.getPlayerHand().add(fiveSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(sevenSpade);
        blackJack.calculateDealerHandValue();
        blackJack.calculatePlayerHandValue();
        boolean expected = true;
        boolean actual = blackJack.checkForWin();
        System.out.println(blackJack.getResults());
        Assert.assertEquals("Asserts that compare would return true 27 Bust", expected, actual);
    }

    @Test
    public void checkForWinPush() {
        blackJack.getPlayerHand().add(fiveSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(fiveSpade);
        blackJack.calculateDealerHandValue();
        blackJack.calculatePlayerHandValue();
        boolean expected = false;
        boolean actual = blackJack.checkForWin();
        System.out.println(blackJack.getResults());
        Assert.assertEquals("Asserts that compare would return false 15 Push", expected, actual);
    }

    @Test
    public void checkForWinNatural21Win() {
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(fiveSpade);
        blackJack.calculateDealerHandValue();
        blackJack.calculatePlayerHandValue();
        boolean expected = true;
        boolean actual = blackJack.checkForWin();
        System.out.println(blackJack.getResults());
        Assert.assertEquals("Asserts that compare would return true 21 > 15", expected, actual);
    }

    @Test
    public void checkForWinNatural21Push() {
        blackJack.getPlayerHand().add(aceSpade);
        blackJack.getPlayerHand().add(kingSpade);
        blackJack.getDealerHand().add(kingSpade);
        blackJack.getDealerHand().add(aceSpade);
        blackJack.calculateDealerHandValue();
        blackJack.calculatePlayerHandValue();
        boolean expected = false;
        boolean actual = blackJack.checkForWin();
        System.out.println(blackJack.getResults());
        Assert.assertEquals("Asserts that compare would return false 21 push", expected, actual);
    }



    @Test
    public void createHandValuesDealerTest() {
        blackJack.setTable();
        blackJack.dealToPlayers();
        blackJack.calculateDealerHandValue();
        boolean expected = true;
        boolean actual = (blackJack.getDealerHandValue() > 0);
        Assert.assertEquals("Asserts that Player's Handvalue is > 0", expected, actual);
    }


    @Test
    public void doubleDownTest(){
        randall.updateBalance(1000.00);
        blackJack.setWager(randall, 500);
        blackJack.doubleDown(randall);
        double expected = 0.0;
        double actual = randall.getBalance();
        Assert.assertEquals("Expects balance to be 0.0 after double down", expected, actual, 0.0);

    }
}
