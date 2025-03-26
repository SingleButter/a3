package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Runner{
    private Maze aMaze;

    public Runner(char[][] aMaze){
        this.aMaze = new Maze(aMaze);
    }

    //validate whether the path is correct
    public void validationPath(String aPath) {
        try {
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

    public void rightHandPathFinder(){
        try {
            Cursor.getInstance().initialize(aMaze, new Position(0, 0), Direction.RIGHT);
            FindingPath finder = new RightHandFinder(aMaze, Cursor.getInstance());
            System.out.println(toFactorized(finder.findPath()));
        }catch(NullPointerException e){
            System.out.println("Null pointer exception" + e.getMessage());
        }
    }

    public String toFactorized(String path){
        int index = 0;
        String str = "";
        while(index < path.length()){
            int numSame = 0;
            for(int i = index; i < path.length(); i++){
                if(path.charAt(index) != path.charAt(i)){
                    break;
                }else{
                    numSame++;
                }
            }
            str += numSame +""+ path.charAt(index);
            index += numSame;
        }
        return str;
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