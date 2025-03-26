package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Runner{

    private static Runner instance;

    private Runner(){
    }

    public static Runner getInstance(){
        if(instance == null){
            instance = new Runner();
        }
        return instance;
    }

    //validate whether the path is correct
    public void validationPath(String aPath, char[][] maze) {
        try {
            Maze aMaze = Maze.getInstance();
            aMaze.initialize(maze);
            String path = toCanonical(aPath);
            Cursor.getInstance().initialize(aMaze, new Position(0, 0), Direction.RIGHT);
            PathValidation pathValidation = new PathValidation(aMaze, Cursor.getInstance());
            if (pathValidation.validateFromLeftEntry(path)) { //starts from left entry
                System.out.println("correct Path");
            } else if (pathValidation.validateFromRightEntry(path)) { //starts from right entry
                System.out.println("correct Path");
            } else {
                System.out.println("incorrect Path");
            }
        }catch(NullPointerException e){
            System.out.println("Null pointer exception" + e.getMessage());
        }
    }

    public void findPath(char[][] maze){
        try {
            Maze aMaze = Maze.getInstance();
            aMaze.initialize(maze);
            PathRecorder pathRecorder = new PathRecorder();
            Cursor.getInstance().addObserver(pathRecorder);
            Cursor.getInstance().initialize(aMaze, new Position(0, 0), Direction.RIGHT);
            FindingPath finder = new RightHandFinder(aMaze, Cursor.getInstance());
            finder.findPath();
            System.out.println(pathRecorder.getFactorizedPath());
        }catch(NullPointerException e){
            System.out.println("Null pointer exception" + e.getMessage());
        }
    }


    public String toCanonical(String path){
        String str = "";
        int index = 0;
        while(index < path.length()){
            String num = "";
            while(path.charAt(index) >= '0' && path.charAt(index) <= '9'){
                num += path.charAt(index);
                index++;
            }
            int number = Integer.parseInt(num);
            for(int i = 0; i < number; i++){
                str += path.charAt(index);
            }
            index++;
        }
        return str;
    }
}