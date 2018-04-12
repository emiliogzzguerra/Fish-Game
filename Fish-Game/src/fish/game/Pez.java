/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author NirvanaGaming
 * 
 * Movimiento:
 * Derecha e Izquierda
 * 
 */
public class Pez extends Item{
    private Game game;
    //animation
    private Animation pezUp;
    private Animation pezLeft;
    private Animation pezRight;
    //boolean
    private boolean moveToRight;
    
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
        //animation
        pezUp = new Animation(Assets.player1Up,100);
        pezLeft = new Animation(Assets.player1Left,100);
        pezRight = new Animation(Assets.player1Right,100);
        //boolean
        moveToRight = true;
    }

    @Override
    public void tick() {
        //moviendo al personaje de derecha a izquierda
        if(moveToRight){
            setX(getX() + 5);
            if(getX()+getWidth() >= game.getWidth()-20){
                moveToRight = false;
            }
        } else {
            setX(getX() - 5);
            if(getX() <= 20){
                moveToRight = true;
            }
        }
        pezUp.tick();
        pezLeft.tick();
        pezRight.tick();
    }

    @Override
    public void render(Graphics g) {
        //imagenes segun el movimiento
        if(game.getKeyManager().space){
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform at = AffineTransform.getTranslateInstance(getX(), getY());
            if(moveToRight)
                at.rotate(Math.toRadians(45),30,20);
            else
                at.rotate(Math.toRadians(-45),10,30);
            at.scale(1.2, 1.2);
            g2d.drawImage(pezUp.getCurrentFrame(), at,null);
        }
        else if(moveToRight)
            g.drawImage(pezRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(pezLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
