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
        //Display Dealt cards, 1 card down 
        do {
            IO.displayBlackJackHand(blackJack.getMembersInGame(), "Do you want to Hit or Stay");
            setPrompt();
            if (prompt.equalsIgnoreCase("HIT"));
                blackJack.hit(blackJack.playerHand);
            System.out.println(blackJack.playerHand.size());
            blackJack.createHandValues();
            if (blackJack.isBust(blackJack.getPlayerHandValue())) {
                prompt = "BUST";
                break;
            }
        } while (!prompt.equalsIgnoreCase( "STAY"));
        //Display flip hidden card
        while (blackJack.getDealerHandValue() < 17){
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


