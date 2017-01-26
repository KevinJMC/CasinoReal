/*
package com.casinoreal;

import java.util.*;


/**
 * Created by kevinmccann on 1/25/17.
 */

public class Craps extends Game{

    Player player;
    int comeOutRoll;
    int pointRoll;
    boolean playing = true;
    boolean betPass;

    public Craps(Player player) {
        this.player = player;
    }

    private int getDiceRoll(){
        return ((int) Math.floor(Math.random()*6)) + ((int) Math.floor(Math.random()*6));
    }

    void setComeOutRoll() {
        this.comeOutRoll = getDiceRoll();
    }

    void setPointRoll() {
        this.pointRoll = getDiceRoll();
    }

    @Override
    public void checkForWin() {
        //Check for Win?
    }

    void winPass() {
//        IO.displayYouWin();
        player.setBalance(player.getBalance() + getBet() * 2);
        playing = false;
    }



    void checkBetPassLine(int roll) {
        if(checkSeven(roll) || roll == 11) {
            winPass();
        }
        if(roll <= 3 || roll == 12) {
            setBet(0);
//            IO.displayYouLose();
            playing = false;
        }
        else
            this.comeOutRoll = roll;
    }

    boolean checkSeven(int roll) {
        return roll == 7;
    }

    void checkBetDontPass(int roll) {
        if(checkSeven(roll) || roll == 11) {
            setBet(0);
//            IO.displayYouLose();
            playing = false;
        }
        if(roll <= 3 || roll == 12) {
            player.setBalance(player.getBalance()+ getBet() * 2);
//            IO.displayYouWin();
            playing = false;
        }
        else
            this.comeOutRoll = roll;
    }

    void checkNoBetPassOdds (int comeOutRoll, int pointRoll) {
        if (betPass) {
            if (checkSeven(pointRoll)) {
//                IO.displayYouLose();
                playing = false;
            } else if (comeOutRoll == pointRoll) {
                winPass();
            } else
                checkNoBetPassOdds(comeOutRoll, getDiceRoll());
        } else {
            if (checkSeven(pointRoll)) {
                winPass();
            } else if (comeOutRoll == pointRoll) {
//                IO.displayYouLose();
                setBet(0);
                playing = false;
            } else
                checkNoBetPassOdds(comeOutRoll, getDiceRoll());
        }
    }


    void checkBetPassOdds (int comeOutRoll, int pointRoll) {
        if (comeOutRoll == 4 || comeOutRoll == 10) {
            if(checkSeven(pointRoll)){
                setBet(0);
//                IO.displayYouLose();
                playing = false;
            }
            else if (pointRoll == comeOutRoll) {
                player.setBalance(player.getBalance() + getBet() + getBet() * 2);
//                IO.displayYouWin();
                playing = false;
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());

        }
        if (comeOutRoll == 5 || comeOutRoll == 9) {
            if (checkSeven(pointRoll)) {
                setBet(0);
//                IO.displayYouLose();
                playing = false;
            } else if (pointRoll == comeOutRoll) {
                player.setBalance(player.getBalance() + getBet() + getBet() * (3/2));
//                IO.displayYouWin();
                playing = false;
                //pay 3 to 2
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());
        }
        if (comeOutRoll == 6 || comeOutRoll == 8) {
            if (checkSeven(pointRoll)) {
                 setBet(0);
//                IO.displayYouLose();
                playing = false;
            } else if (pointRoll == comeOutRoll) {
                player.setBalance(player.getBalance() + getBet() + getBet() * (6/5));
//                IO.displayYouWin();
                playing = false;
                //pay 6 to 5
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());
        }
    }

    void checkBetDontPassOdds (int comeOutRoll, int pointRoll) {
        if (comeOutRoll == 4 || comeOutRoll == 10) {
            if(checkSeven(pointRoll)) {
                player.setBalance(player.getBalance()+ getBet() + getBet() * (1/2));
            }
            else if(comeOutRoll == pointRoll) {
                setBet(0);
                playing = false;
            }
            else
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());
        }
        if (comeOutRoll == 5 || comeOutRoll == 9) {
            if(checkSeven(pointRoll)) {
                player.setBalance(player.getBalance()+ getBet() + getBet() * (2/3));
                playing = false;
                //pay 2 to 3
            }
            else if(comeOutRoll == pointRoll) {
                setBet(0);
                playing = false;
            }
            else
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());

        }
        if (comeOutRoll == 6 || comeOutRoll == 8) {
            if(checkSeven(pointRoll)) {
                player.setBalance(player.getBalance()+ getBet() + getBet() * (5/6));
                playing = false;
            }
            else if (comeOutRoll == pointRoll) {
                setBet(0);
                playing = false;
            }
            else
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());
                //pay 5 to 6
        }
    }

    public void startGame() {
        boolean notExit = true;
        while(notExit) {
        while(playing && player.getBalance()>0) {
            betPass = IO.getCrapsHasPlayerBetOnPass();
            setBet(IO.getWager());
            if (betPass)
                checkBetPassLine(getDiceRoll());
            else
                checkBetDontPass(getDiceRoll());
            CrapsPassOddsBet cpob = IO.getCrapsBetOnPassOdds();
            if(cpob == CrapsPassOddsBet.PASS_ODDS)
                checkBetPassOdds(comeOutRoll, getDiceRoll());
            else if (cpob == CrapsPassOddsBet.DONT_PASS_ODDS)
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());
            else
                checkNoBetPassOdds(comeOutRoll, getDiceRoll());
            }
//            notExit = IO.continuePlaying();
        }
    }
}

