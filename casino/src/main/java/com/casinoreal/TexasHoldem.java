package com.casinoreal;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

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
    Shoe shoe = new Shoe(1);
    private double bet;
    private boolean play = false;



    public void setPlayer(Player theUser) {
        player1 = theUser;
    }

    public void placeBet(double number) {
        player1.setBalance(-number);
    }

    public void deal() {
        playerHand = new PokerHand();
        playerHand.addCard(shoe.drawCard());
        playerHand.addCard(shoe.drawCard());
        shoe.shuffle();
        dealerHand = new PokerHand();
        dealerHand.addCard(shoe.drawCard());
        dealerHand.addCard(shoe.drawCard());
    }

    public void displayPlayerHand() {
        //System.out.println("Your hand:");
        //playerHand.getHand();
    }

    public void displayDealerHand() {
        //System.out.println("Dealer's  hand:");
        //dealerHand.getHand();
    }

    public void flopTurnRiver() {
        ArrayList<Card> turnCards = new ArrayList<Card>();
        //System.out.println("The turn");
        //System.out.println("The Flop");
        //System.out.println("The River");
        turnHand.addCards(5);
        //IO.displayPokerHandScreen(turnHand, "TURN");
    }

    public void CombineHand() {
        for (int i = 0; i < 5; i++) {
            playerHand.addCard(turnHand.cards.get(i));
            dealerHand.addCard(turnHand.cards.get(i));
        }
    }

    public String compare() {
        String message= "";
        int winCounter = 0;
        //System.out.println("Your hand: ");
        IO.displayPokerHandScreen(playerHand, "PLAYER HAND");
        System.out.println("You have a ");
        playerHand.rankHand();
        playerHand.getRank();
        System.out.println();
        System.out.println();
        displayDealerHand();
        System.out.println("Dealer has a ");
        dealerHand.rankHand();
        dealerHand.getRank();

        if (playerHand.getRank() > dealerHand.getRank()) {
            message = "You Win!!!!!";
        } else if (playerHand.getRank() == dealerHand.getRank()) {

            message = "There are no ties. Dealer wins";
        } else {
            message = "You lose";
        }

        System.out.println(message);
        return  message;

    }

    @Override
    public void startGame() {
        do {
            IO.displayPokerWelcomeScreen();
            //System.out.println("Place your bet");
            bet = IO.getWager();
            placeBet(bet);
            System.out.println("Your Hand");
            this.deal();
            IO.displayPokerHandScreen(dealerHand, playerHand, turnHand, "");
            IO.waitForEnter();

            //IO.displayPokerHandScreen(playerHand, "PLAYER HAND");

            this.flopTurnRiver();
            IO.displayPokerHandScreen(dealerHand, playerHand, turnHand, "ALL CARDS");
            IO.waitForEnter();

            this.CombineHand();
            this.compare();
            System.out.println("Would you like to play again?");

        }
        while (play);


    }


    public boolean checkForWin() {
        return false;

    }


    public static void main(String[] args) {
        TexasHoldem game = new TexasHoldem();

        game.startGame();

    }
}
