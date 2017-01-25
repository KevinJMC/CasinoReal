package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/24/17.
 */
abstract public class CardGames extends Game {

    Shoe shoe;
    Card[] hand;

    abstract public void deal();

    abstract public void compare();

    abstract public void startGame();

    abstract public void checkForWin();
}
