package com.casinoreal;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jeriahhumphrey on 1/25/17.
 */
public class PokerHand {

    private static int MAXCARDS = 7;
    int cardCount = 0;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Shoe holdemShoe = new Shoe(1);

    public void addCard(Card c) {
        cards.add(c);
        Collections.sort(cards);
    }

    public int addCards(int number) {
        for (int i = 0; i < number; i++) {
            if (cards.size() == MAXCARDS) {
                System.out.println(" You cannot get anymore cards");
                break;
            } else {
                cards.add(holdemShoe.drawCard());

            }
        }
        cardCount = cards.size();
        Collections.sort(cards);

        return cardCount;
    }

    public boolean isAFlush() {
        boolean condition = false;
        int match = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).getSuit().equals(cards.get(j).getSuit())) {
                    match++;
                }
            }
        }
        if (match >= 5) {
            condition = true;
        }
        return condition;
    }

    public void getHand() {

        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card # " + (i + 1) + " is a(n) " + cards.get(i));
        }
    }
}





