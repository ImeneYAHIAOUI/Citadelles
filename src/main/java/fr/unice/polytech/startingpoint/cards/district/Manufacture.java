package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IPlayer;
/*
* this class is used to creat the manufacture district
*/

public class Manufacture extends DistrictD implements IWonder {
            IAToWonder info;
            String description;
            int gold=0;

       public Manufacture() {
        this.name = DistrictName.MANUFACTURE;
        this.color = Color.PURPLE;
        this.price = 5;
        this.description = "Une fois par tour, vous pouvez payer trois pieces d'or pour piocher trois cartes";
    }



    @Override
    public boolean isWonder() {
        return true ;
    }
    /**
     * action of wonder as mentioned in "description
     * @param info
     */
    @Override
    public void doAction(IAToWonder info) {
        IPlayer player = info.getplayer();
            player.getDistrict(info.getdistrictdeck().giveDistrict(3));
            player.removeGold(3);
            info.getTreasure().addToTreasure(3);
    }


    @Override
    public String getDescription() {
        return this.description;
    }
}