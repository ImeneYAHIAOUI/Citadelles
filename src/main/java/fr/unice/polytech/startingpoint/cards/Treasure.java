package fr.unice.polytech.startingpoint.cards;

import javax.swing.text.StyledEditorKit;
import java.util.function.Predicate;

public class Treasure {
    private int pieces;

    public Treasure(int pieces){

        this.pieces=pieces;
    }
    public int getPieces(){
        return pieces;
    }
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
    public Boolean isEnough(int gold){
        return gold<=pieces;
    }
    public void addToTreasure(int gold){
        pieces+=gold;
    }

}
