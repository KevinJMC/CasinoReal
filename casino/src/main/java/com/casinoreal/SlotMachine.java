package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/25/17.
 */
public class SlotMachine {
    //I/O I/O;
    //Player player;
    double bet;

    Slots slots;

    public void setBet(double bet){
        if (bet == 1){
            this.bet = 1;
        }
        if (bet == 2){
            this.bet = 2;
        }
        if (bet == 3){
            this.bet = 3;
        }
    }

    public double pull(){
        slots = new Slots();
        double winnings = 0;
        if (bet >= 1) {
            winnings = (bet * slots.checkWinMiddle());
        }
        if (bet >= 2){
            winnings += (bet * slots.checkWinDiagonalLeft());
            winnings += (bet * slots.checkWinDiagonalRight());
        }
        if (bet >= 3){
            winnings += (bet * slots.checkWinTop());
            winnings += (bet * slots.checkWinBottom());
        }
        return winnings;
    }


}
