package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.District;
import fr.unice.polytech.startingpoint.cards.IDistrict;
import fr.unice.polytech.startingpoint.heros.Assassin;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IPlayer;
import fr.unice.polytech.startingpoint.player.Information;

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
     * @param round
     */
    static public void round(List<IPlayer> playersList, int round){

        System.out.println("\tRound : " + round + "\n");
        displayHeroChoice(playersList);
        playersList.forEach(player -> {
            if(player.getCrown()) {
                System.out.print(ANSI_YELLOW + "\t,  ,() , ,\n" +
                        "\t|\\/\\/\\/\\/|\n" +
                        "\t|_o_<>_o_|\n" + ANSI_RESET);
            }
            String sep;
            System.out.println("\t" + player + " ;");
            System.out.println("\tOR : " + player.getGold());


            System.out.print("\t" + "District -> [ ");
            List<IDistrict> builDistricts= player.getBuiltDistricts();
            if(builDistricts.size()>0){
                for(int i=0;i<builDistricts.size();i++){
                    sep=i>0? " , " :" ";
                    System.out.print(sep);
                    switch(builDistricts.get(i).getColor()){
                        case YELLOW ->  System.out.print(ANSI_YELLOW);
                        case BLUE -> System.out.print(ANSI_BLUE);
                        case RED ->  System.out.print(ANSI_RED);
                        case GREEN -> System.out.print(ANSI_GREEN);
                        case PURPLE -> System.out.print(ANSI_PURPLE);
                    }
                    System.out.print(builDistricts.get(i).getDistrictName() + " = " + builDistricts.get(i).getPrice() + ANSI_RESET );

                }
            }

            System.out.println(" ]");

            System.out.print("\t" + "Hand -> [");
            List<IDistrict> hand=player.getHand();
            if(hand.size()>0){
                for(int i=0;i<hand.size();i++){
                    sep=i>0? " , " :" ";
                    System.out.print(sep);
                    switch(hand.get(i).getColor()){
                        case YELLOW ->  System.out.print(ANSI_YELLOW);
                        case BLUE -> System.out.print(ANSI_BLUE);
                        case RED ->  System.out.print(ANSI_RED);
                        case GREEN -> System.out.print(ANSI_GREEN);
                        case PURPLE -> System.out.print(ANSI_PURPLE);
                    }
                    System.out.print(hand.get(i).getDistrictName() + " = " + hand.get(i).getPrice() + ANSI_RESET );
                }

            }

            System.out.println(" ]");

            System.out.print("\t" + "Hero -> [ ");
            switch(player.getRole().getColor()){
                case YELLOW -> System.out.print(ANSI_YELLOW);
                case PURPLE -> System.out.print(ANSI_PURPLE);
                case RED -> System.out.print(ANSI_RED);
                case BLUE -> System.out.print(ANSI_BLUE);
                case GREEN -> System.out.print(ANSI_GREEN);
            }
            System.out.println( player.getRole().getName() + " " + player.getHeroRank() + ANSI_RESET + " ]\n");
        });

        System.out.println("\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀ \n");
    }
    public static void displayHeroChoice(List<IPlayer> players){
        players.forEach(player -> {
            System.out.print(ANSI_RESET);
            System.out.print(player+" chose: ");
            switch(player.getRole().getColor()){
                case YELLOW -> System.out.print(ANSI_YELLOW);
                case PURPLE -> System.out.print(ANSI_PURPLE);
                case RED -> System.out.print(ANSI_RED);
                case BLUE -> System.out.print(ANSI_BLUE);
                case GREEN -> System.out.print(ANSI_GREEN);
            }
            System.out.println(player.getRole().getName());

        });
        System.out.println(ANSI_RESET);
    }
    public static void displayAssassin(Information information){
        System.out.println(information.getCurrentPlayer()+" has assassinated "+ information.getChosenPlayer());
    }
    public static void displayMagician(Information information){
        System.out.print(information.getCurrentPlayer());
        if(information.getChosenPlayer()!=null){
            System.out.println(" has exchanged hand with "+information.getChosenPlayer());
        }
        else if (information.getChosenCards().size()>0) System.out.println(" has exchanged these cards with deck: "+information.getChosenCards());
        else System.out.println(" has chosen to keep their hand");
    }
    public static void displayKing(Information information){
        System.out.println(ANSI_YELLOW);
        System.out.println("The crown has been passed to "+information.getCurrentPlayer());
        long nobleDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == YELLOW).count();
        if(nobleDistrictNum>0)
        System.out.println(information.getCurrentPlayer()+" gets "+nobleDistrictNum+" extra gold pieces for their noble districts");
    }
    public static void displayMerchant(Information information){
        System.out.println(ANSI_GREEN);
        System.out.println(information.getCurrentPlayer()+" gets an extra gold piece for being the merchant");
        long merchantDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == GREEN).count();
        if(merchantDistrictNum>0)
        System.out.println(information.getCurrentPlayer()+" gets "+merchantDistrictNum+" extra gold pieces for their merchant districts");

    }
    public static void displayTheif(Information information){
        System.out.println(information.getCurrentPlayer()+" has stolen "+information.getChosenPlayer()+"'s gold");

    }

    public static void displayBishop(Information information){
        System.out.print(ANSI_BLUE);
        long religiousDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == BLUE).count();
        if(religiousDistrictNum>0)
        System.out.println(information.getCurrentPlayer()+" gets "+religiousDistrictNum+" extra gold pieces for their religious districts");
    }

    public static void displayAction(Information information){
        HeroName role = information.getCurrentPlayer().getRole().getName();
        System.out.println(role+"'s turn: ");
        switch (role){
            case Assassin -> displayAssassin(information);
            case Thief -> displayTheif(information);
            case Magician -> displayMagician(information);
            case King -> displayKing(information);
            case Bishop -> displayBishop(information);
            default -> displayMerchant(information);
        }
        System.out.println(information.getChoice());
        System.out.print(ANSI_RESET +"\n");

    }
}