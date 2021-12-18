package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArchitectChoice {
    public List<IDistrict> randomDistrictChoice(IPlayer randomIA){
        Random random = new Random();
        int randomBuiltDistrictNumber = random.nextInt(3) + 1;
        List<IDistrict> toBeBuiltDistricts = new ArrayList<>();
        while (randomBuiltDistrictNumber>0 && randomIA.getHand().stream().anyMatch(district -> district.getPrice()<=randomIA.getGold())){
            IDistrict randomAffordableDistrict = randomIA.getHand().stream().filter(district -> district.getPrice()<=randomIA.getGold()).findAny().orElse(null);
            toBeBuiltDistricts.add(randomAffordableDistrict);
        }
        return toBeBuiltDistricts;
    }

    /**
     *
     * @param ia
     * @return
     */
    private List<IDistrict> findTheMostInterestingCombination(IPlayer ia){
        List<IDistrict> districtList = new ArrayList<>();
        List<IDistrict> districtListTest;
        IDistrict district;
        int gold = ia.getGold();
        int cpt;

        // I choose a card from my hand
        for(int i = 0; i < ia.getHand().size(); i ++){
            districtListTest = new ArrayList<>();
            district = ia.getHand().get(i);

            districtListTest.add(district);
            cpt = district.getPrice();

            // If the price is higher than my gold, I move on to the next one
            if(cpt > gold) continue;

            //I compare the card with the rest of my hand
            for(int j = 0; j < ia.getHand().size(); j++){
                district = ia.getHand().get(j);
                // If it fits in my budget, I add it to a test list
                if((district.getPrice() + cpt) <= gold && districtListTest.size() <= 3 && i != j){
                    cpt += district.getPrice();
                    districtListTest.add(district);
                }
            }

            // I choose the one that earns me the most points
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
     * Find the best combot to score the most points
     * @param ia
     * @return
     */
    public List<IDistrict> choiceDistrictsAtBuild(IPlayer ia){
        // Initialization of variables
        List<IDistrict> districtList = new ArrayList<>();
        if (ia.bot.equals("random")){
            return randomDistrictChoice(ia);
        }else{
            districtList = this.findTheMostInterestingCombination(ia);
        }


        return districtList;
    }

    /**
     * Build the chosen districts
     * @param ia
     */
    public void buildDistrict(IPlayer ia, Treasure treasure, IAToHero info){
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
