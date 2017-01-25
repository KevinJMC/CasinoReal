package com.casinoreal;

/**
 * Created by kevinmccann on 1/25/17.
 */
public class Craps extends Game{

    Player player;
    int comeOutRoll;
    int pointRoll;
    boolean playing = true;

    public Craps(Player player) {

    }

    int getDiceRoll(){
        return (int) Math.round(Math.random()*12);
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

    void checkBetPassLine(int roll) {
        if(checkSeven(roll) || roll == 11) {
            player.setBalance(player.getBalance() + getBet() * 2);
//            IO.displayYouWin();
            playing = false;

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
                player.setBalance(player.getBalance()+ getBet() + getBet() * (1/2);
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

    public Player start() {

        while(playing && player.getBalance()>0) {
            //BetPass or BetDon't Pass
            boolean BetPass = IO.getCrapsHasPlayerBetOnPass();
            setBet(IO.getCrapsWager());
            if (BetPass)
                checkBetPassLine(getDiceRoll());
            else
                checkBetDontPass(getDiceRoll());
/*
boolean BetPassOdds = IO.getCrapsHasPlayerBetOnPassOdds();
boolean BetDontPassOdds = IO.getCrapsHasPlayerBetOnDontPassOdds();
*/
            if(BetPassOdds)
                checkBetPassOdds(comeOutRoll, getDiceRoll());
            else if (BetDontPassOdds)
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());
        }
        return player;
}



}
