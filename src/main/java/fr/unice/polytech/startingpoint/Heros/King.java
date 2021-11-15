package fr.unice.polytech.startingpoint.Heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

public class King implements IHero{
    private Hero name;
    private Color color;

    public King() {
        name = Hero.King;
        color = Color.YELLOW;
    }


    @Override
    public Hero getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void doAction() {}

    @Override
    public void doAction(IA player) {
        player.setKing();

    }

    @Override
    public void doAction(IA player1, IA player2) {}


}
