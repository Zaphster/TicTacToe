/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;

/**
 *
 * @author csb5h4
 */
public class Grid {
    public int numAcross;
    public int numDown;
    public int numSquares;
    public ArrayList<Square> grid;
    public int numEmpty;
    
    public Grid(){
        //default is 4x4 grid. since that is what the assignment is
        grid = new ArrayList();
        numSquares = 16;
        numAcross = 4;
        numDown = 4;
        numEmpty = numSquares;
        int i, j;
        for(i = 0; i < numAcross; i++){
            for(j = 0; j < numDown; j++){
                Square newSquare = new Square(j+1, i+1);
                grid.add(newSquare);
            }
        }
    }
    
    public void setPiece(int index, Player player){
        if(index < 0 || index > numSquares - 1){
            System.out.println("Piece not set");
            return;
        }
        grid.get(index).setPiece(player);
        numEmpty--;
    }
    
    public void setPiece(int x, int y, Player player){
        if(checkValidCoords(x, y) == false){
            System.out.println("Piece not set");
            return;
        }
        int index = convertCoordsToIndex(x, y);
        grid.get(index).setPiece(player);
        numEmpty--;
    }
    
    public void removePiece(int x, int y){
        if(checkValidCoords(x, y) == false){
            System.out.println("Piece not removed");
            return;
        }
        int index = convertCoordsToIndex(x, y);
        grid.get(index).removePiece();
        numEmpty++;
    }
    
    public Boolean checkSquareForPiece(int x, int y){
        if(checkValidCoords(x, y) == false){
            System.out.println("Cannot check that square for occupying symbol");
            return true;
        }
        return grid.get(convertCoordsToIndex(x, y)).piecePlaced;
    }
    
    public int convertCoordsToIndex(int x, int y){
        return ((y - 1) * numAcross) + x - 1;
    }
    
    public int getXfromIndex(int index){
        if(checkValidIndex(index)){
        //System.out.println("Square " + index + " x: " + grid.get(index).x + " y: " + grid.get(index).y);
            return grid.get(index).x;
        } else {
            System.out.println(index + " is not a valid index.");
            return -1;
        }
    }
    
    public int getYfromIndex(int index){
        if(checkValidIndex(index)){
        //System.out.println("Square " + index + " x: " + grid.get(index).x + " y: " + grid.get(index).y);
            return grid.get(index).y;
        } else {
            System.out.println(index + " is not a valid index.");
            return -1;
        }
    }
    
    public PlayerSymbol getPieceOnSquare(int x, int y){
        int index = convertCoordsToIndex(x, y);
        return grid.get(index).getPiece();
    }
    
    public Boolean checkValidCoords(int x, int y){
        if(x > 0 && x <= numAcross){
            if(y > 0 && y <= numDown){
                return true;
            } else {
                System.out.println("Invalid y value: " + y);
            }
        } else {
            System.out.println("Invalid x value: " + x);
        }
        return false;
    }
    
    public Boolean checkValidIndex(int index){
        return index >= 0 && index < numSquares;
    }
    
    public static void printGrid(Grid grid){
        int i, j;
        Square square;
        String toPrint;
        for(i = 0; i < grid.numAcross; i++){
            toPrint = "  ";
            if(i == 0){
                for(j = 0; j < grid.numDown; j++){
                    toPrint += "  " + (char)(j + 65) + " ";
                    if(j == grid.numDown - 1){
                        toPrint += " ";
                    }
                }
                System.out.println(toPrint);
            }
            toPrint = "  ";
            for(j = 0; j < grid.numDown; j++){
                toPrint += "+---";
                if(j == grid.numDown - 1){
                    toPrint += "+";
                }
            }
            System.out.println(toPrint);
            toPrint = "";
            for(j = 0; j < grid.numDown; j++){
                square = grid.grid.get(grid.numAcross*i + j);
                //if(square.player != null){
                if(square.getPiece() == PlayerSymbol.CIRCLE){
                    //print out a circle
                    if(j == 0){
                        toPrint += (i + 1) + " | O ";
                    } else {
                        toPrint += "| O ";
                    }
                    if(j == grid.numDown - 1){
                        toPrint += "|";
                    }
                } else if(square.getPiece() == PlayerSymbol.CROSS){
                    //print out a cross
                    if(j == 0){
                        toPrint += (i + 1) + " | X ";
                    } else {
                        toPrint += ("| X ");
                    }
                    if(j == grid.numDown - 1){
                        toPrint += "|";
                    }
                } else {
                    if(j == 0){
                        toPrint += (i + 1) + " |   ";
                    } else {
                        toPrint += "|   ";
                    }
                    if(j == grid.numDown - 1){
                        toPrint += "|";
                    }
                }
            }
            System.out.println(toPrint);
        }
        toPrint = "  ";
        for(j = 0; j < grid.numDown; j++)
        {
            toPrint += "+---";
            if(j == grid.numDown - 1){
                toPrint += "+";
            }
        }
        System.out.println(toPrint);
    }
    
    public Boolean checkWinCondition(Player turn, int move){
        int x = getXfromIndex(move);
        int y = getYfromIndex(move);
        //check column
        int num = 0;
        boolean interrupted = false;
        for(int i = 1; i <= 4; i++){
            if(getPieceOnSquare(x, i) == turn.symbol){
                num++;
            } else if (i == 2 || i == 3) {
                interrupted = true;
            }
        }
        if (num >= 3 && !interrupted){
            //System.out.println("Win condition on column " + x + " for player " + turn.name);
            return true;
        }
        
        //check row
        num = 0;
        interrupted = false;
        for(int i = 1; i <= 4; i++){
            if(getPieceOnSquare(i, y) == turn.symbol){
                num++;
            } else if (i == 2 || i == 3) {
                interrupted = true;
            }
        }
        if (num >= 3 && !interrupted){
            //System.out.println("Win condition on row " + y + " for player " + turn.name);
            return true;
        }
        
        //check diagonal in the direction of this slash \
        num = 0;
        interrupted = false;
        //find the starting point
        int tempx = x, tempy = y;
        while(tempx != 1 && tempy != 1){
            tempx--;
            tempy--;
        }
        //loop through to check for a win
        while(tempx <= 4 && tempy <= 4){
            if(getPieceOnSquare(tempx, tempy) == turn.symbol){
                num++;
            } else if(tempx == 2 || tempx == 3){
                interrupted = true;
            }
            tempx++;
            tempy++;
        }
        if(num >= 3 && !interrupted){
            //System.out.println("Win condition on diagonal for player " + turn.name);
            return true;
        }
        
        //check diagonal in the direction of this slash /
        num = 0;
        interrupted = false;
        //find the starting point
        tempx = x;
        tempy = y;
        while(tempx != 4 && tempy != 1){
            tempx++;
            tempy--;
        }
        //loop through to check for a win
        while(tempx >= 1 && tempy <= 4){
            if(getPieceOnSquare(tempx, tempy) == turn.symbol){
                num++;
            } else if(tempx == 2 || tempx == 3){
                interrupted = true;
            }
            tempx--;
            tempy++;
        }
        if(num >= 3 && !interrupted){
            //System.out.println("Win condition on diagonal for player " + turn.name);
            return true;
        }
        //System.out.println("No win conditions for player " + turn.name);
        return false;
    }
    
    public int getNextAvailableSquare(){
        int move = -1;
        for(int i = 0; i < numSquares; i++){
            System.out.println(grid.get(i).toString());
            if(grid.get(i).piecePlaced == false){
                move = i;
                break;
            }
        }
        return move;
    }
}


