package fr.unice.polytech.startingpoint.cards;



public  abstract class DistrictD implements  IDistrict {
    protected DistrictName name;
    protected Color color;
    protected int price;

    @Override
    public DistrictName getDistrictName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color ;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "price: "+price+" name: "+name+" color: "+color;
    }
}


