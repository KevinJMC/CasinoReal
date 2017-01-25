package com.casinoreal;
import  java.util.ArrayList;
/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJack extends CardGames{
    private Object blackJack; //Shoe Object
    private int playerHandValue;
    private int dealerHandValue;
    private double bet;
    private double payout = 2.0;
    private double naturalBlackJackPayout = 2.5;
    private ArrayList<Object> playerHand = new ArrayList<>();
    private ArrayList<Object> dealerHand = new ArrayList<>();

    protected void deal(){
        //Deals 2 cards to each player
    }


    protected void setWager(double bet){
        //Set Bet
        this.bet = bet;
    }

    protected void hit(){
        //Command requesting another card
    }

    protected void stay(){
        //Command end turn
    }

    protected void doubleDown(){
        // raises bets 2x the amount
        // request another card
        // Command ends turn
    }

    protected void split(){
        // raises bets to another pot of the same bet
        // splits hand to 2 hands
        // deals 2nd card to new hands
    }

    private boolean isSplittable(){
        //checks to see if starting hand is splittable 2
        return false;
    }

    public void setPlayerHandValue(ArrayList playerHandValue) {

                this.playerHandValue = 0; // sum of ArrayList Hand
    }

    public int getPlayerHandValue() {
        return playerHandValue;
    }

    public void setDealerHandValue(ArrayList dealerHandValue) {
        this.dealerHandValue = 0; // sum of ArrayList Hand
    }

    public int getDealerHandValue() {
        return dealerHandValue;
    }

    protected boolean compare(int playerHandValue, int dealerHandValue){
        //compares the value to see which is greater
        return false;
    }

    private boolean isNatural21(){
        //checks to see if starting hand is a Natural 21
        return false;
    }

    private void dealerNatural21(){
        // ends game for all who didn't have a natural 21
    }

    private int aceAs1or11(){
        // sets the value of Ave as 1 or 11 based on which is better
        return 0;
    }

    private boolean isBust(int handvalue){
        // checks if  handvalue is greater then 21
        return false;
    }

    private boolean isSoft16(int dealerHandValue){
        // checks if dealer handvalue is greater then 17
        return false;
    }

    private void insurance(){
        // request bets for insurance
        // checks for dealers natural 21
    }

    private void playerTurn(){
        // runs loop for player's turn until bust or stay occurs
    }

    private void dealerTurn(){
        // runs loop for dealer's turn until bust or stay occurs. Soft16 conditions occurs
    }

    private boolean isLowCardCount(){
        //checks to see if shoe card count is 2.3 gone
        return false;
    }

    private void shuffleShoe(){
        // shuffles a new Shoe deck for blackjack.
    }
}
