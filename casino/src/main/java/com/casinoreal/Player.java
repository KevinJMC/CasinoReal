package com.casinoreal;

/**
 * Created by andresholland on 1/25/17.
 */

public class Player {

    private double playerBalance;
    private String playerName;
    private int reUpCount;

    public void updateBalance (double amount) {
        playerBalance += amount;
    }

    public void setName (String name) {
        playerName = name;
    }

    public String getName () {
        return playerName;
    }

    public void setBalance (double balance) {
        playerBalance = balance;
    }

    public double getBalance () {
        return playerBalance;
    }

    public void setReUpCount () {
        reUpCount++;
    }

    public int getReUpCount () {
        return reUpCount;
    }

}
