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
 */
public class ObstacleR extends Item{
    private Game game;
    
    public ObstacleR(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
        //moviendo a los obstaculos
        if(game.getKeyManager().space){
            setY(getY()+game.getVel());
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.wood, getX(), getY(), getWidth(), getHeight(), null);
    }
}
