package com.casinoreal;

/**
 * Created by kevinmccann on 1/24/17.
 */
public class Card {
    private String value;
    private String suit;

    Card(String suit,String value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value + this.suit;
    }
}
