package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.player.IPlayer;

public class Manufacture extends DistrictD implements IWonder {
            infoaction info;
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
    /** action of wonder as mentioned in "description"*/
    @Override
    public void doAction(infoaction info) {

        IPlayer player = info.getplayer();
            player.getDistrict(info.getattributeHand());
            player.removeGold(3);
            info.getTreasure().addToTreasure(3);

    }


    @Override
    public String getDescription() {
        return this.description;
    }
}