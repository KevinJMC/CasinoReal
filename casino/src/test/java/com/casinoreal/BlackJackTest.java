package com.casinoreal;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJackTest {

    Player randall = new Player();
    BlackJack blackJack = new BlackJack();
    Card aceSpade = new Card(Suit.SPADE, Rank.ACE);
    Card kingSpade = new Card(Suit.SPADE, Rank.KING);
    Card sevenSpade = new Card(Suit.SPADE,Rank.SEVEN);
    Card fiveSpade = new Card(Suit.SPADE, Rank.FIVE);

    @Test
    public void joinMembersInGameTest(){
        blackJack.setTable();
        int expected = 2;
        int actual = blackJack.getMembersInGame().size();
        Assert.assertEquals("Expects number of arrays to be 2", expected, actual);

    }
    @Test
    public void dealToPlayersPlayersHandTest(){
        blackJack.setTable();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.playerHand.size();
        Assert.assertEquals("Expects hand size to be 2", expected, actual);
    }

    @Test
    public void dealToPlayersDealersHandTest(){
        blackJack.setTable();
        blackJack.dealToPlayers();
        int expected = 2;
        int actual = blackJack.dealerHand.size();
        Assert.assertEquals("Expects hand size to be 2", expected, actual);
    }

    @Test
    public void dealFromShoeTest(){
        blackJack.setTable();
        blackJack.dealToPlayers();
        blackJack.dealFromShoe(blackJack.playerHand);
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
    @Test
    public void createHandValuesPlayerTest(){
        blackJack.playerHand.add(aceSpade);
        blackJack.createPlayerHandValue();
        int expected = 11;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's Handvalue is 11", expected, actual);
    }

    @Test
    public void createHandValuesPlayer2CardsTest(){
        blackJack.playerHand.add(aceSpade);
        blackJack.playerHand.add(kingSpade);
        blackJack.createPlayerHandValue();
        int expected = 21;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's Handvalue is 21", expected, actual);
    }

    @Test
    public void createHandValuesPlayer2AcesTest(){
        blackJack.setTable();
        blackJack.playerHand.add(aceSpade);
        blackJack.playerHand.add(aceSpade);
        blackJack.createPlayerHandValue();
        int expected = 12;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's Handvalue is > 12", expected, actual);
    }

    @Test
    public void createHandValuesPlayer2AcesAndKingTest(){
        blackJack.playerHand.add(aceSpade);
        blackJack.playerHand.add(aceSpade);
        blackJack.playerHand.add(kingSpade);
        blackJack.createPlayerHandValue();
        int expected = 22;
        int actual = blackJack.getPlayerHandValue();
        Assert.assertEquals("Asserts that Player's Handvalue is > 22", expected, actual);
    }

    @Test
    public void comparePlayer22vsDealer17Test(){
        blackJack.playerHand.add(aceSpade);
        blackJack.playerHand.add(aceSpade);
        blackJack.playerHand.add(kingSpade);
        blackJack.dealerHand.add(kingSpade);
        blackJack.dealerHand.add(sevenSpade);

        System.out.println(blackJack.getDealerHandValue());
        System.out.println(blackJack.getPlayerHandValue());
        boolean expected = true;
        boolean actual = blackJack.compare(blackJack.getPlayerHandValue(), blackJack.getDealerHandValue(), randall);
        Assert.assertEquals("Asserts that compare would return true 22 > 17", expected, actual);
    }

    @Test
    public void createHandValuesDealerTest(){
        blackJack.setTable();
        blackJack.dealToPlayers();
        blackJack.createDealerHandValue();
        boolean expected = true;
        boolean actual = (blackJack.getDealerHandValue() > 0);
        Assert.assertEquals("Asserts that Player's Handvalue is > 0", expected, actual);
    }

    @Test
    public void isNatural21Test(){
        boolean actual =blackJack.isNatural21(21);
        boolean expected = true;
        Assert.assertEquals("Expects to return TRUE because of 21", expected, actual);
    }

}
