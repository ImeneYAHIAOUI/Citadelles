package fr.unice.polytech.startingpoint.cards.district;

import fr.unice.polytech.startingpoint.cards.CardException;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.DistrictD;
import fr.unice.polytech.startingpoint.cards.DistrictName;


public class District extends DistrictD {

    public District(int price, Color color, DistrictName name) throws CardException {
        if(price < 0)
            throw new CardException("District cannot have a price below 0");

        this.name=name;
        this.color=color;
        this.price=price;
    }

    @Override
    public boolean isWonder() {
        return false;
    }
}
