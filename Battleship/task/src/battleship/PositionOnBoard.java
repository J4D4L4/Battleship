package battleship;

import java.security.PublicKey;

public class PositionOnBoard {
    int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    char y;
    boolean hasPosition;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    Vehicle vehicle;
    public boolean isHasPosition() {
        return hasPosition;
    }

    public void setHasPosition(boolean hasPosition) {
        this.hasPosition = hasPosition;
    }

    public boolean isWasHit() {
        return wasHit;
    }

    public void setWasHit(boolean wasHit) {
        this.wasHit = wasHit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    boolean wasHit;
    String value;
    PositionOnBoard(int x, char y){
        this.x = x;
        this.y = y;
        this.hasPosition = false;
        this.wasHit = false;
        this.value ="~";

    }


}
