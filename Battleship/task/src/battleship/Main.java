package battleship;

import java.util.Scanner;

public class Main {

    Main(){
    }
    public static void main(String[] args) {
        PlayingBoard playingBoard= new PlayingBoard();
        playingBoard.printBoard();
        setUpVehicles(playingBoard);



        playingBoard.printBoard();
    }

    static void setUpVehicles(PlayingBoard pB){
        Scanner scanner = new Scanner(System.in);
        String input[];
        for(Ship ship: Ship.values()){
            boolean correctInput = false;
            System.out.printf("Enter the coordinates of the %s (%d cells):%n",ship.name, ship.length);
            input = scanner.nextLine().split(" ");
            input = switchUserInput(input[0],input[1]);
            Vehicle inV = new Vehicle(input[0],input[1], ship);
            if(inV.length ==ship.length && pB.checkAroundOpenStartToEnd(inV)&&(input[0].charAt(0)==input[1].charAt(0)||input[0].charAt(1)==input[1].charAt(1))) correctInput = true;
            while (!correctInput){
                if(inV.length !=ship.length && inV.length!=-1)
                    System.out.printf("Error! Wrong length of the %s! Try again:%n",ship.name);
                else if (inV.length==-1) {
                    System.out.printf("Error! Wrong ship location! Try again:%n");

                } else if (!pB.checkAroundOpenStartToEnd(inV)) {
                    System.out.printf("Error! You placed it too close to another one. Try again:%n");
                } else System.out.printf("Error! Wrong ship location! Try again:%n");
                input = scanner.nextLine().split(" ");
                input = switchUserInput(input[0],input[1]);
                inV = new Vehicle(input[0],input[1], ship);
                if(inV.length ==ship.length) correctInput = true;
            }
            pB.setVehicle(inV);
            pB.printBoard();
        }
    }

    public static String[] switchUserInput(String start, String end){
        boolean startThree = (start.length()>2);
        boolean endThree = (end.length()>2);
        boolean wentThrough = false;
        String temp;
        if (startThree) {
            temp = start;
            start = end;
            end = temp;
            wentThrough = true;
        }

        if (start.charAt(1)>end.charAt(1)&&!endThree&&!wentThrough){
            temp = start;
            start = end;
            end = temp;
        }
        if(start.charAt(0)>end.charAt(0)){
            temp = start;
            start = end;
            end = temp;
        }

        String[] out = {start,end};
        return out;
    }


}
