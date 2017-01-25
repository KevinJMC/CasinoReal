package com.casinoreal;

/**
 * Created by kevinmccann on 1/25/17.
 */
public class Craps {

    int comeOutRoll;
    int pointRoll;
    double wager;


    int getDiceRoll(){
        return (int) Math.round(Math.random()*12);
    }

    void setComeOutRoll() {
        this.comeOutRoll = getDiceRoll();
    }

    void setPointRoll() {
        this.pointRoll = getDiceRoll();
    }

    void setWager(double wager) {
        this.wager = wager;
    }

    double getWager() {
        return this.wager;
    }

    double checkBetPassLine(int roll) {
        if(roll == 7 || roll == 11) {
            return wager * 2;
        }
        if(roll <= 3 || roll == 12) {
            wager = 0;
            return 0;
        }
        else
            this.comeOutRoll = roll;
    }

    boolean checkSeven(int roll) {
        return roll == 7;
    }

    double checkBetDontPass(int roll) {
        if(roll == 7 || roll == 11) {
            wager = 0;
            return 0;
        }
        if(roll <= 3 || roll == 12) {
            return wager * 2;
        }
        else
            this.comeOutRoll = roll;
    }

    double checkBetPassOdds (int comeOutRoll, int pointRoll) {
        if (comeOutRoll == 4 || comeOutRoll == 10) {
            if(checkSeven(pointRoll)){
                wager = 0;
                return 0;
            }
            else if (pointRoll == comeOutRoll) {
                return wager + wager * 2;
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());

        }
        if (comeOutRoll == 5 || comeOutRoll == 9) {
            if (checkSeven(pointRoll)) {
                wager = 0;
                return 0;
            } else if (pointRoll == comeOutRoll) {
                return wager + wager * (3/2);
                //pay 3 to 2
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());
        }
        if (comeOutRoll == 6 || comeOutRoll == 8) {
            if (checkSeven(pointRoll)) {
                wager = 0;
                return 0;
            } else if (pointRoll == comeOutRoll) {
                return wager + wager * (6/5);
                //pay 6 to 5
            }
            else
                checkBetPassOdds(comeOutRoll, getDiceRoll());
        }
        return -1;
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

}

}
