package fr.unice.polytech.startingpoint.cards;

public class Treasure {
    private int pieces;
    public Treasure(int pieces){
        this.pieces=pieces;
    }
    public int getPieces(){
        return pieces;
    }
    public void removeGold(int gold){
        pieces-=gold;
    }
    public void addToTreasure(int gold){
        pieces+=gold;
    }

}
