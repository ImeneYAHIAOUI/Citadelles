package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;
/*
 * this class is used to creat the laboratory district
 */

public class Laboratory extends DistrictD implements IWonder {
    private String description;

    public Laboratory() {
        this.name = DistrictName.LABORATOIRE;
        this.color = Color.PURPLE;
        this.price = 5;
        this.description="Une fois par tour, vous pouvez vous défausser d'une carte quartier de votre main et recevoir une pièce d'or en contrepartie";
    }
    /**
     * action of wonder as mentioned in "description
     * @param info
     */
    @Override
    public void doAction(IAToWonder info) {

        if (info.getDistrictremove() != null) {
            info.getplayer().getHand().remove(info.getDistrictremove());
                info.getplayer().addGold(1);
                info.getTreasure().removeGold(1);

        }
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
