package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/24/17.
 * The parent class for all casino games.  Most getters and setters are standard, except in the case of setBet().
 * setBet() checks the balance to see if it's zero, if the player has enough to pay the bet, and withdraws the bet from the player's balance.
 */
abstract public class Game {
    protected double bet;
    protected Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    abstract public void startGame();

    abstract public void checkForWin();

    public double getBet(){
        return bet;
    }

    public void setBet(double bet) {
        if (player.getBalance() == 0) {
            IO.displayGTFOScreen();
        } else if (player.getBalance() < bet) {
            this.bet = player.getBalance();
            player.setBalance(0);
        }else {
            this.bet = bet;
            player.setBalance(player.getBalance() - getBet());

        }
    }

}
