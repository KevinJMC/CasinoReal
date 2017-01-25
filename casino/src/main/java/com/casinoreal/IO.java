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

    public static double getInputSlotsBet() {
        double d;

        do {
            d = scanner.nextDouble();
        } while ( d != 1 || d != 2 || d != 3 );

        return d;
    }

    public static boolean getInputSlotsPlayAgain() {
        scanner.nextLine();
        String playSlotsAgain = scanner.next();

        return playSlotsAgain.equalsIgnoreCase("y");

    }

    public static void waitForEnter() {
        scanner.nextLine();
    }


    public static boolean getCrapsHasPlayerBetOnPass() {
        scanner.nextLine();
        String passOrDontPass = scanner.next();

        return passOrDontPass.equalsIgnoreCase("pass");
    }

    public static double getWager() {
        double d;

        do {
            d = scanner.nextDouble();
        } while (d < 0.0);

        return d;
    }

    public static CrapsPassOddsBet getCrapsBetOnPassOdds() {
        scanner.nextLine();
        String passOdds = scanner.next();

        switch ( passOdds ) {
            case "don't pass odds": return CrapsPassOddsBet.DONT_PASS_ODDS;
            case "pass odds": return CrapsPassOddsBet.PASS_ODDS;
            case "neither" : return CrapsPassOddsBet.NEITHER;
            default: return CrapsPassOddsBet.NEITHER;
        }
    }

    public static boolean getInputWarPlayAgain() {
        getInputSlotsPlayAgain();
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

    public static void displaySlotsWelcomeScreen() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("WELCOME TO CASINO REAL SLOTS");
        displayBlankPipeLine();
        displayLineOfStars();

        for (int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("HOW MUCH DO YOU WISH TO BET?");
        displayLineWithMessage("$1  $2  $3");

        for (int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displaySlotsWheelHasSpunScreen(char[][] slotWheels, boolean hasPlayerWon, double payoutAmount) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("THE SLOTS HAVE SPUN");
        displayBlankPipeLine();
        displayLineOfStars();


        displayBlankPipeLine();

        // gotta display slot wheels here
        displayLineWithMessage("-------------");

        String toDisplay = "| " + slotWheels[0][0] + " | " + slotWheels[0][1] + " | " + slotWheels[0][2] + " |";
        displayLineWithMessage(toDisplay);

        displayLineWithMessage("-------------");

        toDisplay = (hasPlayerWon ? "CONGRATULATIONS!      " : "      SORRY!       ")
               + "| " + slotWheels[1][0] + " | " + slotWheels[1][1] + " | " + slotWheels[1][2] + " |"
                + (hasPlayerWon ? "    YOU'VE WON " + payoutAmount + "  ": "     YOU DIDN'T WIN");
        displayLineWithMessage(toDisplay);

        displayLineWithMessage("-------------");

        toDisplay = "| " + slotWheels[2][0] + " | " + slotWheels[2][1] + " | " + slotWheels[2][2] + " |";
        displayLineWithMessage(toDisplay);

        displayLineWithMessage("-------------");

        displayLineWithMessage("play again y/n?");
        displayLineOfStars();

        displayPipe();
        displayPrompt();
    }

    public static void displayWelcomeToWarScreen() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("WELCOME TO THE DEADLY GAME OF WAR");
        displayBlankPipeLine();
        displayLineOfStars();

        displayBlankPipeLine();
        displayBlankPipeLine();
        displayLineWithMessage("PLACE YOUR WAGER");
        displayLineWithMessage("AND");
        displayLineWithMessage("CROSS YOUR FINGERS FOR A HIGH CARD!");
        displayBlankPipeLine();
        displayBlankPipeLine();
        displayBlankPipeLine();
        displayBlankPipeLine();

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayWarHand(Card playerCard, Card dealerCard, boolean hasPlayerWon) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("THE BATTLE HAS BEEN FOUGHT");
        displayBlankPipeLine();
        displayLineOfStars();

        displayLineWithMessage("DEALER CARD");
        displayLineWithMessage("----");
        displayLineWithMessage("|" + dealerCard.toString() + "|");
        displayLineWithMessage("----");

        String toDisplay = hasPlayerWon ? "YOU HAVE BEEN MEASURED AND FOUND WANTING" :
                                            "YOU'VE WON THE BATTLE BUT YOU WON'T WIN THE WAR";
        displayLineWithMessage(toDisplay);

        displayLineWithMessage("----");
        displayLineWithMessage("|" + playerCard.toString() + "|");
        displayLineWithMessage("----");
        displayLineWithMessage("PLAYER CARD");
        displayLineOfStars();

        displayPipe();
        displayPrompt();
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

    // just for testing stuff in this class out, will be removed
    public static void main(String[] args) {
        /*displaySlotsWelcomeScreen();*/

        char[][] wheels = new char[3][3];
        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++ ) {
                wheels[i][j] = new Character('a');
            }
        }

        wheels[1][0] = 'b';
        wheels[2][0] = 'c';

        //displayIntroScreen();
        //displayBalanceScreen("JOHN", 100.0);
        //displayGameSelectScreen("JOHN", 1000.00);
        //displaySlotsWelcomeScreen();
        //displaySlotsWheelHasSpunScreen(wheels, true, 100.0);

        //displayWelcomeToWarScreen();

        Card playerCard = new Card(Suit.HEART, Rank.THREE);
        Card dealerCard = new Card(Suit.CLUB, Rank.JACK);
        boolean hasPlayerWon = true;
        //displayWarHand(playerCard, dealerCard, hasPlayerWon);

        displayWarHand(dealerCard, playerCard, false);

    }
}
