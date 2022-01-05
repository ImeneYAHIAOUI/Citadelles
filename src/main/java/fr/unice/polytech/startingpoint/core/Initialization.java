package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.district.*;
import fr.unice.polytech.startingpoint.heros.*;
import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.heros.character.*;

import fr.unice.polytech.startingpoint.player.IA.*;
import fr.unice.polytech.startingpoint.player.IPlayer;


import java.util.*;

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
        addCards(districtDeck, 3,3,Color.GREEN,DistrictName.COMPTOIR);
        addCards(districtDeck, 3,4,Color.GREEN,DistrictName.PORT);
        addCards(districtDeck, 2,5,Color.GREEN,DistrictName.HOTELDEVILLE);
        //blue
        addCards(districtDeck, 2,5,Color.BLUE,DistrictName.CATHEDRALE);
        addCards(districtDeck, 3,1,Color.BLUE,DistrictName.TEMPLE);
        addCards(districtDeck,4 ,2,Color.BLUE,DistrictName.EGLISE);
        addCards(districtDeck,3 ,3,Color.BLUE,DistrictName.MONASTERE);
        //RED
        addCards(districtDeck, 3,1,Color.RED,DistrictName.TOURDEGUET);
        addCards(districtDeck, 3,2,Color.RED,DistrictName.PRISON);
        addCards(districtDeck, 3,3,Color.RED,DistrictName.CASERNE);
        addCards(districtDeck, 2,5,Color.RED,DistrictName.FORTERESSE);
        //Purple Wonder
        districtDeck.add(new MiracleCourt());
        districtDeck.add(new Laboratory());
        districtDeck.add(new Manufacture());
        districtDeck.add(new Observatory());
        districtDeck.add(new Library());
        districtDeck.add(new Dungeon());//il y a 2 dongeon
        districtDeck.add(new Dungeon());
        districtDeck.add(new Drocoport());
        districtDeck.add(new University());
        districtDeck.add(new MagicSchool());
        districtDeck.add(new Cemetery());

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
    public static HeroDeck heroeList(int number){
        HeroDeck heroes = new HeroDeck();
        heroes.add(new King());
        heroes.add(new Merchant());
        heroes.add(new Magician());
        if(number>=4){
            heroes.add(new Assassin());
        }
        heroes.add(new Thief());
        heroes.add(new Bishop());
        heroes.add(new Architect());
        heroes.add(new Condottiere());
        return heroes;
    }
    public static int treasureOfTheGame(){
        return 30;
    }
    public static List<IPlayer> playersSimulation1() {
        List<IPlayer> players = new ArrayList<>();
        players.add(new NeutralBot("Player" + 1));
        players.add(new RandomBot("Player" + 2));
        return players;
    }
    public static List<IPlayer> playersSimulation2() {
        List<IPlayer> players = new ArrayList<>();
        players.add(new NiceBot("Player" + 1));
        players.add(new NiceBot("Player" + 2));
        return players;
    }

    public static List<IPlayer> numberOfPlayers() {
        List<IPlayer> players = new ArrayList<>();
        Random random = new Random();
        int numberOfPlayers = random.nextInt(5) + 4;

        int what = 0;

        for(int i = 0; i < numberOfPlayers; i++){

            what = random.nextInt(5);

            if(what == 0){
                players.add(new Nastybot("Player" + i));

            }
            if(what == 1) {
                players.add(new NiceBot("Player" + (i)));

            }

            if(what == 2) {
                players.add(new RandomBot("Player" + (i)));

            }

            if(what == 3) {
                players.add(new NeutralBot("Player" + (i)));

            }
<<<<<<< HEAD
            if(what==4){
                players.add(new BuilderBot("Player" + (i)));

            }


=======
            if(what == 4) {
                players.add(new BuilderBot("Player" + (i)));

            }
>>>>>>> a1338ee63167f2336e1a7e7494fadaed38e7ac64
        }
        return players;
    }


}
