package fr.unice.polytech.startingpoint.core;

/*
 * This class manages the cash flow of the game
 */
public class Treasure {
    private int pieces;

    /**
     * Constructor
     * @param pieces
     */
    public Treasure(int pieces){
        this.pieces=pieces;
    }

    /**
     * Return the treasorie account
     * @return
     */
    public int getPieces(){
        return pieces;
    }

    /**
     * remove gold from the treasure
     * @param gold
     */
    public int removeGold(int gold){
        int giveGold=pieces;
        if( isEnough(gold)){
            pieces-=gold;
            giveGold=gold;
        }else{
            pieces=0;
        }
        return giveGold;
    }

    /**
     *  verify if we can get gold from the treasure
     * @param gold
     */
    public Boolean isEnough(int gold){
        return gold<=pieces;
    }

    /**
     * add gold to the treasure
     * @param gold
     */
    public void addToTreasure(int gold){
        pieces+=gold;
    }

}
