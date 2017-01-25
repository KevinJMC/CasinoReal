package com.casinoreal;

/**
 * Created by kevinmccann on 1/24/17.
 */
public class Card {
    private Rank rank;
    private Suit suit;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return this.rank.toString() + this.suit.toString();
    }

    Suit getSuit() {
        return this.suit;
    }

    Rank getRank() {
        return this.rank;
    }
}
