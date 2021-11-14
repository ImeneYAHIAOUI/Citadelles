package fr.unice.polytech.startingpoint.Heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.Player;

public class Merchant implements IHero{
    private Hero name;
    private Color color;

    public Merchant(){
        name = Hero.Merchant;
        color = Color.GREEN;
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
    public void doAction() {

    }

    @Override
    public void doAction(Player player) {

    }

    @Override
    public void doAction(Player player1, Player player2) {

    }
}
