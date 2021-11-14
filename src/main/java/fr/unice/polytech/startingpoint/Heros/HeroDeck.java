package fr.unice.polytech.startingpoint.Heros;

import java.util.List;

public class HeroDeck {
   private List<IHero>  HeroList ;

    public HeroDeck(List<IHero> HeroList ) {
        this.HeroList=HeroList;
    }

    public List<IHero> getHeroList() {
        return HeroList;
    }

    public void removeHero( IHero hero) {
        HeroList.remove(hero);
    }

    public void putHero( IHero hero){
        HeroList.add(hero);
    }
}
