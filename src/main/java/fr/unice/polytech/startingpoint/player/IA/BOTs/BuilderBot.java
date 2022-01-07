package fr.unice.polytech.startingpoint.player.IA.BOTs;

import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.StrategyBot;

// BOT de la version FINAL

public class BuilderBot extends IA {
    /**
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */
    public BuilderBot(String playerName) {
        super(playerName);
        strategyBot = StrategyBot.BUILDER_BOT;
    }
}
