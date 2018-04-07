/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.Graphics;

/**
 *
 * @author NirvanaGaming
 * 
 * Movimiento:
 * Derecha e Izquierda
 * 
 * Nota:
 * Necesita animation Up and Down??
 * 
 */
public class Pez extends Item{
    chequen pez movimiento!!!

    private Game game;
    private Animation animationUp;  //to store the animation for going up
    private Animation animationLeft;    //to store the animation for going left
    private Animation animationDown;    //to store the animation for going down
    private Animation animationRight;   //to store the animation for going right
    private SoundClip musica;
    
    
    /**
     * To build a Player object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game an <code>int</code> value to get outside elements
     */
    public Pez(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        //sonido al traspasar los limites de la ventana
        musica = new SoundClip("/sonido/choque-madera.wav");
        //animaciones
        this.animationUp = new Animation(Assets.playerUp,100);
        this.animationLeft = new Animation(Assets.playerLeft,100);
        this.animationDown = new Animation(Assets.playerDown,100);
        this.animationRight = new Animation(Assets.playerRight,100);
    }

    @Override
    public void tick() {
        //moviendo al personaje
        
        
        //personaje fuera de limites de ventana
        
    }

    @Override
    public void render(Graphics g) {
        //imagenes segun el movimiento
        
    }
}
