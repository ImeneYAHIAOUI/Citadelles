package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Observatory extends DistrictD implements IWonder {
    IPlayer player;
    String description;
    public Predicate<IDistrict> choosencard(District district) {
        Predicate<IDistrict> choiceD = d -> d.getDistrictName().equals(district.getDistrictName());
        return choiceD;
    }
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

    /**
     * action of wonder as mentioned in "description
     * @param info
     */
    @Override
    public void doAction(infoaction info) {
    }


    @Override
    public String getDescription() {
        return this.description;
    }
}
