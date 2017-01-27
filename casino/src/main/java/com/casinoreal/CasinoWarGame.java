package com.casinoreal;

/**
 * @author Created by andresholland on 1/25/17.
 */
public class CasinoWarGame extends CardGames {

    /**
     * inherited from game
     * runs game
     * takes bet
     * draws card
     * compares card with dealer
     * repeats unless player quits
     */
    public void startGame() {

        Card dealerCard;
        Card playerCard;

        while (true) {

            IO.displayWelcomeToWarScreen();

            bet = IO.getWager();

            // if you're broke get out the game
            if (player.getBalance() == 0.0) {
                break;
            }
            // if you bet too much get out the game
            if (bet > player.getBalance()) {
                break;
            }

            Shoe casinoWarGame = new Shoe(1);
            dealerCard = casinoWarGame.drawCard();
            playerCard = casinoWarGame.drawCard();

            // you win
            if (dealerCard.compareTo(playerCard) < 0) {
                player.setBalance(player.getBalance() + (bet));
                IO.displayWarHand(playerCard, dealerCard, true);
            }
            // you lose or tie (house advantage)
            else {
                player.setBalance(player.getBalance() - bet);
                IO.displayWarHand(playerCard, dealerCard, false);
            }

            if (!IO.getInputWarPlayAgain()) {
                break;
            }
        }
    }

    /**
     * inherited from game
     * not used
     * @return boolean
     */
    @Override
    public boolean checkForWin() {
        return false;
    }

}

