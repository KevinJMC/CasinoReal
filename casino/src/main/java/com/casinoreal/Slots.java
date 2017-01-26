package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/24/17.
 */
public class Slots {

    final private char[] WHEEL_OPTIONS =
            {'\u2618', '\u2618', '\u2618', '\u2618', '\u2618', '\u2618', '\u2618', '\u2618', '\u2618',
                    '\u277C', '\u277C', '\u277C', '\u277C', '\u277C', '\u277C', '\u277C', '\u277C',
                    '\u262F', '\u262F', '\u262F', '\u262F', '\u262F', '\u262F', '\u262F',
                    '\u2725', '\u2725', '\u2725', '\u2725', '\u2725', '\u2725',
                    '\u29D7', '\u29D7', '\u29D7', '\u29D7', '\u29D7',
                    '\u263B', '\u263B', '\u263B', '\u263B',
                    '\u2726', '\u2726', '\u2726',
                    '\u27C1', '\u27C1',
                    '\u2A37'};

    public char[][] gameWheel;

    //public char[] gameWheelTop; = new char[3];
    //public char[] gameWheelBottom; = new char [3];

    public Slots() {
        gameWheel = new char[3][3];
        for (int i = 0; i < gameWheel.length; i++) {
            for (int j = 0; j < gameWheel.length; j++){
                gameWheel[i][j] = WHEEL_OPTIONS[(int) (Math.random() * 45)];
            }
        }
    }

    public double checkWinMiddle(){
        return checkLine(gameWheel[1]);
    }

    public double checkWinTop(){
        return checkLine(gameWheel[0]);
    }

    public double checkWinBottom(){
        return checkLine(gameWheel[2]);
    }

    public double checkWinDiagonalLeft(){
        char[] line = {gameWheel[2][0], gameWheel[1][1], gameWheel[0][2]};
        return checkLine(line);
    }

    public double checkWinDiagonalRight(){
        char[] line = {gameWheel[0][0], gameWheel[1][1], gameWheel[2][2]};
        return checkLine(line);
    }

    private double checkLine(char[] line){
        if (line[0] == line[1] && line[1] == line[2]){
            return setMultiplier(line[0]);
        }
        if (line[0] == '\u2618' || line[1] == '\u2618' || line[2] == '\u2618'){
            if (line[0] == '\u2618' && line[1] == '\u2618' || line[2] == '\u2618' && line[1] == '\u2618' || line[0] == '\u2618' && line[2] == '\u2618') {
                return 3;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    private double setMultiplier(char winner){
        switch (winner){
            case '\u2A37': return 400;
            case '\u27C1': return 200;
            case '\u2726': return 100;
            case '\u263B': return 50;
            case '\u29D7': return 25;
            case '\u2725': return 20;
            case '\u262F': return 15;
            case '\u277C': return 10;
            case '\u2618': return 5;
        }
        return 0;
    }


    /*public void printWheel(){
        for(char[] x: gameWheel){
            System.out.println();
            for( char y: x){
                System.out.print(y);
            }
        }
    }

    public void printWheelOptions() {
        for(char x: WHEEL_OPTIONS){
            System.out.print(x);
        }
    }



    public static void main(String [] args){
        Slots slotGame = new Slots();
        //slotGame.printWheelOptions();
        slotGame.printWheel();
        System.out.println();
        System.out.println(slotGame.checkWinMiddle());
    }*/
}
