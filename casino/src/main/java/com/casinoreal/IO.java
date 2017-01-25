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

    public static int getInputSelectedGame() {
        int i;
        do {
            i = scanner.nextInt();
        } while( i > 4 || i < 1 );

        return i;
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

        displayBlankPipeLine();

        displayLineOfStars();

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("ENTER YOUR NAME");

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

        displayLineWithMessage("WELCOME BACK ", userName);

        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("YOUR BALANCE IS: ", userBalance);


        displayLineWithMessage("CARE TO ADD TO YOUR BALANCE?");

        displayLineWithMessage("ENTER A NUMBER");

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

        String toSend = userName + " you have ";
        displayLineWithMessage(toSend, userBalance);

        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 2; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("SELECT A GAME");
        displayBlankPipeLine();
        displayLineWithMessage("1. SLOTS");
        displayLineWithMessage("2. BLACK JACK");
        displayLineWithMessage("3. CASINO WAR");
        displayLineWithMessage("4. TEXAS HOLD'EM");

        displayBlankPipeLine();

        displayLineOfStars();

        displayPipe();
        displayPrompt();
    }

    public int getGameSelection() {
        return 1;
    }

    private static void displayLineWithMessage(String message) {
        int numSpacesForPadding = (99 - message.length()) / 2;

        displayPipe();
        displaySpaces(numSpacesForPadding);
        displayString(message);

        if ( message.length() % 2 == 0 ) {
            numSpacesForPadding++;
        }

        displaySpaces(numSpacesForPadding);
        displayPipe();
        newline();
    }

    private static void displayLineWithMessage(String message, String variableLengthMessage) {

        int numSpacesForPadding = (99 - (message.length() + variableLengthMessage.length())) / 2;

        displayPipe();
        displaySpaces(numSpacesForPadding);
        displayString(message + variableLengthMessage);

        // handle odd length inputs
        if ( variableLengthMessage.length() % 2 != 0) {
            numSpacesForPadding++;
        }

        displaySpaces(numSpacesForPadding);
        displayPipe();
        newline();
    }

   private static void displayLineWithMessage(String message, double dollarAmount) {

       int numSpacesForPadding = (99 - (message.length() + Double.toString(dollarAmount).length() + 1)) / 2;

       //System.out.println("m:" + message.length() + " " + "d:" + (Double.toString(dollarAmount).length() + 1));
       displayPipe();
       displaySpaces(numSpacesForPadding);
       displayString(message);
       displayDollarAmount(dollarAmount);

       // odd odd
      if ( (message.length() % 2 != 0) && (((Double.toString(dollarAmount).length() + 1) % 2) != 0) ) {
          //System.out.println("odd odd");
          numSpacesForPadding++;
      } // odd even
       else if ( (message.length() % 2 != 0) && (((Double.toString(dollarAmount).length() + 1) % 2) == 0) ) {
          //System.out.println("odd even");
          //numSpacesForPadding++;
       } // even odd
       else if ( (message.length() % 2 == 0) && (((Double.toString(dollarAmount).length() + 1) % 2) != 0) ){
          //System.out.println("even odd");
          //numSpacesForPadding--;
      } // even even
      else {
          //System.out.println("even even");
          numSpacesForPadding++;
      }

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

        int gameSelected = getInputSelectedGame();

        // pass display off to game loop for that game
    }
}
