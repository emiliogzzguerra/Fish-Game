/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.Color;
import java.awt.Graphics;

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
        this.moveToRight = true;
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
    }

    @Override
    public void render(Graphics g) {
        //imagenes segun el movimiento
        g.setColor(Color.WHITE);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
