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
                    case 1: // keno
                        theGame = new Keno();
                        theGame.setPlayer(theUser);
                        break;
                    case 2: // craps
                        //theGame = new Craps();
                        //theGame.setPlayer(theUser);
                        break;
                    case 3: // slots
                        theGame = new SlotMachine();
                        theGame.setPlayer(theUser);
                        break;
                    case 4: // black jack
                        //theGame = new BlackJackEngine();
                        //theGame.setPlayer(theUser);
                        break;
                    case 5: // casino war
                        theGame = new CasinoWarGame();
                        theGame.setPlayer(theUser);
                        break;
                    case 6: // hold em
                        break;
                    case 7: // bar
                        //theGame = new CasinoBar();

                    default:
                }

                theGame.startGame();
            }
    }
}
