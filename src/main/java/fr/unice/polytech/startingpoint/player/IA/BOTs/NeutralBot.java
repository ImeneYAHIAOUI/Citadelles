package fr.unice.polytech.startingpoint.player.IA.BOTs;

import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.StrategyBot;

public class NeutralBot extends IA {
    /**
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */
    public NeutralBot(String playerName) {
        super(playerName);
        strategyBot = StrategyBot.NEUTRAL_BOT;
    }

}
