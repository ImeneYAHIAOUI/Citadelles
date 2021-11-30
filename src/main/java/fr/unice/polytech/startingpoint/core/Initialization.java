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

        // YELLOW
        for(int i = 0; i < 5; i++) {
            try {
                districtDeck.add(new District(3, Color.YELLOW, DistrictName.MANOIR));
            }catch (CardException e){
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 4; i++) {
            try {
                districtDeck.add(new District(4,Color.YELLOW,DistrictName.CHATEAU));
            } catch (CardException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 2; i++) {
            try {
                districtDeck.add(new District(5,Color.YELLOW,DistrictName.PALAIS));
            } catch (CardException e) {
                e.printStackTrace();
            }
        }

        //Green
        for(int i = 0; i < 3; i++) {
            try {
                districtDeck.add(new District(2,Color.GREEN,DistrictName.ECHAPPE));
            } catch (CardException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 5; i++) {
            try {
                districtDeck.add(new District(1,Color.GREEN,DistrictName.TAVERNE));
            } catch (CardException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 4; i++) {
            try {
                districtDeck.add(new District(2,Color.GREEN,DistrictName.MARCHE));
            } catch (CardException e) {
                e.printStackTrace();
            }
        }
        //blue
        for(int i = 0; i < 1; i++) {
            try {
                districtDeck.add(new District(5,Color.BLUE,DistrictName.MARCHE));
            } catch (CardException e) {
                e.printStackTrace();
            }
        }


        //Purple Wonder
        districtDeck.add(new CourtOfMiracles());
        districtDeck.add(new Laboratory());
        districtDeck.add(new Manufacture());

        Collections.shuffle(districtDeck);

        return districtDeck;
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
