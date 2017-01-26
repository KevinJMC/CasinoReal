package com.casinoreal;

/**
 * Created by randallcrame on 1/25/17.
 */
public class BlackJackEngine {
    Player player;
    BlackJack blackJack = new BlackJack();
    String prompt = " ";
    double amount;
    BlackJackEngine(Player player){
        this.player = player;
    }
    public void setPrompt(){
        prompt = IO.getInputName();
    }
    public void setWagerAmount(){amount = IO.getWager();}

    public void setTable (){
        blackJack.joinMembersInGame();
    }

    public void runRound(){
        setTable();
        IO.displayBlackJackWelcomeScreen();
        setWagerAmount();
        blackJack.setWager(player , amount);
        blackJack.dealToPlayers();
        blackJack.createHandValues();
        IO.displayBlackJackHand(blackJack.getMembersInGame(), "Do you want to Hit or Stay");
        //Display Dealt cards, 1 card down 
        do {
            setPrompt();
            if (prompt == "HIT")
                blackJack.hit(blackJack.playerHand);
            blackJack.setHandValue(blackJack.playerHand, blackJack.getPlayerHandValue());
            if (blackJack.isBust(blackJack.getPlayerHandValue())) {
                prompt = "BUST";
                break;
            }
        } while (prompt != "STAY");
        //Display flip hidden card
        while (blackJack.getDealerHandValue() < 17 || prompt == "BUST"){
             blackJack.hit(blackJack.dealerHand);
             blackJack.setHandValue(blackJack.dealerHand,blackJack.getDealerHandValue());
        }
        if (blackJack.isBust(blackJack.getPlayerHandValue())){
            //Display You Bust, You Lose;
        }
        else
            blackJack.compare(blackJack.getPlayerHandValue(),blackJack.getDealerHandValue(), player);
    }

}


