package com.casinoreal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by johnsquier on 1/24/17.
 * Input/output class to handle getting inputs from the player and displaying the different result/decision screens.
 */
public abstract class IO {

    private static Player theUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void setUser(Player user) {
        theUser = user;
    }

    public static String getInputName() {
        String s = scanner.nextLine();
        return s;
    }

    public static String getBlackJackString() {
        scanner.nextLine();
        String s = scanner.next();
        return s;
    }

    public static double getInputAdditionalBalance() {
        double d = -1.0;

        try {
            d = scanner.nextDouble();
        }
        catch (Exception e) {
            do {
                displayInputErrorScreenBalanceSelection();
                scanner.nextLine();
                d = getInputAdditionalBalance();
            } while (d == -1.0);
            //waitForEnter();
        }

        if (d > 0.00) {
            return d;
        } else {
            return 0.00;
        }
    }

    public static int getInputSelectedGame() {
        int i;

        try {
            do {
                i = scanner.nextInt();
            } while (i > 8 || i < 1);
        }
        catch (Exception e) {
            displayInputErrorScreenGameSelection();
            i = 3; // default to slots
            scanner.nextLine();
            waitForEnter();
        }

        return i;
    }

    public static double getInputSlotsBet() {
        double d;

        do {
            d = scanner.nextDouble();
        } while (d != 1 && d != 2 && d != 3);

        return d;
    }

    public static boolean getInputSlotsPlayAgain() {
        String playSlotsAgain = scanner.next();

        boolean playAgain = playSlotsAgain.equalsIgnoreCase("y") ||
                            playSlotsAgain.equalsIgnoreCase("yes");

        return playAgain;

    }

