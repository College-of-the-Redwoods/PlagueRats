package com.thaddycat.gradletest.backend;

public class Cell {
    private final Position position;
    private boolean isOccupied;

    public Cell(int x, int y){
        this.position = new Position();
        this.position.setPosition(x, y);
        this.isOccupied = false;
    }

    public void setPosition(int x, int y){
        this.position.setPosition(x, y);
    }

    public Position getPosition(){
        return this.position;
    }

    public int getXPos(){
        return this.position.getX();
    }

    public int getYPos(){
        return this.position.getY();
    }

    public boolean isOccupied(){
        return this.isOccupied;
    }

    public void occupyCell(){
        this.isOccupied = true;
        System.out.println("Cell at (" + this.position.getX() + ", " + this.position.getY() + ") is now occupied.");
    }

    public void leaveCell(){
        this.isOccupied = false;
        System.out.println("Cell at (" + this.position.getX() + ", " + this.position.getY() + ") is no longer occupied.");
    }
}
