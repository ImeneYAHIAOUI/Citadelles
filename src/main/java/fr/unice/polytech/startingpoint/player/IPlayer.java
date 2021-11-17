package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.Heros.Hero;
import fr.unice.polytech.startingpoint.Heros.HeroDeck;
import fr.unice.polytech.startingpoint.Heros.IHero;
import fr.unice.polytech.startingpoint.cards.*;

import java.util.*;

public interface IPlayer {

    void chooseHero(HeroDeck heroes);
    void activateHero(IHero hero);
    void doAction();


}
