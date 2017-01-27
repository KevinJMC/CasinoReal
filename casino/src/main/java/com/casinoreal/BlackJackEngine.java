package com.casinoreal;

/**
 * Created by randallcrame on 1/25/17.
 */
public class BlackJackEngine extends Game{
    Player player;
    BlackJack blackJack = new BlackJack();
    String prompt = " ";
    String results = "";
    double amount;

    BlackJackEngine(Player player){
        this.player = player;
    }
    public void setPrompt(){
        prompt = IO.getInputName();
    }

    private void setWagerAmount(){amount = IO.getWager();}

    private void setDisplay(){ IO.displayBlackJackWelcomeScreen();}



    protected void playerTurn(){
        int doubleFlag = 0;
        String message;
        do {
            if (blackJack.isNatural21(blackJack.getDealerHandValue())){
                IO.displayBlackJackHand(blackJack.getMembersInGame(),"Dealer has 21, press Enter.");
                IO.waitForEnter();
                break;
            }
            if (doubleFlag == 0)
                message = " Do you want to Hit, Stay or Double.";
            else
                message = " Do you want to Hit or Stay";
            IO.displayBlackJackHand(blackJack.getMembersInGame(), message);

            setPrompt();
            if (prompt.equalsIgnoreCase("DOUBLE") && doubleFlag < 1){
                blackJack.doubleDown(player);
                blackJack.dealFromShoe(blackJack.playerHand);
                break;
            }
            if (prompt.equalsIgnoreCase("HIT")) {
                blackJack.dealFromShoe(blackJack.playerHand);
                blackJack.createPlayerHandValue();
            }
            if (blackJack.isBust(blackJack.getPlayerHandValue())) {
                break;
            }
            doubleFlag = 1;
        } while (!(prompt.equalsIgnoreCase("STAY")));
    }
    protected void dealerTurn(){
        while (blackJack.isSoft16()) {
            if (blackJack.isBust(blackJack.getPlayerHandValue()))
                break;
            IO.displayBlackJackHand(blackJack.getMembersInGame(), "Hit Enter to continue");
            IO.waitForEnter();
            blackJack.dealFromShoe(blackJack.dealerHand);
            blackJack.createDealerHandValue();
        }
    }

    protected void compareToWin(){
            results = "...";
        if (blackJack.getPlayerHandValue() == 21 && blackJack.playerHand.size() == 2 && blackJack.getDealerHandValue() != 21) {
            results = "you WIN BIG!";
            player.updateBalance(blackJack.natural21Payout());
        } else if (blackJack.isBust(blackJack.getPlayerHandValue()))
            results = "you lose.";
        else if (blackJack.isBust(blackJack.getDealerHandValue())) {
            results = "you win.";
            player.updateBalance(blackJack.standardWin());
        } else if (blackJack.getDealerHandValue() == blackJack.getPlayerHandValue()){
            results = "PUSH.";
            player.updateBalance(blackJack.pushBet());
        } else if (blackJack.getDealerHandValue() > blackJack.getPlayerHandValue())
            results = "you lose.";
        else if (blackJack.getDealerHandValue() > blackJack.getPlayerHandValue() )
            results = "you win.";
        player.updateBalance(blackJack.standardWin());
    }

    public void startGame(){
        blackJack.setTable();
        boolean notExit;
        do {
            setDisplay();
            setWagerAmount();
            blackJack.setWager(player , amount);
            blackJack.dealToPlayers();
            playerTurn();
            dealerTurn();
            compareToWin();
            IO.displayBlackJackHand(blackJack.getMembersInGame(),
                    "In the end " + results + " Do you want to play again? Y/N");
            blackJack.clearHands();
            blackJack.shuffleShoeWhenLow();
            notExit = IO.getInputSlotsPlayAgain();
        } while (notExit);
    }

    @Override
    public boolean checkForWin() {
        return true;
    }
}


