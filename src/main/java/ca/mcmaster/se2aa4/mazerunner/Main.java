package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import ca.mcmaster.se2aa4.mazerunner.Direction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "input file path");
        options.addOption("p", true, "input maze path");
        CommandLineParser parser = new DefaultParser();
        char[][] maze = null;
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("i")){
                String optionValue = cmd.getOptionValue("i");
                maze = fillIntoArray(optionValue);

                //checking -p flag
                if(cmd.hasOption("p")){
                    Runner aRun = new Runner(maze);
                    aRun.validationPath(cmd.getOptionValue("p"));
                }else{
                    Runner otherRun = new Runner(maze);
                    otherRun.rightHandPathFinder();
                }
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\", e);
        }
    }


    public static char[][] fillIntoArray(String optionValue){
        try {
            int numRow = 0;
            int numCol = 0;
            BufferedReader reader = new BufferedReader(new FileReader(optionValue));
            String line;
            while ((line = reader.readLine()) != null) {
                numCol = line.length();
                numRow++;
            }

            //put contents into the 2D array
            char[][] maze = new char[numRow][numCol];
            BufferedReader areader = new BufferedReader(new FileReader(optionValue));
            String newline;
            int rowIndex = 0;
            while ((newline = areader.readLine()) != null) {
                for (int idx = 0; idx < newline.length(); idx++) {
                    maze[rowIndex][idx] = newline.charAt(idx);
                }
                rowIndex++;
            }
            return maze;
        }catch(FileNotFoundException e) {
            System.out.println("File not found: " + optionValue);
            return new char[0][0];
        }catch(IOException e) {
            System.out.println("I/O error: " + e.getMessage());
            return new char[0][0];
        }catch(NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
            return new char[0][0];

        }
    }
}