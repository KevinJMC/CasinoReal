package com.casinoreal;

/**
 * Created by andresholland on 1/25/17.
 */
public class CasinoWarGame extends CardGames {

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
            if ( dealerCard.compareTo(playerCard) < 1 ) {
                player.setBalance(player.getBalance() + (2 * bet));
                IO.displayWarHand(playerCard, dealerCard, true);
            }
            // you tie
            else if ( dealerCard.compareTo(playerCard) == 0 ) {
                IO.displayWarHand(playerCard, dealerCard, false);
            }
            // you lose
            else {
                player.setBalance(player.getBalance() - bet);
                IO.displayWarHand(playerCard, dealerCard, false);
            }

            if (!IO.getInputWarPlayAgain()) {
                break;
            }
        }
    }

    /*
    // might wanna refactor this in Game since it has no
    //  return and takes no args
    @Override
    public boolean checkForWin() {
        // can this be done inside each game loop or
        //  should it return a bool?
        return true;
    }

    void quitGame() {
        // is a break statement in most cases, do we need this in Game?
    }

    public void deal() {
        // we 'deal' from shoe not card game, all card games involve dealing
        // but the shoe is the object that actually 'deals out' a card
    }


    public void compare() {
        // do we need this if we have a compare to method for cards?
    }
    */

}
