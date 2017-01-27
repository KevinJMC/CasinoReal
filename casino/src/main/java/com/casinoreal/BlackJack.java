package com.casinoreal;
import  java.util.ArrayList;
/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJack {
    private Shoe blackJack = new Shoe(3); //Shoe Object
    private int playerHandValue = 0;
    private int dealerHandValue = 0;
    private double bet = 0.0;
    private double secondBet = 0.0;
    private double payout = 2.0;
    private double naturalBlackJackPayout = 2.5;
    protected ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> player2ndHand = new ArrayList<>();
    protected ArrayList<Card> dealerHand = new ArrayList<>();
    private ArrayList<ArrayList<Card>> membersInGame = new ArrayList();
    private int numberOfAces;



    private void addMemberToGame(ArrayList member){
        this.getMembersInGame().add(member);
    }

    protected void setTable(){
        addMemberToGame(playerHand);
        addMemberToGame(dealerHand);
        // will add function to add NPCs
    }

    protected void dealFromShoe(ArrayList<Card> currentMember){
        currentMember.add(blackJack.drawCard());
    }

    protected void dealToPlayers(){
        for (int i =0; i<2; i++) {
            for (ArrayList member : this.getMembersInGame()) {
                dealFromShoe(member);
            }
        }
    createPlayerHandValue();
    createDealerHandValue();
    }


    protected void setWager(Player player ,double bet){
        //Set Bet
        this.bet = bet;
        player.updateBalance(-bet);
    }


    protected void createPlayerHandValue() {
        int playerHandValue = 0;
        this.playerHandValue = setHandValue(playerHand, playerHandValue);
    }

    protected void createDealerHandValue(){
        int dealerHandValue = 0;
        this.dealerHandValue = setHandValue(dealerHand, dealerHandValue);
    }


    protected void doubleDown(Player player){
        // raises bets 2x the amount
        player.updateBalance(-bet);
        this.bet += this.bet;
    }
    private void splitHand(ArrayList currentPlayer, ArrayList secondHand){
        secondHand.add(1, currentPlayer);
        currentPlayer.remove(1);
    }
    private void splitBet(ArrayList currentHand, ArrayList secondHand){
        // raises bets to another pot of the same bet
        this.secondBet = this.bet;
        splitHand(currentHand, secondHand);
        // deals 2nd card to new hands
        dealFromShoe(currentHand);
        dealFromShoe(secondHand);
        // flag so no further splits can occur
    }

    private boolean isSplittable(Card card1, Card card2){
        //checks to see if starting hand is splittable 2
        return (card1 == card2);
    }

    protected int getCardValue(Card card) {
        int cardValue;
        this.numberOfAces = 0;
        String cardRank = card.getRank().toString();
        if (cardRank.equals("K") || cardRank.equals("Q") || cardRank.equals("J") || cardRank.equals("10"))
            cardValue = 10;
        else if (cardRank.equals("A")){
            cardValue = 11;
            numberOfAces++;
        } else
            cardValue = Integer.parseInt(cardRank);

        return cardValue;
    }

    protected int setHandValue(ArrayList<Card> playerHand, int handValue) {
        for (Card card:playerHand) {
            handValue += getCardValue(card);
            handValue = aceAs1or11(handValue);
         }
        return handValue;
    }



    private int aceAs1or11(int handValue){
        while (numberOfAces>0 && handValue> 21) {
            handValue -= 10;
            this.numberOfAces--;
        }
        return handValue;
    }

    protected int getPlayerHandValue() {
        return this.playerHandValue;
    }



    protected int getDealerHandValue() {
        return this.dealerHandValue;
    }

    private boolean didPlayerWin(int playerHandValue, int dealerHandValue){
        //compares the value to see which is greater
        return (playerHandValue >dealerHandValue);
    }

    private boolean didPlayerTie(int playerHandValue, int dealerHandValue){
        return (playerHandValue == dealerHandValue);
    }

    protected boolean compare(int playerHandValue, int dealerHandValue, Player player){
        boolean result = true;
        if (didPlayerTie(playerHandValue, dealerHandValue)){
            player.updateBalance(bet);
            result =  false;
        }
        else if (didPlayerWin(playerHandValue, dealerHandValue) || isBust(dealerHandValue)){
            player.updateBalance(bet * payout);
        }
        return result;
    }

    protected double standardWin(){
        return this.bet * this.payout;
    }

    protected double natural21Payout(){
        return this.bet * this.naturalBlackJackPayout;
    }

    protected  double pushBet(){
        return bet;
    }
    protected boolean isNatural21(int handValue){
        //checks to see if starting hand is a Natural 21
        return (handValue == 21);
    }

    protected boolean isBust(int handvalue){
        // checks if  handvalue is greater then 21
        return (handvalue > 21);
    }

    protected boolean isSoft16(){
        return (getDealerHandValue() < 17);
    }

    private void insurance(){
        // request bets for insurance
        // checks for dealers natural 21
    }


    protected void shuffleShoeWhenLow(){
        if (blackJack.size() < blackJack.size()/3)
            blackJack.shuffle();
    }

    protected ArrayList<ArrayList<Card>> getMembersInGame() {
        return membersInGame;
    }

    protected void clearHands(){
        for (ArrayList<Card> hand: membersInGame) hand.clear();
    }



}
