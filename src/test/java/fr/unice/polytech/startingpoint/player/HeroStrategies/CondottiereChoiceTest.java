package fr.unice.polytech.startingpoint.player.HeroStrategies;

import fr.unice.polytech.startingpoint.cards.CardException;
import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.district.District;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.heros.character.Condottiere;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IA.BOTs.NeutralBot;
import fr.unice.polytech.startingpoint.player.IA.Strategies.CondottiereChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CondottiereChoiceTest {
    IA player1 ;
    IA player2 ;
    IA player3 ;
    IA player4 ;
    Condottiere condottiere;
    IAToHero information;
    List<Integer>scores;
    Treasure tresor;
    IDistrict district1;
    IDistrict district2;
    IDistrict district3;
    IDistrict district4;
    IDistrict district5;
    IDistrict district6;
    IDistrict district7;
    CondottiereChoice choice;



    @BeforeEach
    void setup(){
        tresor=new Treasure(17);
        condottiere=new Condottiere();
        player1=new NeutralBot("sophie");
        player1.setRole(condottiere);
        player1.addGold(15);
        player2=new NeutralBot("mael");
        player3=new NeutralBot("raphael");
        player4=new NeutralBot("pierre");
        information = new IAToHero();
        player2.setScore(15);
        player3.setScore(10);
        player4.setScore(4);
        scores =List.of(25,9,4);
        information.setScores(scores);
        information.setCurrentPlayer(player1);
      choice= new CondottiereChoice();

        try {
            district1 = new District(1, Color.YELLOW,DistrictName.MANOIR);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district2 = new District(5,Color.YELLOW,DistrictName.PALAIS);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district3 = new District(2,Color.BLUE,DistrictName.EGLISE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district4 = new District(5,Color.BLUE,DistrictName.CATHEDRALE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district5 = new District(5,Color.RED,DistrictName.FORTERESSE);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district6 = new District(4,Color.GREEN,DistrictName.PORT);
        } catch (CardException e) {
            e.printStackTrace();
        }
        try {
            district7 = new District(3,Color.PURPLE,DistrictName.DONGEON);
        } catch (CardException e) {
            e.printStackTrace();
        }
        information.setPlayers(List.of(player2,player3,player4));

        information.setPlayersName(List.of("mael","raphael","pierre"));

    }
    @Test
    void makeChoiceTest(){// il attaque le joeur qui a construit plus que 6 quartiers et qui est le plus avancé au niveau du score
        player1.setScore(10);
        information.setPlayerBuiltDistricts(List.of(
                List.of(district1,district2,district4,district3,district5,district6,district7)
                ,List.of(district1,district7),
                List.of(district7,district6,district3)));
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),player2);
        assertEquals(information.getCurrentPlayer().getCardDestroyedByCondottiere(),district1);

    }
    @Test
    void makeChoiceTest1(){//les joeurs sont loin de la fin du jeu
        player1.setScore(16);
        information.setPlayerBuiltDistricts(List.of(
                List.of(district1,district7),
                List.of(district7,district6,district3)));
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),player2);

    }
    @Test
    void makeChoiceTest_1(){//les joeurs sont loin de la fin du jeu
        player1.setScore(16);
        information.setPlayerBuiltDistricts(List.of(
                List.of(district7),
                List.of(district7,district6,district3)));
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),null);

    }
    @Test
    void makeChoiceTest2(){//il n' pas assez d'or
        player1.setScore(16);
        information.setPlayerBuiltDistricts(List.of(
                List.of(district1,district2,district4,district3,district5,district6,district7)
                ,List.of(district1,district7),
                List.of(district7,district6,district3)));
        player1.removeGold(15);
        assertEquals(player1.getGold(),0);
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),player2);
    }
    @Test
    void makeChoiceTest_2(){//il n' pas assez d'or
        player1.setScore(16);
        information.setPlayerBuiltDistricts(List.of(
                List.of(district2,district4,district3,district5,district6,district7)
                ,List.of(district7),
                List.of(district7,district6,district3)));
        player1.removeGold(15);
        assertEquals(player1.getGold(),0);
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),null);
    }
    @Test
    void makeChoiceTest3() {//il est le plus avancé il attaque personne
        player1.setScore(30);
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),null);

    }
    @Test
    void makeChoiceTest4(){//il peut pas détruire la dongeon
        player1.setScore(16);
        information.setPlayerBuiltDistricts(List.of(
                List.of(district7)
                ,List.of(district7),
                List.of(district7,district6,district3)));
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),null);
    }
    @Test
    void makeChoiceTest_4(){//il peut pas détruire la dongeon
        player1.setScore(16);
        information.setPlayerBuiltDistricts(List.of(
                List.of(district7)
                ,List.of(district1,district7),
                List.of(district7,district6,district3)));
        choice.makeChoice(information);
        assertEquals(information.getChosenPlayer(),player3);
    }
    @Test
    void destroyDistrictAt1Gold(){

    }


}


