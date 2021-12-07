package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Observatory extends DistrictD implements IWonder{
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

    @Override
    public void doAction(infoaction info) {
        int i;
        DistrictDeck districtdeck = info.getdistrictdeck();
        info.getplayer();
        List<IDistrict> AffordableDistricts = info.gettriocard().stream().filter(choosencard(info.getchoice())).collect(Collectors.toList());
        info.getplayer().getDistrict(AffordableDistricts);
        for (i = 0;i < info.gettriocard().size(); i++) {
            if (info.gettriocard().get(i) != info.getchoice()) {
               districtdeck.getDistrictList().add(info.gettriocard().get(i));

            }


        }
    }


    @Override
    public String getDescription() {
        return this.description;
    }
}
