package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import java.util.*;

public class RightHandFinder implements FindingPath{
    private Maze maze;
    private Cursor cursor;

    public RightHandFinder(Maze maze, Cursor cursor){
        this.maze = maze;
        this.cursor = cursor;
           }

    public void findPath(){
        cursor.setCurrentPosition(maze.getLeftEntry());

        while(!cursor.reachExit(maze.getRightEntry())){
            Position rightPosition = cursor.getCurrentPosition().getRightPosition(cursor.getCurrentDirection());
            Position leftPosition = cursor.getCurrentPosition().getLeftPosition(cursor.getCurrentDirection());
            Position nextPosition = cursor.getCurrentPosition().getNextPosition(cursor.getCurrentDirection());
            if(maze.isValidPosition(rightPosition)){ //right position is valid
                cursor.turnRight();
                cursor.moveForward();
            }else if(maze.isValidPosition(nextPosition)){ //if next position is valid
                cursor.moveForward();
            }else if(maze.isValidPosition(leftPosition)){ //if left position is valid
                cursor.turnLeft();
                cursor.moveForward();
            }else{
                cursor.turnRight();
                cursor.turnRight();
            }

        }
    }

}