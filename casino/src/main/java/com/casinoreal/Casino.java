package com.casinoreal;

/**
 * Created by johnsquier on 1/24/17.
 */
public class Casino {

    private Game theGame;
    private Player theUser;

    public Casino() {
        theUser = new User();
    }

    public void run() {
            IO.setUser(theUser);

            IO.displayIntroScreen();

            theUser.setName(IO.getInputName());

            theUser.setBalance(IO.checkFileForUserName(theUser.getName()));

            while ( true ) {

                IO.displayBalanceScreen(theUser.getName(), theUser.getBalance());
                theUser.setBalance(theUser.getBalance() + IO.getInputAdditionalBalance());

                IO.displayGameSelectScreen(theUser.getName(), theUser.getBalance());
                int gameSelected = IO.getInputSelectedGame();

                switch ( gameSelected ) {
                    case 1: // slots
                        theGame = new SlotMachine();
                        theGame.setPlayer(theUser);
                        break;
                    case 2: // blackjack
                        //theGame = new BlackJackEngine();
                        break;
                    case 3: // war
                        theGame = new CasinoWarGame();
                        theGame.setPlayer(theUser);
                        break;
                    case 4: // hold em
                        // set the game to whatever the poker class is
                        break;
                    default:
                }

                theGame.startGame();
            }
    }
}
