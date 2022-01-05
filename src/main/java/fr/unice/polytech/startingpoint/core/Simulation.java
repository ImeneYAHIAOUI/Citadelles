package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import fr.unice.polytech.startingpoint.Citadelles;

import au.com.bytecode.opencsv.CSVWriter;
import fr.unice.polytech.startingpoint.output.TerminalFormatter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulation {
    private FileWriter file;
    private  int mode;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String ANSI_RESET = "\033[0m";  // Text Reset

    // Logger configuration
    static{
        LOGGER.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new TerminalFormatter());
        //handler.setLevel(Level.FINEST);
        LOGGER.addHandler(handler);
    }

    public Simulation(int mode ){
        this.mode=mode;
        try{
            this.file=new FileWriter("./src/main/resources/save/result.csv", true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * simulation
     * @param level
     */
    public void Simulation(Level level) {
        int partieGagne1 = 0;
        int partiePerdue1 = 0;
        int partieNulle1 = 0;
        int partieGagne2 = 0;
        int partiePerdue2 = 0;
        int partieNulle2 = 0;
        int score1 = 0;
        int score2 = 0;
        for (int i = 0; i < 1000; i++) {
            Citadelles citadelle = new Citadelles(level);
            citadelle.game(mode);
            if (citadelle.getPlayers().get(0).getScore() == citadelle.getPlayers().get(1).getScore()) {
                partieNulle1++;
                partieNulle2++;
            } else if (citadelle.getPlayers().get(0).getScore() > citadelle.getPlayers().get(1).getScore()) {
                partieGagne1++;
                partiePerdue2++;
            } else {
                partiePerdue1++;
                partieGagne2++;
            }
            if (citadelle.getPlayers().get(0).getScore() > citadelle.getPlayers().get(1).getScore()) {
                score1 += citadelle.getPlayers().get(0).getScore();
                score2 += citadelle.getPlayers().get(1).getScore();
            } else {
                score1 += citadelle.getPlayers().get(1).getScore();
                score2 += citadelle.getPlayers().get(0).getScore();
            }
        }
        String record []=statisticsToWrite(partieGagne1, partiePerdue1, partieNulle1, score1/1000 , partieGagne2, partiePerdue2, partieNulle2, score2/1000,"./src/main/resources/save/result.csv");
        Write(record);

    }


    /**
     *     write statistics to file
     * @param record
     */
    public  void Write(String[] record){
        //Ecrire les nouvelles statistiques
        CSVWriter writer = new CSVWriter(file);
        if (mode == 1){
            writer.writeNext(new String[]{"SIMULATION1"});
        }else{
            LOGGER.finer( WHITE_BOLD_BRIGHT + ""+mode);
            writer.writeNext(new String[]{"SIMULATION2"});
        }
            //En-tête de fichier
        String[] header = {"PG","% PG"," PP","% PP"," PN",
                    "% PN","SC1"," PG","% PG"," PP","% PP"
                    ," PN","% PN","SC2"};
        writer.writeNext(header);
        //Write the record to file
        writer.writeNext(record);
            //close the writer
        try {
            writer.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    /**
     *
     * @param partieGagne1
     * @param partiePerdue1
     * @param partieNulle1
     * @param score1
     * @param partieGagne2
     * @param partiePerdue2
     * @param partieNulle2
     * @param score2
     * @param path
     * @return
     */
    public String[] statisticsToWrite(int partieGagne1,int partiePerdue1,int partieNulle1,int score1,int partieGagne2,int partiePerdue2,int partieNulle2,int score2,String path){
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
        File fich = new File(path);
        if(fich.length()>0){
            String[] old =oldStatistics(path);
            int PG1=Integer.parseInt(old[0]);
            float PPG1=Float.parseFloat(old[1].replace(',','.'));
            int PG2=Integer.parseInt(old[7]);
            float PPG2=Float.parseFloat(old[8].replace(',','.'));
            int PP1=Integer.parseInt(old[2]);
            float PPP1=Float.parseFloat(old[3].replace(',','.'));
            int PP2=Integer.parseInt(old[9]);
            float PPP2=Float.parseFloat(old[10].replace(',','.'));
            int PN1=Integer.parseInt(old[4]);
            float PPN1=Float.parseFloat(old[5].replace(',','.'));
            int PN2=Integer.parseInt(old[11]);
            float PPN2=Float.parseFloat(old[12].replace(',','.'));
            int sc1=Integer.parseInt(old[6]);
            int sc2=Integer.parseInt(old[13]);
            return new String[] {Integer.toString((partieGagne1 + PG1) / 2), df.format(((float) (partieGagne1 * 0.1 + PPG1) / 2)),
                    Integer.toString((partiePerdue1 + PP1) / 2)
                    , df.format(((float) (partiePerdue1 * 0.1 + PPP1) / 2)), Integer.toString((partieNulle1 + PN1) / 2), df.format(((float) (partieNulle1 * 0.1 + PPN1) / 2)),
                    Integer.toString((score1 + sc1) / 2), Integer.toString((partieGagne2 + PG2) / 2), df.format(((float) (partieGagne2 * 0.1 + PPG2) / 2)),
                    Integer.toString((partiePerdue2 + PP2) / 2), df.format(((float) (partiePerdue2 * 0.1 + PPP2) / 2))
                    , Integer.toString((partieNulle2 + PN2) / 2), df.format(((float) (partieNulle2 * 0.1 + PPN2) / 2)), Integer.toString((score2 + sc2) / 2)};
        }else{
            return
                new String[] {Integer.toString(partieGagne1 ), df.format(partieGagne1*0.1 ),
                        Integer.toString(partiePerdue1 )
                        , df.format(partiePerdue1 * 0.1), Integer.toString(partieNulle1 ), df.format(partieNulle1 * 0.1),
                        Integer.toString(score1 ), Integer.toString(partieGagne2 ), df.format(partieGagne2 * 0.1),
                        Integer.toString(partiePerdue2 ), df.format(partiePerdue2 * 0.1 )
                        , Integer.toString(partieNulle2 ), df.format(partieNulle2 * 0.1 ), Integer.toString(score2 )};
        }
    }



    /**
     * old statistics
     * @return
     */
    public  String[]  oldStatistics(String path){
        String[] old={"0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
        //récupérer les anciennes statistiques1
        if(Files.exists(Paths.get(path))){
            File fich = new File(path);
            if(fich.length()>0){
                try {
                    CSVReader reader = new CSVReader(new FileReader(path), ',', '"', 1);
                    List<String[]> allRows = reader.readAll();
                    for(int i=0;i<allRows.size();i++){
                        if((allRows.get(allRows.size()-1-i)[0]).equals("SIMULATION1") && mode==1  ){
                            old=allRows.get(allRows.size()-1-i+2);
                            break;
                        }
                        if((allRows.get(allRows.size()-1-i)[0]).equals("SIMULATION2") && mode==2){

                            old=allRows.get(allRows.size()-1-i+2);
                            break;
                        }
                        if(mode==1 && allRows.size()>=2 && i==allRows.size()-1 ){
                            old=allRows.get(1);
                            break;
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }}
        return old;
    }

    /**
     * show statistics
     * @param level
     */
    public  void showResult(Level level){
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/main/resources/save/result.csv"), ',', '"', 1);
            List<String[]> allRows = reader.readAll();

            String[] sim2=allRows.get(allRows.size()-1);
            String[] sim1=allRows.get(allRows.size()-4);

            List<String[]> list = new ArrayList<>();
            list.add(sim1);
            list.add(sim2);

            final int[] numSim = {1};

            list.forEach(sim -> {

                if(numSim[0] == 1) {
                    this.simulation1display();
                    numSim[0]++;
                }else {
                    this.simulation2display();
                }

                LOGGER.finer(BLUE_BOLD_BRIGHT + "\n\t_______________________________________________________________________________________________________________________" + WHITE_BOLD_BRIGHT);
                LOGGER.finer( "\n\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Gagnée1 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"% Partie Gagnée1 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Perdue1  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Perdue1  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Nulle1 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Nulle1    "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+" SCORE1        "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"\n");
                LOGGER.finer("\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"");
                int i = 1;
                for(String el:sim){
                    LOGGER.finer( String.format("%.4s             "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT, el));
                    if(i == 7) {
                        LOGGER.finer(BLUE_BOLD_BRIGHT + "\n\t_______________________________________________________________________________________________________________________\n" + WHITE_BOLD_BRIGHT);
                        LOGGER.finer("\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Gagnée2 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"% Partie Gagnée2 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Perdue2  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Perdue2  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Nulle2  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Nulle2   "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+" SCORE2        "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"\n");
                        LOGGER.finer("\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"");
                    }
                    i++;
                }
                LOGGER.finer(BLUE_BOLD_BRIGHT + "\n\t_______________________________________________________________________________________________________________________\n" + WHITE_BOLD_BRIGHT);

            });
/*
            LOGGER.finer( WHITE_BOLD_BRIGHT + "\n\n");
            LOGGER.finer( WHITE_BOLD_BRIGHT + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"*SIMULATION2*\n");
            for(String el:sim2){
                LOGGER.finer( WHITE_BOLD_BRIGHT + String.format("%s | ", el));
            }
*/
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void simulation1display(){
        LOGGER.finer(  BLUE_BOLD_BRIGHT + "\n\n" +
                "███████╗██╗███╗   ███╗██╗   ██╗██╗      █████╗ ████████╗██╗ ██████╗ ███╗   ██╗     ██╗\n" +
                "██╔════╝██║████╗ ████║██║   ██║██║     ██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║    ███║\n" +
                "███████╗██║██╔████╔██║██║   ██║██║     ███████║   ██║   ██║██║   ██║██╔██╗ ██║    ╚██║\n" +
                "╚════██║██║██║╚██╔╝██║██║   ██║██║     ██╔══██║   ██║   ██║██║   ██║██║╚██╗██║     ██║\n" +
                "███████║██║██║ ╚═╝ ██║╚██████╔╝███████╗██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║     ██║\n" +
                "╚══════╝╚═╝╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═╝" + WHITE_BOLD_BRIGHT);
    }

    private void simulation2display(){
        LOGGER.finer(  BLUE_BOLD_BRIGHT + "\n\n" +
                "███████╗██╗███╗   ███╗██╗   ██╗██╗      █████╗ ████████╗██╗ ██████╗ ███╗   ██╗    ██████╗ \n" +
                "██╔════╝██║████╗ ████║██║   ██║██║     ██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║    ╚════██╗\n" +
                "███████╗██║██╔████╔██║██║   ██║██║     ███████║   ██║   ██║██║   ██║██╔██╗ ██║     █████╔╝\n" +
                "╚════██║██║██║╚██╔╝██║██║   ██║██║     ██╔══██║   ██║   ██║██║   ██║██║╚██╗██║    ██╔═══╝ \n" +
                "███████║██║██║ ╚═╝ ██║╚██████╔╝███████╗██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║    ███████╗\n" +
                "╚══════╝╚═╝╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝    ╚══════╝" + WHITE_BOLD_BRIGHT);
    }

    /**
     *
     * @param file
     */
    public void setFile(FileWriter file){
        this.file=file;
    }

}
