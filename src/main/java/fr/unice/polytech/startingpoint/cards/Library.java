package fr.unice.polytech.startingpoint.cards;

import java.util.List;

public class Library extends DistrictD implements IWonder {
    private String description;
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
        List<IDistrict> cards=info.getChosenCards();
            info.getplayer().getDistrict(cards);

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
