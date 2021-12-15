package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.Color;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.IA;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;

import java.util.List;
import static fr.unice.polytech.startingpoint.cards.Color.*;


public abstract class Display {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";



    public Display() {}

    /**
     * @param players show the rank of each player
     * @return String
     */

    public static String displayRank(List<IPlayer> players) {
        StringBuilder ranking;
        int rank = 1;
        ranking = new StringBuilder("1st place : " + players.get(0) + " -> " + players.get(0).getScore() + " points.\n");
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getScore() != players.get(i - 1).getScore()) rank = i + 1;
                String s = switch (rank) {
                    case 1 -> "st";
                    case 2 -> "nd";
                    case 3 -> "rd";
                    default -> "th";

                };
            ranking.append(rank+s+" place : "+players.get(i)+" -> "+players.get(i).getScore()+" points.\n");


        }
        return ranking.toString();
    }
    /**
     * show the winners
     * @return String
     */
    public static String displayWinners(List<IPlayer> ranking){
        StringBuilder winners = new StringBuilder("" + ranking.get(0));
         int nbWinners = 1;

        for(int i = 1; i < ranking.size();i++){
            if (ranking.get(i).getScore() == ranking.get(0).getScore()) {
                winners.append(" - ").append(ranking.get(i));
                nbWinners++;
            }
        }
        String plural = nbWinners>1 ? "s":"";
        winners = new StringBuilder("Winner" + plural + " : " + winners + "\n\n");
        return winners.toString();
    }
    /**
     * show the result
     * @param result
     */
    public static void displayResult(GameResult result){
        List<IPlayer> rankedPlayers = result.getRanking();
        System.out.println(displayWinners(rankedPlayers)+displayRank(rankedPlayers));
    }

    /**
     * Displays all elements of the players of the round
     * @param playersList
     */
    static public void round(List<IPlayer> playersList){

        playersList.forEach(player -> {
            if(player.getCrown()) {
                System.out.print(ANSI_YELLOW + "\t,  ,() , ,\n" +
                        "\t|\\/\\/\\/\\/|\n" +
                        "\t|_o_<>_o_|\n" + ANSI_RESET);
            }
            System.out.println("\t" + player + " ;");
            System.out.println("\tOR : " + player.getGold());


            System.out.print("\t" + "District -> [ ");
            List<IDistrict> builtDistricts= player.getBuiltDistricts();
            displayDistrictList(builtDistricts);


            System.out.println(" ]");

            System.out.print("\t" + "Hand -> [");
            List<IDistrict> hand=player.getHand();
            displayDistrictList(hand);

            System.out.println(" ]");

            System.out.print("\t" + "Hero -> [ ");
            setColor(player.getRole().getColor());
            System.out.println( player.getRole().getName() + " " + player.getHeroRank() + ANSI_RESET + " ]\n");
        });

        System.out.println("\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀\n");
    }

   // Sys

    /**
     *this methode is responsible for displaying the roles that have been chosen by each player
     * before each round
     */
    public static void displayHeroChoice(List<IPlayer> players,int round){
        System.out.println("\tRound : " + round + "\n");
        players.forEach(player -> {
            System.out.print(ANSI_RESET);
            System.out.print("\t"+player+" has chosen: ");
            setColor(player.getRole().getColor());

            System.out.print(player.getRole().getName());

            System.out.print(ANSI_RESET);/*
            if(player.getRole().getName() == HeroName.King)
                System.out.print("\t");*/
            System.out.println("\t\tThought path : " + ((IA)player).thoughtPathList + "");
        });
        System.out.println(ANSI_RESET);
    }

    /**
     * this method is responsible for displaying the choice made by the assassin
     *
     */
    public static void displayAssassin(IAToHero information){
        System.out.println("\t"+HeroName.Assassin +"'s turn: ");
        if(information.getChosenPlayer() != null)
            System.out.println("\t"+information.getCurrentPlayer()+" has assassinated the "+ information.getChosenPlayer().getRole().getName());
        else
            System.out.println("\t"+information.getCurrentPlayer()+" has assassinated a hero that hasn't been chosen by anyone");
    }

    /**
     *
     * this method is responsible for displaying the choice made by the magician
     */
    public static void displayMagician(IAToHero information){
        System.out.println("\t"+HeroName.Magician +"'s turn: ");
        System.out.print("\t"+information.getCurrentPlayer());
        if(information.getChosenPlayer()!=null){
            System.out.println("\thas exchanged hand with "+information.getChosenPlayer());
        }
        else if(information.getChosenCards().size()>0){
            System.out.print(" has exchanged these cards with deck: "+ANSI_RESET+"[");
            displayDistrictList(information.getChosenCards());
            System.out.println("]");
        }

        else System.out.println("\t has chosen to keep their hand");
    }

    /**
     *
     * this method is responsible for displaying the choice made by the king
     */
    public static void displayKing(IAToHero information){
        System.out.println(ANSI_YELLOW);
        System.out.println("\t"+HeroName.King +"'s turn: ");
        System.out.println("\tThe crown has been passed to "+information.getCurrentPlayer());
        long nobleDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == YELLOW).count();
        String plural = nobleDistrictNum>1 ? "s":"";
        if(nobleDistrictNum>0)
        System.out.println("\t"+information.getCurrentPlayer()+" gets "+nobleDistrictNum+" extra gold piece"+plural+" for their noble district"+plural);
    }
    /**
     * this method is responsible for displaying the choice made by the merchant
     */
    public static void displayMerchant(IAToHero information){
        System.out.println(ANSI_GREEN);
        System.out.println("\t"+HeroName.Merchant +"'s turn: ");
        System.out.println("\t"+information.getCurrentPlayer()+" gets an extra gold piece for being the merchant");
        long merchantDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == GREEN).count();
        String plural = merchantDistrictNum>1 ? "s":"";
        if(merchantDistrictNum>0)
        System.out.println("\t"+information.getCurrentPlayer()+" gets "+merchantDistrictNum+" extra gold piece"+plural+" for their merchant district"+plural);

    }

    /**
     *this method is responsible for displaying the choice made by the theif
     */
    public static void displayThief(IAToHero information){
        System.out.println("\t"+HeroName.Thief +"'s turn: ");
        if(information.getChosenPlayer() != null)
            System.out.println("\t"+information.getCurrentPlayer()+" has stolen the "+information.getChosenPlayer().getRole().getName()+"'s gold");
        else
            System.out.println("\t"+information.getCurrentPlayer()+" has stolen from a hero that hasn't been chosen by anyone");

    }

    /**
     *this method is responsible for displaying the choice made by the bishop
     */
    public static void displayBishop(IAToHero information){
        System.out.print(ANSI_BLUE);
        System.out.println("\t"+HeroName.Bishop +"'s turn: ");
        System.out.println("\t"+information.getCurrentPlayer()+"'s districts are protected form the condottiere");
        long religiousDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == BLUE).count();
        String plural = religiousDistrictNum>1 ? "s":"";
        if(religiousDistrictNum>0)
        System.out.println("\t"+information.getCurrentPlayer()+" gets "+religiousDistrictNum+" extra gold piece"+plural+" for their religious district"+plural);
    }
    public static void displayCondottiere(IAToHero information){
        System.out.print(ANSI_RED);
        System.out.println("\t"+HeroName.Condottiere +"'s turn: ");
        if(information.getChosenCards().size()>0) {
            System.out.println("\t" + information.getCurrentPlayer() + " has destroyed " + information.getChosenPlayer() + " s " + information.getChosenCards().get(0));
        }else
            System.out.println("\t"+"has chosen not to destroy any district");
    }


    public static void displayArchitect(IAToHero information){
        System.out.println("\t"+HeroName.Architect +"'s turn: ");
        System.out.println("\t"+information.getCurrentPlayer()+" gets to draw two additional cards");
        System.out.println("\t"+information.getCurrentPlayer()+" can build up to 3 districts");
    }

    /**
     *
     * this method calls one of the hero display methods bellow, and displays
     * the other choices unrelated to the hero (draw or get gold, build a district)
     */

    public static void displayAction(IAToHero information){
        HeroName role = information.getCurrentPlayer().getRole().getName();
        switch (role){
            case Assassin -> displayAssassin(information);
            case Thief -> displayThief(information);
            case Magician -> displayMagician(information);
            case King -> displayKing(information);
            case Bishop -> displayBishop(information);
            case Merchant -> displayMerchant(information);
            case Architect -> displayArchitect(information);
            case Condottiere -> displayCondottiere(information);
        }
        System.out.println("\t"+information.getChoice());
        if(information.getBuiltDistrict().size()>0) {
            System.out.print("\t"+information.getCurrentPlayer()+" has chosen to build "+ANSI_RESET+"[");
            displayDistrictList(information.getBuiltDistrict());
            System.out.println(" ]");
        }
        System.out.print(ANSI_RESET +"\n");

    }

    /**
     * displays a list of districts colored according to their family (hand, built districts, exchanged districts, ...)
     */
    public static void displayDistrictList(List<IDistrict> districtList){
        for(int i=0;i<districtList.size();i++){
            String sep=i>0? " , " :" ";
            System.out.print(sep);
            setColor(districtList.get(i).getColor());
            System.out.print(districtList.get(i).getDistrictName() + " = " + districtList.get(i).getPrice() + ANSI_RESET );
        }
    }


    /**
     *
     * sets a display color according to the parameter
     */
    public static void setColor(Color color){
        switch(color){
            case YELLOW ->  System.out.print(ANSI_YELLOW);
            case BLUE -> System.out.print(ANSI_BLUE);
            case RED ->  System.out.print(ANSI_RED);
            case GREEN -> System.out.print(ANSI_GREEN);
            case PURPLE -> System.out.print(ANSI_PURPLE);
        }
    }

    public static void displayVisibleHeroes(List<HeroName> visibleHeroes){
        if (visibleHeroes.size()>0){
            System.out.println("\tvisible drawn heroes : "+visibleHeroes+"\n");
        }
    }
    public static void displayHiddenHero(IHero visibleHeroes){
        System.out.println("\thidden drawn hero : "+visibleHeroes.getName()+"\n");

    }
}