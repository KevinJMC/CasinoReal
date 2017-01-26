package com.casinoreal;

import java.util.ArrayList;

 /**
 * Created by jeriahhumphrey on 1/26/17.
 */
public class TexasHoldem extends CardGames {
    private PokerHand playerHand;
    private PokerHand dealerHand;
    PokerHand turnHand = new PokerHand();
    private Player player1;
    private int winCounter;


    @Override
    public void setPlayer(Player player) {
        super.setPlayer(player);
        playerHand = new PokerHand();
    }


    public void displayHand() {
        System.out.println("Your hand:");
        playerHand.getHand();
    }

    public void flopTurnRiver() {
        ArrayList<Card> turnCards = new ArrayList <Card>();
        System.out.println("The turn");
        turnHand.addCards(3);
        turnHand.getHand();
        System.out.println("The Flop");
        turnHand.addCards(1);
        System.out.println("The River");
        turnHand.addCards(1);
    }

    public void CombineHand() {
        for (int i = 0; i < 5; i++) {
            playerHand.addCard(turnHand.cards.get(i));
            dealerHand.addCard(turnHand.cards.get(i));
        }
    }

    public void  compare() {
//        int winCounter=0;
//        playerHand.rankHand();
//        dealerHand.rankHand();
//
//        if (playerHand.getRank() > dealerHand.getRank()) {
//            winCounter = 1;
//        } else if (playerHand.getRank() == dealerHand.getRank()) {
//            if (playerHand.trigger.getRank().ordinal() > dealerHand.trigger.getRank().ordinal()) {
//                winCounter = 1;
//            } else {
//                winCounter = 0;
//            }
//        }


    }

    @Override
    public void startGame() {

    }

    @Override
    public boolean checkForWin() {
        if (winCounter==1){
            return true;
        }
        else{
            return false;
        }
    }
}

