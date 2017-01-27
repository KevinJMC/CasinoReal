package com.casinoreal;

import java.util.ArrayList;

/**
 * Created by randallcrame on 1/25/17.
 */
public class BlackJackEngine extends CardGames{
    private Player player;
    private String prompt = " ";
    private String results = "";
    private double amount;
    private Shoe blackJack = new Shoe(3); //Shoe Object
    private int playerHandValue = 0;
    private int dealerHandValue = 0;
    private double bet = 0.0;
    //private double secondBet = 0.0;
    private double payout = 2.0;
    private double naturalBlackJackPayout = 2.5;
    private ArrayList<Card> getPlayerHand = new ArrayList<>();
    //private ArrayList<Card> player2ndHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private ArrayList<ArrayList<Card>> membersInGame = new ArrayList();
    private int numberOfAces;

    BlackJackEngine(Player player){
        this.player = player;
    }

    public void startGame(){
        setTable();
        boolean notExit;
        do {
            setWelcomeDisplay();
            setWagerAmount();
            setWager(player , amount);
            dealToPlayers();
            playerTurn();
            dealerTurn();
            checkForWin();
            IO.displayBlackJackHand(getMembersInGame(),
                    "In the end " + results + " Do you want to play again? Y/N");
            clearHands();
            shuffleShoeWhenLow();
            notExit = IO.getInputSlotsPlayAgain();
        } while (notExit);
    }

    private void setPrompt(){
        prompt = IO.getInputName();
    }

    private void setWagerAmount(){amount = IO.getWager();}

    private void setWelcomeDisplay(){ IO.displayBlackJackWelcomeScreen();}

    private void playerTurn(){
        int doubleFlag = 0;
        String message;
        do {
            if (isNatural21(getDealerHandValue())){
                IO.displayBlackJackHand(getMembersInGame(),"Dealer has 21, press Enter.");
                IO.waitForEnter();
                break;
            }
            if (doubleFlag == 0)
                message = " Do you want to Hit, Stay or Double.";
            else
                message = " Do you want to Hit or Stay";

            IO.displayBlackJackHand(getMembersInGame(), message);

            setPrompt();

            if (prompt.equalsIgnoreCase("DOUBLE") && doubleFlag < 1){
                doubleDown(player);
                dealFromShoe(getPlayerHand);
                calculatePlayerHandValue();
                break;
            }

            if (prompt.equalsIgnoreCase("HIT")) {
                dealFromShoe(getPlayerHand);
                calculatePlayerHandValue();
            }

            if (isBust(getPlayerHandValue())) {
                break;
            }
            doubleFlag = 1;
        } while (!(prompt.equalsIgnoreCase("STAY")));
    }

    private void dealerTurn(){
        while (isSoft16()) {
            if (isBust(getPlayerHandValue()))
                break;
            getDealerHand().get(1).flipFaceUp();
            IO.displayBlackJackHand(getMembersInGame(), "Hit Enter to continue");
            IO.waitForEnter();
            dealFromShoe(dealerHand);
            calculateDealerHandValue();
        }
    }

    protected String getResults() {
        return results;
    }
    protected ArrayList<Card> getPlayerHand() {
        return getPlayerHand;
    }

    protected ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    private void addMemberToGame(ArrayList member){
        this.getMembersInGame().add(member);
    }

    protected void setTable(){
        addMemberToGame(getPlayerHand);
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
        getDealerHand().get(1).setFaceUp(false);
        calculatePlayerHandValue();
        calculateDealerHandValue();
    }

    protected void setWager(Player player ,double bet){
        //Set Bet
        this.bet = bet;
        player.updateBalance(-bet);
    }

    protected void calculatePlayerHandValue() {
        int playerHandValue = 0;
        this.numberOfAces = 0;
        this.playerHandValue = setHandValue(getPlayerHand, playerHandValue);
    }

    protected void calculateDealerHandValue(){
        int dealerHandValue = 0;
        this.numberOfAces = 0;
        this.dealerHandValue = setHandValue(dealerHand, dealerHandValue);
    }

    protected void doubleDown(Player player){
        // raises bets 2x the amount
        player.updateBalance(-bet);
        this.bet += this.bet;
    }

    private void insurance(){
        // request bets for insurance
        // checks for dealers natural 21
    }

    private void shuffleShoeWhenLow(){
        if (blackJack.size() < blackJack.size()/3)
            blackJack.shuffle();
    }

    private void clearHands(){
        for (ArrayList<Card> hand: membersInGame) hand.clear();
    }

    private int getCardValue(Card card) {
        int cardValue;
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

    private int setHandValue(ArrayList<Card> playerHand, int handValue) {
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

    public boolean checkForWin(){
        boolean condition = false;
        if (didPlayerNatural21Win()) {
            results = "you WIN BIG!";
            player.updateBalance(natural21Payout());
            condition = true;
        } else if (isBust(getPlayerHandValue()))
            results = "you BUST, so lose.";
        else if (isBust(getDealerHandValue())) {
            results = "Dealer BUST, you win.";
            player.updateBalance(standardWin());
            condition =true;
        } else if (didPlayerTie(getPlayerHandValue(), getDealerHandValue())){
            results = "PUSH.";
            player.updateBalance(pushBet());
        } else if (didPlayerWin(getPlayerHandValue(), getDealerHandValue())) {
            results = "you win.";
            player.updateBalance(standardWin());
            condition = true;
        } else
            results = "you lose.";
        return condition;
    }

    private boolean didPlayerWin(int playerHandValue, int dealerHandValue){
        //compares the value to see which is greater
        return (playerHandValue >dealerHandValue);
    }

    private boolean didPlayerTie(int playerHandValue, int dealerHandValue){
        return (playerHandValue == dealerHandValue);
    }

    private boolean didPlayerNatural21Win(){
        return (isNatural21(getPlayerHandValue()) && getPlayerHand.size() == 2 && !(isNatural21(getDealerHandValue())));
    }

    private boolean isNatural21(int handValue){
        //checks to see if starting hand is a Natural 21
        return (handValue == 21);
    }

    private boolean isBust(int handValue){
        // checks if  handvalue is greater then 21
        return (handValue > 21);
    }

    private boolean isSoft16(){
        return (getDealerHandValue() < 17);
    }
    private double standardWin(){
        return this.bet * this.payout;
    }

    private double natural21Payout(){
        return this.bet * this.naturalBlackJackPayout;
    }

    private   double pushBet(){
        return bet;
    }

    protected ArrayList<ArrayList<Card>> getMembersInGame() {
        return membersInGame;
    }











   /*private void splitHand(ArrayList currentPlayer, ArrayList secondHand){
        secondHand.add(1, currentPlayer);
        currentPlayer.remove(1);
    }/**/
    /* private void splitBet(ArrayList currentHand, ArrayList secondHand){
        // raises bets to another pot of the same bet
        this.secondBet = this.bet;
        splitHand(currentHand, secondHand);
        // deals 2nd card to new hands
        dealFromShoe(currentHand);
        dealFromShoe(secondHand);
        // flag so no further splits can occur
    }/**/

    /* private boolean isSplittable(Card card1, Card card2){
        //checks to see if starting hand is splittable 2
        return (card1 == card2);
    }/**/

}