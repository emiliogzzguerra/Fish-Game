/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

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
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        musica = new SoundClip("/sonido/choque-madera.wav");
        
        this.animationUp = new Animation(Assets.playerUp,100);
        this.animationLeft = new Animation(Assets.playerLeft,100);
        this.animationDown = new Animation(Assets.playerDown,100);
        this.animationRight = new Animation(Assets.playerRight,100);
    }

    @Override
    public void tick() {
        // moving player depending on flags
        if(game.getKeyManager().left){
            setX(getX()-1);
            //updating animation
            this.animationLeft.tick();
        }
        else if(game.getKeyManager().right){
            setX(getX()+1);
            //updating animation
            this.animationRight.tick();
        }
        
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
            musica.play();
        }
        else if (getX() <= -30) {
            setX(-30);
            musica.play();
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
            musica.play();
        }
        else if (getY() <= -20) {
            setY(-20);
            musica.play();
        }
    }

    @Override
    public void render(Graphics g) {
        if(game.getKeyManager().left)
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        else if(game.getKeyManager().right)
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(animationUp.getFrames(0), getX(), getY(), getWidth(), getHeight(), null);
    }
}
