package fr.unice.polytech.startingpoint.cards;

public class Laboratory implements IWonder {
    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public Color getColor() {
        return Color.PURPLE;
    }

    @Override
    public DistrictName getDistrictName() {
        return DistrictName.LABORATOIRE;
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
