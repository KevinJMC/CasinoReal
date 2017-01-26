package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/24/17.
 */
abstract public class Game {
    protected double bet;
    protected Player player;

    //private I/O;

    abstract public void startGame();

    abstract public void checkForWin();

    abstract void quitGame();

}
