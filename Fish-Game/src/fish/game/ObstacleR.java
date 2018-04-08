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
            setY(getY()+1);
        }
        
    }

    @Override
    public void render(Graphics g) {
        //rectangulo por ahora...
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
