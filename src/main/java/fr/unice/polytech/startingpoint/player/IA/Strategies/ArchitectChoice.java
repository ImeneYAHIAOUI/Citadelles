package fr.unice.polytech.startingpoint.player.IA.Strategies;

import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.core.Treasure;
import fr.unice.polytech.startingpoint.player.IA.Bots;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArchitectChoice {
    public List<IDistrict> randomDistrictChoice(IA randomIA){
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
     * I find the most interesting combination
     * @param ia
     * @return
     */
<<<<<<< HEAD
    private List<IDistrict> findTheMostInterestingCombination(IA ia){
=======
    public List<IDistrict> choiceDistrictsAtBuild(IA ia){

        if (ia.bot.equals(Bots.random)){
            return randomDistrictChoice(ia);
        }
        // Initialization of variables
>>>>>>> eafe4869e1d141e1db4065e49f2e84b70c8e9784
        List<IDistrict> districtList = new ArrayList<>();
        List<IDistrict> districtListTest;
        IDistrict district;
        int gold = ia.getGold();
        int cpt;

        for(int i = 0; i < ia.getHand().size(); i ++){
            districtListTest = new ArrayList<>();
            district = ia.getHand().get(i);

            districtListTest.add(district);
            cpt = district.getPrice();

            if(cpt > gold) continue;

            // Find a potentiel list
            districtListTest = this.findAPotentielList(cpt,ia,gold,i);

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
     * Find a potentiel list
     * @param cpt
     * @param ia
     * @param gold
     * @param i
     * @return
     */
    private List<IDistrict> findAPotentielList(int cpt, IA ia, int gold, int i){
        IDistrict district;
        List<IDistrict> districtListTest = new ArrayList<>();
        for(int j = 0; j < ia.getHand().size(); j++){
            district = ia.getHand().get(j);
            if((district.getPrice() + cpt) <= gold && districtListTest.size() <= 3 && i != j){
                cpt += district.getPrice();
                districtListTest.add(district);
            }
        }
        return districtListTest;
    }

    /**
     * Find the best combot to score the most points
     * @param ia
     * @return
     */
    public List<IDistrict> choiceDistrictsAtBuild(IA ia){
        // Initialization of variables
        List<IDistrict> districtList = new ArrayList<>();

        if (ia.bot.equals("random")){
            districtList = randomDistrictChoice(ia);
        }else{
            // I find the most interesting combination
            districtList = this.findTheMostInterestingCombination(ia);
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
