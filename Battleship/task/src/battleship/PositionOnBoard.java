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
    boolean wasMissed;
    String fogOfWarValue;

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
    HitStatus hitStatus;
    PositionOnBoard(int x, char y){
        this.x = x;
        this.y = y;
        this.hasPosition = false;
        this.wasHit = false;
        this.wasMissed = false;
        this.value ="~";
        this.fogOfWarValue = "~";

    }

    public HitStatus shootAt(){
        boolean isHit = false;
        if (this.vehicle != null && !this.wasHit){
            this.hitStatus=vehicle.hitVehicle();
            this.wasHit = true;
            this.value = "X";
            this.fogOfWarValue = "X";
            isHit= true;

        }
        if(this.vehicle == null && !this.wasHit){
            this.hitStatus = HitStatus.MISS;
            this.wasMissed=true;
            this.value = "M";
            this.fogOfWarValue = "M";

        }
        return this.hitStatus;
    }


}
