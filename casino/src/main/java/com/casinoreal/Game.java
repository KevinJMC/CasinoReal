package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/24/17.
 */
abstract public class Game {
    protected double bet;
    protected Player player;

    abstract public void startGame();

    abstract public void checkForWin();

    public double getBet(){
        return bet;
    }

    public void setBet(double bet){
        this.bet = bet;
        player.setBalance(player.getBalance() - getBet());
    }

}
