package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import fr.unice.polytech.startingpoint.Citadelles;

import au.com.bytecode.opencsv.CSVWriter;
import fr.unice.polytech.startingpoint.output.TerminalFormatter;
import fr.unice.polytech.startingpoint.player.IPlayer;

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

        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");

        int partieGagne1 = 0;
        int partiePerdue1 = 0;
        int partieNulle1 = 0;
        int partieGagne2 = 0;
        int partiePerdue2 = 0;
        int partieNulle2 = 0;
        int score1 = 0;
        int score2 = 0;
        List<IPlayer> players;
        for (int i = 0; i < 1000; i++) {
            Citadelles citadelle = new Citadelles(level);
             players=citadelle.game(mode);
            if (players.get(0).getScore() == players.get(1).getScore()) {
                partieNulle1++;
                partieNulle2++;
            } else if (players.get(0).getScore() >players.get(1).getScore()) {
                partieGagne1++;
                partiePerdue2++;
            } else {
                partiePerdue1++;
                partieGagne2++;
            }
            if (players.get(0).getScore() > players.get(1).getScore()) {
                score1 += players.get(0).getScore();
                score2 += players.get(1).getScore();
            } else {
                score1 += players.get(1).getScore();
                score2 += players.get(0).getScore();
            }
        }
        showResult(List.of(Integer.toString(partieGagne1 ), df.format(partieGagne1*0.1 ),
                Integer.toString(partiePerdue1 )
                , df.format(partiePerdue1 * 0.1), Integer.toString(partieNulle1 ), df.format(partieNulle1 * 0.1),
                Integer.toString(score1 ), Integer.toString(partieGagne2 ), df.format(partieGagne2 * 0.1),
                Integer.toString(partiePerdue2 ), df.format(partiePerdue2 * 0.1 )
                , Integer.toString(partieNulle2 ), df.format(partieNulle2 * 0.1 ), Integer.toString(score2 )));
        String record []=statisticsToWrite(partieGagne1, partiePerdue1, partieNulle1, score1/1000 , partieGagne2, partiePerdue2, partieNulle2, score2/1000,"./src/main/resources/save/result.csv");
        Write(record);

    }
    public Boolean WriteHeaderOrNot(){
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/main/resources/save/result.csv"), ',', '"', 1);
            List<String[]> allRows = reader.readAll();
            if (allRows.size() > 0) {
                return false;
            }
        }catch(Exception e){
                e.printStackTrace();
                return false;
        }
        return true;

    }


    /**
     *     write statistics to file
     * @param record
     */
    public  void Write(String[] record){

        //Ecrire les nouvelles statistiques
        CSVWriter writer = new CSVWriter(file);
        if(WriteHeaderOrNot()){
            //En-tête de fichier
            String[] header = {"PG","% PG"," PP","% PP"," PN",
                    "% PN","SC1"," PG","% PG"," PP","% PP"
                    ," PN","% PN","SC2"};
            writer.writeNext(header);
        }
        if (mode == 1){
            writer.writeNext(new String[]{"SIMULATION1"});
        }else{
            LOGGER.finer( WHITE_BOLD_BRIGHT + ""+mode);
            writer.writeNext(new String[]{"SIMULATION2"});
        }

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
    public String[] statisticsToWrite(int partieGagne1,int partiePerdue1,int partieNulle1,int score1,int partieGagne2,int partiePerdue2,int partieNulle2,int score2,String path) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
        try {
            CSVReader reader = new CSVReader(new FileReader(path), ',', '"', 1);
            List<String[]> allRows = reader.readAll();
            String[] old = oldStatistics(path);
            int PG1 = Integer.parseInt(old[0]);
            float PPG1 = Float.parseFloat(old[1].replace(',', '.'));
            int PG2 = Integer.parseInt(old[7]);
            float PPG2 = Float.parseFloat(old[8].replace(',', '.'));
            int PP1 = Integer.parseInt(old[2]);
            float PPP1 = Float.parseFloat(old[3].replace(',', '.'));
            int PP2 = Integer.parseInt(old[9]);
            float PPP2 = Float.parseFloat(old[10].replace(',', '.'));
            int PN1 = Integer.parseInt(old[4]);
            float PPN1 = Float.parseFloat(old[5].replace(',', '.'));
            int PN2 = Integer.parseInt(old[11]);
            float PPN2 = Float.parseFloat(old[12].replace(',', '.'));
            int sc1 = Integer.parseInt(old[6]);
            int sc2 = Integer.parseInt(old[13]);
            int lines=(allRows.size()/4);
            return new String[]{Integer.toString((partieGagne1 + lines * PG1) / (lines+1)), df.format(((float) (partieGagne1 * 0.1 + 2 * PPG1) / 2)),
                        Integer.toString((partiePerdue1 + lines * PP1) / (lines+1))
                        , df.format(((float) (partiePerdue1 * 0.1 + lines * PPP1) / (lines+1))), Integer.toString((partieNulle1 + lines * PN1) / (lines+1)), df.format(((float) (partieNulle1 * 0.1 + lines * PPN1) / (lines+1))),
                        Integer.toString((score1 + lines * sc1) / (lines+1)), Integer.toString((partieGagne2 + lines * PG2) / (lines+1)), df.format(((float) (partieGagne2 * 0.1 + lines * PPG2) / (lines+1))),
                        Integer.toString((partiePerdue2 + lines * PP2) /(lines+1)), df.format(((float) (partiePerdue2 * 0.1 + lines * PPP2) / (lines+1)))
                        , Integer.toString((partieNulle2 + lines * PN2) / (lines+1)), df.format(((float) (partieNulle2 * 0.1 + lines* PPN2) / (lines+1))), Integer.toString((score2 + lines * sc2) / (lines+1))};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
                            old=allRows.get(allRows.size()-i);
                            break;
                        }
                        if((allRows.get(allRows.size()-1-i)[0]).equals("SIMULATION2") && mode==2){

                            old=allRows.get(allRows.size()-i);
                            break;
                        }
                        if(mode==1 && allRows.size()>=1 && i==allRows.size()-1 ){
                            old=allRows.get(0);
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
     *
     * @param simulation
     */
    public  void showResult(List<String> simulation){

            if(mode == 1) {
                    this.simulation1display();
            }else {
                    this.simulation2display();
            }
        LOGGER.finer(BLUE_BOLD_BRIGHT + "\n\t_______________________________________________________________________________________________________________________" + WHITE_BOLD_BRIGHT);
        LOGGER.finer( "\n\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Gagnée1 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"% Partie Gagnée1 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Perdue1  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Perdue1  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Nulle1 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Nulle1    "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+" SCORE1        "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"\n");
        LOGGER.finer("\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"");
        int i = 1;
        for(String el:simulation){
            LOGGER.finer( String.format("%.4s             "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT, el));
            if(i == 7) {
                LOGGER.finer(BLUE_BOLD_BRIGHT + "\n\t_______________________________________________________________________________________________________________________\n" + WHITE_BOLD_BRIGHT);
                LOGGER.finer("\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Gagnée2 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"% Partie Gagnée2 "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Perdue2  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Perdue2  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"Partie Nulle2  "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"%Partie Nulle2   "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+" SCORE2        "+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"\n");
                LOGGER.finer("\t"+BLUE_BOLD_BRIGHT+"|"+WHITE_BOLD_BRIGHT+"");
            }
            i++;
        }
        LOGGER.finer(BLUE_BOLD_BRIGHT + "\n\t_______________________________________________________________________________________________________________________\n" + WHITE_BOLD_BRIGHT);

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
