package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;

/*
 * this class in used to creat the dungeon district
 */
public class Dungeon extends DistrictD implements IWonder {
    private String description;

    public Dungeon() {
        this.name = DistrictName.DONGEON;
        this.color = Color.PURPLE;
        this.price = 3;
        this.description = "Le Donjon ne peut pas " +
                "être détruit par le " +
                "Condottière.\n";
    }

    @Override
    public void doAction(IAToWonder info) {
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isWonder() {
        return true ;
    }
}
