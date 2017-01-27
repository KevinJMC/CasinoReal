package com.casinoreal;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kevinmccann on 1/24/17.
 */
public class Shoe extends Deck{
    private ArrayList<Card> shoe = new ArrayList<>();

    Shoe(int numberOfDecks) {
        for(int j = 0; j < numberOfDecks; j++) {
            Deck d = new Deck();
            d.addCardsToDeck();
            shoe.addAll(d.getDeck());
        }
    }

    @Override
    void shuffle() {
        Collections.shuffle(this.shoe);
    }

    Card drawCard(){
         int randomDraw = (int)(Math.random()*this.shoe.size());
         Card drawnCard = shoe.get(randomDraw);
         shoe.remove(randomDraw);
         return drawnCard;
    }

    int size() {return shoe.size();}

}
