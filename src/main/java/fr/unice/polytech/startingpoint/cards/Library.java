package fr.unice.polytech.startingpoint.cards;

import fr.unice.polytech.startingpoint.player.Information;

import java.util.List;

public class Library extends DistrictD implements IWonder {
    private String description;

    boolean draw;

    public Library() {
        this.name = DistrictName.LIBRARY;
        this.color = Color.PURPLE;
        this.price = 6;
        this.description = "Si vous choisissez de" +
                "piocher des cartes au " +
                "début de votre tour," +
                "vous en piochez deux " +
                "et les conservez " +
                "toutes les deux.\n";
    }
    @Override
    public void doAction(infoaction info) {
        if (draw) {
            info.setplayer(info.getinformation().getCurrentPlayer());
            info.getplayer().getDistrict( info.districtdeck.giveDistrict(1));



        }
    }
    @Override
    public void effectOfAction() {



    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isWonder() {
        return true ;
    }
    }
