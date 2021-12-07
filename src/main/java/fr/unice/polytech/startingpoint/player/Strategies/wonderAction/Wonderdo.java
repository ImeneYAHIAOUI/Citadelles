package fr.unice.polytech.startingpoint.player.Strategies.wonderAction;


import fr.unice.polytech.startingpoint.cards.DistrictDeck;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.cards.Treasure;
import fr.unice.polytech.startingpoint.cards.infoaction;
import fr.unice.polytech.startingpoint.player.IA;

import java.util.List;

public interface Wonderdo {

    public void applyLibrary(IA player, List<IDistrict> cards);


    public void applyDongeon();

    public void applyManufacture(IA player, DistrictDeck deck, Treasure tresor);

    public void applyMiracleCourt(IA player);

    public void observatory(IA player);




}

