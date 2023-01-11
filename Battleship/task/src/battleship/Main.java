package battleship;

import java.util.Scanner;

public class Main {

    Main(){
    }
    public static void main(String[] args) {

        PlayingBoard player1= new PlayingBoard("Player 1");
        PlayingBoard pLayer2 = new PlayingBoard("Player 2");
                //playingBoard.printBoard();
        setUpVehicles(player1);

        setUpVehicles(pLayer2);
        //playingBoard=setUpTest();
        //playingBoard.printBoard();
        System.out.println("The game starts!");
        //playingBoard.printFogOfWarBoard();
        //shootingPhase(playingBoard);
        playerTurns(player1,pLayer2);



    }

    static void setUpVehicles(PlayingBoard pB){
        Scanner scanner = new Scanner(System.in);
        String input[];
        System.out.printf("%s, place your ships on the game field%n",pB.player);
        pB.printBoard();
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
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

    }

    static void shootingPhase(PlayingBoard pb){
        System.out.println("Take a shot!");
        while (pb.checkIfVehicleLeft()){
            takeShot(pb);
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    static void takeShot(PlayingBoard pb){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int xCor=0;
        char yCor;
        boolean wrongInput = true;
        while (wrongInput){
            if(input.length()>2){
                if (input.charAt(2)!='0'){
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    input = scanner.nextLine();
                    continue;
                }
                else xCor=10;
            }
            if(input.charAt(0)>'J'){
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                input = scanner.nextLine();
                continue;
            }
            else if(xCor!=10) xCor=Integer.parseInt(""+input.charAt(1));
            yCor = input.charAt(0);
            PositionOnBoard position = pb.getPosition(xCor,yCor);
            HitStatus hit = position.shootAt();
            pb.printFogOfWarBoard();
            
            
            if(hit.name=="hit"){
                System.out.println("You hit a ship!");
            } else if (hit.name=="destroyed") {
                System.out.println("You sank a ship! Specify a new target:");
                
            } else System.out.println("You missed!");
            //pb.printBoard();
            break;
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

        if (start.charAt(1) > end.charAt(1) && !endThree && !wentThrough){
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
    public static PlayingBoard setUpTest(){
        PlayingBoard returnPB = new PlayingBoard("test");
        Vehicle ac  = new Vehicle("F3","F7", Ship.AIRCRAFTCARRIER);
        returnPB.setVehicle(ac);
        Vehicle bs  = new Vehicle("A1","D1", Ship.BATTLESHIP);
        returnPB.setVehicle(bs);
        Vehicle sm  = new Vehicle("J8","J10", Ship.SUBMARINE);
        returnPB.setVehicle(sm);
        Vehicle cr  = new Vehicle("B9","D9", Ship.CRUISER);
        returnPB.setVehicle(cr);
        Vehicle ds  = new Vehicle("I2","J2", Ship.DESTROYER);
        returnPB.setVehicle(ds);

        return returnPB;

    }
    public static void playerTurns(PlayingBoard player1, PlayingBoard player2){
        Scanner scanner = new Scanner(System.in);
        boolean playerDead = false;
        String winner="";
        while(!playerDead){
            player2.printFogOfWarBoard();
            System.out.println("---------------------");
            player1.printBoard();
            System.out.printf("%s , it's your turn:%n", player1.player);
            takeShot(player2);
            if(!player2.checkIfVehicleLeft()) {
                playerDead = true;
                winner=player1.player;
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
            player1.printFogOfWarBoard();
            System.out.println("---------------------");
            player2.printBoard();
            System.out.printf("%s , it's your turn:%n", player2.player);
            takeShot(player1);
            if(!player1.checkIfVehicleLeft()) {
                playerDead = true;
                winner=player2.player;
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();

        }
        System.out.printf("You sank the last ship. You won. Congratulations!",winner);
    }

}
