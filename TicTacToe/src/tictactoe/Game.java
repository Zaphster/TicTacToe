/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author csb5h4
 */
public class Game {
    public Player player1;
    public Player player2;
    public Grid grid;
    public Player turn;
    public Player notTurn;
    public Player winner;
    private BufferedReader br;
    
    public Game(Player player1, Player player2, Grid grid){
        this.player1 = player1;
        this.player2 = player2;
        this.grid = grid;
        this.winner = null;
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void setTurn(Player player){
        if(player != player1 && player != player2){
            System.out.println("Trying to set the turn to " + player.toString() + ".  This player is not playing.");
        }
        this.turn = player;
        if(player == player1){
            this.notTurn = player2;
        } else {
            this.notTurn = player1;
        }
    }
    
    public void nextTurn(){
        if(turn == player1){
            turn = player2;
            notTurn = player1;
        } else if (turn == player2){
            turn = player1;
            notTurn = player2;
        }
    }
    
    public void clearScreen(){
        for(int i = 0; i < 25;i++){
            System.out.println();
        }
    }
    
    public int takeTurn(){
        Boolean validSquare = false;
        int x = -1, y = -1;
        clearScreen();
        Grid.printGrid(grid);
        String column = "";
        String row = "";
        int move = -1;
        if(turn.type == PlayerType.COMPUTER){
//            System.out.println(turn);
//            System.out.println(turn.ai);
//            System.out.println(grid);
//            System.out.println(turn);
//            System.out.println(notTurn);
            move = turn.ai.chooseNextMove(grid, turn, notTurn);
            if(move > -1){
                grid.setPiece(move, turn);
                x = grid.getXfromIndex(move);
                y = grid.getYfromIndex(move);
            } else {
                System.out.println("no valid move for player " + turn.name);
            }
        } else if(turn.type == PlayerType.HUMAN){
            while(!validSquare){
                column = "";
                row = "";
                while(column.equals("A") == false && column.equals("B") == false && column.equals("C") == false && column.equals("D") == false && column.equals("a") == false && column.equals("b") == false && column.equals("c") == false && column.equals("d") == false){
                    System.out.println("Place a piece on column: ");
                    try{
                        column = br.readLine();
                    }catch(IOException e){
                        System.out.println(e);
                    }
                }
                while(row.equals("1") == false && row.equals("2") == false && row.equals("3") == false && row.equals("4") == false){
                    System.out.println("Place a piece on row: ");
                    try{
                        row = br.readLine();
                    }catch(IOException e){
                        System.out.println(e);
                    }
                }
                switch(column){
                    case "a":
                    case "A":
                        x = 1;
                        break;
                    case "b":
                    case "B":
                        x = 2;
                        break;
                    case "c":
                    case "C":
                        x = 3;
                        break;
                    case "d":
                    case "D":
                        x = 4;
                        break;
                    default:
                        System.out.println("This shouldn't happen.  column: " + column);
                        x = 0;
                }
                y = Integer.parseInt(row);
                validSquare = !grid.checkSquareForPiece(x, y);
                if(!validSquare){
                    System.out.println("There is already a piece on (" + column + ", " + row + ")");
                }
            }
            grid.setPiece(x, y, turn);
        }
        return grid.convertCoordsToIndex(x, y);
    }
    
    public void play(){
        setTurn(player1);
        int move;
        boolean catsGame = false;
        while(winner == null && !catsGame){
            move = takeTurn();
            //System.out.println("chosen move is: " + move);
            if(grid.checkWinCondition(turn, move)){
                winner = turn;
                Grid.printGrid(grid);
                System.out.println("And the winner is " + winner.name + "!");
            }
            if(grid.numEmpty == 0){
                catsGame = true;
                System.out.println("Cat's Game!");
            }
            nextTurn();
        }
    }

}


