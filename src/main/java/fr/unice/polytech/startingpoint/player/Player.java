package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.ArrayList;
import java.util.List;

/**
 *No matter what type of player class we create, we will always have certain methods in commun
 * creating this abstract class allows us to only implement these methods once so the other player
 * classes can inherit them from it.
 */
public abstract class Player implements IPlayer{

    protected List<IDistrict> hand;
    protected List<IDistrict> builtDistricts;
    protected List<IDistrict> drawnDistricts;
    protected IHero role;
    protected String name;
    protected int score;
    protected int gold;
    protected boolean crown;
    protected boolean isAssigned;
    protected boolean isStolenPerson;
    protected IPlayer stolenBy;


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
        builtDistricts = new ArrayList<>();
        hand = new ArrayList<>();
        name = playerName;
        score = 0;
        gold = 0;
        crown=false;
        isAssigned= false;
        isStolenPerson=false;
        stolenBy=null;
    }

    /**
     * builtDistricts getter
     * @return the list of built districts
     */
    @Override
    public List<IDistrict> getBuiltDistricts(){
        return builtDistricts;
    }

    /**
     * if this player was king in the previous and know they have to pass the crown
     * this method is called to unmark them
     */

    public void setCrown(){
        crown = true;
    }
    public void setIsAssigned(){
        isAssigned = true;
    }
    public void unsetIsAssigned(){
        isAssigned = false;
    }
    public boolean getIsAssigned(){
        return isAssigned;
    }
    public void setStolenPerson(){
        this.isStolenPerson=true;
    }
    public void unSetStolenPerson(){
        this.isStolenPerson=false;
        this.stolenBy=null;
    }
    public boolean getStolenPerson(){
        return isStolenPerson;
    }
    public IPlayer getStolenBy(){
        return this.stolenBy;
    }
    public void setStolenBy(IPlayer player){
        this.stolenBy=player;
    }


    public void setScore(int score){
        this.score = score;
    }
    public void unSetCrown(){
        crown = false;
    }
    public boolean getCrown(){
        return crown;
    }

    /**
     * once the role is chosen, the role attribute get initialised by its setter
     * @param hero the chosen role in the list of available heroes
     */
    public void setRole(IHero hero){
        this.role = hero;
    }




    @Override
    public int getGold(){
        return gold;
    }

    public void setGold(int gold){
        this.gold=gold;
    }
    @Override
    public String getName(){
        return name;
    }
    public void setHand(List<IDistrict> hand){
        this.hand = hand;
    }




    public void buildDistrict(IDistrict builtDistrict){
        if(gold>=builtDistrict.getPrice()){
            builtDistricts.add(builtDistrict);
            score += builtDistrict.getPrice();
            gold -= builtDistrict.getPrice();
            hand.remove(builtDistrict);
        }
    }

    @Override
    public void addGold(int addedValue) {
        gold += addedValue;
    }
    @Override
    public void removeGold(int removedValue) {
        gold-=removedValue;
    }

    /**
     * once a king is chosen (randomly at first or based on the king role card), this method
     * is called to mark the player that has the crown with isKing
     */

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



    /**
     * Give the player its first district cards by adding them to hand
     * @param attributedHand list of given district cards
     */
    @Override
    public void getDistrict(List<IDistrict> attributedHand){
        attributedHand.forEach(h -> {
            hand.add(h);
        });
    }




    /**
     * hand getter
     * @return the players hand
     */
    @Override
    public List<IDistrict> getHand(){
        return hand;
    }

    @Override
    public int getHeroRank(){
        return this.getRole().getRank();
    }

    @Override
    public String toString(){
        return name;
    }
}


