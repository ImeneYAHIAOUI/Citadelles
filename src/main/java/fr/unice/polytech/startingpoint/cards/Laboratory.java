package fr.unice.polytech.startingpoint.cards;

public class Laboratory extends DistrictD implements IWonder {
    private String description;

    public Laboratory() {
        this.name = DistrictName.LABORATOIRE;
        this.color = Color.PURPLE;
        this.price = 5;
        this.description="Une fois par tour, vous pouvez vous défausser d'une carte quartier de votre main et recevoir une pièce d'or en contrepartie";
    }

    @Override
    public void doAction(infoaction info) {
        info.player1.getHand().remove(info.index);
      //  info.player1.goldWon(1);
    }

    @Override
    public void effectOfAction() {

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
