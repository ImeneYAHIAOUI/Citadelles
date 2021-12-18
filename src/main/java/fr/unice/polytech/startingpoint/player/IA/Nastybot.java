package fr.unice.polytech.startingpoint.player.IA;

public class Nastybot  extends IA{
    public Nastybot(String playerName) {
        super(playerName);
        bot = Bots.random;
    }
}
