package com.casinoreal;

/**
 * Created by johnsquier on 1/24/17.
 */
public class Casino {

    //private Game theGame;
    /*private Player theUser;



    public Casino() {
        /*theUser = new User();
        blackJackGame = new BlackJackGame();
        texasHoldemGame = new TexasHoldemGame();
        casinoWarGame = new CasinoWarGame();
        slotsGame = new SlotsGame();*/
    //}

    // runs the main game loop for the casino
    public void run() {

        // while ( not exit status )

            // print an intro screen ( also gets user name )
            IO.displayIntroScreen();

            // display welcome back screen for player
            // Player.setName()
            String userName = IO.getInputName();

            // check if the user has a balance stored
            double userBalance = IO.checkFileForUserName(userName);

            // prompt the user if they want to add to their balance
            IO.displayBalanceScreen(userName, userBalance);
            userBalance += IO.getInputAdditionalBalance();

            // prompt the user for the game they wish to play

            // how many NPC's in that game

            // call the game loop for that game
    }
}
