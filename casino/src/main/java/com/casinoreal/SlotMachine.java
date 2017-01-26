package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/25/17.
 */
public class SlotMachine extends Game{

    private Slots slots;
    private double playerWager = 0.0;

    public void setPlayerWager(double playerWager) {
        this.playerWager = playerWager;
    }

    public double getPlayerWager() {
        return playerWager;
    }

    public double pull(){
        slots = new Slots();
        double winnings = 0;
        if (/*getBet()*/ playerWager >= 1) {
            winnings = (playerWager * slots.checkWinMiddle());
        }
        if (playerWager >= 2){
            winnings += (playerWager * slots.checkWinDiagonalLeft());
            winnings += (playerWager * slots.checkWinDiagonalRight());
        }
        if (playerWager >= 3){
            winnings += (playerWager * slots.checkWinTop());
            winnings += (playerWager * slots.checkWinBottom());
        }
        return winnings;
    }

    // gonna edit this to comply with Game
    public boolean checkForWin(){
        return (pull() > 0);
    }

    public void startGame(){

        IO.displaySlotsWelcomeScreen();

        do {
            //playerWager = IO.getWager();
            setBet(IO.getInputSlotsBet());

            //player.setBalance(player.getBalance() -  playerWager);
            player.setBalance(player.getBalance() - getBet());

            double winnings = pull();

            if(winnings > 0){
                player.setBalance(player.getBalance() + winnings);
            }

            IO.displaySlotsWheelHasSpunScreen(slots.gameWheel, winnings > 0, winnings);

        } while (IO.getInputSlotsPlayAgain());
    }
}
