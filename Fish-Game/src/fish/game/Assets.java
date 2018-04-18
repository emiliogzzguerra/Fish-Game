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
    public static BufferedImage backgroundP1;
    public static BufferedImage backgroundP2;
    public static BufferedImage backgroundP3;
    public static BufferedImage backgroundP4;
    public static BufferedImage gameover;
    public static BufferedImage start;
    //decoracion
    public static BufferedImage arbusto;
    public static BufferedImage arbusto2;
    public static BufferedImage arbol;
    //player
    public static BufferedImage player1Up[];
    public static BufferedImage player1Left[];
    public static BufferedImage player1Right[];
    public static BufferedImage player2Up[];
    public static BufferedImage player2Left[];
    public static BufferedImage player2Right[];
    public static BufferedImage player3Up[];
    public static BufferedImage player3Left[];
    public static BufferedImage player3Right[];
    public static BufferedImage player4Up[];
    public static BufferedImage player4Left[];
    public static BufferedImage player4Right[];
    public static BufferedImage sprites_player;
    //stalker
    public static BufferedImage stalker[];
    public static BufferedImage sprites_stalker;
    //obstacle
    public static BufferedImage wood;
    public static BufferedImage flores;
    public static BufferedImage sprites_Tree;
    public static BufferedImage sprites_Tree2;
    public static BufferedImage sprites_Tree3;

    /**
     * initializing the images of the game
     */
    public static void init() {
        //load images
        //imagenes directas
        backgroundP1 = ImageLoader.loadImage("/imagenes/waterP1.png");
        backgroundP2 = ImageLoader.loadImage("/imagenes/waterP2.png");
        backgroundP3 = ImageLoader.loadImage("/imagenes/waterP3.png");
        backgroundP4 = ImageLoader.loadImage("/imagenes/waterP4.png");
        gameover = ImageLoader.loadImage("/imagenes/gameover.jpg");
        start = ImageLoader.loadImage("/imagenes/start.jpg");
        //sprites
        sprites_player = ImageLoader.loadImage("/imagenes/pez.png");
        sprites_stalker = ImageLoader.loadImage("/imagenes/agua2.png");
        sprites_Tree = ImageLoader.loadImage("/imagenes/Tree.png");
        sprites_Tree2 = ImageLoader.loadImage("/imagenes/Tree2.png");
        sprites_Tree3 = ImageLoader.loadImage("/imagenes/Tree3.png");
        //loading sprites
        SpreadSheet spritesheet_Player = new SpreadSheet(sprites_player);
        SpreadSheet spritesheet_Stalker = new SpreadSheet(sprites_stalker);
        SpreadSheet spritesheet_Tree = new SpreadSheet(sprites_Tree);
        SpreadSheet spritesheet_Tree2 = new SpreadSheet(sprites_Tree2);
        SpreadSheet spritesheet_Tree3 = new SpreadSheet(sprites_Tree3);
        //creating the arrenge
        //player
        player1Up = new BufferedImage[3];
        player1Left = new BufferedImage[3];
        player1Right = new BufferedImage[3];
        player2Up = new BufferedImage[3];
        player2Left = new BufferedImage[3];
        player2Right = new BufferedImage[3];
        player3Up = new BufferedImage[3];
        player3Left = new BufferedImage[3];
        player3Right = new BufferedImage[3];
        player4Up = new BufferedImage[3];
        player4Left = new BufferedImage[3];
        player4Right = new BufferedImage[3];
        //stalker
        stalker = new BufferedImage[3];
        //crop
        for(int i=0; i<3; i++){
            //player
            player1Up[i]=spritesheet_Player.crop(i*32,3*32,32,32);
            player1Left[i]=spritesheet_Player.crop(i*32,1*32,32,32);
            player1Right[i]=spritesheet_Player.crop(i*32,2*32,32,32);
            player2Up[i]=spritesheet_Player.crop(3*32+i*32,3*32,32,32);
            player2Left[i]=spritesheet_Player.crop(3*32+i*32,1*32,32,32);
            player2Right[i]=spritesheet_Player.crop(3*32+i*32,2*32,32,32);
            player3Up[i]=spritesheet_Player.crop(3*32+i*32,7*32,32,32);
            player3Left[i]=spritesheet_Player.crop(3*32+i*32,5*32,32,32);
            player3Right[i]=spritesheet_Player.crop(3*32+i*32,6*32,32,32);
            player4Up[i]=spritesheet_Player.crop(6*32+i*32,7*32,32,32);
            player4Left[i]=spritesheet_Player.crop(6*32+i*32,5*32,32,32);
            player4Right[i]=spritesheet_Player.crop(6*32+i*32,6*32,32,32);
            //stalker
            stalker[i]=spritesheet_Stalker.crop(3*96+i*96,0,94,94);
        }
        //Obstacle
        wood = spritesheet_Tree2.crop(70,115,60,120);
        flores = spritesheet_Tree3.crop(250,0,100,120);
        //decoraciones
        arbusto = spritesheet_Tree.crop(210,0,110,100);
        arbusto2 = spritesheet_Tree.crop(265,100,140,140);
        arbol = spritesheet_Tree.crop(640,0,120,200);
    }
}