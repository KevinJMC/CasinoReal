package com.casinoreal;

/**
 * @author Created by kevinmccann on 1/24/17.
 */
public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        BlackJackEngine run = new BlackJackEngine(player);
        player.updateBalance(1000.0);
        run.runRound();
    }
}

