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
 * Obstaculos ... ?
 * Background [3] rio
 * Background de
 * Pausa ... ? (se puede dar pausa?)
 * Gameover ...?
 * vidas ... ?
 * 
 */
public class Assets {
    public static BufferedImage background; // background base
    public static BufferedImage backgroundPausa;
    public static BufferedImage gameover;
    //Pez
    public static BufferedImage playerDown[];
    public static BufferedImage playerRight[];
    public static BufferedImage playerUp[];
    public static BufferedImage playerLeft[];
    public static BufferedImage sprites_Player;

    /**
     * initializing the images of the game
     */
    public static void init() {
        //load images
        //imagenes directas
        background = ImageLoader.loadImage("/images/LuchaDeRatones.jpg");
        backgroundPausa = ImageLoader.loadImage("/images/Esperando.jpg");
        gameover = ImageLoader.loadImage("/images/buu.jpg");
        //imagenes sprites
        sprites_Player = ImageLoader.loadImage("/images/Greedy_Mouse.png");
        //loading sprites
        SpreadSheet spritesheet_Player = new SpreadSheet(sprites_Player);
        //creating the arrenge
        //player
        playerLeft = new BufferedImage[4];
        playerUp= new BufferedImage[4];
        playerRight = new BufferedImage[4];
        playerDown = new BufferedImage[4];
        //crop
        //mouse
        for(int i=0;i<4;i++){
            playerUp[i]=spritesheet_Player.crop(i*48,3*48,48,48);
            playerLeft[i]=spritesheet_Player.crop(i*48,1*48,48,48);
            playerRight[i]=spritesheet_Player.crop(i*48,2*48,48,48);
            playerDown[i]=spritesheet_Player.crop(i*48,0*48,48,48);
        }
    }
    
}
