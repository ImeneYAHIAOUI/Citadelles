package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.player.IPlayer;
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

        playersList.forEach(player -> {
            if(player.isKing()) {
                System.out.print(ANSI_YELLOW + "\t,  ,() , ,\n" +
                        "\t|\\/\\/\\/\\/|\n" +
                        "\t|_o_<>_o_|\n" + ANSI_RESET);
            }

            System.out.println("\t" + player + " ;");
            //System.out.println("\tOR : " + player.getGold());

            System.out.print("\t" + "District -> [ ");
            player.getHand().forEach(district -> {
                switch(district.getColor()){
                    case YELLOW ->  System.out.print(ANSI_YELLOW + district.getDistrictName() + " = "+district.getPrice() + ANSI_RESET +" , ");
                    case BLUE -> System.out.print(ANSI_BLUE + district.getDistrictName() + " = "+district.getPrice() + ANSI_RESET +" , ");
                    case RED ->  System.out.print(ANSI_RED + district.getDistrictName() + " = "+district.getPrice() + ANSI_RESET +" , ");
                    case GREEN -> System.out.print(ANSI_GREEN + district.getDistrictName() + " = "+district.getPrice() + ANSI_RESET +" , ");
                    case PURPLE -> System.out.print(ANSI_PURPLE + district.getDistrictName() + " = "+district.getPrice() + ANSI_RESET +" , ");

                };



            });
            System.out.println(" ]");

            System.out.print("\t" + "Hero -> [ ");
            switch(player.getRole().getColor()){
                case YELLOW -> System.out.print(ANSI_YELLOW);
                case PURPLE -> System.out.print(ANSI_PURPLE);
                case RED -> System.out.print(ANSI_RED);
                case BLUE -> System.out.print(ANSI_BLUE);
                case GREEN -> System.out.print(ANSI_GREEN);
            }
            System.out.println( player.getRole().getName() + " " + player.getTheHeroRank() + ANSI_RESET + " ]\n");
        });

        System.out.println("\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀ \n");
    }
}