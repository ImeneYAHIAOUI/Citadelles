package fr.unice.polytech.startingpoint.player;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;

import java.util.ArrayList;
import java.util.List;

/*
 * This class factors the code for the player
 */
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
    protected IDistrict cardDestroyedByCondottiere;
    protected HeroName targetedHero;



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
     * Building the district
     * @param builtDistrict
     */
    public void buildDistrict(IDistrict builtDistrict){
        if(gold>=builtDistrict.getPrice()){
            builtDistricts.add(builtDistrict);
            score += builtDistrict.getPrice();
            gold -= builtDistrict.getPrice();
            hand.remove(builtDistrict);
        }
    }
    @Override
    public HeroName getTargetedHero(){
        return targetedHero;
    }
    @Override
    public void setTargetedHero(HeroName hero){
        targetedHero = hero;
    }
    @Override
    public int getGold(){
        return gold;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void addGold(int addedValue) {
        gold += addedValue;
    }

    @Override
    public void addScore(int addedScore){ score += addedScore;}

    @Override
    public void removeGold(int removedValue) {
        gold-=removedValue;
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public IHero getRole(){return role; }

    @Override
    public void getDistrict(List<IDistrict> attributedHand){
        attributedHand.forEach(h -> {
            hand.add(h);
        });
    }

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

    @Override
    public List<IDistrict> getBuiltDistricts(){
        return builtDistricts;
    }

    // ========================================================================================================
    //                                                SETTER
    // ========================================================================================================
    @Override
    public void setRole(IHero hero){
        this.role = hero;
    }

    public void setGold(int gold){
        this.gold=gold;
    }

    public IDistrict getCardDestroyedByCondottiere() {
        return cardDestroyedByCondottiere;
    }

    public void setCardDestroyedByCondottiere(IDistrict cardDestroyedByCondottiere) {
        this.cardDestroyedByCondottiere = cardDestroyedByCondottiere;
    }

    public void setHand(List<IDistrict> hand){
        this.hand = hand;
    }
    public void unSetCrown(){
        crown = false;
    }
    public void setCrown(){
        crown = true;
    }
    public void setIsAssigned(){
        isAssigned = true;
    }
    public void unsetIsAssigned(){
        isAssigned = false;
    }
    public void setStolenBy(IPlayer player){
        this.stolenBy=player;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setStolenPerson(){
        this.isStolenPerson=true;
    }
    public void unSetStolenPerson(){
        this.isStolenPerson=false;
        this.stolenBy=null;
    }

    // ========================================================================================================
    //                                                GETTER
    // ========================================================================================================

    public boolean getCrown(){
        return crown;
    }
    public boolean getStolenPerson(){
        return isStolenPerson;
    }
    public IPlayer getStolenBy(){
        return this.stolenBy;
    }
    public boolean getIsAssigned(){
        return isAssigned;
    }
}


