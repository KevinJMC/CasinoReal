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
                break;
            }
        } while (!(prompt.equalsIgnoreCase( "STAY")));

        while (blackJack.getDealerHandValue() < 17){
            if (blackJack.isBust(blackJack.getPlayerHandValue()))
                break;
            System.out.println(blackJack.getDealerHandValue());
            IO.displayBlackJackHand(blackJack.getMembersInGame(), "Hit Enter to continue");
            IO.waitForEnter();
            blackJack.hit(blackJack.dealerHand);
            blackJack.setHandValue(blackJack.dealerHand,blackJack.getDealerHandValue());
            blackJack.createHandValues();
        }


        if (blackJack.isBust(blackJack.getPlayerHandValue())){
            results = "you lose.";
        }
        else {
            results = (blackJack.compare(blackJack.getPlayerHandValue(), blackJack.getDealerHandValue(), player)) ?  "you Win!" : "Push";
        }
        IO.displayBlackJackHand(blackJack.getMembersInGame(), "In the end " + results + " Do you want to play again?");
    }

}


