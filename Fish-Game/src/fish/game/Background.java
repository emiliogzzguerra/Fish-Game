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
 * Fondo de pantalla
 * Ira descendiendo conforme el jugador avanza
 * 
 * Sugerencia:
 * podriamos poner una musica para cada tipo de fondo, asi es diferente fondo, tendra diferente musica.
 * 
 */
public class Background extends Item{
    private Game game;
    
    /**
     * To build a Player object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game an <code>int</code> value to get outside elements
     */
    public Background(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
        //moviendo al personaje
        if(game.getKeyManager().space){
            setY(getY()+1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.background, getX(), getY(), getWidth(), getHeight(), null);
    }
}