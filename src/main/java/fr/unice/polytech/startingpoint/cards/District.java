package fr.unice.polytech.startingpoint.cards;

public class District implements IDistrict{
    private int price;
    private Color color;
    private DistrictName name;

    public District(int price, Color color, DistrictName name) {
        this.price = price;
        this.color = color;
        this.name = name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean isWonder() {
        return false;
    }

    @Override
    public DistrictName getDistrictName(){
        return this.name;
    }

    @Override
    public String toString(){
        return "price: "+price+" name: "+name+" color: "+color;
    }
}
