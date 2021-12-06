package fr.unice.polytech.startingpoint.cards;

import java.util.List;

public class Dongeon  extends DistrictD implements IWonder{
    private String description;

    public Dongeon() {
        this.name = DistrictName.DONGEON;
        this.color = Color.PURPLE;
        this.price = 3;
        this.description = "Le Donjon ne peut pas " +
                "être détruit par le " +
                "Condottière.\n";
    }

    @Override
    public void doAction(infoaction info) {
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
