package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.*;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;
import static fr.unice.polytech.startingpoint.cards.Color.*;

import java.util.logging.*;


public abstract class Display{
    // Reset
    public static final String ANSI_RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String ANSI_BLACK = "\033[0;30m";   // BLACK
    public static final String ANSI_RED = "\033[0;31m";     // RED
    public static final String ANSI_GREEN = "\033[0;32m";   // GREEN
    public static final String ANSI_YELLOW = "\033[0;33m";  // YELLOW
    public static final String ANSI_BLUE = "\033[0;34m";    // BLUE
    public static final String ANSI_PURPLE = "\033[0;35m";  // PURPLE
    public static final String ANSI_CYAN = "\033[0;36m";    // CYAN
    public static final String ANSI_WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Logger configuration
    static{
        LOGGER.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new TerminalFormatter());
        handler.setLevel(Level.FINEST);
        LOGGER.addHandler(handler);
    }
    /**
     *
     * @param level
     */
    public static void initLogger(Level level){
        /**
         * SEVERE (valeur la plus élevée)
         * WARNING
         * INFO
         * CONFIG
         * FINE
         * FINER
         * FINEST (valeur la plus basse)
         */
        LOGGER.setLevel(level);
    }

    /**
     * Hello World Citadelles
     */
    public static void hello() {
        LOGGER.finest( YELLOW_BOLD_BRIGHT + "\n\n ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄            ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░▌          ▐░▌          ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌          ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ \n" +
                "▐░▌               ▐░▌          ▐░▌     ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌          ▐░▌          ▐░▌          ▐░▌          \n" +
                "▐░▌               ▐░▌          ▐░▌     ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌          ▐░▌          ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ \n" +
                "▐░▌               ▐░▌          ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌          ▐░▌          ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                "▐░▌               ▐░▌          ▐░▌     ▐░█▀▀▀▀▀▀▀█░▌▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌          ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀█░▌\n" +
                "▐░▌               ▐░▌          ▐░▌     ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌          ▐░▌          ▐░▌                    ▐░▌\n" +
                "▐░█▄▄▄▄▄▄▄▄▄  ▄▄▄▄█░█▄▄▄▄      ▐░▌     ▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄█░▌\n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░▌       ▐░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀       ▀       ▀         ▀  ▀▀▀▀▀▀▀▀▀▀   ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀ \n" +
                "                                                                                                                                  " +
                "                                                                                                                     \n\n" +
                ""+ ANSI_WHITE +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀\n" + ANSI_RESET);
    }

    public static String displayRank(List<IPlayer> players) {
        StringBuilder ranking;
        int rank = 1;
        ranking = new StringBuilder("1st place : " + showPlayer(players.get(0)) + " -> " + players.get(0).getScore() + " points.\n");
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getScore() != players.get(i - 1).getScore()) rank = i + 1;
                String s = switch (rank) {
                    case 1 -> "st";
                    case 2 -> "nd";
                    case 3 -> "rd";
                    default -> "th";

                };
            ranking.append(rank+s+" place : "+showPlayer(players.get(i))+" -> "+players.get(i).getScore()+" points.\n");


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
        LOGGER.finest( displayWinners(rankedPlayers)+displayRank(rankedPlayers));
    }

    /**
     * Displays all elements of the players of the round
     * @param playersList
     */
    static public void round(List<IPlayer> playersList){

        playersList.forEach(player -> {
            if(player.getCrown()) {
                LOGGER.finest( ANSI_YELLOW + "\t,  ,() , ,\n" +
                        "\t|\\/\\/\\/\\/|\n" +
                        "\t|_o_<>_o_|\n" + ANSI_RESET + WHITE_BOLD_BRIGHT);
            }
            LOGGER.finest( "\t" +showPlayer(player) + " ;\n" + WHITE_BOLD_BRIGHT);
            LOGGER.finest( "\tOR : " + player.getGold() + "\n");


            LOGGER.finest( "\t" + "District -> [ ");
            List<IDistrict> builtDistricts= player.getBuiltDistricts();
            displayDistrictList(builtDistricts);


            LOGGER.finest( WHITE_BOLD_BRIGHT + " ]\n");

            LOGGER.finest( "\t" + "Hand -> [");
            List<IDistrict> hand=player.getHand();
            displayDistrictList(hand);

            LOGGER.finest( WHITE_BOLD_BRIGHT +" ]\n");

            LOGGER.finest( "\t" + "Hero -> [ ");
            setColor(player.getRole().getColor());
            LOGGER.finest( player.getRole().getName() + " " + player.getHeroRank() + ANSI_RESET + WHITE_BOLD_BRIGHT +" ]\n\n");
        });

        LOGGER.finest( "\n\n\n" + WHITE_BOLD_BRIGHT +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀\n\n\n" + ANSI_RESET);
    }

    /**
     *
     */
    public static void smallBar(String word){
        LOGGER.finest( "\n\t" + WHITE_BOLD_BRIGHT + word +
                "\n ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀  "+ ANSI_RESET +"\n\n");
    }

    /**
     * show bots
     * @param player
     * @return
     */
    public static String showPlayer(IPlayer player){
        String ch="";
        if(player instanceof RandomBot){
            ch= player+WHITE_BOLD_BRIGHT +" Random bot ";
        }
        if(player instanceof NiceBot){
            ch= player+WHITE_BOLD_BRIGHT +" nice bot ";
        }
        if(player instanceof Nastybot){
            ch= player+WHITE_BOLD_BRIGHT +" nasty bot ";
        }
        if(player instanceof  NeutralBot){
            ch= player+WHITE_BOLD_BRIGHT +" neutral bot ";

        }
        if(player instanceof  BuilderBot){
            ch= player+WHITE_BOLD_BRIGHT +" builder bot ";

        }



        return ch;



    }

    /**
     *this methode is responsible for displaying the roles that have been chosen by each player
     * before each round
     */
    public static void displayHeroChoice(List<IPlayer> players,int round){
        LOGGER.finest( WHITE_BOLD_BRIGHT +"\tRound : " + round + "\n\n");
        players.forEach(player -> {
            LOGGER.finest(ANSI_RESET +WHITE_BOLD_BRIGHT);
            LOGGER.finest( "\t"+showPlayer(player)+" has chosen: ");
            setColor(player.getRole().getColor());

            LOGGER.finest( ""+player.getRole().getName());

            LOGGER.finest(ANSI_RESET+WHITE_BOLD_BRIGHT);/*
            if(player.getRole().getName() == HeroName.King)
                LOGGER.finest("\t");*/
            LOGGER.finest( "\t\tThought path : " + ((IA)player).thoughtPathList + "\n");
        });
        LOGGER.finest(ANSI_RESET);
    }

    /**
     * this method is responsible for displaying the choice made by the assassin
     *
     */
    public static void displayAssassin(IAToHero information){
        LOGGER.finest( "\t"+HeroName.Assassin +"'s"+WHITE_BOLD_BRIGHT +" turn: \n"+WHITE_BOLD_BRIGHT);
        if(information.getChosenPlayer() != null)
            LOGGER.finest( "\t"+showPlayer(information.getCurrentPlayer())+" has assassinated the "+ information.getChosenPlayer().getRole().getName()+"\n");
        else
            LOGGER.finest( "\t"+showPlayer(information.getCurrentPlayer())+" has assassinated a hero that hasn't been chosen by anyone\n");
    }

    /**
     *
     * this method is responsible for displaying the choice made by the magician
     */
    public static void displayMagician(IAToHero information){
        LOGGER.finest( "\t"+HeroName.Magician +"'s "+WHITE_BOLD_BRIGHT +"turn: \n"+WHITE_BOLD_BRIGHT);
        LOGGER.finest( "\t"+showPlayer(information.getCurrentPlayer()));
        if(information.getChosenPlayer()!=null){
            LOGGER.finest( "\thas exchanged hand with "+information.getChosenPlayer()+"\n");
        }
        else if(information.getChosenCards().size()>0){
            LOGGER.finest( " has exchanged these cards with deck: "+ANSI_RESET+"[");
            displayDistrictList(information.getChosenCards());
            LOGGER.finest( "]\n");
        }

        else LOGGER.finest( "\t has chosen to keep their hand\n");
    }

    /**
     *
     * this method is responsible for displaying the choice made by the king
     */
    public static void displayKing(IAToHero information){
        LOGGER.finest(ANSI_YELLOW);
        LOGGER.finest( "\t"+HeroName.King +"'s"+WHITE_BOLD_BRIGHT +" turn: \n"+WHITE_BOLD_BRIGHT);
        LOGGER.finest( "\tThe crown has been passed to "+information.getCurrentPlayer()+"\n");
        long nobleDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == YELLOW).count();
        String plural = nobleDistrictNum>1 ? "s":"";
        if(nobleDistrictNum>0)
        LOGGER.finest( "\t"+information.getCurrentPlayer()+" gets "+nobleDistrictNum+" extra gold piece"+plural+" for their noble district"+plural+"\n");
        LOGGER.finest(ANSI_RESET);
    }
    /**
     * this method is responsible for displaying the choice made by the merchant
     */
    public static void displayMerchant(IAToHero information){
        LOGGER.finest(ANSI_GREEN);
        LOGGER.finest( "\t"+HeroName.Merchant +"'s "+WHITE_BOLD_BRIGHT +"turn: \n"+WHITE_BOLD_BRIGHT);
        LOGGER.finest( "\t"+information.getCurrentPlayer()+" gets an extra gold piece for being the merchant\n");
        long merchantDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == GREEN).count();
        String plural = merchantDistrictNum>1 ? "s":"";
        if(merchantDistrictNum>0)
        LOGGER.finest( "\t"+information.getCurrentPlayer()+" gets "+merchantDistrictNum+" extra gold piece"+plural+" for their merchant district"+plural+"\n");
        LOGGER.finest(ANSI_RESET);
    }

    /**
     *this method is responsible for displaying the choice made by the thief
     */
    public static void displayThief(IAToHero information){
        LOGGER.finest( "\t"+HeroName.Thief +"'s "+WHITE_BOLD_BRIGHT +"turn: \n"+WHITE_BOLD_BRIGHT);
        if(information.getChosenPlayer() != null)
            LOGGER.finest( "\t"+information.getCurrentPlayer()+" has stolen the "+information.getChosenPlayer().getRole().getName()+"'s gold\n");
        else
            LOGGER.finest( "\t"+information.getCurrentPlayer()+" has stolen from a hero that hasn't been chosen by anyone\n");

    }

    /**
     *this method is responsible for displaying the choice made by the bishop
     */
    public static void displayBishop(IAToHero information){
        LOGGER.finest(ANSI_BLUE);
        LOGGER.finest( "\t"+HeroName.Bishop +"'s"+WHITE_BOLD_BRIGHT +" turn: \n"+WHITE_BOLD_BRIGHT );
        LOGGER.finest( "\t"+information.getCurrentPlayer()+"'s districts are protected form the" +ANSI_RED+ " condottiere"+ANSI_BLUE+"\n");
        long religiousDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == BLUE).count();
        String plural = religiousDistrictNum>1 ? "s":"";
        if(religiousDistrictNum>0)
            LOGGER.finest( "\t"+information.getCurrentPlayer()+" gets "+religiousDistrictNum+" extra gold piece"+plural+" for their religious district"+plural+"\n");
        LOGGER.finest(ANSI_RESET);
    }
    public static void displayCondottiere(IAToHero information){
        LOGGER.finest(ANSI_RED);
        LOGGER.finest( "\t"+HeroName.Condottiere +"'s " +WHITE_BOLD_BRIGHT +" turn: \n");
        if(information.getChosenCards().size()>0) {
            IDistrict destroyedCard = information.getChosenCards().get(0);
            LOGGER.finest( "\t" + information.getCurrentPlayer() + " has destroyed " + information.getChosenPlayer() + "s ");
            setColor(destroyedCard.getColor());
            LOGGER.finest( ""+destroyedCard.getDistrictName()+"\n");

        }else
            LOGGER.finest( "\t"+"has chosen not to destroy any district\n");
        LOGGER.finest(ANSI_RESET);
    }


    public static void displayArchitect(IAToHero information){
        LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +HeroName.Architect +"'s " + WHITE_BOLD_BRIGHT +" turn: \n");
        LOGGER.finest( "\t"+information.getCurrentPlayer()+" gets to draw two additional cards\n");
        LOGGER.finest( "\t"+information.getCurrentPlayer()+" can build up to 3 districts\n");
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
        LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +information.getChoice() + "\n");
        if(information.getBuiltDistrict().size()>0) {
            LOGGER.finest( "\t"+information.getCurrentPlayer()+" has chosen to build "+ANSI_RESET+WHITE_BOLD_BRIGHT +"[");
            displayDistrictList(information.getBuiltDistrict());
            LOGGER.finest( WHITE_BOLD_BRIGHT +" ]\n");
        }
        LOGGER.finest( ANSI_RESET +"\n");

    }

    /**
     * displays a list of districts colored according to their family (hand, built districts, exchanged districts, ...)
     */
    public static void displayDistrictList(List<IDistrict> districtList){
        for(int i=0;i<districtList.size();i++){
            String sep=i>0? WHITE_BOLD_BRIGHT +" , " :WHITE_BOLD_BRIGHT +" ";
            LOGGER.finest(sep);
            setColor(districtList.get(i).getColor());
            LOGGER.finest( districtList.get(i).getDistrictName() + " = " + districtList.get(i).getPrice() + ANSI_RESET );
        }
    }

    /**
     *
     * sets a display color according to the parameter
     */
    public static void setColor(Color color){
        switch(color){
            case YELLOW ->  LOGGER.finest(ANSI_YELLOW);
            case BLUE -> LOGGER.finest(ANSI_BLUE);
            case RED ->  LOGGER.finest(ANSI_RED);
            case GREEN -> LOGGER.finest(ANSI_GREEN);
            case PURPLE -> LOGGER.finest(ANSI_PURPLE);
        }
    }

    public static void displayVisibleHeroes(List<IHero> visibleHeroes){
        if (visibleHeroes.size()>0){
            LOGGER.finest( WHITE_BOLD_BRIGHT +"\tvisible drawn heroes : ");
            visibleHeroes.forEach(hero -> {
                setColor(hero.getColor());
                LOGGER.finest( hero.getName()+"  ");
            });
        }
        LOGGER.finest(ANSI_RESET);
    }

    public static void displayHiddenHero(IHero visibleHeroes){
        LOGGER.finest( WHITE_BOLD_BRIGHT + "\thidden drawn hero : ");
        setColor(visibleHeroes.getColor());
        LOGGER.finest( visibleHeroes.getName()+"\n");
        LOGGER.finest(ANSI_RESET);
    }


    public static void displayCemeteryAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenCardOfCemetry() != null){
            LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +wonderInformation.getplayer() + " has chosen to use the "+ DistrictName.CEMETRY
            +" and take the "+wonderInformation.getChoosenCardOfCemetry().getDistrictName() + "\n");
        }
    }
    public static void displayLaboratoryAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenCardOfLaboratory() != null){
            LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +wonderInformation.getplayer() + " has chosen to use the "+DistrictName.LABORATOIRE
                    +" and trade the "+wonderInformation.getChoosenCardOfLaboratory().getDistrictName()+" with a gold piece\n");
        }
    }
    public static void displayMiracleCourtAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenColorOfMiracleCourt() != null){
            LOGGER.finest( WHITE_BOLD_BRIGHT +showPlayer(wonderInformation.getplayer()) + " has chosen to use the "+DistrictName.LACOURDESMIRACLES
                    +" and change its color to \n");
            setColor(wonderInformation.getChoosenColorOfMiracleCourt());
            LOGGER.finest( wonderInformation.getChoosenColorOfMiracleCourt()+"\n");
            LOGGER.finest(ANSI_RESET);
        }
    }
    public static void displayMagicSchoolAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenColorOfMagicSchool() != null){
            LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +showPlayer(wonderInformation.getplayer()) + " has chosen to use the "+DistrictName.ECOLEDEMAGIE+
                    " and change its color to \n" );
            setColor(wonderInformation.getChoosenColorOfMagicSchool());
            LOGGER.finest( ""+wonderInformation.getChoosenColorOfMagicSchool());
            LOGGER.finest(ANSI_RESET);
        }
    }
    public static void displayDecisionNeededWonders(IAToWonder wonderInformation){
        displayCemeteryAction(wonderInformation);
        displayLaboratoryAction(wonderInformation);
        displayMiracleCourtAction(wonderInformation);
        displayMagicSchoolAction(wonderInformation);
        LOGGER.finest( "\n");
    }

    public static void displayObservatoryAction(IPlayer player){
        if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.OBSERVATORY)){
            LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +showPlayer(player)+" has the "+DistrictName.OBSERVATORY+", they can draw 3 cards\n");
        }
    }

    public static void displayLibraryAction(IPlayer player){
        if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.LIBRARY)){
            LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +showPlayer(player)+" has the "+DistrictName.OBSERVATORY+", they can choose to keep up to two cards\n");
        }
    }

    public static void displayDonjonAction(IPlayer player){
        if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.DONGEON)){
            LOGGER.finest( "\t"+WHITE_BOLD_BRIGHT +showPlayer(player)+ " has the "+DistrictName.DONGEON+", it can't be destroyed by the "+ANSI_RED+ "condottiere"+ANSI_RESET + "\n");
        }
    }

    public static void displayNoDecisionNeededWonders(IPlayer player){
        displayObservatoryAction(player);
        displayLibraryAction(player);
        displayDonjonAction(player);
        LOGGER.finest( "\n");
    }

    public static void displayDracoportAction(List<IPlayer> players){
        for(IPlayer player: players ){
            if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.DROCOPORT)){
                LOGGER.finest(WHITE_BOLD_BRIGHT + showPlayer(player)+ " has the "+DistrictName.DROCOPORT+", its value increases by two\n");
                break;
            }
        }
    }

    public static void displayUniversityAction(List<IPlayer> players){
        for(IPlayer player: players ){
            if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.UNIVERSITY)){
                LOGGER.finest(WHITE_BOLD_BRIGHT + showPlayer(player)+ " has the "+DistrictName.UNIVERSITY+", its value increases by two\n");
                break;
            }
        }
    }

    public static void displayValueIncreaseWonders(List<IPlayer> players){
        displayDracoportAction(players);
        displayUniversityAction(players);
        LOGGER.finest( "\n");
    }
    public static void displayEightDistrictsBonus(List<IPlayer> players){
        for(int i = 0; i < players.size(); i++){
            if(i == 0){
               LOGGER.finest( WHITE_BOLD_BRIGHT +showPlayer(players.get(i))+" has built 8 district first, they get 4 bonus points\n");
            }else if(players.get(i).getBuiltDistricts().size() == 8){
                LOGGER.finest( showPlayer(players.get(i))+" has built 8 district but wasn't the first to do it, they get 2 bonus points\n");
            }
        }
    }

    public static void displayColorDistrictBonus(List<IPlayer> players){
        players.forEach(player -> {
            int val = 0;

            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.YELLOW))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.RED))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.BLUE))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.PURPLE))
                val ++;
            if(player.getBuiltDistricts().stream().map(district -> district.getColor()).anyMatch(d -> d == Color.GREEN))
                val ++;

            if(val == 5){
                LOGGER.finest( showPlayer(player)+" has districts from all categories, they get 3 bonus points\n");
            }
        });
    }

    public static void displayBonusPoints(List<IPlayer> players){
        displayEightDistrictsBonus(players);
        displayColorDistrictBonus(players);
        LOGGER.finest( "\n");
    }

}