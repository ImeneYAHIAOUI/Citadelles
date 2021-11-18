package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;

public abstract class Hero implements IHero {
    protected HeroName name;
    protected Color color;
    protected int rank;

    @Override
    public HeroName getName() {
        return name;
    }

     @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getRank() {
        return this.rank;
    }
}
