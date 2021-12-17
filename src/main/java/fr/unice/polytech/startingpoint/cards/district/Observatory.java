package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.function.Predicate;
/*
this class is used to creat the observatory district
* **/
public class Observatory extends DistrictD implements IWonder {
    String description;

    public Observatory(){
        this.name = DistrictName.OBSERVATORY;
        this.color = Color.PURPLE;
        this.price = 5;
        this.description = "Si vous choisissez de piocher des cartes au début de" +
                " votre tour, vous en piochez trois, en choisissez " +
                "une et défaussez les deux autres.";
    }
    @Override
    public boolean isWonder() {
        return true;
    }

    @Override
    public String getDescription() {
        return this.description;
    }


    /**
     * action of wonder as mentioned in "description
     * @param info
     */
    @Override
    public void doAction(IAToWonder info) {
    }



}
