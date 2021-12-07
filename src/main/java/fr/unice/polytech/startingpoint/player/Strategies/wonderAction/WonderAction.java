package fr.unice.polytech.startingpoint.player.Strategies.wonderAction;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.player.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class WonderAction {
    infoaction info ;
    Manufacture manufacture=new Manufacture();
    MiracleCourt miracleCourt=new MiracleCourt();

    public WonderAction(){
        this.info = new infoaction();
    }
    public void applyLibrary(IA player,List<IDistrict> cards){
        IDistrict wonder=player.getBuiltDistricts().stream()
                .filter(district -> district.getDistrictName()== DistrictName.LIBRARY)
                .findAny().orElse(null);
        if(wonder!=null){
            info.setplayer(player);
            info.setChosenCards(cards);
            ((IWonder )wonder).doAction(info);
        }
    }
    public void applyDongeon(){
    }

    public void applyManufacture(IA player,DistrictDeck deck,Treasure tresor) {
        int i;
        info.setTreasure(tresor);
        info.setplayer(player);
        info.setdistrictdeck(deck);
        IDistrict wonder = player.getBuiltDistricts().stream()
                .filter(district -> district.getDistrictName() == DistrictName.MANUFACTURE)
                .findAny().orElse(null);
        if (wonder != null & player.getGold() >= 3) {
            int s = 0;
            int c = 0;
           for (i = 0; i < player.getHand().size(); i++) {
               if (player.getHand().get(i).getPrice() >= 3) {
                    s = s + 1;
                } else c = c + 1;}
            if ( s > c || player.getHand().size()== 0)
            { manufacture.doAction(info);}

        }
        }
        public void applyMiracleCourt(IA player){

        List<Color> color= new ArrayList<>();
        List<Color> colorList= List.of(new Color[]{Color.PURPLE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW});
        IDistrict wonder = player.getBuiltDistricts().stream()
                .filter(district -> district.isWonder() && district.getDistrictName() == DistrictName.LACOURDESMIRACLES).findAny().orElse(null);
        if(wonder!=null){
            int val = 0;

            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.YELLOW)){
                val ++;color.add(Color.YELLOW);}
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.RED)){
                val ++; color.add(Color.RED);}
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.BLUE)){
                val ++; color.add(Color.BLUE);}
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.PURPLE)){
                val ++;color.add(Color.PURPLE);}
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.GREEN)){
                val ++;color.add(Color.GREEN);}
            if(val == 4){
                Color choosencolor = color.stream().filter(color1 -> !colorList.contains(color1)).findAny().orElse(null);
                info.setchoosencolor(choosencolor);
                info.setbuildlist(player.getBuiltDistricts());
                info.setplayer(player);
                miracleCourt.doAction(info);

            }

            }

            }



        }


