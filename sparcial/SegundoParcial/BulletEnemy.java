/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author NamiDW
 */
public class BulletEnemy extends Item{

    private int width;
    private int height;
    private Game game;
    private int direccion; //0 player. 1 enemigo
    
    public BulletEnemy(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.direccion = direccion;
    }

    @Override
    public void tick() {
        setY(getY()+1);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chees, getX(), getY(), getWidth(), getHeight(), null);
    }
}
