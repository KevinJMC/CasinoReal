package com.casinoreal;

import java.util.Random;

/**
 * Created by kevinmccann on 1/24/17.
 */
public class CasinoBar extends Game {


    final String[][] jokes = {
            new String[] {"A baby seal walks into a bar and sits down. \"What can I get you?\" asked the bartender.", "\"Anything but a Canadian Club\" replied the seal."},
            new String[] {"A guy walks into a bar carrying a pair of jumper cables.", "He sets em down on the bar." + "And then the bartender said \"Now dont you start anything!\""},
            new String[] {"Helium walks into a bar.", "The bartender says \"We don't serve noble gasses in here.\"" + "Helium doesn't react."},
            new String[] {"A guy sits down at the bar and orders drink after drink rapidly.", "\"Is everything okay, pal?\", the bartender asks.", "\"My wife and I got into a fight and she said she isn’t talking to me for a month!\".",
                    "Trying to put a positive spin on things, the bartender says, \"Well, maybe that’s kind of a good thing.", "You know, a little peace and quiet?\"", "\"Yeah. But today is the last day\"."},
            new String[] {"A number twelve walks into a bar and asks the barman for a pint of beer.", "\"Sorry I cant serve you,\" states the barman.",
                    "\"Why not?!\" asks the number twelve with anger showing in its voice.", "\"You\'re under 18,\" replies the barman."}
    };

    void bartenderJoke() {
        Random random = new Random();
        int index = random.nextInt(jokes.length);
        System.out.println(index);
        System.out.println(jokes.length);
        String[] joke = jokes[index];
        IO.displayGenericHeaderAndMessageScreen("The bartender says, \"Lemme tell you a joke!\"", joke);
        IO.waitForEnter();
    }

    //Random chance to lose money or gain a tip
    void talkToWomanInRedDress() {
        double chance = Math.random();
        if (chance < .2 ) {
            getLucky();
            IO.waitForEnter();
        }
        else if (chance > .8) {
            IO.displayGenericHeaderAndMessageScreen("She winks at you as you walk over...", new String[]{"You buy a round of drinks",
                    "She talks and flirts for a bit...", " ", "then leaves with her boyfriend", " ", "-$25"}) ;
            player.setBalance(player.getBalance()-25);
            IO.waitForEnter();
                }
        else if (chance > .45 && chance < .52) {
            IO.displayGenericHeaderAndMessageScreen("You strike up a conversation", new String[] {"Her eyes sparkle as she laughs at your jokes", " ", "\"Let's go for a walk,\" she says...",
                    "You head out the back door, down a dark alley...", " ", "!!!", "She set you up, 3 men jump you, and steal your wallet!","They've taken everything!"});
            player.setBalance(0.0);
            IO.waitForEnter();
            }
        else {
            IO.displayGenericHeaderAndMessageScreen("You try to strike up a conversation", "She seems disinterested...");
            IO.waitForEnter();
        }
    }

    void getLucky(){
        IO.displayGenericHeaderAndMessageScreen("You got lucky!", "You find $100 on the ground!");
        player.setBalance(player.getBalance()+100);
    }

    public boolean checkForWin() { return true; }

    public void startGame() {
        boolean drinking = true;
        while (drinking) {
            IO.displayGenericHeaderAndMessageScreen("Welcome to the Casino Real Bar", new String[]{"What would you like to do?", " ", "1. Talk to the Bartender", " ", "2. Talk to the woman in the red dress", " ", "3. Have a ginger beer", " ", "otherwise, exit to casino"});
            int choice = IO.getBarInput();
            switch (choice) {
                case 1: bartenderJoke();
                        break;
                case 2: talkToWomanInRedDress();
                        break;
                case 3:
                        IO.displayGenericHeaderAndMessageScreen(" ", "You enjoy a cool, refreshing ginger beer.");
                        IO.waitForEnter();
                        break;
                default:
                        drinking = false;
            }
        }
        
    }

}
