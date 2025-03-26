package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Direction;

public class PathValidation {
    private Cursor cursor;
    private Maze maze;

    public PathValidation(Maze maze, Cursor cursor) {
        this.maze = maze;
        this.cursor = cursor;
    }

    public boolean validateFromLeftEntry(String path){
        Position entry = maze.getLeftEntry();
        Position exit = maze.getRightEntry();
        cursor.setCurrentPosition(entry);
        cursor.setCurrentDirection(Direction.RIGHT);

        for(int i = 0; i < path.length(); i++){
            char oneIns = path.charAt(i); //one instruction
            switch(oneIns){
                case 'F':
                    if(cursor.moveForward() == false){
                        return false;
                    }
                    break;
                case 'L':
                    cursor.turnLeft();
                    break;
                case 'R':
                    cursor.turnRight();
                    break;
            }
            if(cursor.reachExit(exit)){
                return true;
            }
        }
        return false;
    }

    public boolean validateFromRightEntry(String path){
        Position entry = maze.getRightEntry();
        Position exit = maze.getLeftEntry();
        cursor.setCurrentPosition(entry);
        cursor.setCurrentDirection(Direction.LEFT);

        for(int i = 0; i < path.length(); i++){
            char oneIns = path.charAt(i); //one instruction
            switch(oneIns){
                case 'F':
                    if(cursor.moveForward() == false){
                        return false;
                    }
                    break;
                case 'L':
                    cursor.turnLeft();
                    break;
                case 'R':
                    cursor.turnRight();
                    break;
            }
            if(cursor.reachExit(exit)){
                return true;
            }
        }
        return false;
    }



}