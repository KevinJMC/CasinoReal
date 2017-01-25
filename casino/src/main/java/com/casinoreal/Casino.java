package com.casinoreal;

/**
 * Created by johnsquier on 1/24/17.
 */
public class Casino {

    //private Game theGame;
    private Player theUser;

    public Casino() {
        theUser = new User();
    }

    public void run() {
            IO.setUser(theUser);

            IO.displayIntroScreen();

            theUser.setName(IO.getInputName());

            theUser.setBalance(IO.checkFileForUserName(theUser.getName()));

            // while ( not exit status )

            IO.displayBalanceScreen(theUser.getName(), theUser.getBalance());
            theUser.setBalance(theUser.getBalance() + IO.getInputAdditionalBalance());

            IO.displayGameSelectScreen(theUser.getName(), theUser.getBalance());
            int gameSelected = IO.getInputSelectedGame();

            // call the game loop for that game
                // need game class for this, switch on the user input and instantiate that game
                // then call start to run that games' loop
    }
}
