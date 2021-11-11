package proposition1;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.District;

import java.util.ArrayList;
import java.util.List;

public class Citadelle {
    public static void main(String args[]){
        District district1 = new District(2,Color.JAUNE,"Cat1");
        District district2 = new District(1,Color.ROUGE,"Cat2");

        // YELLOW
        for(int i = 0; i < 5; i++)
            districtDeck.add(new District(1, Color.YELLOW,"Manoir"));
        for(int i = 0; i < 4; i++)
            districtDeck.add(new District(2,Color.YELLOW,"Château"));
        for(int i = 0; i < 2; i++)
            districtDeck.add(new District(2,Color.YELLOW,"Palais"));

        Wonder wonder = new Wonder(4,Color.JAUNE,"Cat3","Something");

        List<IDistrict> deck = new ArrayList<IDistrict>();
        deck.add(district1);
        deck.add(district2);
        deck.add(wonder);

        deck.forEach(district -> {
            System.out.println(district.isWonder());
            System.out.println(district.getColor());
            System.out.println(district.getVal());

            if(district.isWonder()){
                ((IWonder)district).doAction();
                ((IWonder)district).effectOfAction();
            }
        });


            // INIT
        Compare compare = new Compare();
        Display display = new Display();
        GameResult gameResult = new GameResult();


            // Automate
        /*
        .
        .
        .
        .
         */
        gameResult = compare.getResult(List IA);
        display(gameResult);

        ia.getDistrictList(districtDeck.giveDistrict(1));
    }
}