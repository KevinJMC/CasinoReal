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
    Shoe shoe= new Shoe(1);


    @Override
    public void setPlayer(Player player) {
        super.setPlayer(player);
        playerHand = new PokerHand();
    }

    public void deal(){
        playerHand = new PokerHand();
        playerHand.addCard(shoe.drawCard());
        playerHand.addCard(shoe.drawCard());
        shoe.shuffle();
        dealerHand = new PokerHand();
        dealerHand.addCard(shoe.drawCard());
        dealerHand.addCard(shoe.drawCard());
    }
    public void displayPlayerHand() {
        System.out.println("Your hand:");
        playerHand.getHand();
    }

    public void displayDealerHand() {
        System.out.println("Dealer's  hand:");
        dealerHand.getHand();
    }

    public void flopTurnRiver() {
        ArrayList<Card> turnCards = new ArrayList <Card>();
        System.out.println("The turn");
        System.out.println("The Flop");
        System.out.println("The River");
        turnHand.addCards(5);
        turnHand.getHand();
    }

    public void CombineHand() {
        for (int i = 0; i < 5; i++) {
            playerHand.addCard(turnHand.cards.get(i));
            dealerHand.addCard(turnHand.cards.get(i));
        }
    }

    public void  compare() {
        int winCounter=0;
        this.displayPlayerHand();
        System.out.println("You have a ");
        playerHand.rankHand();
        playerHand.getRank();


        this.displayDealerHand();
        System.out.println("Dealer has a ");
        dealerHand.rankHand();
        dealerHand.getRank();

        if (playerHand.getRank() > dealerHand.getRank()) {
            System.out.println("You win");
        } else if (playerHand.getRank() == dealerHand.getRank()) {
            if (playerHand.trigger.getRank().ordinal() > dealerHand.trigger.getRank().ordinal()) {
                System.out.println("You win");
            } else {
                System.out.println("You lose");
            }
        }


    }

    @Override
    public void startGame() {
        System.out.println("Welcome to Texas Holdem!");
        System.out.println("Place your bet");
        this.deal();
        this.displayPlayerHand();
        this.displayDealerHand();
        this.flopTurnRiver();
        this.CombineHand();
        this.compare();

    }

    @Override
    public boolean checkForWin() {
        if (winCounter>0){
            System.out.println("You Win!!");
            return true;
        }
        else{
            System.out.println("You lose");
            return false;
        }
    }


    }


