package fr.unice.polytech.startingpoint.core;

import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.DistrictName;
import fr.unice.polytech.startingpoint.cards.IWonder;
import fr.unice.polytech.startingpoint.cards.district.MagicSchool;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.district.Cemetry;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    IPlayer assassinated;
    IPlayer stolenPerson;
    IPlayer thief;
    IDistrict cardDestroyedByCondottiere;
    IPlayer HaveCemetry;
    IPlayer condottiere;

    int  NumberOfBuiltDistrict;
    List<IDistrict> builtDistrictsThisRound;

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
    public void update(List<IPlayer> players, Treasure tresor , DistrictDeck deck){
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
                    HaveCemetry.applyCemetry(deck,tresor,cardDestroyedByCondottiere);
                    condottiere.setCardDestroyedByCondottiere(null);
                }

            }

            if(player.getRole().getRank()==8){
                this.condottiere=player;
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

    public IPlayer getCondottiere() {
        return condottiere;
    }

    public IPlayer getHaveCemetry() {
        return HaveCemetry;
    }

    public void setHaveCemetry(IPlayer haveCemetry) {
        HaveCemetry = haveCemetry;
    }

    public void setStolenPerson(IPlayer stolenPerson) {
            this.stolenPerson = stolenPerson;

    }

    public void colorChangeWithWonder(List<IPlayer> players){
        players.forEach( player -> {
            if(player.getBuiltDistricts().stream().map(district -> district.getDistrictName()).anyMatch(districtName -> districtName.equals(DistrictName.LACOURDESMIRACLES))){
                if(builtDistrictsThisRound.stream().noneMatch(district -> district.getDistrictName().equals(DistrictName.LACOURDESMIRACLES))){
                    player.applyMiracleCourt();
                }
            }
        });
    }

    /**
     * this method calls the method that's responsible for changing the
     * magic school card color if the player has it, and if he picks a colored role
     */
    public void changeMagicSchoolColor(IPlayer player){
        player.applyMagicSchool();
    }

    /**
     * this method resets the magic school color after each round if it exists
     */
    public void resetMagicSchoolColor(List<IPlayer> players){
        players.forEach(player -> {
            MagicSchool MagicSchool = (MagicSchool) player.getBuiltDistricts().stream().filter(wonder -> wonder.getDistrictName().equals(DistrictName.ECOLEDEMAGIE)).findAny().orElse(null);
            if (MagicSchool != null){
                MagicSchool.resetColor();
            }
        });
    }

    public void addTOBuiltDistricts(List<IDistrict> districtList){
        builtDistrictsThisRound.addAll(districtList);

    }
    public void setBuiltDistricts(){
        builtDistrictsThisRound = new ArrayList<>();
    }
}
