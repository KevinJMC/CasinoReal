package com.casinoreal;

/**
 * Created by randallcrame on 1/25/17.
 */
public class BlackJackEngine {
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
        blackJack.createDealerHandValue();
        blackJack.createPlayerHandValue();
        do {
            if (blackJack.isNatural21(blackJack.getDealerHandValue())){
                IO.displayBlackJackHand(blackJack.getMembersInGame(),"Dealer has 21, press Enter.");
                IO.waitForEnter();
                break;
            }
            IO.displayBlackJackHand(blackJack.getMembersInGame(), "Do you want to Hit or Stay");

            setPrompt();
            if (prompt.equalsIgnoreCase("HIT"))
                blackJack.hit(blackJack.playerHand);
            blackJack.createPlayerHandValue();
            if (blackJack.isBust(blackJack.getPlayerHandValue())) {
                break;
            }
        } while (!(prompt.equalsIgnoreCase("STAY")));
    }
    protected void dealerTurn(){
        while (blackJack.getDealerHandValue() < 17) {
            if (blackJack.isBust(blackJack.getPlayerHandValue()))
                break;
            IO.displayBlackJackHand(blackJack.getMembersInGame(), "Hit Enter to continue");
            IO.waitForEnter();
            blackJack.hit(blackJack.dealerHand);
            blackJack.setHandValue(blackJack.dealerHand, blackJack.getDealerHandValue());
            blackJack.createDealerHandValue();
        }
    }

    protected void compareToWin(){
        if (blackJack.isBust(blackJack.getPlayerHandValue()))
            results = "you lose.";
        if (blackJack.getDealerHandValue() > blackJack.getPlayerHandValue())
            results = "you lose.";
        else
            results = (blackJack.compare(blackJack.getPlayerHandValue(), blackJack.getDealerHandValue(), player))
                    ? "you Win!" : "Push";
         }

    public void runRound(){
        blackJack.joinMembersInGame();
        boolean notExit;
        do {
            setDisplay();
            setWagerAmount();
            blackJack.setWager(player , amount);
            blackJack.dealToPlayers();
            blackJack.createPlayerHandValue();
            playerTurn();
            dealerTurn();
            compareToWin();
            IO.displayBlackJackHand(blackJack.getMembersInGame(),
                    "In the end " + results + " Do you want to play again? Y/N");
            blackJack.clearHands();
            notExit = IO.getInputSlotsPlayAgain();
        } while (notExit);
    }


}


