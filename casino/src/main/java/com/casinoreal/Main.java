package com.casinoreal;
/**
 * Created by kevinmccann on 1/24/17.
 */
public class Main {

    public static void main(String[] args) {
        Deck.addCardstoDeck();
        for (Card card: Deck.deck) {
            System.out.println(card);
        }
    }
}
