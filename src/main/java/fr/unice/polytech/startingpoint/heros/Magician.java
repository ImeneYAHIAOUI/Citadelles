package fr.unice.polytech.startingpoint.heros;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

import java.util.List;

public class Magician extends Hero{
    public Magician(){
    this.name = HeroName.Magician;
    this.color = Color.PURPLE;
    this.rank = 3;
    }



    @Override
    public void doAction(Information information) {
        IPlayer playerChosen=information.getChosenPlayer();
        if(playerChosen==null){
            int numberCards=information.getChosenNumberOfCard();
            //exchange numberCards

        }else{
            //exchange Cards With playerChosen;
        }

    }



}
