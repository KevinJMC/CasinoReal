package com.casinoreal;

import java.util.Scanner;

/**
 * Created by johnsquier on 1/24/17.
 */
public class IO {

    private Scanner scanner;

    public IO() {
        scanner = new Scanner(System.in);
    }

    public static void displayIntroScreen() {
        displayLineOfStars();
        newline();

        displayBlankPipeLine();

        displayPipe();
        displaySpaces(36);
        displayString("WELCOME TO THE CASINO ROYAL");
        displaySpaces(36);
        displayPipe();
        newline();

        displayBlankPipeLine();
        displayLineOfStars();

    }

    public static void displayLineOfStars() {
        for ( int i = 0; i < 101; i++ ) {
            System.out.printf("*");
        }
    }

    public static void newline() {
        System.out.printf("\n");
    }

    public static void displayBlankPipeLine() {
        displayPipe();
        displaySpaces(99);
        displayPipe();
        newline();
    }

    public static void displayPipe() {
        System.out.printf("|");
    }

    public static void displaySpaces(int n) {
        for ( int i = 0 ; i < n; i++ ) {
            System.out.printf(" ");
        }
    }

    public static void displayString(String s) {
        System.out.printf("%s", s);
    }

    public static void main(String[] args) {
        displayIntroScreen();
    }
}
