package com.casinoreal;

/**
 * @author Created by alexandraarmstrong on 1/24/17.
 */
abstract public class CardGames extends Game {

    Shoe shoe;
    Card[] hand;

    /**
     * abstract method inherited from game
     * used to start/run game
     */
    abstract public void startGame();

    /**
     * abstract method inherited from game
     * used to return win condition
     * @return boolean win or no win
     */
    abstract public boolean checkForWin();
}
