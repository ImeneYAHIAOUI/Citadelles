package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.Heros.Hero;
import fr.unice.polytech.startingpoint.Heros.HeroDeck;
import fr.unice.polytech.startingpoint.Heros.IHero;
import fr.unice.polytech.startingpoint.cards.District;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Player implements IPlayer{

    protected List<District> hand;
    protected List<District> builtDistricts;
    protected HeroDeck HeroList;
    protected IHero role;
    protected String name;
    protected int score;
    protected boolean isKing;
    protected IPlayer player;

    public Player(String playerName){
        this.name = playerName;
        builtDistricts = new ArrayList<>();
        hand = new ArrayList<>();
        this.HeroList = new HeroDeck();
        name = playerName;
        score = 0;
        isKing = false;
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
    public void HaveTheListOfHeroes(HeroDeck heros){
        this.HeroList = heros;
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

    //public void setHeroes(HeroDeck heroes){ this.HeroList = heroes;}

    /**
     *
     * @param index
     */
    public void setRole(int index){
        this.role = this.HeroList.get(index);
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


