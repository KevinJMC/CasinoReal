

package com.casinoreal;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by kevinmccann on 1/25/17.
 */

public class Craps extends Game {

    Player player;
    int comeOutRoll;
    boolean playing = true;
    boolean betPass;
    double sideWager;


    /**
     * Constructor is passed a player to maintain consistent balance
     * @param player
     */
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

    @Override
    void setBet(double bet) {
        if (player.getBalance() == 0) {
            IO.displayGTFOScreen();
        } else if (player.getBalance() < bet) {
            this.bet = player.getBalance();
            player.setBalance(0);
        } else {
            this.bet = bet;
            player.setBalance(player.getBalance() - getBet());
        }
    }

    void setSideWager(double bet) {
        if (player.getBalance() == 0) {
            IO.displayGTFOScreen();
        } else if (player.getBalance() < bet) {
            this.sideWager = player.getBalance();
            player.setBalance(0);
        }else {
            this.sideWager = bet;
            player.setBalance(player.getBalance() - sideWager);

        }
    }

    public double getSideWager() {
        return sideWager;
    }

    void winPass(int roll) {
        IO.displayYouWinScreen("You Win! Shooter rolled a " + roll);
        IO.waitForEnter();
        applyOddsToBalance(2.0, getBet());
        playing = false;
    }


    void checkBetPassLine(int roll) {
        if (checkSeven(roll) || roll == 11) {
            winPass(roll);
        }
        if (roll <= 3 || roll == 12) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose! Shooter rolled a " + roll);
            IO.waitForEnter();
            playing = false;
        } else
            this.comeOutRoll = roll;
    }

    boolean checkSeven(int roll) {
        return roll == 7;
    }

    private void applyOddsToBalance(double odds, double betOrSideWager) {
        player.setBalance(player.getBalance() + betOrSideWager * odds);
    }

    private void checkBetDontPass(int roll) {
        if (checkSeven(roll) || roll == 11) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose! Shooter rolled a " + roll);
            IO.waitForEnter();
            playing = false;
        }
        else if (roll <= 3 || roll == 12) {
            applyOddsToBalance(2 , getBet());
            IO.displayYouWinScreen("You Win! Shooter rolled a " + roll);
            IO.waitForEnter();
            playing = false;
        }
        else {
            this.comeOutRoll = roll;
        }
    }

    private void checkNoBetPassOdds(int comeOutRoll, int pointRoll) {
        if (!betPass) {
            if (checkSeven(pointRoll)) {
                IO.displayYouLoseScreen("You Lose! Shooter rolled a " + pointRoll);
                IO.waitForEnter();
                playing = false;
            } else if (comeOutRoll == pointRoll) {
                winPass(pointRoll);
            } else
                checkNoBetPassOdds(comeOutRoll, getDiceRoll());
        } else {
            if (checkSeven(pointRoll)) {
                winPass(pointRoll);
            } else if (comeOutRoll == pointRoll) {
                IO.displayYouLoseScreen("You Lose! Shooter rolled a " + pointRoll);
                IO.waitForEnter();
                setBet(0);
                playing = false;
            } else
                checkNoBetPassOdds(comeOutRoll, getDiceRoll());
        }
    }


    void checkBetPassOdds(int comeOutRoll, int pointRoll) {
        if(comeOutRoll == pointRoll) {
            if (comeOutRoll == 4 || comeOutRoll == 10)
                applyOddsToBalance(2.0, getSideWager());
            if (comeOutRoll == 5 || comeOutRoll == 9)
                applyOddsToBalance(3/2, getSideWager());
            if (comeOutRoll == 6 || comeOutRoll == 8)
                applyOddsToBalance(6/5, getSideWager());
            IO.displayYouWinScreen("You Win! Shooter rolled a " + pointRoll);
            IO.waitForEnter();
        }
        else if (checkSeven(pointRoll)) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose! Shooter rolled a " + pointRoll);
            IO.waitForEnter();
        }
        else
            checkBetPassOdds(comeOutRoll, getDiceRoll());
        IO.waitForEnter();
        checkNoBetPassOdds(comeOutRoll, pointRoll);
    }

    void checkBetDontPassOdds(int comeOutRoll, int pointRoll) {
        if(checkSeven(pointRoll)) {
            if (comeOutRoll == 4 || comeOutRoll == 10)
                applyOddsToBalance(1/2, getSideWager());
            if (comeOutRoll == 5 || comeOutRoll == 9)
                applyOddsToBalance(2/3, getSideWager());
            if (comeOutRoll == 6 || comeOutRoll == 8)
                applyOddsToBalance(5/6, getSideWager());
            IO.displayYouWinScreen("You Win! Shooter rolled a " + pointRoll);
            IO.waitForEnter();
        }
        else if (comeOutRoll == pointRoll) {
            setBet(0);
            IO.displayYouLoseScreen("You Lose! Shooter rolled a " + pointRoll);
            IO.waitForEnter();
        }
        else
            checkBetDontPassOdds(comeOutRoll, getDiceRoll());
        IO.waitForEnter();
        checkNoBetPassOdds(comeOutRoll, pointRoll);
    }

    /**
     * startGame, like in all other games, runs the persistant loop during which the game is played.
     * When it is exited, it returns the player to the casino 'floor' to choose another game.
     */

    public void startGame() throws NullPointerException {
        boolean notExit = true;
            while (notExit) {
                do {
                    IO.displayGenericHeaderAndMessageScreen("How would you like to bet?", new String[]{"Pass line", " ", "Don't Pass Bar", "", "enter pass for pass line, anything else for don't pass"});
                    betPass = IO.getCrapsHasPlayerBetOnPass();
                    IO.displayGenericHeaderAndMessageScreen("How much would you like to wager?", "Input an amount to wager");
                    setBet(IO.getWager());
                    if (betPass)
                        checkBetPassLine(getDiceRoll());
                    else
                        checkBetDontPass(getDiceRoll());
                    if(playing) {
                        IO.displayGenericHeaderAndMessageScreen("Would you like to bet on the odds?", new String[] {"The come out roll was " + comeOutRoll,
                                "", "To bet on the odds", "", "Enter \'pass odds\' or \'don\'t pass odds\'", "", "or neither"});
                        CrapsPassOddsBet cpob = IO.getCrapsBetOnPassOdds();
                        if (cpob != CrapsPassOddsBet.NEITHER) {
                            IO.displayGenericHeaderAndMessageScreen("How much would you like to wager?", "Input an amount to wager");
                            setSideWager(IO.getWager());
                        }
                        if (cpob == CrapsPassOddsBet.PASS_ODDS)
                            checkBetPassOdds(comeOutRoll, getDiceRoll());
                        else if (cpob == CrapsPassOddsBet.DONT_PASS_ODDS)
                            checkBetDontPassOdds(comeOutRoll, getDiceRoll());
                        else
                            checkNoBetPassOdds(comeOutRoll, getDiceRoll());
                    }
                } while (playing && player.getBalance() > 0);
                if(player.getBalance() != 0) {
                    IO.displayGenericHeaderAndMessageScreen("", new String[]{"Play again? Feelin\' lucky?", " ", "New Balance: " + player.getBalance()});
                    notExit = IO.getInputCrapsPlayAgain();
                }
                else {notExit = false;}
            }
        }
    }


