package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IWonder;
import fr.unice.polytech.startingpoint.cards.district.MagicSchool;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.district.Cemetry;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;

public class Controller {
    IPlayer assassinated;
    IPlayer stolenPerson;
    IPlayer thief;
    IDistrict cardDestroyedByCondottiere;
    IPlayer HaveCemetry;

    int  NumberOfBuiltDistrict;

    /**
     * give gold to the thief
     */
    public void  GiveGoldToTheTief(){
        if(stolenPerson!=null && thief!=null){
            int gold=stolenPerson.getGold();
            thief.addGold(gold);
            stolenPerson.removeGold(gold);
            stolenPerson=null;
            thief=null;
        }

    }

    /**
     * update the controller
     * @param players
     */
    public void update(List<IPlayer> players,Treasure tresor){
        if(assassinated != null) assassinated.unsetIsAssigned();
        players.forEach(player -> {
            if(player.getIsAssigned()){
                this.assassinated=player;
            }
            if(player.getStolenPerson()){
                this.stolenPerson=player;
                this.thief=player.getStolenBy();
            }
            if(haveCemetry(player) ){
                HaveCemetry=player;
                if( cardDestroyedByCondottiere!=null && HaveCemetry!=null){
                    HaveCemetry.applyCemetry(tresor,cardDestroyedByCondottiere);
                }
            }

            if(player.getRole().getRank()==8){
                cardDestroyedByCondottiere=player.getCardDestroyedByCondottiere();


            }
        });
        this.NumberOfBuiltDistrict=maxDistrictObtained(players);
    }

    /**
     * player have the cemetry
     * @param player
     *
     */
    public Boolean haveCemetry(IPlayer player){
        return player.getBuiltDistricts().stream().anyMatch(District-> District.getDistrictName().equals(DistrictName.CEMETRY));

    }
    /**
     * Returns the maximum number of district among all players
     * @return int
     */
    public int maxDistrictObtained(List<IPlayer> players){
        int max = players.stream().mapToLong(player -> player.getBuiltDistricts().stream().count()).mapToInt(player -> (int) player).filter(player -> player >= 0).max().orElse(0);
        return max;
    }


    public boolean endTheGame(){
        return this.NumberOfBuiltDistrict>=8;
    }

    public boolean isStolenPerson(IPlayer player){
        return player.equals(stolenPerson);

    }
    public boolean isAssasinated(IPlayer player){
        return player.equals(assassinated);
    }
    public void unSetAssassinated() {
        this.assassinated = null;
    }
    public void setAssassinated(IPlayer assassinated) {
        this.assassinated = assassinated;
    }

    public IPlayer getAssassinated() {
        return assassinated;
    }

    public IPlayer getThief() {
        return thief;
    }

    public void setThief(IPlayer thief) {
        this.thief = thief;
    }

    public IPlayer getStolenPerson() {
        return stolenPerson;
    }

    public void setStolenPerson(IPlayer stolenPerson) {
            this.stolenPerson = stolenPerson;

    }

    public void colorChangeWithWonder(List<IPlayer> players){
        players.forEach( player -> {
            if(player.getBuiltDistricts().stream().map(district -> district.getDistrictName()).anyMatch(districtName -> districtName.equals(DistrictName.LACOURDESMIRACLES))){
                player.applyMiracleCourt();
            }
        });
    }
    public void changeMagicSchoolColor(IPlayer player){
        player.applyMagicSchool();
    }
    public void resetMagicSchoolColor(List<IPlayer> players){
        players.forEach(player -> {
            IWonder MagicSchool = (IWonder) player.getBuiltDistricts().stream().filter(wonder -> wonder.getDistrictName().equals(DistrictName.ECOLEDEMAGIE)).findAny().orElse(null);
            if (MagicSchool != null){
                int MagicSchoolIndex = player.getBuiltDistricts().indexOf(MagicSchool);
                player.getBuiltDistricts().set(MagicSchoolIndex, new MagicSchool());
            }
        });
    }
}
