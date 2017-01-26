package com.casinoreal;

import java.util.Random;

/**
 * Created by kevinmccann on 1/24/17.
 */
public class CasinoBar extends Game {


    final String[] jokes = {
            "A baby seal walks into a bar and sits down. \"What can I get you?\" asked the bartender. \"Anything but a Canadian Club\" replied the seal.",
            "A guy walks into a bar carrying a pair of jumper cables. \n" +
                    "He sets em down on the bar. \n" +
                    "And then the bartender said \"Now dont you start anything!\"",
            "Helium walks into a bar. \n" +
                    "The bartender says \"We don't serve noble gasses in here.\" \n" +
                    "Helium doesn't react.",
            "A guy sits down at the bar and orders drink after drink rapidly.\n" +
                    "\"Is everything okay, pal?\", the bartender asks.\n" +
                    "\"My wife and I got into a fight and she said she isn’t talking to me for a month!\".\n" +
                    "Trying to put a positive spin on things, the bartender says, \"Well, maybe that’s kind of a good thing. \n" +
                    "You know, a little peace and quiet?\"\n" +
                    "\"Yeah. But today is the last day\".",
            "A number twelve walks into a bar and asks the barman for a pint of beer.\n" +
                    "\"Sorry I cant serve you,\" states the barman.\n" +
                    "\"Why not?!\" asks the number twelve with anger showing in its voice.\n" +
                    "\"Youre under 18,\" replies the barman."
    };

//    int talkToBartender() {
//        if(choice.equals("Tell a joke!"))
//            bartenderJoke();
//        if(choice.equals("Have a drink")) {
//            updateBalance(-10);
//            increaseDrunkMultiplier();
//        }
//    }

    String bartenderJoke() {
        Random random = new Random();
        int index = random.nextInt(jokes.length);
        return jokes[index];
    }

    //Random chance to lose money or gain a tip
    void talkToWomanInRedDress() {
        double chance = Math.random();
        if (chance < .2 ) {
            getLucky();
        }
        if (chance > .8) {
            IO.displayGenericHeaderAndMessageScreen("She winks at you as you walk over...", new String[]{"You buy a round of drinks",
                    "She talks and flirts for a bit...", " ", "then leaves with her boyfriend", " ", "-$25"}) ;
            player.setBalance(player.getBalance()-25);
                }
        else {
            IO.displayGenericHeaderAndMessageScreen("You try to strike up a conversation", "She seems disinterested...");
        }


    }

    void getLucky(){
        IO.displayGenericHeaderAndMessageScreen("You got lucky!", "You find $100 on the ground!");
        player.setBalance(player.getBalance()+100);
    }

    public boolean checkForWin() { return true; }

    void barEngine() {
        
    }

}
