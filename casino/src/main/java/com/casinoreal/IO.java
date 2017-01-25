package com.casinoreal;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    public static double getInputAdditionalBalance() {
        double d = scanner.nextDouble();

        if ( d > 0.00 ) {
            return d;
        }
        else {
            return 0.00;
        }
    }

    public static double checkFileForUserName(String userName) {
        // Gonna need converted to relative path but having trouble
        String usersFileName = "/Users/johnsquier/dev/labs/CasinoReal/casino/src/main/java/com/casinoreal/userNames.txt";
        Path path = Paths.get(usersFileName);
        List<String> userNamesAndBalances = null;

        try {
            userNamesAndBalances = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("FILE ERROR");
        }

        if (userNamesAndBalances != null) {

            for (String s : userNamesAndBalances) {
                // split a csv line
                String[] nameAndBalance = s.split(",");

                if (nameAndBalance[0].equalsIgnoreCase(userName)) {
                    return Double.parseDouble(nameAndBalance[1]);
                }
            }
        }

        // default balance if userName isn't in file
        return 0.0;

    }



    public static void displayIntroScreen() {
        displayLineOfStars();

        displayBlankPipeLine();

        displayLineWithMessage("WELCOME TO THE CASINO REAL!");

/*        displayPipe();
        displaySpaces(37);
        displayString("WELCOME TO THE CASINO REAL");
        displaySpaces(36);
        displayPipe();
        newline();*/

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

    public static void displayBalanceScreen(String userName, double userBalance) {
        displayLineOfStars();
        displayBlankPipeLine();

        int numSpacesForPadding = (99 - (12 + userName.length())) / 2;

        displayPipe();
        displaySpaces(numSpacesForPadding);
        displayString("WELCOME BACK " + userName);

        // handle odd length names
        if ( userName.length() % 2 != 0) {
            numSpacesForPadding--;
        }

        displaySpaces(numSpacesForPadding);
        displayPipe();
        newline();

        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        numSpacesForPadding = (99 - (17 + Double.toString(userBalance).length())) / 2;

        displayPipe();
        displaySpaces(numSpacesForPadding);
        displayString("YOUR BALANCE IS: ");
        displayDollarAmount(userBalance);

        // handle even length dollar amts
        if ( Double.toString(userBalance).length() % 2 == 0 ) {
            numSpacesForPadding--;
        }

        displaySpaces(numSpacesForPadding);
        displayPipe();
        newline();

        numSpacesForPadding = ((99 - 29) / 2);
        displayPipe();
        displaySpaces(numSpacesForPadding);
        displayString("CARE TO ADD TO YOUR BALANCE? ");
        displaySpaces(numSpacesForPadding);
        displayPipe();
        newline();

        numSpacesForPadding = ((99 - "ENTER A NUMBER:".length()) / 2);
        displayPipe();
        displaySpaces(numSpacesForPadding);
        displayString("ENTER A NUMBER: ");
        displaySpaces(numSpacesForPadding - 1);
        displayPipe();
        newline();

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayGameSelectScreen(String userName, double userBalance) {

        displayLineOfStars();
        displayBlankPipeLine();

        // <name> you have <balance>
        //  SELECT A GAME
        displayPipe()


        // Please select a game so and so
        //  Balance:
        // 1. blackjack
        // 2. poker
        // 3. slots
        // 4. casino war
    }

    public int getGameSelection() {
        return 1;
    }

    private static void displayLineWithMessage(String message) {
        int numSpacesForPadding = (99 - message.length()) / 2;

        displayPipe();
        displaySpaces(numSpacesForPadding);
        displayString(message);
        displaySpaces(numSpacesForPadding);
        displayPipe();
        newline();
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
        System.out.printf(">>>> ");
    }

    private static void displaySpaces(int n) {
        for ( int i = 0 ; i < n; i++ ) {
            System.out.printf(" ");
        }
    }

    private static void displayString(String s) {
        System.out.printf("%s", s);
    }

    private static void displayDollarAmount(double d) {
        System.out.printf("%.2f", d);
    }


    public static void displayReUpMessage(String message) {

    }

    public static void main(String[] args) {
        displayIntroScreen();

        String userName = getInputName();

        // after getting name set user name of player object

        double userBalance = checkFileForUserName(userName);

        // display balance screen w get balance at bottom
        // these args are gonna be a from player object
        displayBalanceScreen(userName, userBalance);

        userBalance += getInputAdditionalBalance();

        // display game select screen w games and balance and user name at top
        displayGameSelectScreen(userName, userBalance);

        int gameSelected = 1;

        // pass display off to game loop for that game
    }
}
