package com.casinoreal;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jeriahhumphrey on 1/25/17.
 */
public class PokerHand {

    Card trigger;
    private static int MAXCARDS = 7;
    int cardCount = 0;
    ArrayList<Card> cards = new ArrayList<Card>();
    private Shoe holdemShoe = new Shoe(1);
    private String handName= "";

    public void addCard(Card c) {
        cards.add(c);
        Collections.sort(cards);
    }

    public ArrayList <Card> getCards(){
        for (int i = 0; i<cards.size();i++){
            //System.out.println(cards.get(i));
        }
        return cards;
    }

    public int addCards(int number) {
        for (int i = 0; i < number; i++) {
            if (cards.size() == MAXCARDS) {
                //System.out.println(" You cannot get anymore cards");
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
                if (spadeCounter>=5){
                    trigger = cards.get(i);
                }
            }
            else if(cards.get(i).getSuit().equals(Suit.HEART)){
                heartCounter++;
            }if (heartCounter>=5){
                trigger = cards.get(i);
            }
            else if(cards.get(i).getSuit().equals(Suit.CLUB)){
                clubCounter++;
            }if (clubCounter>=5){
                trigger = cards.get(i);
            }
            else if(cards.get(i).getSuit().equals(Suit.DIAMOND)){
                diamondCounter++;
                if (diamondCounter>=5){
                    trigger = cards.get(i);
                }
            }
        }
        if (spadeCounter >=5 || heartCounter>=5){
            condition = true;
        } else if (diamondCounter >= 5 || clubCounter >= 5) {

            condition = true;
        }
        return condition;
    }

    public boolean isAStraight(){
        boolean condition = false;
        int increment=1;

        for (int i = 1; i < cards.size()-2 ; i++) {
            if (cards.get(i).getRank().ordinal() +1 == cards.get(i+1).getRank().ordinal() &&
                    cards.get(i).getRank().ordinal() -1 == cards.get(i-1).getRank().ordinal()){
                if(cards.get(i).getRank().ordinal()+2== (cards.get(i+2).getRank().ordinal())){
                    increment++;
                }

            }
        }

        if (increment >=3){

            condition = true;
        }

        return condition;
    }

    public boolean isAFourOfAKind() {
        ArrayList <Card> c1 = new ArrayList <Card>();
        int count=1;
        boolean condition = false;
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                {
                    if (cards.get(i).getRank().ordinal()==cards.get(j).getRank().ordinal()){
                        c1.add(cards.get(i));
                    }
                }
            }
        }
        if (c1.size() >= 6) {
            trigger = c1.get(3);
            condition = true;
        }

        return condition;
    }


    public boolean isAThreeOfAKind() {

        boolean condition = false;
        for (int i = 0; i < cards.size() - 2; i++) {
            if(cards.get(i).getRank().ordinal()==cards.get(i+1).getRank().ordinal()){
                if(cards.get(i+1).getRank().ordinal()==cards.get(i+2).getRank().ordinal()){
                    condition=true;
                    trigger = cards.get(i+2);
                }
            }

        }

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
                    trigger =cards.get(i);
                    cards.remove(j);
                }
            }
        }
        if (pairCount==1){
            condition = true;
        }
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
                    trigger = cards.get(i);
                    cards.remove(j);

                }
            }

        }
        if (pairCount>=2){
            condition = true;

        }
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
        return condition;
    }

    public boolean isAFullHouse() {
        int counter = 1;
        int pairCounter = 0;
        int threeCounter = 0;
        boolean condition = false;
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i).getRank().ordinal()==cards.get(i+1).getRank().ordinal()){
                if(cards.get(i+1).getRank().ordinal() == cards.get(i+2).getRank().ordinal()){
                    threeCounter++;
                    trigger= cards.get(i);
                }
                else{
                    pairCounter++;
                }
            }
        }
        if (pairCounter == 2 && threeCounter == 1) {
            condition = true;
        }

        return condition;
    }


    public boolean isARoyalFlush(){
        boolean condition= false;
        int cardrank=0;
        for(int i=0;i< cards.size();i++){
            if(cards.get(i).getRank().ordinal()>=8){
                cardrank++;
                if (cardrank >=5){
                    trigger = cards.get(i);
                }
            }
        }
        if(cardrank>=5 && isAStraightFlush()){
            condition = true;
        }

        return condition;
    }


    public void getHand() {

        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card # " + (i + 1) + " is a(n) " + cards.get(i));
        }
    }

    public String rankHand(){
        if(this.isARoyalFlush()) {
            handName = "Royal Flush";
        }
        else if (this.isAStraightFlush()){
            handName = "Straight Flush";

        }else if (this.isAFourOfAKind()){
            handName = "Four of a Kind";

        }else if (this.isAFullHouse()){
            handName = "Full House";

        }else if (this.isAFlush()){
            handName = "Flush";

        }else if (this.isAStraight()){
            handName = "Straight";

        }else if (this.isAThreeOfAKind()){
            handName = "Three of a Kind";

        }else if (this.isTwoPair()){
            handName = "Two Pair";

        }else if (this.isAPair()){
            handName = "Pair";

        }
        else{
            handName= "High Card";
        }

        System.out.println(handName);
        return handName;
    }


    public int getRank(){
        int cardRank = 0;
        switch (handName){
            case "Royal Flush":
                cardRank=10;
                break;
            case "Straight Flush":
                cardRank=9;
                break;
            case "Four of a Kind":
                cardRank = 8;
                break;
            case "Full House":
                cardRank = 7;
                break;
            case "Flush":
                cardRank=6;
                break;
            case "Straight":
                cardRank =5;
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
