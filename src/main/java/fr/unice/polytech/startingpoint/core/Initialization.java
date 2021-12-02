package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.cards.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public abstract class Initialization {

    /**
     * Initialization of the district deck
     * @return List<District>
     */
    public static List<IDistrict> districtList(){
        List<IDistrict> districtDeck = new ArrayList<IDistrict>();

        //yellow
        addCards(districtDeck,5,3,Color.YELLOW,DistrictName.MANOIR);
        addCards(districtDeck, 4,4,Color.YELLOW,DistrictName.CHATEAU);
        addCards(districtDeck, 2,5,Color.YELLOW,DistrictName.PALAIS);
        //green
        addCards(districtDeck, 3,2,Color.GREEN,DistrictName.ECHAPPE);
        addCards(districtDeck, 5,1,Color.GREEN,DistrictName.TAVERNE);
        addCards(districtDeck, 4,2,Color.GREEN,DistrictName.MARCHE);
        //blue
        addCards(districtDeck, 1,5,Color.BLUE,DistrictName.CATHEDRALE);

        //Purple Wonder
        districtDeck.add(new CourtOfMiracles());
        districtDeck.add(new Laboratory());
        districtDeck.add(new Manufacture());

        Collections.shuffle(districtDeck);

        return districtDeck;
    }
    public static void  addCards(List<IDistrict> districtDeck,int numberOfCards,int price,Color color,DistrictName nameOfCard){
        for(int i = 0; i < numberOfCards; i++) {
            try {
                districtDeck.add(new District(price,color,nameOfCard));
            } catch (CardException e) {
                e.printStackTrace();
            }
        }

    }


    /** add chosen hero to heroList
     *
     * @return heroList
     */
    public static HeroDeck heroeList(){
        HeroDeck heroes = new HeroDeck();
        heroes.add(new King());
        heroes.add(new Merchant());
        heroes.add(new Magician());
        heroes.add(new Assassin());
        return heroes;
    }
    public static int treasureOfTheGame(){
        return 30;
    }
}
