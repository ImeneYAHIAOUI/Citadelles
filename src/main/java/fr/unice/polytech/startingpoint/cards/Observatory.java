package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.player.IPlayer;

public class Observatory extends DistrictD implements IWonder{
    IPlayer player;
    String description;
    public Observatory(){
        this.name = DistrictName.OBSERVATORY;
        this.color = Color.PURPLE;
        this.price = 5;
        this.description = "Si vous choisissez de piocher des cartes au début de votre tour, vous en piochez trois, en choisissez une et défaussez les deux autres.";
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
        return null;
    }
}
