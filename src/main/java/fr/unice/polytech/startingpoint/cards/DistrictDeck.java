package fr.unice.polytech.startingpoint.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistrictDeck {
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
     * @param nb
     * @return
     */
    public List<District> giveDistrict(int nb){
        List<District> districtProposal = new ArrayList<District>();
        int lengthOfDistrictList = this.districtList.size() - 1;
        for(int i = 0; i < nb; i++) {
            Collections.shuffle(districtList);

            districtProposal.add(this.districtList.get((lengthOfDistrictList - i)));
            districtList.remove(lengthOfDistrictList - i);
        }

        return districtProposal;
    }
}
