/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author csb5h4
 */
public class Square {
    public int x;
    public int y;
    public Boolean piecePlaced;
    private Player player;
    
    public Square(int x, int y){
        this.x = x;
        this.y = y;
        piecePlaced = false;
        player = null;
    }
    
    public void setPiece(Player player){
        //System.out.println("Setting piece on (" + x + ", " + y + ") for player " + player.name);
        this.player = player;
        piecePlaced = true;
    }
    
    public void removePiece(){
        //System.out.println("Removing piece from (" + x + ", " + y + ")");
        piecePlaced = false;
        player = null;
    }
    
    public PlayerSymbol getPiece(){
        if(player != null){
            return player.symbol;
        }
        return null;
    }
    
    @Override
    public String toString(){
        String symbol;
        if(player != null){
            symbol = " a " + player.symbol.toString();
        } else {
            symbol = "nothing";
        }
        return "(" + x + ", " + y + ")  Occupied by " + symbol + ".  Piece placed = " + piecePlaced.toString();
    }
}


