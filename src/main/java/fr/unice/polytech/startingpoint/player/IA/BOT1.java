package fr.unice.polytech.startingpoint.player.IA;

public class BOT1 extends IA{
    StrategieName strategieName = null;

    public BOT1(){
        super("BOT1");
        this.strategieName = StrategieName.onlyCheapCards;
    }
}
