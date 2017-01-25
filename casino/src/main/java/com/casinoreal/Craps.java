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

    void checkBetPassLine(int roll) {
        if(checkSeven(roll) || roll == 11) {
            player.setBalance(player.getBalance() + getBet * 2);
//            IO.displayYouWin();
            playing = false;

        }
        if(roll <= 3 || roll == 12) {
            wager = 0;
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
            wager = 0;
//            IO.displayYouLose();
            playing = false;
        }
        if(roll <= 3 || roll == 12) {
            player.setBalance(player.getBalance()+ wager * 2);
//            IO.displayYouWin();
            playing = false;
        }
        else
            this.comeOutRoll = roll;
    }

    void checkBetPassOdds (int comeOutRoll, int pointRoll) {
        if (comeOutRoll == 4 || comeOutRoll == 10) {
            if(checkSeven(pointRoll)){
                wager = 0;
//                IO.displayYouLose();
                playing = false;
            }
            else if (pointRoll == comeOutRoll) {
                player.setBalance(player.getBalance() + wager + wager * 2);
//                IO.displayYouWin();
                playing = false;
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());

        }
        if (comeOutRoll == 5 || comeOutRoll == 9) {
            if (checkSeven(pointRoll)) {
                wager = 0;
//                IO.displayYouLose();
                playing = false;
            } else if (pointRoll == comeOutRoll) {
                player.setBalance(player.getBalance() + wager + wager * (3/2));
//                IO.displayYouWin();
                playing = false;
                //pay 3 to 2
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());
        }
        if (comeOutRoll == 6 || comeOutRoll == 8) {
            if (checkSeven(pointRoll)) {
                wager = 0;
//                IO.displayYouLose();
                playing = false;
            } else if (pointRoll == comeOutRoll) {
                player.setBalance(player.getBalance() + wager + wager * (6/5));
//                IO.displayYouWin();
                playing = false;
                //pay 6 to 5
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());
        }
    }

    double checkBetDontPassOdds (int comeOutRoll, int pointRoll) {
        if (comeOutRoll == 4 || comeOutRoll == 10) {
            if(checkSeven(pointRoll)) {
                return wager + wager * (1/2);
            }
            else if(comeOutRoll == pointRoll) {
                wager = 0;
                return 0;
            }
            else
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());
        }
        if (comeOutRoll == 5 || comeOutRoll == 9) {
            if(checkSeven(pointRoll)) {
                return wager + wager * (2/3)
                //pay 2 to 3
            }
            else if(comeOutRoll == pointRoll) {
                wager = 0;
                return 0;
            }
            else
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());

        }
        if (comeOutRoll == 6 || comeOutRoll == 8) {
            if(checkSeven(pointRoll)) {
                return wager + wager * (5/6);
            }
            else if (comeOutRoll == pointRoll) {
                wager = 0;
                return 0
            }
            else
                checkBetDontPassOdds(comeOutRoll, getDiceRoll());
                //pay 5 to 6
        }
        return -1;
    }

    public Player start() {

        while(playing && player.getBalance()>0) {
            //BetPass or BetDon't Pass
            //Wager amount?
            if (//BetPass)
                checkBetPassLine(getDiceRoll());
        }
        return player;
}



}
