/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background; // to store background image
    //Mouse
    public static BufferedImage playerDown[];
    public static BufferedImage playerRight[];
    public static BufferedImage playerUp[];
    public static BufferedImage playerLeft[];
    public static BufferedImage sprites_Player;
    //Enemy
    public static BufferedImage enemy1Down[];
    public static BufferedImage enemy1Up[];
    public static BufferedImage enemy1Left[];
    public static BufferedImage enemy1Right[];
    public static BufferedImage enemy2Down[];
    public static BufferedImage enemy2Up[];
    public static BufferedImage enemy2Left[];
    public static BufferedImage enemy2Right[];
    public static BufferedImage enemy3Down[];
    public static BufferedImage enemy3Up[];
    public static BufferedImage enemy3Left[];
    public static BufferedImage enemy3Right[];
    public static BufferedImage enemy4Down[];
    public static BufferedImage enemy4Up[];
    public static BufferedImage enemy4Left[];
    public static BufferedImage enemy4Right[];
    public static BufferedImage enemy5Down[];
    public static BufferedImage enemy5Up[];
    public static BufferedImage enemy5Left[];
    public static BufferedImage enemy5Right[];
    public static BufferedImage enemy6Down[];
    public static BufferedImage enemy6Up[];
    public static BufferedImage enemy6Left[];
    public static BufferedImage enemy6Right[];
    public static BufferedImage enemy7Down[];
    public static BufferedImage enemy7Up[];
    public static BufferedImage enemy7Left[];
    public static BufferedImage enemy7Right[];
    public static BufferedImage enemy8Down[];
    public static BufferedImage enemy8Up[];
    public static BufferedImage enemy8Left[];
    public static BufferedImage enemy8Right[];
    public static BufferedImage sprites_Enemy;
    //birds
    public static BufferedImage bird1Down[];
    public static BufferedImage bird1Up[];
    public static BufferedImage bird1Left[];
    public static BufferedImage bird1Right[];
    public static BufferedImage bird2Down[];
    public static BufferedImage bird2Up[];
    public static BufferedImage bird2Left[];
    public static BufferedImage bird2Right[];
    public static BufferedImage bird3Down[];
    public static BufferedImage bird3Up[];
    public static BufferedImage bird3Left[];
    public static BufferedImage bird3Right[];
    public static BufferedImage bird4Down[];
    public static BufferedImage bird4Up[];
    public static BufferedImage bird4Left[];
    public static BufferedImage bird4Right[];
    public static BufferedImage sprites_Birds;
    //Extras
    public static BufferedImage vida;
    public static BufferedImage sprites_Vida;
    public static BufferedImage chees;
    //imagenes
    public static BufferedImage backgroundPausa;
    public static BufferedImage gameover;
    public static BufferedImage ganaste;

    /**
     * initializing the images of the game
     */
    public static void init() {
        //load images
        background = ImageLoader.loadImage("/images/LuchaDeRatones.jpg");
        sprites_Player = ImageLoader.loadImage("/images/Greedy_Mouse.png");
        sprites_Enemy = ImageLoader.loadImage("/images/gatos.png");
        sprites_Vida = ImageLoader.loadImage("/images/personaje.png");
        sprites_Birds = ImageLoader.loadImage("/images/aves.png");
        backgroundPausa = ImageLoader.loadImage("/images/Esperando.jpg");
        gameover = ImageLoader.loadImage("/images/buu.jpg");
        ganaste = ImageLoader.loadImage("/images/AsesinoDeGatos.jpg");
        chees = ImageLoader.loadImage("/images/Cheese-Sprite.png");
        //loading sprites
        SpreadSheet spritesheet_Player = new SpreadSheet(sprites_Player);
        SpreadSheet spritesheet_Enemy = new SpreadSheet(sprites_Enemy);
        SpreadSheet spritesheet_Vida = new SpreadSheet(sprites_Vida);
        SpreadSheet spritesheet_Birds = new SpreadSheet(sprites_Birds);
        //creating the arrenge
        //player
        playerLeft = new BufferedImage[4];
        playerUp= new BufferedImage[4];
        playerRight = new BufferedImage[4];
        playerDown = new BufferedImage[4];
        //enemy
        enemy1Left = new BufferedImage[3];
        enemy1Up = new BufferedImage[3];
        enemy1Right = new BufferedImage[3];
        enemy1Down = new BufferedImage[3];
        enemy2Left = new BufferedImage[3];
        enemy2Up = new BufferedImage[3];
        enemy2Right = new BufferedImage[3];
        enemy2Down = new BufferedImage[3];
        enemy3Left = new BufferedImage[3];
        enemy3Up = new BufferedImage[3];
        enemy3Right = new BufferedImage[3];
        enemy3Down = new BufferedImage[3];
        enemy4Left = new BufferedImage[3];
        enemy4Up = new BufferedImage[3];
        enemy4Right = new BufferedImage[3];
        enemy4Down = new BufferedImage[3];
        enemy5Left = new BufferedImage[3];
        enemy5Up = new BufferedImage[3];
        enemy5Right = new BufferedImage[3];
        enemy5Down = new BufferedImage[3];
        enemy6Left = new BufferedImage[3];
        enemy6Up = new BufferedImage[3];
        enemy6Right = new BufferedImage[3];
        enemy6Down = new BufferedImage[3];
        enemy7Left = new BufferedImage[3];
        enemy7Up = new BufferedImage[3];
        enemy7Right = new BufferedImage[3];
        enemy7Down = new BufferedImage[3];
        enemy8Left = new BufferedImage[3];
        enemy8Up = new BufferedImage[3];
        enemy8Right = new BufferedImage[3];
        enemy8Down = new BufferedImage[3];
        //birds
        bird1Left = new BufferedImage[3];
        bird1Up = new BufferedImage[3];
        bird1Right = new BufferedImage[3];
        bird1Down = new BufferedImage[3];
        bird2Left = new BufferedImage[3];
        bird2Up = new BufferedImage[3];
        bird2Right = new BufferedImage[3];
        bird2Down = new BufferedImage[3];
        bird3Left = new BufferedImage[3];
        bird3Up = new BufferedImage[3];
        bird3Right = new BufferedImage[3];
        bird3Down = new BufferedImage[3];
        bird4Left = new BufferedImage[3];
        bird4Up = new BufferedImage[3];
        bird4Right = new BufferedImage[3];
        bird4Down = new BufferedImage[3];
        //crop
        //mouse
        for(int i=0;i<4;i++){
            playerUp[i]=spritesheet_Player.crop(i*48,3*48,48,48);
            playerLeft[i]=spritesheet_Player.crop(i*48,1*48,48,48);
            playerRight[i]=spritesheet_Player.crop(i*48,2*48,48,48);
            playerDown[i]=spritesheet_Player.crop(i*48,0*48,48,48);
        }
        //enemy and birds
        for(int i=0;i<3;i++){
            enemy1Up[i]=spritesheet_Enemy.crop(    i*32 + 0*32,3*32,32,32);
            enemy1Left[i]=spritesheet_Enemy.crop(  i*32 + 0*32,1*32,32,32);
            enemy1Right[i]=spritesheet_Enemy.crop( i*32 + 0*32,2*32,32,32);
            enemy1Down[i]=spritesheet_Enemy.crop(  i*32 + 0*32,0*32,32,32);
            
            enemy2Up[i]=spritesheet_Enemy.crop(    i*32 + 3*32,3*32,32,32);
            enemy2Left[i]=spritesheet_Enemy.crop(  i*32 + 3*32,1*32,32,32);
            enemy2Right[i]=spritesheet_Enemy.crop( i*32 + 3*32,2*32,32,32);
            enemy2Down[i]=spritesheet_Enemy.crop(  i*32 + 3*32,0*32,32,32);
            
            enemy3Up[i]=spritesheet_Enemy.crop(    i*32 + 6*32,3*32,32,32);
            enemy3Left[i]=spritesheet_Enemy.crop(  i*32 + 6*32,1*32,32,32);
            enemy3Right[i]=spritesheet_Enemy.crop( i*32 + 6*32,2*32,32,32);
            enemy3Down[i]=spritesheet_Enemy.crop(  i*32 + 6*32,0*32,32,32);
            
            enemy4Up[i]=spritesheet_Enemy.crop(    i*32 + 9*32,3*32,32,32);
            enemy4Left[i]=spritesheet_Enemy.crop(  i*32 + 9*32,1*32,32,32);
            enemy4Right[i]=spritesheet_Enemy.crop( i*32 + 9*32,2*32,32,32);
            enemy4Down[i]=spritesheet_Enemy.crop(  i*32 + 9*32,0*32,32,32);
            
            enemy5Up[i]=spritesheet_Enemy.crop(    i*32 + 0*32,7*32,32,32);
            enemy5Left[i]=spritesheet_Enemy.crop(  i*32 + 0*32,5*32,32,32);
            enemy5Right[i]=spritesheet_Enemy.crop( i*32 + 0*32,6*32,32,32);
            enemy5Down[i]=spritesheet_Enemy.crop(  i*32 + 0*32,4*32,32,32);
            
            enemy6Up[i]=spritesheet_Enemy.crop(    i*32 + 3*32,7*32,32,32);
            enemy6Left[i]=spritesheet_Enemy.crop(  i*32 + 3*32,5*32,32,32);
            enemy6Right[i]=spritesheet_Enemy.crop( i*32 + 3*32,6*32,32,32);
            enemy6Down[i]=spritesheet_Enemy.crop(  i*32 + 3*32,4*32,32,32);
            
            enemy7Up[i]=spritesheet_Enemy.crop(    i*32 + 6*32,7*32,32,32);
            enemy7Left[i]=spritesheet_Enemy.crop(  i*32 + 6*32,5*32,32,32);
            enemy7Right[i]=spritesheet_Enemy.crop( i*32 + 6*32,6*32,32,32);
            enemy7Down[i]=spritesheet_Enemy.crop(  i*32 + 6*32,4*32,32,32);
            
            enemy8Up[i]=spritesheet_Enemy.crop(    i*32 + 9*32,7*32,32,32);
            enemy8Left[i]=spritesheet_Enemy.crop(  i*32 + 9*32,5*32,32,32);
            enemy8Right[i]=spritesheet_Enemy.crop( i*32 + 9*32,6*32,32,32);
            enemy8Down[i]=spritesheet_Enemy.crop(  i*32 + 9*32,4*32,32,32);
            
            bird1Up[i]=spritesheet_Birds.crop(      i*48 + 0*48,7*48,48,48);
            bird1Left[i]=spritesheet_Birds.crop(    i*48 + 0*48,5*48,48,48);
            bird1Right[i]=spritesheet_Birds.crop(   i*48 + 0*48,6*48,48,48);
            bird1Down[i]=spritesheet_Birds.crop(    i*48 + 0*48,4*48,48,48);
            
            bird2Up[i]=spritesheet_Birds.crop(      i*48 + 3*48,7*48,48,48);
            bird2Left[i]=spritesheet_Birds.crop(    i*48 + 3*48,5*48,48,48);
            bird2Right[i]=spritesheet_Birds.crop(   i*48 + 3*48,6*48,48,48);
            bird2Down[i]=spritesheet_Birds.crop(    i*48 + 3*48,4*48,48,48);
            
            bird3Up[i]=spritesheet_Birds.crop(      i*48 + 6*48,7*48,48,48);
            bird3Left[i]=spritesheet_Birds.crop(    i*48 + 6*48,5*48,48,48);
            bird3Right[i]=spritesheet_Birds.crop(   i*48 + 6*48,6*48,48,48);
            bird3Down[i]=spritesheet_Birds.crop(    i*48 + 6*48,4*48,48,48);
            
            bird4Up[i]=spritesheet_Birds.crop(      i*48 + 9*48,7*48,48,48);
            bird4Left[i]=spritesheet_Birds.crop(    i*48 + 9*48,5*48,48,48);
            bird4Right[i]=spritesheet_Birds.crop(   i*48 + 9*48,6*48,48,48);
            bird4Down[i]=spritesheet_Birds.crop(    i*48 + 9*48,4*48,48,48);
        }
        //lives
        vida = spritesheet_Vida.crop(10*80, 10*80, 2*80, 2*80);
    }
    
}
