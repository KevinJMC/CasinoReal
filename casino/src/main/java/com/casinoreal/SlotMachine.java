package com.casinoreal;

/**
 * @author Created by alexandraarmstrong on 1/25/17.
 */
public class SlotMachine extends Game {

    private Slots slots;

    /**
     * simulates pull of crank in slot machine
     * calculates winnings according to bet
     * @return double winnings calculated
     */
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

    /**
     * inherited from game
     * @return boolean win condition
     */
    public boolean checkForWin(){
        return (pull() > 0);
    }

    /**
     * operates methods in order and in loop until player quits
     */
    public void startGame(){


        do {
            IO.displaySlotsWelcomeScreen();

            setBet(IO.getInputSlotsBet());

            //player.setBalance(player.getBalance() - getBet());

            double winnings = pull();

            if(winnings > 0){
                player.setBalance(player.getBalance() + winnings);
            }

            IO.displaySlotsWheelHasSpunScreen(slots.gameWheel, winnings > 0, winnings);

        } while (IO.getInputSlotsPlayAgain());
    }
}