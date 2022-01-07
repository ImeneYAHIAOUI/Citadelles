package fr.unice.polytech.startingpoint.player.IA.BOTs;


import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.StrategyBot;

public class Nastybot extends IA {
    /**
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */
    public Nastybot(String playerName){
        super(playerName);
        strategyBot = StrategyBot.NASTY_BOT;
    }
}
