package com.casinoreal;

/**
 * Created by randallcrame on 1/25/17.
 */
public class BlackJackEngine {
    Player player;
    BlackJack blackJack = new BlackJack();
    String prompt;

    BlackJackEngine(Player player){
        this.player = player;
    }
    public void setPrompt(){
        prompt = IO.getInputName();
    }

    public void setTable (){
        blackJack.joinMembersInGame();
    }

    public void runRound(){
        blackJack.setWager(player ,Integer.getInteger(prompt));
        blackJack.dealToPlayers();
        blackJack.createHandValues(); 
        //Display Dealt cards, 1 card down 
        do {
            setPrompt();
            if (prompt == "HIT")
                blackJack.hit(blackJack.playerHand);
            blackJack.setHandValue(blackJack.playerHand, blackJack.getPlayerHandValue());
        } while (prompt != "STAY");

        while (blackJack.getDealerHandValue() < 17){
             blackJack.hit(blackJack.dealerHand);
             blackJack.setHandValue(blackJack.dealerHand,blackJack.getDealerHandValue())
        }
        blackJack.compare();

    }


}
