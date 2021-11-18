package fr.unice.polytech.startingpoint.Heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Player;

import java.util.List;
import java.util.Optional;

public interface IHero {
    public Hero getName();
    public Color getColor();
    public void doAction(List<District> districts, IPlayer player);
    //public void doAction(IA player);
    //public void doAction(IA player1, IA player2);
}
