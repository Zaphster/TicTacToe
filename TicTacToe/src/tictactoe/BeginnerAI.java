/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Cameron
 */
public class BeginnerAI implements AI{
    public Grid grid;
    public Player turn;
    public Player notTurn;
    
    @Override
    public int chooseNextMove(Grid grid, Player turn, Player notTurn){
        this.grid = grid;
        this.turn = turn;
        this.notTurn = notTurn;
        int spot;
        //find a spot where the AI can win and return it if it exists
        //System.out.println("Checking for winning moves");
        spot = checkForMyWin();
        if(spot >= 0){
            //System.out.println("My win at " + spot);
            return spot;
        }
        //find a spot where the AI will lose if the opponent goes there
        //return it if it exists
        //System.out.println("Checking for opponent's winning moves");
        spot = checkForOpponentWin();
        if(spot >= 0){
            //System.out.println("Opponent's win at " + spot);
            return spot;
        }
        //if no winning spots and no defend spots, choose the next available square
        spot = grid.getNextAvailableSquare();
        //System.out.println("No one is about to win. Making a move at " + spot);
        return spot;
    }
    
    @Override
    public String toString(){
        return "Beginner AI";
    }
    
    public int checkForWin(Player player){
        int winningSquare = -1;
        int index;
        for(int y = 1; y <= 4; y++){
            for(int x = 1; x <= 4; x++){
                //Grid.printGrid(grid);
                if(grid.checkSquareForPiece(x, y)){
                    continue;
                }
                grid.setPiece(x, y, player);
                index = grid.convertCoordsToIndex(x, y);
                if(grid.checkWinCondition(player, grid.convertCoordsToIndex(x, y)))
                {
                    winningSquare = index;
                    grid.removePiece(x, y);
                    break;
                }
                grid.removePiece(x, y);
            }
            if(winningSquare > -1){
                break;
            }
        }
        return winningSquare;
    }
    
    public int checkForMyWin(){
        return checkForWin(turn);
    }
    
    public int checkForOpponentWin(){
        return checkForWin(notTurn);
    }
}
