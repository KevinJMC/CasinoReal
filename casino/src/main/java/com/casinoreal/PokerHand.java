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
    private String handName= "";



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

                cardCount = cards.size();
                Collections.sort(cards);


            }

            }
           return cardCount;
        }

    public boolean isAFlush() {
        int spadeCounter=0;
        int clubCounter=0;
        int heartCounter=0;
        int diamondCounter=0;
        boolean condition = false;
        for (int i = 0; i < cards.size(); i++) {
              if(cards.get(i).getSuit().equals(Suit.SPADE)){
                  spadeCounter++;
            }
            else if(cards.get(i).getSuit().equals(Suit.HEART)){
                heartCounter++;
            }
              else if(cards.get(i).getSuit().equals(Suit.CLUB)){
                  clubCounter++;
              }
              else if(cards.get(i).getSuit().equals(Suit.DIAMOND)){
                  diamondCounter++;
              }
        }
        if (spadeCounter >=5 || heartCounter>=5){
            condition = true;
        } else if (diamondCounter >= 5 || clubCounter >= 5) {

            condition = true;
        }
        handName = "Flush";
        return condition;
    }

    public boolean isAStraight(){
        boolean condition = false;
        int increment=1;
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).getRank().ordinal() + 1 == cards.get(j).getRank().ordinal()){
                    increment++;
                }
            }
        }
        if (increment >=5){
            condition = true;
        }
        handName = "Straight";
        return condition;
    }

    public boolean isAFourOfAKind() {
        ArrayList <Card> c1 = (ArrayList <Card> ) cards.clone();
        int count=1;
        boolean condition = false;
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                {
                    if (cards.get(i).getRank().ordinal()==cards.get(j).getRank().ordinal()){
                        count++;
                        cards.set(i, new Card(Suit.CLUB,Rank.EIGHT));

                    }

                }

            }
        }
            if (count == 4) {
                condition = true;

            }
            handName = "Four of a Kind";
            cards =  (ArrayList <Card> ) c1.clone();
        System.out.println(count);
        return condition;

    }


    public boolean isAThreeOfAKind() {
        ArrayList <Card> c1 = (ArrayList <Card> ) cards.clone();
        int count=1;
        boolean condition = false;
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                {
                    if (cards.get(i).getRank().ordinal()==cards.get(j).getRank().ordinal()){
                        count++;
                        cards.set(i, new Card(Suit.CLUB,Rank.EIGHT));

                    }

                }

            }
        }
        if (count == 3) {
            condition = true;

        }
        handName = "Three of a Kind";
        cards =  (ArrayList <Card> ) c1.clone();
        System.out.println(count);
        return condition;

    }

    public boolean isAPair() {
        ArrayList <Card> c1 = (ArrayList <Card> ) cards.clone();

        int pairCount = 0;

        boolean condition = false;
        for (int i = 0; i < cards.size()-1; i++) {

            for(int j = i+1; j < cards.size();j++){
                if(cards.get(i).getRank().equals(cards.get(j).getRank())){
                    pairCount++;
                    cards.remove(j);




                }
            }

            }
        if (pairCount==1){
            condition = true;

        }
        handName = "Pair";
        cards =  (ArrayList <Card> ) c1.clone();
        return condition;
    }

    public boolean isTwoPair() {
        ArrayList <Card> c1 = (ArrayList <Card> ) cards.clone();
        int pairCount = 0;

        boolean condition = false;
        for (int i = 0; i < cards.size()-1; i++) {

            for(int j = i+1; j < cards.size();j++){
                if(cards.get(i).getRank().equals(cards.get(j).getRank())){
                    pairCount++;

                    cards.remove(j);



                }
            }

        }
        if (pairCount==2){
            condition = true;

        }
        handName = "Two Pair";
        System.out.println(pairCount);
        cards =  (ArrayList <Card> ) c1.clone();
        return condition;
    }

    public boolean isAStraightFlush(){
        boolean condition= false;
        if (this.isAStraight()){
            for (int i =0; i< 3; i++){
                PokerHand p2 = new PokerHand();
                p2.addCard(cards.get(i));
                p2.addCard(cards.get(i+1));
                p2.addCard(cards.get(i+2));
                p2.addCard(cards.get(i+3));
                p2.addCard(cards.get(i+4));

                if (p2.isAFlush()){
                    condition = true;
                    break;
                }
            }

        }
        handName = "Straight Flush";
        return condition;
    }

    public boolean isAFullHouse() {
        ArrayList <Card> c1 = (ArrayList <Card> ) cards.clone();
        boolean condition= false;
            this.getHand();
            for (int i = 0; i < cards.size() - 1; i++) {
                for (int j = i + 1; j < cards.size(); j++) {
                    {
                        if (cards.get(i).getRank().ordinal() == cards.get(j).getRank().ordinal()) {
                            cards.remove(j);

                        }

                    }

                }
            }
            this.getHand();
            if (this.isAPair()) {
                condition= true;

            }
            handName = "Full House";
        cards =  (ArrayList <Card> ) c1.clone();

        return  condition;
    }

    public boolean isARoyalFlush(){
        boolean condition= false;
        int cardrank=0;
        for(int i=0;i< cards.size();i++){
            if(cards.get(i).getRank().ordinal()>=8){
                cardrank++;
            }
        }
        System.out.println(cardrank);
        if(cardrank>=5 && isAStraightFlush()){
        condition = true;
        }
        handName= "Royal Flush";
        return condition;

    }



    public void getHand() {

        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card # " + (i + 1) + " is a(n) " + cards.get(i));
        }
    }


    public int rankHand(){
        int cardRank = 0;
        switch (handName){
            case "Royal Flush":
                cardRank=9;
                break;
            case "Straight Flush":
                cardRank=8;
                break;
            case "Four of a Kind":
                cardRank = 7;
                break;
            case "Full House":
                cardRank = 6;
                break;
            case "Flush":
                cardRank=5;
                break;
            case "Three of a Kind":
                cardRank=4;
                break;
            case "Two Pair":
                cardRank=3;
                break;
            case "Pair":
                cardRank = 2;
                break;
            default:
                cardRank= 1;
        }
            return cardRank;

        }




    }







