package com.casinoreal;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by andresholland on 1/25/17.
 */
public class CasinoWarGame {

    private double runningBalance;
    private double currentBet;
    private Card dealerCard;
    private Card playerCard;
    private boolean keepPlaying = true;



    public double runGame () {

        while (keepPlaying) {

            System.out.println("How much would you like to bet?");
            Scanner scanBet = new Scanner(System.in);
            currentBet = scanBet.nextDouble();
            if (runningBalance == 0) {
                break;
            }
            if (currentBet > runningBalance) {
                currentBet = runningBalance;
            }
            runningBalance -= currentBet;

            Shoe casinoWarGame = new Shoe(1);
            dealerCard = casinoWarGame.drawCard();
            playerCard = casinoWarGame.drawCard();


            int dealerCardValue;
            int playerCardValue;
            String dealerCardRank = dealerCard.getRank().toString();
            String playerCardRank = playerCard.getRank().toString();
            if (dealerCardRank.equals("J")) {
                dealerCardValue = 11;
        }
                else if (dealerCardRank.equals("Q")) {
                    dealerCardValue = 12;
                }
                else if (dealerCardRank.equals("K")) {
                        dealerCardValue = 13;
                    }
                else if (dealerCardRank.equals("A")) {
                    dealerCardValue = 14;
                }
                else {
                    dealerCardValue = parseInt(dealerCardRank);
                }

            if (playerCardRank.equals("J")) {
                playerCardValue = 11;
            }
            else if (playerCardRank.equals("Q")) {
                playerCardValue = 12;
            }
            else if (playerCardRank.equals("K")) {
                playerCardValue = 13;
            }
            else if (playerCardRank.equals("A")) {
                playerCardValue = 14;
            }
            else {
                playerCardValue = parseInt(playerCardRank);
            }


            if (dealerCardValue == playerCardValue) {
                System.out.println("you tied");
                System.out.println("ask user to play again after tie logic done");
            }

            if (playerCardValue > dealerCardValue) {
                runningBalance += currentBet * 2;
                System.out.println("Congrats, you're a winner! You're killing it, want to play again? (yes or no)");
            }

            if (playerCardValue < dealerCardValue) {
                System.out.println("You were so close, but looks like you lost. Time to reverse your luck, want to play again? (yes or no)");
            }

            Scanner scanPlayAgain = new Scanner(System.in);
            String userAnswer = scanPlayAgain.nextLine();

            if (userAnswer.equalsIgnoreCase("no")) {
                keepPlaying = false;
            }

            if (!keepPlaying) {
                break;
            }
        }
        return runningBalance;
    }

}
