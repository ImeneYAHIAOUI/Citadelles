package fr.unice.polytech.startingpoint.cards;

public class courtofmiracles implements IWonder {
    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public Color getColor() {
        return Color.PURPLE;
    }

    @Override
    public DistrictName getDistrictName() {
        return DistrictName.LACOURDESMIRACLES;
    }

    @Override
    public boolean isWonder() {
        return true;
    }

    @Override
    public void doAction() {

    }

    @Override
    public void effectOfAction() {

    }
}
