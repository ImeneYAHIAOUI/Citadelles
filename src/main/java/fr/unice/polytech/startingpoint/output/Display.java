package fr.unice.polytech.startingpoint.output;

import fr.unice.polytech.startingpoint.cards.*;
import fr.unice.polytech.startingpoint.heros.HeroName;
import fr.unice.polytech.startingpoint.heros.IHero;
import fr.unice.polytech.startingpoint.player.IA.*;
import fr.unice.polytech.startingpoint.player.IPlayer;

import java.util.List;
import static fr.unice.polytech.startingpoint.cards.Color.*;


public abstract class Display {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";



    public Display() {}

    /**
     * Hello World Citadelles
     */
    public static void hello() {
        System.out.println("\n\n ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄            ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
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
                ""+
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀\n");
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
            System.out.println("\t" +showPlayer(player) + " ;");
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

        System.out.println("\n\n\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀\n\n\n");
    }

    /**
     *
     */
    public static void smallBar(String word){
        System.out.println("\n\t" + word +
                "\n ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n");
    }

    /**
     * show bots
     * @param player
     * @return
     */
    public static String showPlayer(IPlayer player){
        String ch="";
        if(player instanceof RandomBot){
            ch= player+", Random bot,";
        }
        if(player instanceof NiceBot){
            ch= player+", nice bot,";
        }
        if(player instanceof Nastybot){
            ch= player+", nasty bot,";
        }
        return ch;



    }

    /**
     *this methode is responsible for displaying the roles that have been chosen by each player
     * before each round
     */
    public static void displayHeroChoice(List<IPlayer> players,int round){
        System.out.println("\tRound : " + round + "\n");
        players.forEach(player -> {
            System.out.print(ANSI_RESET);
            System.out.print("\t"+showPlayer(player)+" has chosen: ");
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
            System.out.println("\t"+showPlayer(information.getCurrentPlayer())+" has assassinated the "+ information.getChosenPlayer().getRole().getName());
        else
            System.out.println("\t"+showPlayer(information.getCurrentPlayer())+" has assassinated a hero that hasn't been chosen by anyone");
    }

    /**
     *
     * this method is responsible for displaying the choice made by the magician
     */
    public static void displayMagician(IAToHero information){
        System.out.println("\t"+HeroName.Magician +"'s turn: ");
        System.out.print("\t"+showPlayer(information.getCurrentPlayer()));
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
        System.out.print(ANSI_RESET);
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
        System.out.print(ANSI_RESET);
    }

    /**
     *this method is responsible for displaying the choice made by the thief
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
        System.out.println("\t"+information.getCurrentPlayer()+"'s districts are protected form the" +ANSI_RED+ " condottiere"+ANSI_BLUE);
        long religiousDistrictNum = information.getCurrentPlayer().getBuiltDistricts().
                stream().filter(d -> d.getColor() == BLUE).count();
        String plural = religiousDistrictNum>1 ? "s":"";
        if(religiousDistrictNum>0)
            System.out.println("\t"+information.getCurrentPlayer()+" gets "+religiousDistrictNum+" extra gold piece"+plural+" for their religious district"+plural);
        System.out.print(ANSI_RESET);
    }
    public static void displayCondottiere(IAToHero information){
        System.out.print(ANSI_RED);
        System.out.println("\t"+HeroName.Condottiere +"'s turn: ");
        if(information.getChosenCards().size()>0) {
            IDistrict destroyedCard = information.getChosenCards().get(0);
            System.out.print("\t" + information.getCurrentPlayer() + " has destroyed " + information.getChosenPlayer() + "s ");
            setColor(destroyedCard.getColor());
            System.out.println(destroyedCard.getDistrictName());

        }else
            System.out.println("\t"+"has chosen not to destroy any district");
        System.out.print(ANSI_RESET);
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

    public static void displayVisibleHeroes(List<IHero> visibleHeroes){
        if (visibleHeroes.size()>0){
            System.out.print("\tvisible drawn heroes : ");
            visibleHeroes.forEach(hero -> {
                setColor(hero.getColor());
                System.out.print(hero.getName()+"  ");
            });
        }
        System.out.println(ANSI_RESET);
    }

    public static void displayHiddenHero(IHero visibleHeroes){
        System.out.print("\thidden drawn hero : ");
        setColor(visibleHeroes.getColor());
        System.out.println(visibleHeroes.getName()+"\n");
        System.out.println(ANSI_RESET);
    }


    public static void displayCemeteryAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenCardOfCemetry() != null){
            System.out.println("\t"+wonderInformation.getplayer() + " has chosen to use the "+ DistrictName.CEMETRY
            +" and take the "+wonderInformation.getChoosenCardOfCemetry().getDistrictName());
        }
    }
    public static void displayLaboratoryAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenCardOfLaboratory() != null){
            System.out.println("\t"+wonderInformation.getplayer() + " has chosen to use the "+DistrictName.LABORATOIRE
                    +" and trade the "+wonderInformation.getChoosenCardOfLaboratory().getDistrictName()+" with a gold piece");
        }
    }
    public static void displayMiracleCourtAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenColorOfMiracleCourt() != null){
            System.out.print(showPlayer(wonderInformation.getplayer()) + " has chosen to use the "+DistrictName.LACOURDESMIRACLES
                    +" and change its color to ");
            setColor(wonderInformation.getChoosenColorOfMiracleCourt());
            System.out.println(wonderInformation.getChoosenColorOfMiracleCourt()+"\n");
            System.out.print(ANSI_RESET);
        }
    }
    public static void displayMagicSchoolAction(IAToWonder wonderInformation){
        if(wonderInformation.getChoosenColorOfMagicSchool() != null){
            System.out.print("\t"+showPlayer(wonderInformation.getplayer()) + " has chosen to use the "+DistrictName.ECOLEDEMAGIE+
                    " and change its color to " );
            setColor(wonderInformation.getChoosenColorOfMagicSchool());
            System.out.println(wonderInformation.getChoosenColorOfMagicSchool());
            System.out.print(ANSI_RESET);
        }
    }
    public static void displayDecisionNeededWonders(IAToWonder wonderInformation){
        displayCemeteryAction(wonderInformation);
        displayLaboratoryAction(wonderInformation);
        displayMiracleCourtAction(wonderInformation);
        displayMagicSchoolAction(wonderInformation);
        System.out.println();
    }

    public static void displayObservatoryAction(IPlayer player){
        if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.OBSERVATORY)){
            System.out.println("\t"+showPlayer(player)+" has the "+DistrictName.OBSERVATORY+", they can draw 3 cards");
        }
    }

    public static void displayLibraryAction(IPlayer player){
        if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.LIBRARY)){
            System.out.println("\t"+showPlayer(player)+" has the "+DistrictName.OBSERVATORY+", they can choose to keep up to two cards");
        }
    }

    public static void displayDonjonAction(IPlayer player){
        if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.DONGEON)){
            System.out.println("\t"+showPlayer(player)+ " has the "+DistrictName.DONGEON+", it can't be destroyed by the "+ANSI_RED+ "condottiere"+ANSI_RESET);
        }
    }

    public static void displayNoDecisionNeededWonders(IPlayer player){
        displayObservatoryAction(player);
        displayLibraryAction(player);
        displayDonjonAction(player);
        System.out.println();
    }

    public static void displayDracoportAction(List<IPlayer> players){
        for(IPlayer player: players ){
            if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.DROCOPORT)){
                System.out.println(showPlayer(player)+ " has the "+DistrictName.DROCOPORT+", its value increases by two");
                break;
            }
        }
    }

    public static void displayUniversityAction(List<IPlayer> players){
        for(IPlayer player: players ){
            if(player.getBuiltDistricts().stream().anyMatch(d -> d.getDistrictName() == DistrictName.UNIVERSITY)){
                System.out.println(showPlayer(player)+ " has the "+DistrictName.UNIVERSITY+", its value increases by two");
                break;
            }
        }
    }

    public static void displayValueIncreaseWonders(List<IPlayer> players){
        displayDracoportAction(players);
        displayUniversityAction(players);
        System.out.println();
    }
    public static void displayEightDistrictsBonus(List<IPlayer> players){
        for(int i = 0; i < players.size(); i++){
            if(i == 0){
               System.out.println(showPlayer(players.get(i))+" has built 8 district first, they get 4 bonus points");
            }else if(players.get(i).getBuiltDistricts().size() == 8){
                System.out.println(showPlayer(players.get(i))+" has built 8 district but wasn't the first to do it, they get 2 bonus points");
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
                System.out.println(showPlayer(player)+" has districts from all categories, they get 3 bonus points");
            }
        });
    }

    public static void displayBonusPoints(List<IPlayer> players){
        displayEightDistrictsBonus(players);
        displayColorDistrictBonus(players);
        System.out.println();
    }

}