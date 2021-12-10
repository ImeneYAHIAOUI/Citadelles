package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IPlayer;

public class Cemetry extends DistrictD implements IWonder {
    private String description;

    public Cemetry() {
        this.name = DistrictName.CEMETRY;
        this.color = Color.PURPLE;
        this.price = 5;
        this.description = "Lorsque le Condottière " +
                "détruit un quartier, vous  " +
                "pouvez payer une " +
                "pièce d'or pour le " +
                "reprendre dans votre " +
                "main. Vous ne pouvez " +
                "pas faire cela si vous " +
                "êtes vous-même " +
                "Condottiere.\n";
    }

    @Override
    public void doAction(IAToWonder info) {
        IDistrict card=info.getCard();
        IPlayer currentPlayer= info.getplayer();
        currentPlayer.getHand().add(card);
        info.getdistrictdeck().getDistrictList().remove(card);
        currentPlayer.removeGold(1);
        info.getTreasure().addToTreasure(1);
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
