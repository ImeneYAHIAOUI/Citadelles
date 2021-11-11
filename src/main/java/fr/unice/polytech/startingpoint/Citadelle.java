package proposition1;

import java.util.ArrayList;
import java.util.List;

public class Citadelle {
    public static void main(String args[]){
        District district1 = new District(2,Color.JAUNE,"Cat1");
        District district2 = new District(1,Color.ROUGE,"Cat2");

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