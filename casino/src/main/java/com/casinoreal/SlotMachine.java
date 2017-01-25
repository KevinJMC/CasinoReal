package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/25/17.
 */
public class SlotMachine extends Game{

    public Slots slots;

    public void setBet(double bet){
        if (bet == 1){
            setBet(1);
        }
        if (bet == 2){
            setBet(2);
        }
        if (bet == 3){
            setBet(3);
        }
    }

    public double pull(){
        slots = new Slots();
        double winnings = 0;
        if (getBet() >= 1) {
            winnings = (getBet() * slots.checkWinMiddle());
        }
        if (getBet() >= 2){
            winnings += (getBet() * slots.checkWinDiagonalLeft());
            winnings += (getBet() * slots.checkWinDiagonalRight());
        }
        if (getBet() >= 3){
            winnings += (getBet() * slots.checkWinTop());
            winnings += (getBet() * slots.checkWinBottom());
        }
        return winnings;
    }

    public void startGame(){}

    public void checkForWin(){}
}
