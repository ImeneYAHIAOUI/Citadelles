package fr.unice.polytech.startingpoint.cards;

public class District extends DistrictD{

    public District(int Price, Color color, DistrictName name) {
        this.name=name;
        this.color=color;
        this.price=price;
    }

    @Override
    public String toString(){

        return "price: "+price+" name: "+name+" color: "+color;
    }


    @Override
    public boolean isWonder() {
        return false;
    }
}
