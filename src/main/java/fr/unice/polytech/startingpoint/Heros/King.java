package fr.unice.polytech.startingpoint.Heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.Player;

public class King implements IHero{
    private Hero name;
    private Color color;

    public King() {
        name = Hero.King;
        color = Color.YELLOW;
    }


    @Override
    public Hero getName() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void doAction() {}

    @Override
    public void doAction(Player player) {
        player.setKing();

    }

    @Override
    public void doAction(Player player1, Player player2) {}


}
