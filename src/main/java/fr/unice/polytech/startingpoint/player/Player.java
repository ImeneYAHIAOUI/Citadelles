package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.*;

import java.util.*;

public class Player {
    private List<District> hand;
    private List<District> builtDistricts;
    private String name;
    private int score;
    private boolean isKing;


    public Player(List<District> attributedHand, String playerName){
        hand = attributedHand;
        builtDistricts = new ArrayList<>();
        name = playerName;
        score = 0;
        isKing = false;

    }

    public List<District> getHand(){
        return hand;
    }

    public List<District> getBuiltDistricts(){
        return builtDistricts;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }

    public void setKing(){
        isKing = true;
    }

    public void addDistrict(District district){
        hand.add(district);
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
