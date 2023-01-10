package battleship;

import javax.swing.text.Position;
import java.util.*;



public class PlayingBoard {
    List<List<PositionOnBoard>> board = new ArrayList<List<PositionOnBoard>>();
    String newLine = System.getProperty("line.separator");

    PlayingBoard() {
        for (char i = 'A'; i <= 'J'; i++) {
            List<PositionOnBoard> row = new ArrayList<>();
            for (int c = 0; c < 10; c++) {
                row.add(new PositionOnBoard(c, i));

            }
            board.add(row);
        }

    }

    public void printBoard() {
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        char rowCounter = 'A';
        for (List<PositionOnBoard> e : this.board) {
            System.out.print(rowCounter);
            for (PositionOnBoard pB : e) {
                System.out.print(pB.value + " ");
            }
            System.out.print(newLine);
            rowCounter++;
        }

    }

    public boolean setVehicle(Vehicle vIn) {
        boolean setPosition = false;
        int startX = Integer.parseInt(""+vIn.start.charAt(1));
        int endX = 0;
        if(vIn.end.length()>2){
            endX = 10;
        }
        else {
            endX = Integer.parseInt(""+vIn.end.charAt(1));
        }
        try {
            int lengthOfShip = vIn.length;

            if (lengthOfShip >= 2 && lengthOfShip < 6) {
                boolean openAround = true;
                if (vIn.start.charAt(0) == vIn.end.charAt(0)) {

                    for (int i =startX; i <= endX; i++) {
                        setPosition(i, vIn.start.charAt(0), vIn);
                        setPosition = true;

                    }

                } else {


                    for (char i = vIn.start.charAt(0); i <= vIn.end.charAt(0); i++) {
                        setPosition(Integer.parseInt("" + vIn.start.charAt(1)), i, vIn);
                        setPosition = true;

                    }
                }
            }

        } catch (IndexOutOfBoundsException e) {

        }
        return setPosition;
    }

    public boolean setPosition(int x, char y, Vehicle v) {
        this.board.get(y - 'A').get(x - 1).value = "O";
        this.board.get(y - 'A').get(x - 1).vehicle = v;
        return true;
    }

    public int lengthOfShip(String start, String end) {
       /*
        boolean startThree = (start.length()>2);
        boolean endThree = (end.length()>2);
        String temp;
        if (startThree) {
            temp = start;
            start = end;
            end = start;
        }

        if (start.charAt(1)>end.charAt(1)&&!endThree){
            temp = start;
            start = end;
            end = start;
        }
        */
        int length = 0;
        if (start.charAt(0) == end.charAt(0)) {
            length = end.charAt(1) - start.charAt(1);

        } else if (start.charAt(1) == end.charAt(1)) {
            length = end.charAt(0) - start.charAt(0) + 1;
        } else length = -1;
        return length;
    }

    public boolean checkAroundOpenPosition(int x, char y, Vehicle v) {
        boolean isOpen = true;

        //PositionOnBoard p = ;
        if (x <= 9)
            if (getPosition(x + 1, y).vehicle != null && getPosition(x + 1, y).vehicle != v) isOpen = false;
        if (x > 1)
            if (getPosition(x - 1, y).vehicle != null && getPosition(x + 1, y).vehicle != v) isOpen = false;

        char oneDown = (char) (y - 1);
        char oneUp = (char) (y + 1);
        if (getPosition(x, oneUp).vehicle != null && getPosition(x + 1, y).vehicle != v) isOpen = false;
        if (y >= 'B')
            if (getPosition(x, oneDown).vehicle != null && getPosition(x + 1, y).vehicle != v) isOpen = false;

        return isOpen;
    }

    public boolean checkAroundOpenStartToEnd(Vehicle vIn) {
        boolean openAround = true;

        for (char i = vIn.start.charAt(1); i <= vIn.end.charAt(1); i++) {
            if (!checkAroundOpenPosition(Integer.parseInt("" + i), vIn.start.charAt(0), vIn)) openAround = false;
        }
        for (char i = vIn.start.charAt(0); i <= vIn.end.charAt(0); i++) {
            if (!checkAroundOpenPosition(Integer.parseInt("" + vIn.start.charAt(1)), i, vIn)) openAround = false;
            ;
        }
        return openAround;
    }


    public PositionOnBoard getPosition(int x, char y) {
        int counter = 0;
        PositionOnBoard returnVal = new PositionOnBoard(0, '0');
        for (char c = 'A'; c <= 'J'; c++) {
            if (c == y) {
                returnVal = board.get(counter).get(x-1);
            }
            counter += 1;
        }
        return returnVal;
    }
}








