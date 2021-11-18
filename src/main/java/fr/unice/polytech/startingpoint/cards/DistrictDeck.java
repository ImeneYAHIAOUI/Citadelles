package fr.unice.polytech.startingpoint.cards;

import java.util.ArrayList;
import java.util.List;

public class DistrictDeck extends ArrayList<IDistrict>{
    /**
     * Includes all the colors of the different quartoers
     */
    private List<District> districtList;

    /**
     * Constructor
     * @param districtList
     */
    public DistrictDeck(List<District> districtList) {
        this.districtList = districtList;
    }

    /**
     * Send n district
     * @param lengthOfTheListToReturn
     * @return
     */
    public List<District> giveDistrict(int lengthOfTheListToReturn){
        List<District> districtProposal = new ArrayList<District>();
        int lengthOfDistrictList = this.districtList.size() - 1;

        if(lengthOfDistrictList > this.districtList.size())
            lengthOfDistrictList = this.districtList.size();
        for(int i = 0; i < lengthOfTheListToReturn; i++) {
            districtProposal.add(this.districtList.get((lengthOfDistrictList - i)));
            districtList.remove(lengthOfDistrictList - i);
        }

        return districtProposal;
    }

    /**
     * Returns the size of the district list
     * @return
     */
    public int getDeckSize(){
        return this.districtList.size();
    }
}
