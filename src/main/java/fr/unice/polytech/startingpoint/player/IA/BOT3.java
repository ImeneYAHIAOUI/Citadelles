package fr.unice.polytech.startingpoint.player.IA;

public class BOT3 extends IA{
    StrategieName strategieName = null;

    public BOT3(){
        super("BOT3");
        this.strategieName = StrategieName.randomChoices;
    }
}
