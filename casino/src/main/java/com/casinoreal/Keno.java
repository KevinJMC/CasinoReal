package com.casinoreal;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.stream.IntStream;

/**
 * @ author Created by alexandraarmstrong on 1/26/17.
 */
public class Keno extends Game{
    private int numberOfSpots;
    public int[] kenoTicket;
    public int[] kenoBallDraw = new int[20];
    double winnings;
    int matches = 0;

    /**
     * takes input from user to choose number of spots on Keno card
     */
    public void chooseNumberOfSpots(){
        IO.displayKenoPickNumSpotsScreen();
        numberOfSpots = IO.getKenoNumbBalls();
        kenoTicket = new int[numberOfSpots];
    }

    /**
     * takes input from user to choose spots on keno card
     */
    public void chooseSpots(){
        IO.displayKenoPickNumberScreen();
        for(int i = 0; i < numberOfSpots; i++){
            int input = IO.getKenoIntegerInput();
            for(int j = 0; j < kenoTicket[i]; i++){
                while (input == kenoTicket[j]){
                    input = IO.getKenoIntegerInput();
                }
            }
            kenoTicket[i] = input;
        }
    }

    /**
     * simulates keno ball pull from a finite pool
     */
    public void kenoBallDrawFill(){
        int[] kenoOptions = IntStream.range(1, 80).toArray();
        for (int i = 0; i < kenoBallDraw.length; i++){
            int temp = kenoOptions[(int) (Math.random() * 79)];
            if (temp != 0) {
                kenoBallDraw[i] = temp;
                kenoOptions[temp - 1] = 0;
            } else {
                while(temp == 0){
                    temp = kenoOptions[(int) (Math.random() * 79)];
                }
                kenoBallDraw[i] = temp;
                kenoOptions[temp - 1] = 0;
            }
        }
    }

    /**
     * checks keno ticket v. balls drawn for matches, and returns true if winnings above 0
     * @return boolean win condition
     */
    @Override
    public boolean checkForWin() {
        for(int i = 0; i < kenoTicket.length; i++){
            for(int j = 0; j < kenoBallDraw.length; j++){
                if (kenoTicket[i] == kenoBallDraw[j]){
                    matches++;
                }
            }
        }
        return (winnings > 0);
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    public double determineWinnings(){
        switch(numberOfSpots){
            case 4: return fourSpotsSwitch();
            case 5: return fiveSpotsSwitch();
            case 6: return sixSpotsSwitch();
            case 7: return sevenSpotsSwitch();
            case 8: return eightSpotsSwitch();
            case 9: return nineSpotsSwitch();
            case 10: return tenSpotsSwitch();
            default: return 0;
        }
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    private double fourSpotsSwitch(){
        switch(matches){
            case 1: return 0;
            case 2: return 1;
            case 3: return 5;
            case 4: return 50;
            default: return 0;
        }
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    private double fiveSpotsSwitch(){
        switch(matches){
            case 1: return 0;
            case 2: return 0;
            case 3: return 2;
            case 4: return 15;
            case 5: return 500;
            default: return 0;
        }
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    private double sixSpotsSwitch(){
        switch(matches){
            case 1: return 0;
            case 2: return 0;
            case 3: return 1;
            case 4: return 5;
            case 5: return 50;
            case 6: return 1500;
            default: return 0;
        }
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    private double sevenSpotsSwitch(){
        switch(matches){
            case 1: return 0;
            case 2: return 0;
            case 3: return 1;
            case 4: return 2;
            case 5: return 15;
            case 6: return 150;
            case 7: return 5000;
            default: return 0;
        }
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    private double eightSpotsSwitch(){
        switch(matches){
            case 1: return 0;
            case 2: return 0;
            case 3: return 0;
            case 4: return 2;
            case 5: return 10;
            case 6: return 50;
            case 7: return 400;
            case 8: return 15000;
            default: return 0;
        }
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    private double nineSpotsSwitch(){
        switch(matches){
            case 1: return 0;
            case 2: return 0;
            case 3: return 0;
            case 4: return 1;
            case 5: return 4;
            case 6: return 25;
            case 7: return 200;
            case 8: return 2500;
            case 9: return 25000;
            default: return 0;
        }
    }

    /**
     * breaks down complicated payout rules of keno
     * @return double winning multiplier
     */
    private double tenSpotsSwitch(){
        switch(matches){
            case 1: return 0;
            case 2: return 0;
            case 3: return 0;
            case 4: return 0;
            case 5: return 3;
            case 6: return 10;
            case 7: return 50;
            case 8: return 500;
            case 9: return 10000;
            case 10: return 200000;
            default: return 0;
        }
    }

    /**
     * method inherited from game
     * plays game in loop until player quits
     */
    @Override
    public void startGame() {
        boolean playing = true;
        while(playing) {
            IO.displayKenoWelcomeScreen();
            //IO.waitForEnter();
            System.out.println();
            IO.displayGenericHeaderAndMessageScreen("What will you wager?", "Enter wager below");
            setBet(IO.getWager());
            System.out.println();
            chooseNumberOfSpots();
            chooseSpots();
            kenoBallDrawFill();
            IO.displayKenoBallsPickedScreen(kenoTicket, kenoBallDraw);
            System.out.println();
            IO.waitForEnter();
            winnings = determineWinnings() * bet;
            player.setBalance(winnings + player.getBalance());

            if (checkForWin()) {
                IO.displayYouWinScreen("Congratulations you won " + winnings);
                player.setBalance(player.getBalance() + winnings);
            } else {
                IO.displayYouLoseScreen("Sorry, you lost, play again.");
            }

            playing = IO.getInputKenoPlayAgain();
        }

    }
}