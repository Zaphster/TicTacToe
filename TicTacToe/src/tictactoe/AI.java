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
public interface AI {
    public int chooseNextMove(Grid grid, Player player, Player player2);
}
