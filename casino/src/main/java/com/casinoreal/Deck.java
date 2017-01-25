package com.casinoreal;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kevinmccann on 1/24/17.
 */
class Deck{
    private ArrayList<Card> deck = new ArrayList<>();

    void addCardsToDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    ArrayList<Card> getDeck() {
        return this.deck;
    }

    void shuffle() {
            Collections.shuffle(deck);
        }
    }

