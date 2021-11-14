package fr.unice.polytech.startingpoint.Heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.Player;

public interface IHero {
    public Hero getName();
    public Color getColor();
    public void doAction();
    public void doAction(Player player);
    public void doAction(Player player1,Player player2);
}
