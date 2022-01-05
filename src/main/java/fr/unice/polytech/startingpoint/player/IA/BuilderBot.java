package fr.unice.polytech.startingpoint.player.IA;

public class BuilderBot extends IA{
    /**
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */
    public BuilderBot(String playerName) {
        super(playerName);
        bot = Bots.nonSpecified;
        strategyBot = StrategyBot.BUILDER_BOT;
    }
}