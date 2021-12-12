package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;

public class Drocoport extends DistrictD implements IWonder {
    private final String description;

    public Drocoport(){
        this.name = DistrictName.DROCOPORT;
        this.color = Color.PURPLE;
        this.price = 6;
        this.description = "Cette réalisation de prestige (on n'a pas vu de dragon dans le Royaume depuis bientôt mille ans) coûte six pièces d'or à bâtir mais vaut huit points dans le décompte de fin de partie.";
    }
    @Override
    public void doAction(IAToWonder info) {
        this.price=8;
        info.getplayer().addScore(2);

    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isWonder() {
        return true;
    }
}
