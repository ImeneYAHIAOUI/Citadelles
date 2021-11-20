package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Player;

import java.util.List;

public class courtofmiracles implements IWonder {
    private final String description;

    public courtofmiracles(int price, Color color, DistrictName name, String description) {
        this.name = DistrictName.LACOURDESMIRACLES;
        this.color = Color.PURPLE;
        this.price = 2;
        this.description="Pour le décompte final des points, la cour des miracles est considérée comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacité si vous avez construit la cour des miracles au dernier tour de jeu.";
    }
    @Override
    public boolean isWonder() {
        return true;
    }






    @Override
    public void doAction(infoaction info) {



    }

    @Override
    public void effectOfAction() {

    }

@Override
    public String getDescription() {
        return this.description;
    }

}
