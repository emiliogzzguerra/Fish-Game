/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author NamiDW
 */
public class Vida extends Item{

    private Game game;
    
    public Vida(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.vida, getX(), getY(), getWidth(), getHeight(), null);
    }
}
