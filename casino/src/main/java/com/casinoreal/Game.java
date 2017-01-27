package com.casinoreal;

/**
 * @author Created by alexandraarmstrong on 1/24/17.
 * The parent class for all casino games.  Most getters and setters are standard, except in the case of setBet().
 * setBet() checks the balance to see if it's zero, if the player has enough to pay the bet, and withdraws the bet from the player's balance.
 */
abstract public class Game {
    protected double bet;
    protected Player player;

    /**
     * sets user in player field in game
     * @param player
     */
    public void setPlayer(Player player){
        this.player = player;
    }

    /**
     * returns player from field game
     * @return Player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * abstract method for starting and running game
     */
    abstract public void startGame();

    /**
     * abstract method for checking win or loss
     * @return boolean win condition
     */
    abstract public boolean checkForWin();

    /**
     * get value of players bet
     * @return double bet
     */
    public double getBet(){
        return bet;
    }

    /**
     * sets bet and removes bet from player balance
     * if balance not high enough bets remaining balance
     * if balance 0 kicks out
     * @param bet
     */
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
