/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

/**
 *
 * @author NirvanaGaming
 * 
 * FishGame
 * Emilio González Guerra   a00817507
 * Naomi Macías Honti       a01282098
 * Mikel Rubíes Isla        a00821467
 * Andrés Aguirre González  a01039656
 * 
 * Version 1.4
 * 7 de abril, 2018
 * Creacion de obstacle, background, player y stalker.
 * Funcionalidad de puntuacion y pantalla de gameover.
 * 
 * Notas:
 * Checar
 *  Game
 *  Assets
 *  Pez
 *  Obstacle
 * Terminado
 *  Animation
 *  Display
 *  Item
 *  SpreadSheet
 *  SoundClip
 *  KeyManager
 * Para version 2
 *  Files
 */
public class FishGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game g = new Game(800, 500, "Fish-Game");
        g.start();
    }
    
}
