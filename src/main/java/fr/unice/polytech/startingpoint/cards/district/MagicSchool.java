package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;

public class MagicSchool extends DistrictD implements IWonder {
    private String description;

    public MagicSchool() {
        this.name = DistrictName.ECOLEDEMAGIE;
        this.color = Color.PURPLE;
        this.price = 6;
        this.description = "Pour la perception des revenus, l'école de magie est considérée comme un quartier de la couleur de votre choix, elle vous rapporte donc si vous êtes, Roi, Evêque, Marchand ou Condottiere.\n";
    }
    public void resetColor(){
        color = Color.PURPLE;
    }
    @Override
    public boolean isWonder() {
        return true;
    }

    @Override
    public void doAction(IAToWonder info) {
        Color color = info.getChoosenColorOfMagicSchool();
        if (color != null)
            this.color = color;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
