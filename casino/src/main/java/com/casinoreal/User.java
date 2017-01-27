package com.casinoreal;

/**
 * Created by andresholland on 1/25/17.
 */
public class User extends Player {

    public void reUp () {
        if (getReUpCount() < 2) {
            updateBalance (100);
            setReUpCount();
        }
       // IO.displayReUpMessage("Fool, get out of the casino before Tariq sees you!");
    }
}
