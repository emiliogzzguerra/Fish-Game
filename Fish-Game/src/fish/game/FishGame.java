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
 * Version 1.3
 * 4 de abril, 2018
 * Creacion de las clases bases.
 * 
 * Notas:
 * Por definir
 *  Tamano de la ventana?
 *  Titulo de la ventana?
 * Checar
 *  Game
 *  Assets
 *  Pez
 *  Obstacle
 *  KeyManager
 * Terminado
 *  Animation
 *  Display
 *  Item
 *  SpreadSheet
 *  SoundClip
 * Para version 2
 *  Files
 */
public class FishGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game g = new Game(800, 500);
        g.start();
    }
    
}
