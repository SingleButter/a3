package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import java.util.*;

public class Cursor{
    private static Cursor instance;

    private Maze maze;
    private Position currentPosition;
    private Direction currentDirection;

    private List<Observer> observers = new ArrayList<Observer>();

    private Cursor(){

    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void removeObserver(Observer observer){
        observers.remove(observers);
    }
    public void notifyObservers(String event){
        for(Observer observer : observers){
            observer.update(event);
        }
    }

    public static Cursor getInstance(){
        if(instance == null){
            instance = new Cursor();
        }
        return instance;
    }

    public void initialize(Maze maze, Position position, Direction direction){
        this.maze = maze;
        currentPosition = position;
        currentDirection = direction;
    }

    public boolean moveForward() {
        Position newPosition = currentPosition.moveOneStep(currentDirection);
        if(maze.isValidPosition(newPosition)){ //check if the position is valid
            currentPosition = newPosition;
            notifyObservers("F");
            return true;
        }else{
            return false;
        }
    }

    public boolean reachExit(Position exit){
        if(currentPosition.getRow() == exit.getRow() && currentPosition.getCol() == exit.getCol()){
            return true;
        }else{
            return false;
        }

    }

    public void turnRight(){
        switch(currentDirection){
            case Direction.UP:
                currentDirection = Direction.RIGHT;
                break;
            case Direction.DOWN:
                currentDirection = Direction.LEFT;
                break;
            case Direction.LEFT:
                currentDirection = Direction.UP;
                break;
            case Direction.RIGHT:
                currentDirection = Direction.DOWN;
                break;
        }
        notifyObservers("R");
    }

    public void turnLeft(){
        switch(currentDirection){
            case Direction.UP:
                currentDirection = Direction.LEFT;
                break;
            case Direction.DOWN:
                currentDirection = Direction.RIGHT;
                break;
            case Direction.LEFT:
                currentDirection = Direction.DOWN;
                break;
            case Direction.RIGHT:
                currentDirection = Direction.UP;
                break;
        }
        notifyObservers("L");
    }



    public void setCurrentPosition(Position newPosition){
        currentPosition = newPosition;
    }

    public void setCurrentDirection(Direction newDirection){
        currentDirection = newDirection;
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}