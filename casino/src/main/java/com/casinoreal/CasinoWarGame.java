package com.casinoreal;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by andresholland on 1/25/17.
 */
public class CasinoWarGame extends CardGames {

    //private double runningBalance;
    //private double currentBet;
    private Card dealerCard;
    private Card playerCard;
    private boolean keepPlaying = true;

    public void startGame() {

        while (keepPlaying) {

            // display welcome
            IO.displayWelcomeToWarScreen();

            // System.out.println("How much would you like to bet?");

            // get bet
            bet = IO.getWager();

            //Scanner scanBet = new Scanner(System.in);

            // if you're broke get out the game
            if (player.getBalance() == 0.0) {
                break;
            }
            // if you bet too much get out the game
            if (bet > player.getBalance()) {
                // should probably display a message about how you're broke
                break;
            }

            // only subtract after the player loses
            //runningBalance -= currentBet;

            Shoe casinoWarGame = new Shoe(1);
            dealerCard = casinoWarGame.drawCard();
            playerCard = casinoWarGame.drawCard();



/*            int dealerCardValue;
            int playerCardValue;
            String dealerCardRank = dealerCard.getRank().toString();
            String playerCardRank = playerCard.getRank().toString();

            if (dealerCardRank.equals("J")) {
                dealerCardValue = 11;
            } else if (dealerCardRank.equals("Q")) {
                dealerCardValue = 12;
            } else if (dealerCardRank.equals("K")) {
                dealerCardValue = 13;
            } else if (dealerCardRank.equals("A")) {
                dealerCardValue = 14;
            } else {
                dealerCardValue = parseInt(dealerCardRank);
            }

            if (playerCardRank.equals("J")) {
                playerCardValue = 11;
            } else if (playerCardRank.equals("Q")) {
                playerCardValue = 12;
            } else if (playerCardRank.equals("K")) {
                playerCardValue = 13;
            } else if (playerCardRank.equals("A")) {
                playerCardValue = 14;
            } else {
                playerCardValue = parseInt(playerCardRank);
            }*/


            // player card greater than dealer card, aka c1 > this in the method
            if ( dealerCard.compareTo(playerCard) < 1 ) {
                // win
                player.setBalance(player.getBalance() += (2 * bet));
                IO.displayWarHand(playerCard, dealerCard, true);
                /*System.out.println("you tied");
                System.out.println("ask user to play again after tie logic done");*/
            }
            // you tie
            else if ( dealerCard.compareTo(playerCard) == 0 ) {
                IO.displayWarHand(playerCard, dealerCard, false);
            }
            // you lose
            else {
                player.setBalance(player.getBalance() /* how much does a player lose when they lose */);
                IO.displayWarHand(playerCard, dealerCard, false);
            }

            /*if (playerCardValue > dealerCardValue) {
                runningBalance += currentBet * 2;
                System.out.println("Congrats, you're a winner! You're killing it, want to play again? (yes or no)");
            }

            if (playerCardValue < dealerCardValue) {
                System.out.println("You were so close, but looks like you lost. Time to reverse your luck, want to play again? (yes or no)");
            }

         /*   Scanner scanPlayAgain = new Scanner(System.in);
            String userAnswer = scanPlayAgain.nextLine();*/

            String userKeepPlaying = IO.getInputWarPlayAgain() ?
                    "y" : "n";

            if (userKeepPlaying.equalsIgnoreCase("n")) {
                break;
            }
            // userKeepPlaying == "y"
            else {}
        }


        return;
    }

    public void checkForWin() {

    }

    void quitGame() {

    }

    public void compare() {}

    public void deal() {}


    // just for notes
    public static void main(String[] args) {
        // run displayWelcomeToWarScreen();
    }

}
