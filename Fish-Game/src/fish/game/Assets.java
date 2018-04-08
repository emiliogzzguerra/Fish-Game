/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.image.BufferedImage;

/**
 *
 * @author NirvanaGaming
 * 
 * Sprites:
 * Jugador, pez
 * Obstaculos ... cuantos diferentes?
 * Background [3] rio
 * Gameover
 * vidas ... ?
 * 
 */
public class Assets {
    public static BufferedImage background;
    public static BufferedImage gameover;

    /**
     * initializing the images of the game
     */
    public static void init() {
        //load images
        //imagenes directas
        background = ImageLoader.loadImage("/imagenes/background.jpg");
        gameover = ImageLoader.loadImage("/imagenes/gameover.jpg");
    }
    
}
