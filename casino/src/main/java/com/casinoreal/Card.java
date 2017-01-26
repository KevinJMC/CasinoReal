package com.casinoreal;

/**
 * Created by kevinmccann on 1/24/17.
 */
public class Card implements Comparable <Card> {
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



    @Override
    public  int compareTo(Card c1){
        int compareRank= c1.rank.ordinal();
        return this.rank.ordinal() - compareRank;
    }




}
