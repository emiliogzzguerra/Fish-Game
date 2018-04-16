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
 * Decoraciones en el borde de la ventana
 * Ira descendiendo conforme el jugador avanza
 * Se genera una decoracion aleatoria
 * 
 */
public class Decoracion extends Item{
    private Game game;
    //int
    private int decora;
    
    /**
     * To build a Player object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game an <code>int</code> value to get outside elements
     */
    public Decoracion(int x, int y, int width, int height, int num, Game game) {
        super(x, y, width, height);
        this.game = game;
        decora = num;
    }

    public void setDecora(int decora) {
        this.decora = decora;
    }

    @Override
    public void tick() {
        if(game.getKeyManager().space){
            setY(getY()+game.getVel());
        }
    }

    @Override
    public void render(Graphics g) {
        if(getX()==0){
            if(decora==0)
                g.drawImage(Assets.arbusto, getX()-30, getY(), getWidth(), getHeight(), null);
            else if(decora==1)
                g.drawImage(Assets.arbusto2, getX()-30, getY(), getWidth(), getHeight(), null);
            else
                g.drawImage(Assets.arbol, getX()-20, getY(), getWidth(), getHeight()+50, null);
        }
        else{
            if(decora==0)
                g.drawImage(Assets.arbusto, getX()+30, getY(), getWidth(), getHeight(), null);
            else if(decora==1)
                g.drawImage(Assets.arbusto2, getX()+30, getY(), getWidth(), getHeight(), null);
            else
                g.drawImage(Assets.arbol, getX()+20, getY(), getWidth(), getHeight()+50, null);
        }
    }
}