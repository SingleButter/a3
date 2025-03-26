package ca.mcmaster.se2aa4.mazerunner;

public class Maze{
    private char[][] maze;
    private Position leftEntry;
    private Position rightEntry;

    public Maze(char[][] maze){
        this.maze = maze;
        findEntry();
    }

    public void findEntry(){
        for(int i = 0; i < maze.length; i++){
            if(maze[i][0] == ' '){ //find left entry
                leftEntry = new Position(i,0);
            }
            if(maze[i][maze[i].length-1] == ' '){ //find right entry
                rightEntry = new Position(i,maze[i].length-1);
            }
        }
    }

    public boolean isValidPosition(Position position){
        if(position.getRow() < 0 || position.getRow() >= maze.length){
            return false;
        }else if(position.getCol() < 0 || position.getCol() >= maze[0].length){
            return false;
        }else if(maze[position.getRow()][position.getCol()] != ' '){
            return false;
        }else{
            return true;
        }
    }

    public char[][] getMaze(){
        return maze.clone();
    }

    public Position getLeftEntry() {
        return leftEntry;
    }
    public Position getRightEntry() {
        return rightEntry;
    }
}