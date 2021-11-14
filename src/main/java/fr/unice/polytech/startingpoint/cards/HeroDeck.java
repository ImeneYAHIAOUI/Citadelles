package fr.unice.polytech.startingpoint.cards;
import java.util.List;

public class HeroDeck {
    private List<Hero>  HeroList ;

    public HeroDeck(List<Hero> HeroList ) {
        this.HeroList=HeroList;

    }
    public List<Hero> getHeroList() {
        return HeroList;
    }

    public List<Hero> removeHero( Hero hero) {
        List<Hero> NHerolist = new List<Hero>();
           NHerolist.remove(hero);
            return NHerolist;
    }
    public List<Hero> putHero( Hero hero){
        List<Hero> N2Herolist = new List<Hero>();
        N2Herolist.add(hero);
        return N2Herolist;
    }
}
