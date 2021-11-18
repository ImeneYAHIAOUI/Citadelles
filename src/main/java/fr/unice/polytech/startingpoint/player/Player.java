package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.Heros.Hero;
import fr.unice.polytech.startingpoint.Heros.HeroDeck;
import fr.unice.polytech.startingpoint.Heros.IHero;
import fr.unice.polytech.startingpoint.cards.District;

import java.util.ArrayList;
import java.util.List;

/**
 *No matter what type of player class we create, we will always have certain methods in commun
 * creating this abstract class allows us to only implement these methods once so the other player
 * classes can inherit them from it.
 */
public abstract class Player implements IPlayer{

    protected List<District> hand;
    protected List<District> builtDistricts;
    protected HeroDeck HeroList;
    protected IHero role;
    protected String name;
    protected int score;
    protected boolean isKing;

    /**
     *
     * @param playerName even though this class has a lot of attributs, we only need the player's
     *                   name to creat the player object, hand (the district cards that we can choose
     *                   from) and builtDistricts are new empty lists at first.
     *                   same with HeroList,though it has a setter that's used to get the list
     *                   of available roles.
     *                   role is also initiated by its setter
     *                   score and isKing are always respectively 0 and false at first
     */

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
     * builtDistricts getter
     * @return the list of built districts
     */

    public List<District> getBuiltDistricts(){
        return builtDistricts;
    }

    /**
     * if this player was king in the previous and know they have to pass the crown
     * this method is called to unmark them
     */

    public void unsetKing(){
        isKing = false;
    }

    /**
     * once the role is chosen, the role attribute get initialised by its setter
     * @param index index of the chosen role in the list of available heroes
     */
    public void setRole(int index){
        this.role = this.HeroList.get(index);
    }

    /**
     * if the player chooses to draw cards, this method adds them in hand
     * @param district the drawn district
     */

    public void addDistrict(District district){
        hand.add(district);
    }

    /**
     * if the player chooses to build a district, this method adds it in buildDistrict
     * @param index index of chosen district
     */
    public void buildDistrict(int index){
        District builtDistrict = hand.get(index);
        builtDistricts.add(builtDistrict);
        score += builtDistrict.getPrice();
        hand.remove(builtDistrict);
    }

    /**
     * once a king is chosen (randomly at first or based on the king role card), this method
     * is called to mark the player that has the crown with isKing
     */
    @Override
    public void setKing(){
        isKing = true;
    }

    /**
     * score getter
     * @return score
     */
    @Override
    public int getScore(){
        return score;
    }

    /**
     * role getter
     * @return the player's role
     */
    @Override
    public IHero getRole(){return role; }

    /**
     * isKing getter
     * @return true or false based on whether or not this player is king
     */
    @Override
    public boolean isKing(){
        return isKing;
    }


    /**
     * Give the player its first district cards by adding them to hand
     * @param attributedHand list of given district cards
     */
    @Override
    public void getDistrict(List<District> attributedHand){
        attributedHand.forEach(h -> {
            hand.add(h);
            score += h.getPrice(); // En attendant que le Player choisisse de construire
        });
    }

    /**
     * Have the list of available heroes
     * @param heros list of available heros
     */
    @Override
    public void HaveTheListOfHeroes(HeroDeck heros){
        this.HeroList = heros;
    }

    /**
     * hand getter
     * @return the players hand
     */
    @Override
    public List<District> getHand(){
        return hand;
    }

    @Override
    public String toString(){
        return name;
    }
}


