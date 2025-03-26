package ca.mcmaster.se2aa4.mazerunner;

public class Position{
    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }


    public Position moveOneStep(Direction direction){
        switch(direction){
            case UP:
                return new Position(row-1, col);
            case DOWN:
                return new Position(row+1, col);
            case LEFT:
                return new Position(row, col-1);
            case RIGHT:
                return new Position(row, col+1);
        }
        return null;
    }
    public Position getRightPosition(Direction direction){
        switch(direction){
            case UP:
                return new Position(row, col+1);
            case DOWN:
                return new Position(row, col-1);
            case LEFT:
                return new Position(row-1, col);
            case RIGHT:
                return new Position(row+1, col);
        }
        return null;
    }

    public Position getLeftPosition(Direction direction){
        switch(direction){
            case UP:
                return new Position(row, col-1);
            case DOWN:
                return new Position(row, col+1);
            case LEFT:
                return new Position(row+1, col);
            case RIGHT:
                return new Position(row-1, col);
        }
        return null;
    }

    public Position getNextPosition(Direction direction){
        switch(direction){
            case UP:
                return new Position(row-1, col);
            case DOWN:
                return new Position(row+1, col);
            case LEFT:
                return new Position(row, col-1);
            case RIGHT:
                return new Position(row, col+1);
        }
        return null;
    }


    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

}