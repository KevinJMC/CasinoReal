package com.casinoreal;

import java.util.Scanner;

/**
 * Created by johnsquier on 1/24/17.
 */
public abstract class IO {

    private static Scanner scanner = new Scanner(System.in);

    public static String getInputName() {
        String s = scanner.next();
        return s;
    }

    public static void displayIntroScreen() {
        displayLineOfStars();

        displayBlankPipeLine();

        displayPipe();
        displaySpaces(36);
        displayString("WELCOME TO THE CASINO ROYAL");
        displaySpaces(36);
        displayPipe();
        newline();

        displayBlankPipeLine();

        displayLineOfStars();

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayPipe();
        displaySpaces(42);
        displayString("ENTER YOUR NAME");
        displaySpaces(42);
        displayPipe();
        newline();

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();

        displayPipe();
        displayPrompt();

    }

    private static void displayLineOfStars() {
        for ( int i = 0; i < 101; i++ ) {
            System.out.printf("*");
        }
        newline();
    }

    private static void newline() {
        System.out.printf("\n");
    }

    private static void displayBlankPipeLine() {
        displayPipe();
        displaySpaces(99);
        displayPipe();
        newline();
    }

    private static void displayPipe() {
        System.out.printf("|");
    }

    private static void displayPrompt() {
        System.out.printf(">>>>>>> ");
    }

    public static void displaySpaces(int n) {
        for ( int i = 0 ; i < n; i++ ) {
            System.out.printf(" ");
        }
    }

    private static void displayString(String s) {
        System.out.printf("%s", s);
    }

    public static void main(String[] args) {
        displayIntroScreen();
        getInputName();
        // after getting name set user name

        // display balance screen w get balance at bottom

        // display game select screen w games

        // pass display off to game loop for that game
    }
}
