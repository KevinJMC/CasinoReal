package com.casinoreal;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;

/**
 * Created by jeriahhumphrey on 1/26/17.
 */
public class TexasHoldem extends CardGames {
     PokerHand playerHand;
     PokerHand dealerHand;
    PokerHand turnHand = new PokerHand();
    private Player player1;
    Shoe shoe = new Shoe(1);
    private double bet;
    private boolean play = false;



    public void setPlayer(Player theUser) {
        player1 = theUser;
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

    public void compare() {

        playerHand.rankHand();
        playerHand.getRank();
        playerHand.displayResult("Player");
//        displayDealerHand();
        dealerHand.rankHand();
        dealerHand.getRank();
        dealerHand.displayResult("Dealer");

        if (playerHand.getRank() > dealerHand.getRank()) {
            IO.displayGenericHeaderAndMessageScreen("A WINNER IS YOU!!", "CONGLATURATION");
        }
        else if (playerHand.getRank() == dealerHand.getRank()) {
            if (playerHand.trigger.getRank().ordinal() > dealerHand.trigger.getRank().ordinal()) {
                IO.displayGenericHeaderAndMessageScreen("A WINNER IS YOU!!", "CONGLATURATION");
            } else if
                    (playerHand.trigger.getRank().ordinal() == dealerHand.trigger.getRank().ordinal()) {
                IO.displayGenericHeaderAndMessageScreen("NO TIES HERE", "YOU LOSE");
            }
        }
         else  {
                IO.displayGenericHeaderAndMessageScreen("YOU LOSE", "SORRY");
            }
        }


    @Override
    public void startGame() {
        do {
            IO.displayPokerWelcomeScreen();
            //System.out.println("Place your bet");
            bet = IO.getWager();
            System.out.println("Your Hand");
            this.deal();
            IO.displayPokerHandScreen(playerHand, dealerHand, turnHand, "");
            IO.waitForEnter();



            this.flopTurnRiver();
            IO.displayPokerHandScreen(playerHand, dealerHand, turnHand, "ALL CARDS");
            IO.waitForEnter();

            this.CombineHand();

            IO.displayPokerHandScreen(playerHand, dealerHand, turnHand, "FINAL DEAL");
            IO.waitForEnter();

            this.compare();
            IO.waitForEnter();


            //System.out.println("Would you like to play again?");
            IO.displayGenericHeaderAndMessageScreen("TEXAS HOLDEM", "WOULD YOU LIKE TO PLAY AGAIN?");
            play = IO.getInputKenoPlayAgain();
            turnHand.resetTurn();
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
