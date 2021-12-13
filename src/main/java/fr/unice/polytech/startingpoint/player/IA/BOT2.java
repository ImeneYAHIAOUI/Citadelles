package fr.unice.polytech.startingpoint.player.IA;

public class BOT2 extends IA{
    StrategieName strategieName = null;

    public BOT2(){
        super("BOT2");
        this.strategieName = StrategieName.onlyExpensiveCards;
    }
}
