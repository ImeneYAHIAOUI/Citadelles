package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

import java.util.ArrayList;
import java.util.List;

public class ArchitectChoice {
    /**
     * Find the best combot to score the most points
     * @param ia
     * @return
     */
    private List<IDistrict> choiceDistrictsAtBuild(IA ia){

        // Initialization of variables
        List<IDistrict> districtList = new ArrayList<>();
        List<IDistrict> districtListTest;
        IDistrict district;
        int gold = ia.getGold();
        int cpt;

        // I find the most interesting combination

        for(int i = 0; i < ia.getHand().size(); i ++){
            districtListTest = new ArrayList<>();
            district = ia.getHand().get(i);

            districtListTest.add(district);
            cpt = district.getPrice();

            if(cpt > gold) continue;

            for(int j = 0; j < ia.getHand().size(); j++){
                district = ia.getHand().get(j);
                if((district.getPrice() + cpt) <= gold && districtListTest.size() <= 3 && i != j){
                    cpt += district.getPrice();
                    districtListTest.add(district);
                }
            }

            // Je choisis celle qui me rapporte le plus de points
            if(districtList.size() == 0){
                districtList = districtListTest;
            }else{
                if(totalPoint(districtList) < totalPoint(districtListTest)){
                    districtList = districtListTest;
                }
            }
        }
        return districtList;
    }

    /**
     * Build the chosen districts
     * @param ia
     */
    public void buildDistrict(IA ia, Treasure treasure, IAToHero info){
        List<IDistrict> districtList = this.choiceDistrictsAtBuild(ia);

        districtList.forEach(district -> {
            ia.buildDistrict(district);
            treasure.addToTreasure(district.getPrice());
            info.addBuiltDistrict(district);
        });
    }

    /**
     * Count the potential score of the hand
     * @param list
     * @return
     */
    private int totalPoint(List<IDistrict> list){
        int cpt = 0;
        for(int i = 0; i < list.size(); i++){
            cpt += list.get(i).getPrice();
        }
        return cpt;
    }
}
