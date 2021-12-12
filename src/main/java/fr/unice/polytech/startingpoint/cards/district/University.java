package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;

public class University extends DistrictD implements IWonder {
    private final String description;

    public University(){
        this.name = DistrictName.UNIVERSITY;
        this.color = Color.PURPLE;
        this.price = 6;
        this.description = "Cette réalisation de prestige (nul n'a jamais compris à quoi pouvait bien servir une université) " +
                "coûte six pièces d'or à bâtir mais vaux huit points dans le décompte de fin de partie.\n";
    }

    @Override
    public boolean isWonder() {
        return true;
    }

    @Override
    public void doAction(IAToWonder info) {
        this.price = 8;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
