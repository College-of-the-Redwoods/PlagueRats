package io.github.CR.PlagueRats.backend;

public class Cell {
    private final Position position;
    private boolean isOccupied;

    public Cell(int x, int y){
        position = new Position(x, y);
        isOccupied = false;
    }

    public Position getPosition(){
        return position;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void occupyCell(){
        isOccupied = true;
        System.out.println("Cell at (" + position.x + ", " + position.y + ") is now occupied.");
    }

    public void leaveCell(){
        isOccupied = false;
        System.out.println("Cell at (" + position.x + ", " + position.y + ") is no longer occupied.");
    }
}
