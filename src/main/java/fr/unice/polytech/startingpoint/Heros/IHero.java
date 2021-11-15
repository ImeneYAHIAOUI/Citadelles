package fr.unice.polytech.startingpoint.Heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

public interface IHero {
    public Hero getName();
    public Color getColor();
    public void doAction();
    public void doAction(IA player);
    public void doAction(IA player1, IA player2);
}
