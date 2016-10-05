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
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grid grid = new Grid();
        Grid.printGrid(grid);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Player, what is your name?");
        Player humanPlayer = null;
        Player computerPlayer = null;
        try{
            String name = br.readLine();
            humanPlayer = new Player(PlayerType.HUMAN, name);
        }catch(IOException e){
            System.out.println(e);
        }
        System.out.println("Play against AI:\n1. Beginner\n2. Intermediate\n3. Advanced\n4. Quit");
        try{
            String choice = br.readLine();
            while(choice.equals("1") == false && choice.equals("2") == false && choice.equals("3") == false && choice.equals("4") == false){
                System.out.println("Not a valid choice.");
                System.out.println("Play against AI:\n1. Beginner\n2. Intermediate\n3. Advanced\n4. Quit");
                choice = br.readLine();
            }
            if(choice.equals("4")){
                return;
            }
            computerPlayer = new Player(PlayerType.COMPUTER, Integer.parseInt(choice));
        }catch(IOException e){
            System.out.println(e);
        }
        humanPlayer.setSymbol(PlayerSymbol.CROSS);
        computerPlayer.setSymbol(PlayerSymbol.CIRCLE);
//        grid.getXfromIndex(0);
//        grid.getXfromIndex(1);
//        grid.getXfromIndex(2);
        Game game = new Game(humanPlayer, computerPlayer, grid);
        game.play();
    }
}