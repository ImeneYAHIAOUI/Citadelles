package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.core.Initialization;
import fr.unice.polytech.startingpoint.heros.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HeroDecisionStandardTest {
    IA ia1 = null;
    IA ia2 = null;
    IA ia3 = null;
    List<IPlayer> players = null;
    HeroDeck heroes = null;
    List<HerosChoice> thoughPath = null;
    HeroDecisionStandard heroDecisionStandard = null;
    IDistrict district1 = null;
    IDistrict district2 = null;
    IDistrict district3 = null;
    IDistrict district4 = null;
    IDistrict district5 = null;
    IDistrict district6 = null;
    IDistrict district7 = null;
    IDistrict district8 = null;
    IHero hero = null;
    Random rand = null;

    private IDistrict addCards(int price, Color color, DistrictName nameOfCard){
        IDistrict district = null;
        try {
            district = new District(price,color,nameOfCard);
        } catch (CardException e) {
            e.printStackTrace();
        }
        return district;
    }

    @BeforeEach
    void setup(){
        this.ia1 = new IA("IA1");
        this.ia2 = new IA("IA2");
        this.ia3 = new IA("IA3");

        this.rand = new Random();

        this.heroDecisionStandard = new HeroDecisionStandard();
        this.players = new ArrayList<IPlayer>();
        this.players.add(this.ia1);
        this.players.add(this.ia2);
        this.players.add(this.ia3);
        this.thoughPath = new ArrayList<HerosChoice>();
        this.heroes = Initialization.heroeList();

        this.district1 = addCards(2,Color.YELLOW,DistrictName.MARCHE);
        this.district2 = addCards(2,Color.RED,DistrictName.MARCHE);
        this.district3 = addCards(2,Color.BLUE,DistrictName.MARCHE);
        this.district4 = addCards(2,Color.GREEN,DistrictName.MARCHE);
        this.district5 = addCards(2,Color.PURPLE,DistrictName.MARCHE);
        this.district6 = addCards(2,Color.YELLOW,DistrictName.MARCHE);
        this.district7 = addCards(2,Color.YELLOW,DistrictName.MARCHE);
        this.district8 = addCards(2,Color.YELLOW,DistrictName.MARCHE);

        this.heroes.add(new Assassin());
        this.heroes.add(new Bishop());
        this.heroes.add(new King());
        this.heroes.add(new Magician());
        this.heroes.add(new Merchant());
        this.heroes.add(new Thief());
    }

    @Test
    void testHeroDecision(){
        ia1.addGold(20);
        ia2.addGold(20);
        ia3.addGold(20);


        ia1.buildDistrict(district1);
        ia1.buildDistrict(district1);
        ia1.buildDistrict(district1);
        ia1.buildDistrict(district2);

        ia2.buildDistrict(district5);
        ia2.buildDistrict(district6);

        ia3.buildDistrict(district1);

        List<IDistrict> districtList = new ArrayList<IDistrict>();
        districtList.add(district1);
        districtList.add(district2);
        districtList.add(district1);
        ia1.getDistrict(districtList);

        assertEquals(3,this.ia1.getHand().size());

        this.rand = mock(Random.class);
        when(rand.nextFloat()).thenReturn((float) 0.1);
        this.ia1.setRole(this.heroDecisionStandard.heroDecision(this.ia1,players,heroes,thoughPath,rand));
        System.out.println(thoughPath);

        assertEquals(HeroName.King,ia1.getRole().getName());
    }
}