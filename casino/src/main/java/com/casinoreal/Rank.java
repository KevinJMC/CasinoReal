package com.casinoreal;

/**
 * Created by kevinmccann on 1/24/17.
 */
public enum Rank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private String value;

    Rank(String value) {
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
