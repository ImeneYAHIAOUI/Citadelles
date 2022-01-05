package fr.unice.polytech.startingpoint.core;

import au.com.bytecode.opencsv.CSVReader;
import fr.unice.polytech.startingpoint.Citadelles;
import fr.unice.polytech.startingpoint.cards.IAToWonder;
import fr.unice.polytech.startingpoint.output.GameResult;
import fr.unice.polytech.startingpoint.player.CircularList;
import fr.unice.polytech.startingpoint.player.IA.IAToHero;
import fr.unice.polytech.startingpoint.player.IPlayer;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Simulation {

    /**
     * simulation  number 1
     */
    public static void Simulation1() {
        int partieGagne1 = 0;
        int partiePerdue1 = 0;
        int partieNulle1 = 0;
        int partieGagne2 = 0;
        int partiePerdue2 = 0;
        int partieNulle2 = 0;
        int score1 = 0;
        int score2 = 0;
        for (int i = 0; i < 1000; i++) {

            Citadelles citadelle = new Citadelles();
            citadelle.game(1);
            if( citadelle.getPlayers().get(0).getScore() == citadelle.getPlayers().get(1).getScore()) {
                partieNulle1++;
                partieNulle2++;
            } else if (citadelle.getPlayers().get(0).getScore() > citadelle.getPlayers().get(1).getScore()) {

                partieGagne1++;
                partiePerdue2++;
            } else {
                partiePerdue1++;
                partieGagne2++;
            }
            score1 += citadelle.getPlayers().get(1).getScore();
            score2 += citadelle.getPlayers().get(0).getScore();

        }
        Write(partieGagne1,partiePerdue1,partieNulle1,score1/1000,partieGagne2,partiePerdue2,partieNulle2,score2/1000);


    }
    /**
     * simulation number 2
     */
    public static void Simulation2() {
        int partieGagne1 = 0;
        int partiePerdue1 = 0;
        int partieNulle1 = 0;
        int partieGagne2 = 0;
        int partiePerdue2 = 0;
        int partieNulle2 = 0;
        int score1 = 0;
        int score2 = 0;
        for (int i = 0; i < 1000; i++) {
            Citadelles citadelle = new Citadelles();
            citadelle.game(2);
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
        Write(partieGagne1, partiePerdue1, partieNulle1, score1 / 1000, partieGagne2, partiePerdue2, partieNulle2, score2 / 1000);

    }

        /**
         * write statistics to file
         * @param partieGagne1
         * @param partiePerdue1
         * @param partieNulle1
         * @param score1
         * @param partieGagne2
         * @param partiePerdue2
         * @param partieNulle2
         * @param score2
         */
    public  static void Write(int partieGagne1,int partiePerdue1,int partieNulle1,int score1,int partieGagne2,int partiePerdue2,int partieNulle2,int score2){

        int PG1=0;
        int PG2=0;
        int PP1=0;
        int PP2=0;
        int PN1=0;
        int PN2=0;
        int sc1=0;
        int sc2=0;
        if(Files.exists(Paths.get("./src/main/resources/save/result.csv"))){
            File fich = new File("./src/main/resources/save/result.csv");
            if(fich.length()>0){
                try {
                    CSVReader reader = new CSVReader(new FileReader("./src/main/resources/save/result.csv"), ',', '"', 1);
                    List<String[]> allRows = reader.readAll();
                    String[] old=allRows.get(allRows.size()-1);
                    PG1=Integer.parseInt(old[0]);
                    PG2=Integer.parseInt(old[7]);
                    PP1=Integer.parseInt(old[2]);
                    PP2=Integer.parseInt(old[9]);
                    PN1=Integer.parseInt(old[4]);
                    PN2=Integer.parseInt(old[11]);
                    sc1=Integer.parseInt(old[6]);
                    sc2=Integer.parseInt(old[13]);

                }catch(Exception e){
                        System.out.println("Cannot read result.csv");
                }

            }


        }
        try {
            FileWriter file = new FileWriter("./src/main/resources/save/result.csv",true);
            CSVWriter writer = new CSVWriter(file);
            writer.writeNext(new String[]{"SIMULATION"});

            //En-tête de fichier
            String[] header = {"PG","% PG"," PP","% PP"," PN",
                    "% PN","SC1"," PG","% PG"," PP","% PP"
                    ," PN","% PN","SC2"};
            //Write the record to file
            writer.writeNext(header);
            //Create record
            String[] record = {Integer.toString(partieGagne1),Float.toString((float)(partieGagne1*0.1)),
                    Integer.toString(partiePerdue1)
                    ,Float.toString((float)(partiePerdue1*0.1)),Integer.toString(partieNulle1), Float.toString((float)(partieNulle1*0.1)),
                    Integer.toString(score1), Integer.toString(partieGagne2),Float.toString((float)(partieGagne2*0.1)),
                    Integer.toString(partiePerdue2), Float.toString((float)(partiePerdue2*0.1))
                    , Integer.toString(partieNulle2),Float.toString((float)(partieNulle2*0.1)), Integer.toString(score2)};
            writer.writeNext(record);
            //close the writer
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }