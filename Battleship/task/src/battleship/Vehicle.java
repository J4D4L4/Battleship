package battleship;
enum Ship{
    AIRCRAFTCARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4,"Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    int length;
    String name;

    Ship(int length, String name){
        this.length = length;
        this.name = name;
    }
}
public class Vehicle {
    String start;
    String end;
    Ship type;
    int hits;
    int length;

    Vehicle(String start, String end, Ship type){
        this.start = start;
        this.end = end;
        this.type = type;
        this.length = lengthOfShip(start,end);

    }

    public int lengthOfShip(String start, String end) {
        int length = 0;
        if (start.charAt(0) == end.charAt(0)) {
            int startInt = Integer.parseInt(""+start.charAt(1));
            int endInt = Integer.parseInt(""+end.charAt(1));
            if (start.length()==3){
                startInt = 10;
            }
            if (end.length()==3){
                endInt = 10;
            }

            length = endInt - startInt+1;

        }
        else if (start.charAt(1) == end.charAt(1)) {
            length = end.charAt(0) - start.charAt(0)+1;
        }
        else length = -1;
        return length;
    }

}
