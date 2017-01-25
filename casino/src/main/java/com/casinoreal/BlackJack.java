package com.casinoreal;
import  java.util.ArrayList;
/**
 * Created by randallcrame on 1/24/17.
 */
public class BlackJack {
    private Shoe blackJack = new Shoe(3); //Shoe Object
    private int playerHandValue;
    private int dealerHandValue;
    private double bet;
    private double secondBet;
    private double payout = 2.0;
    private double naturalBlackJackPayout = 2.5;
    protected ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> player2ndHand = new ArrayList<>();
    protected ArrayList<Card> dealerHand = new ArrayList<>();
    private ArrayList<ArrayList<Card>> membersInGame = new ArrayList();
    private boolean splitState = false;


    private void addMemberToGame(ArrayList member){
        this.membersInGame.add(member);
    }

    protected void joinMembersInGame(){
        addMemberToGame(playerHand);
        addMemberToGame(dealerHand);
        // will add function to add NPCs
    }

    private void dealFromShoe(ArrayList currentMember){
        currentMember.add(blackJack.drawCard());
    }

    protected void dealToPlayers(){
        for (int i =0; i<2; i++) {
            for (ArrayList member : this.membersInGame) {
                dealFromShoe(member);
            }
        }
    }

    private void setWager(double bet){
        //Set Bet
        //this.bet = bet;
        //playersBalance-= bet;
    }


    protected void hit(ArrayList currentMember){
        //Command requesting another card
        dealFromShoe(currentMember);
    }

    private void stay(){
        //Command end turn
        //break;???
    }

    private void doubleDown(ArrayList currentMember){
        // raises bets 2x the amount
        //this.bet += this.bet;
        dealFromShoe(currentMember);
        // Command ends turn
        // stay();
    }
    private void splitHand(ArrayList currentPlayer, ArrayList secondHand){
        secondHand.add(1, currentPlayer);
        currentPlayer.remove(1);
    }
    private void splitBet(ArrayList currentPlayer, ArrayList secondHand){
        // raises bets to another pot of the same bet
        // this.secondBet = this.bet;
        // splitHand(currentPlayer, secondHand);
        // deals 2nd card to new hands
        splitState = true;
    }
    private boolean isSplitState(){
        //checks if turn is currently split?? unsure of redundancy
        return (splitState);
    }
    private boolean isSplittable(ArrayList card1, ArrayList card2){
        //checks to see if starting hand is splittable 2
        return (card1 == card2);
    }

    private int getCardValue(Card card){
        int cardValue;
        String cardRank = card.getRank().toString();
        if (cardRank.equals('K') || cardRank.equals('Q')|| cardRank.equals('J') )
            cardValue = 10;
        else if (cardRank.equals('A'))
            cardValue = 11;
        else
            cardValue = Integer.parseInt(cardRank);
        return cardValue;
    }

    private void setHandValue(ArrayList<Card> playerHand, int handValue) {
        for (Card card:playerHand) {
            handValue += getCardValue(card);
         }

    }

    private int getPlayerHandValue() {
        return this.playerHandValue;
    }



    private int getDealerHandValue() {
        return this.dealerHandValue;
    }

    private boolean didPlayerWin(int playerHandValue, int dealerHandValue){
        //compares the value to see which is greater
        return (playerHandValue >dealerHandValue);
    }

    private boolean didPlayerTie(int playerHandValue, int dealerHandValue){
        return (playerHandValue == dealerHandValue);
    }

    private boolean compare(){return false;}

    private boolean isNatural21(ArrayList player, int handValue){
        //checks to see if starting hand is a Natural 21
        // (getPlayerHandValue(player) == 21)
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
