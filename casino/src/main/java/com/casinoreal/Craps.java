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

    void checkBetPassLine(int comeOutRoll) {
        if(comeOutRoll == 7 || comeOutRoll == 11) {
            //win
        }
        if(comeOutRoll <= 3 || comeOutRoll == 12) {
            //lose
        }
        else
            this.comeOutRoll = comeOutRoll;
    }



}
