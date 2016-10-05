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
public class Player {
    public PlayerType type;
    public String name;
    public PlayerSymbol symbol;
    public AI ai;
    
    public Player(PlayerType type, String name){
        this.type = type;
        this.name = name;
    }
    
    public Player(PlayerType type, int ai){
        switch(ai){
            case 1:
                this.ai = new BeginnerAI();
                break;
            case 2:
                this.ai = new IntermediateAI();
                break;
            case 3:
                this.ai = new AdvancedAI();
                break;
            default:
                System.out.println("Invalid choice for AI");
                break;
        }
        this.type = type;
        this.name = this.ai.toString();
    }
    
    public void setSymbol(PlayerSymbol symbol){
        this.symbol = symbol;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return "Name: " + name + "\n" + type.toString() + " player\nPlaying as " + symbol.toString();
    }
    
    
}