    public static void waitForEnter() {
        scanner.nextLine();
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e) {scanner.nextLine();}

    }


    public static boolean getCrapsHasPlayerBetOnPass() {
        scanner.nextLine();
        String passOrDontPass = scanner.next();
        scanner.nextLine();

        return passOrDontPass.equalsIgnoreCase("pass");
    }

    public static int getIntegerInput() {
        int i;

        try {
            i = scanner.nextInt();
        }
        catch (Exception e) {
            // need to make a keno error screen
            //displayInputErrorScreenGameSelection();
            i = 1; // default to slots
            scanner.nextLine();
            waitForEnter();
        }

        return i;
    }

    public static double getWager() {
        double d;

        do {
            d = scanner.nextDouble();
        } while ( d < 0.0 );

        return d;
    }

    public static CrapsPassOddsBet getCrapsBetOnPassOdds() {
        scanner.nextLine();
        String passOdds = scanner.nextLine();

        switch (passOdds) {
            case "don't pass odds":
                return CrapsPassOddsBet.DONT_PASS_ODDS;
            case "pass odds":
                return CrapsPassOddsBet.PASS_ODDS;
            case "neither":
                return CrapsPassOddsBet.NEITHER;
            default:
                return CrapsPassOddsBet.NEITHER;
        }
    }

    public static boolean getInputWarPlayAgain() {
        return getInputSlotsPlayAgain();
    }

    public static boolean getInputCrapsPlayAgain() { return getInputSlotsPlayAgain(); }

    public static boolean getInputKenoPlayAgain() { return getInputSlotsPlayAgain(); }

    public static boolean getInputBlackJackPlayAgain() { return getInputSlotsPlayAgain(); }

    public static double checkFileForUserName(String userName) {
        // Gonna need converted to relative path but having trouble
        String usersFileName = "/Users/johnsquier/dev/labs/CasinoReal/casino/src/main/java/com/casinoreal/userNames.txt";
        Path path = Paths.get(usersFileName);
        List<String> userNamesAndBalances = null;

        try {
            userNamesAndBalances = Files.readAllLines(path);
        } catch (IOException e) {
            //System.out.println("FILE ERROR");
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

        for (int i = 0; i < 4; i++) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("ENTER YOUR NAME");

        for (int i = 0; i < 4; i++) {
            displayBlankPipeLine();
        }

        displayLineOfStars();

        displayPipe();
        displayPrompt();

    }

    public static void displayBalanceScreen(String userName, double userBalance) {
        displayLineOfStars();
        displayBlankPipeLine();

        displayLineWithMessage("WELCOME  ", userName);

        displayBlankPipeLine();
        displayLineOfStars();

        for (int i = 0; i < 3; i++) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("YOUR BALANCE IS: ", userBalance);


        displayLineWithMessage("CARE TO ADD TO YOUR BALANCE?");

        displayLineWithMessage("ENTER A NUMBER");

        for (int i = 0; i < 3; i++) {
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

        displayBlankPipeLine();

        displayLineWithMessage("SELECT A GAME");
        displayLineWithMessage("1. KENO");
        displayLineWithMessage("2. CRAPS");
        displayLineWithMessage("3. SLOTS");
        displayLineWithMessage("4. BLACK JACK");
        displayLineWithMessage("5. CASINO WAR");
        displayLineWithMessage("6. TEXAS HOLD'EM");

        displayLineWithMessage("7. GO TO THE CASINO BAR");

        displayLineWithMessage("8. QUIT THE CASINO (but why?)");

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

        for (int i = 0; i < 3; i++) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("HOW MUCH DO YOU WISH TO BET?");
        displayLineWithMessage("$1  $2  $3");
        displayLineWithMessage("YOU HAVE $" + (int)theUser.getBalance());

        for (int i = 0; i < 3; i++) {
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

        toDisplay = (hasPlayerWon ? "CONGRATULATIONS!     " : "      SORRY!       ")
                + "| " + slotWheels[1][0] + " | " + slotWheels[1][1] + " | " + slotWheels[1][2] + " |"
                + (hasPlayerWon ? "     YOU'VE WON $" + (int)payoutAmount + "  " : "     YOU DIDN'T WIN");
        displayLineWithMessage(toDisplay);

        displayLineWithMessage("-------------");

        toDisplay = "| " + slotWheels[2][0] + " | " + slotWheels[2][1] + " | " + slotWheels[2][2] + " |";
        displayLineWithMessage(toDisplay);

        displayLineWithMessage("-------------");

        displayLineWithMessage("you have $" + (int) theUser.getBalance() + " play again y/n?");
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
        displayLineWithMessage("you have $" + (int)theUser.getBalance());
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

        String toDisplay = hasPlayerWon ? "YOU'VE WON THE BATTLE BUT YOU WON'T WIN THE WAR" :
                "YOU HAVE BEEN MEASURED AND FOUND WANTING";
        displayLineWithMessage(toDisplay);

        displayLineWithMessage("----");
        displayLineWithMessage("|" + playerCard.toString() + "|");
        displayLineWithMessage("----");
        displayLineWithMessage("Balance: " + (int)theUser.getBalance() + " PLAYER CARD (play again? y/n)");
        displayLineOfStars();

        displayPipe();
        displayPrompt();
    }


    public static void displayBlackJackWelcomeScreen() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("WELCOME TO CASINO REAL BLACKJACK");
        displayBlankPipeLine();
        displayLineOfStars();

        displayBlankPipeLine();
        displayLineWithMessage("YOU HAVE " + (int)theUser.getBalance());
        displayBlankPipeLine();

        displayBlankPipeLine();
        displayLineWithMessage("PLACE YOUR WAGER");
        displayBlankPipeLine();

        displayBlankPipeLine();
        displayLineWithMessage("IF YOU DARE");
        displayBlankPipeLine();

        displayLineOfStars();

        displayPipe();
        displayPrompt();
    }

    public static void displayBlackJackHand(ArrayList<ArrayList<Card>> allCards, String message) {
        ArrayList<Card> dealerCards = allCards.get(1);
        ArrayList<Card> playerCards = allCards.get(0);

        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("BLACK JACK");
        displayBlankPipeLine();
        displayLineOfStars();

        // gotta build top string, middle string, and bottom string based on number of cards
        String dealerHandTopLine = "";
        String dealerHandMiddleLine = "";
        String dealerHandBottomLine = "";

        String playerHandTopLine = "";
        String playerHandMiddleLine = "";
        String playerHandBottomLine = "";

        for (int i = 0; i < dealerCards.size(); i++) {
            dealerHandTopLine += "----";
            dealerHandMiddleLine += "|" + (dealerCards.get(i).getFaceUp() ? dealerCards.get(i).toString() :
            "    ")+ "|";
            dealerHandBottomLine += "----";
        }

        for (int i = 0; i < playerCards.size(); i++) {
            playerHandTopLine += "----";
            playerHandMiddleLine += "|" +(playerCards.get(i).getFaceUp() ? playerCards.get(i).toString() :
            "    ") + "|";
            playerHandBottomLine += "----";

        }

        displayLineWithMessage("DEALER HAND");
        displayLineWithMessage(dealerHandTopLine);
        displayLineWithMessage(dealerHandMiddleLine);
        displayLineWithMessage(dealerHandBottomLine);

        displayLineWithMessage(playerHandTopLine);
        displayLineWithMessage(playerHandMiddleLine);
        displayLineWithMessage(playerHandBottomLine);
        displayLineWithMessage("PLAYER HAND");

        displayLineWithMessage(message);

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayKenoWelcomeScreen() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("WELCOME TO CASINO REAL KENO");
        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("PICK THE NUMBER OF BALLS YOU WISH TO BET ON");
        displayLineWithMessage("AND THE BALL NUMBERS (wait 5 s)");

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayKenoPickNumSpotsScreen() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("CASINO REAL KENO");
        displayBlankPipeLine();
        displayLineOfStars();

        for (int i = 0; i < 3; i++) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("PICK THE NUMBER OF SPOTS YOU WISH TO BET ON");
        displayLineWithMessage("PICK BETWEEN 4 and 10");

        for (int i = 0; i < 4; i++) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayKenoPickNumberScreen() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("CASINO REAL KENO");
        displayBlankPipeLine();
        displayLineOfStars();

        for (int i = 0; i < 3; i++) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("PICK THE NUMBERS YOU WISH TO BET ON");
        displayLineWithMessage("PICK BETWEEN 1 and 80, ENTER THEM ONE AT A TIME PLEASE");

        for (int i = 0; i < 4; i++) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayKenoBallsPickedScreen(int[] a) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("CASINO REAL KENO");
        displayBlankPipeLine();
        displayLineOfStars();

        for (int i = 0; i < 3; i++) {
            displayBlankPipeLine();
        }

        String upperKenoLine = "";
        String lowerKenoLine = "";

        for ( int i = 0; i < 10; i++ ) {
            upperKenoLine += " " + a[i];
        }

        for ( int i = 10; i < 20; i++ ) {
            lowerKenoLine += " " + a[i];
        }

        displayLineWithMessage(upperKenoLine);
        displayLineWithMessage(lowerKenoLine);

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("THE BALLS HAVE BEEN SELECTED");

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayPokerWelcomeScreen() {
        String[] s = new String[3];
        s[0] = "A LITTLE GAMBLING IS FUN WHEN YOU'RE WITH ME";
        s[1] = " ";
        s[2] = "PLACE A WAGER";

        displayGenericHeaderAndMessageScreen("WELCOME TO CASINO REAL POKER", s);
    }

    public static void displayPokerHandScreen(PokerHand player, PokerHand dealer, PokerHand turn, String message) {
/*       displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("CASINO REAL POKER");
        displayBlankPipeLine();
        displayLineOfStars();*/

        String line1= "", line2 = "", line3 = "", line4 = "",
                line5 = "", line6 = "", line7 = "", line8 = "", line9 = "";

        ArrayList<Card> playerCards = player.getCards();
        ArrayList<Card> dealerCards = dealer.getCards();



        String turnLineTop = "";
        String turnLineCards = "";
        String turnlineBottom = "";

        for ( int i = 0; i < turn.getCards().size(); i++ ) {
            turnLineTop += "----";
            turnLineCards += "|" + (turn.getCards().get(i).getFaceUp() ? turn.getCards().get(i)
                    : "    ") + "|";
            turnlineBottom += "----";
        }

        line1 = "DEALER";
        for ( int i = 0; i < dealerCards.size(); i++ ) {
            line2 += "----";
            line3 += "|" + dealerCards.get(i).toString() + "|";
            line4 += "----";
        }
        line5 += message;
        for ( int i = 0; i < playerCards.size(); i++ ) {
            line6 += "----";
            line7 += "|" + playerCards.get(i).toString() + "|";
            line8 += "----";
        }
        line9 = "PLAYER";


        displayLineOfStars();

        displayLineWithMessage(turnLineTop);
        displayLineWithMessage(turnLineCards);
        displayLineWithMessage(turnlineBottom);

        displayLineOfStars();

        displayLineWithMessage(line1);
        displayLineWithMessage(line2);
        displayLineWithMessage(line3);
        displayLineWithMessage(line4);

        displayLineWithMessage(line5);
        displayLineWithMessage(line6);
        displayLineWithMessage(line7);
        displayLineWithMessage(line8);
        displayLineWithMessage(line9);


        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayPokerHandScreen(PokerHand hand, String message) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage(message);
        displayBlankPipeLine();
        displayLineOfStars();

        String turnLineTop = "";
        String turnLineCards = "";
        String turnlineBottom = "";

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        for ( int i = 0; i < hand.getCards().size(); i++ ) {
            turnLineTop += "----";
            turnLineCards += "|" + hand.getCards().get(i).toString() + "|";
            turnlineBottom += "----";
        }

        displayLineWithMessage(turnLineTop);
        displayLineWithMessage(turnLineTop);
        displayLineWithMessage(turnLineTop);

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }


    public static void displayYouWinScreen(String headerMessage) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage(headerMessage);
        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("YOU'VE WON!");

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
        //waitForEnter();
    }


    public static void displayYouLoseScreen(String header) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage(header);
        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("YOU'VE LOST!");

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
        //waitForEnter();
    }

    public static void displayGenericHeaderAndMessageScreen(String header, String body) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage(header);
        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage(body);

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayGenericHeaderAndMessageScreen(String header, String[] bodyArray) {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage(header);
        displayBlankPipeLine();
        displayLineOfStars();

        int numBlankLines = 9 - bodyArray.length;
        int numBlankLinesTop, numBlankLinesBottom;

        // handle odds
        if ( numBlankLines % 2 != 0) {
            numBlankLinesTop = numBlankLines / 2;
            numBlankLinesBottom = numBlankLines / 2 + 1;
        }
        else {
            numBlankLinesTop = numBlankLines / 2;
            numBlankLinesBottom = numBlankLines / 2;
        }

        for ( int i = 0; i < numBlankLinesTop; i++ ) {
            displayBlankPipeLine();
        }

        for ( int i = 0; i < bodyArray.length; i++ ) {
            displayLineWithMessage(bodyArray[i]);
        }

        for ( int i = 0; i < numBlankLinesBottom; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
    }

    public static void displayGTFOScreen() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("UMM WE HAVE A SMALL PROBLEM");
        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("THIS CASINO RUNS ON MONEY...");
        displayLineWithMessage("AND YOU'RE ALL OUT");

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }

        displayLineOfStars();
        displayPipe();
        displayPrompt();
        waitForEnter();
    }

    public static void displayInputErrorScreenGameSelection() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("UMM WE HAVE A SMALL PROBLEM");
        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("WAS THAT A NUMBER YOU JUST INPUT?");
        displayLineWithMessage("hint: it wasn't, so you're playing slots");

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }
        displayLineOfStars();

        displayPipe();
        displayPrompt();
    }

    private static void displayInputErrorScreenBalanceSelection() {
        displayLineOfStars();
        displayBlankPipeLine();
        displayLineWithMessage("UMM WE HAVE A SMALL PROBLEM");
        displayBlankPipeLine();
        displayLineOfStars();

        for ( int i = 0; i < 3; i++ ) {
            displayBlankPipeLine();
        }

        displayLineWithMessage("WAS THAT A NUMBER YOU JUST INPUT?");
        displayLineWithMessage("hint: it wasn't");

        for ( int i = 0; i < 4; i++ ) {
            displayBlankPipeLine();
        }
        displayLineOfStars();

        displayPipe();
        displayPrompt();
    }

     private static void displayLineWithMessage(String message) {
         int numSpacesForPadding = (99 - message.length()) / 2;

         displayPipe();
         displaySpaces(numSpacesForPadding);
         displayString(message);

         if (message.length() % 2 == 0) {
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

        //displayWarHand(dealerCard, playerCard, true);

        //displayBlackJackWelcomeScreen();

        ArrayList<ArrayList<Card>> allCards =  new ArrayList<ArrayList<Card>>();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        playerHand.add(new Card(Suit.HEART, Rank.THREE));
        playerHand.add(new Card(Suit.CLUB, Rank.JACK));

        dealerHand.add(new Card(Suit.DIAMOND, Rank.FIVE));
        dealerHand.add(new Card(Suit.SPADE, Rank.ACE));

        allCards.add(dealerHand);
        allCards.add(playerHand);

        String message = "DO YOU WANT TO HIT OR STAY?";

        //displayBlackJackHand(allCards, message);

        //displayYouLoseScreen();
        //displayYouWinScreen();

    }
}
