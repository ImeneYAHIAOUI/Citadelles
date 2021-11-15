package fr.unice.polytech.startingpoint.player;
import fr.unice.polytech.startingpoint.Heros.IHero;
import fr.unice.polytech.startingpoint.cards.District;

import java.util.*;
public class IA implements IPlayer{
    private List<District> hand;
    private List<District> builtDistricts;
    private List<IHero> herosList;
    private IHero role;
    private String name;
    private int score;
    private boolean isKing;
    private IPlayer player;

    public IA(String playerName){
        this.name = playerName;
        builtDistricts = new ArrayList<District>();
        hand = new ArrayList<District>();
        this.herosList = new ArrayList<IHero>();
        name = playerName;
        score = 0;
        isKing = false;
        //chooseRole();
    }

    public void move(){
        Random rand = new Random();
        int index = rand.nextInt(1);
        this.buildDistrict(index);
    }


    /**
     * Give the player districts
     * @param attributedHand
     */
    public void getDistrict(List<District> attributedHand){
        attributedHand.forEach(h -> {
            hand.add(h);
            score += h.getPrice(); // En attendant que le Player choisisse de construire
        });
    }

    /**
     * Have the list of heroes
     * @param heros
     */
    public void HaveTheListOfHeroes(List<IHero> heros){
        this.herosList = heros;
    }

    public void chooseRole(){
        Random rand = new Random();
        int roleIndex = rand.nextInt(this.herosList.size());
        this.setRole(roleIndex);
        //remove(player.getRole());
        //player.getRole().doAction(player);
    }

    public List<District> getHand(){
        return hand;
    }

    public List<District> getBuiltDistricts(){
        return builtDistricts;
    }

    public int getScore(){
        return score;
    }

    public void setKing(){
        isKing = true;
    }

    public void unsetKing(){
        isKing = false;
    }

    //public void setHeroes(HeroDeck heroes){ this.heroes = heroes;}

    /**
     *
     * @param index
     */
    public void setRole(int index){
        this.role = this.herosList.get(index);
    }

    public IHero getRole(){return role; }

    //public HeroDeck getHeroes(){ return heroes;}

    public void addDistrict(District district){
        hand.add(district);
    }
    public boolean isKing(){
        return isKing;
    }



    public void buildDistrict(int index){
        District builtDistrict = hand.get(index);
        builtDistricts.add(builtDistrict);
        score += builtDistrict.getPrice();
        hand.remove(builtDistrict);
    }


    public String toString(){
        return name;
    }

}
