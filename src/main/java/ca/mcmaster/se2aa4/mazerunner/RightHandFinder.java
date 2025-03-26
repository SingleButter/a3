package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import java.util.*;

public class RightHandFinder implements FindingPath{
    private Maze maze;
    private Cursor cursor;
    private List<String> path;

    public RightHandFinder(Maze maze, Cursor cursor){
        this.maze = maze;
        this.cursor = cursor;
        path = new ArrayList<String>();
    }

    public String findPath(){
        cursor.setCurrentPosition(maze.getLeftEntry());

        while(!cursor.reachExit(maze.getRightEntry())){
            Position rightPosition = cursor.getCurrentPosition().getRightPosition(cursor.getCurrentDirection());
            Position leftPosition = cursor.getCurrentPosition().getLeftPosition(cursor.getCurrentDirection());
            Position nextPosition = cursor.getCurrentPosition().getNextPosition(cursor.getCurrentDirection());
            if(maze.isValidPosition(rightPosition)){ //right position is valid
                cursor.turnRight();
                cursor.moveForward();
                path.add("R");
                path.add("F");
            }else if(maze.isValidPosition(nextPosition)){ //if next position is valid
                cursor.moveForward();
                path.add("F");
            }else if(maze.isValidPosition(leftPosition)){ //if left position is valid
                cursor.turnLeft();
                cursor.moveForward();
                path.add("L");
                path.add("F");
            }else{
                cursor.turnRight();
                cursor.turnRight();
                path.add("R");
                path.add("R");
            }

        }
        String result = "";
        for(String s : path){
            result += s;
        }
        return result;
    }

}