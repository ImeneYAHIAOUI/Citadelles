package fr.unice.polytech.startingpoint.player.IA;

public class Nastybot extends IA {
    /**
     * @param playerName the IA object is constructed the same way as a Player object,
     *                   so we also only need the name of the player here.
     */
    public Nastybot(String playerName){
        super(playerName);
        bot = Bots.nonSpecified;
        niceNastyStrategy = NiceNastyBot.NASTY_BOT;
    }
}
