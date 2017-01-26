

package com.casinoreal;

import java.util.*;


/**
 * Created by kevinmccann on 1/25/17.
 */

public class Craps extends Game {

    Player player;
    int comeOutRoll;
    int pointRoll;
    boolean playing = true;
    boolean betPass;

    public Craps(Player player) {
        this.player = player;
    }

    private int getDiceRoll() {
        return ((int) Math.ceil(Math.random() * 6)) + ((int) Math.ceil(Math.random() * 6));
    }

    @Override
    public boolean checkForWin() {
        //Check for Win?
        return true;
    }

    public void quitGame() {
    }

    ;

    void winPass() {
        IO.displayYouWinScreen("You Win!");
        applyOddsToBalance(2.0);
        playing = false;
    }


    void checkBetPassLine(int roll) {
        if (checkSeven(roll) || roll == 11) {
            winPass();
        }
        if (roll <= 3 || roll == 12) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose! Shooter rolled a " + roll);
            playing = false;
        } else
            this.comeOutRoll = roll;
    }

    boolean checkSeven(int roll) {
        return roll == 7;
    }

    void applyOddsToBalance(double odds) {
        player.setBalance(player.getBalance() + getBet() * odds);
    }

    void checkBetDontPass(int roll) {
        if (checkSeven(roll) || roll == 11) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose! Shooter rolled a " + roll);
            playing = false;
        }
        if (roll <= 3 || roll == 12) {
            applyOddsToBalance(2);
            IO.displayYouWinScreen("You Win! Shooter rolled a " + roll);
            playing = false;
        } else
            this.comeOutRoll = roll;
    }

    void checkNoBetPassOdds(int comeOutRoll, int pointRoll) {
        if (betPass) {
            if (checkSeven(pointRoll)) {
                IO.displayYouLoseScreen("You Lose! Shooter rolled a " + pointRoll);
                playing = false;
            } else if (comeOutRoll == pointRoll) {
                winPass();
            } else
                checkNoBetPassOdds(comeOutRoll, getDiceRoll());
        } else {
            if (checkSeven(pointRoll)) {
                winPass();
            } else if (comeOutRoll == pointRoll) {
                IO.displayYouLoseScreen("You Lose! Shooter rolled a " + pointRoll);
                setBet(0);
                playing = false;
            } else
                checkNoBetPassOdds(comeOutRoll, getDiceRoll());
        }
    }


    void checkBetPassOdds(int comeOutRoll, int pointRoll) {
        if(comeOutRoll == pointRoll) {
            if (comeOutRoll == 4 || comeOutRoll == 10)
                applyOddsToBalance(2.0);
            if (comeOutRoll == 5 || comeOutRoll == 9)
                applyOddsToBalance(3/2);
            if (comeOutRoll == 6 || comeOutRoll == 8)
                applyOddsToBalance(6/5);
            IO.displayYouWinScreen("You Win!");
        }
        else if (checkSeven(pointRoll)) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose!");
        }
        else
            checkBetPassOdds(comeOutRoll, getDiceRoll());
        checkNoBetPassOdds(comeOutRoll, pointRoll);
    }

    void checkBetDontPassOdds(int comeOutRoll, int pointRoll) {
        if(checkSeven(pointRoll)) {
            if (comeOutRoll == 4 || comeOutRoll == 10)
                applyOddsToBalance(1/2);
            if (comeOutRoll == 5 || comeOutRoll == 9)
                applyOddsToBalance(2/3);
            if (comeOutRoll == 6 || comeOutRoll == 8)
                applyOddsToBalance(5/6);
            IO.displayYouWinScreen("You Win!");
        }
        else if (comeOutRoll == pointRoll) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose!");
        }
        else
            checkBetDontPassOdds(comeOutRoll, getDiceRoll());
        checkNoBetPassOdds(comeOutRoll, pointRoll);
    }

    public void startGame() {
        boolean notExit = true;
        while (notExit) {
            do {
                betPass = IO.getCrapsHasPlayerBetOnPass();
                setBet(IO.getWager());
                if (betPass)
                    checkBetPassLine(getDiceRoll());
                else
                    checkBetDontPass(getDiceRoll());
                CrapsPassOddsBet cpob = IO.getCrapsBetOnPassOdds();
                if (cpob == CrapsPassOddsBet.PASS_ODDS)
                    checkBetPassOdds(comeOutRoll, getDiceRoll());
                else if (cpob == CrapsPassOddsBet.DONT_PASS_ODDS)
                    checkBetDontPassOdds(comeOutRoll, getDiceRoll());
                else
                    checkNoBetPassOdds(comeOutRoll, getDiceRoll());
            } while (playing && player.getBalance() > 0);
            notExit = IO.getInputCrapsPlayAgain();
        }
    }


    private static Player p = new Player();

    public static void main(String args[]) {
        p.setBalance(100);
        Craps craps = new Craps(p);

        craps.startGame();
    }
}

