package com.casinoreal;

/**
 * Created by alexandraarmstrong on 1/24/17.
 */
public class Slots {

    final char[] wheelOptions = {'\u2618', '\u2618', '\u2618', '\u2618', '\u2618', '\u2618','\u2618', '\u2618', '\u2618',
                                 '\u277C', '\u277C', '\u277C', '\u277C', '\u277C', '\u277C', '\u277C', '\u277C',
                                 '\u262F', '\u262F', '\u262F', '\u262F', '\u262F', '\u262F', '\u262F',
                                 '\u2725', '\u2725', '\u2725', '\u2725', '\u2725', '\u2725',
                                 '\u29D7', '\u29D7', '\u29D7', '\u29D7', '\u29D7',
                                 '\u263B', '\u263B', '\u263B', '\u263B',
                                 '\u277C', '\u277C', '\u277C',
                                 '\u2726', '\u2726',
                                 '\u27C1',
                                 '\u2A37'};
    char[][] gameWheel = new char[3][3];

    Slots() {
        for (int i = 0; i < gameWheel.length; i++) {
            for (int j = 0; j < gameWheel.length; j++) {
                gameWheel[i][j] = wheelOptions[(int) (Math.random() * 12)];
            }
        }
    }

    protected double checkWin(){
        return 0;
    }

    public void printWheel(){
        for(char[] x: gameWheel){
            System.out.println();
            for( char y: x){
                System.out.print(y);
            }
        }
    }

    public void printWheelOptions() {
        for(char x: wheelOptions){
            System.out.print(x);
        }
    }



    public static void main(String [] args){
        Slots slotGame = new Slots();
        slotGame.printWheelOptions();
        //slotGame.printWheel();
    }
}

