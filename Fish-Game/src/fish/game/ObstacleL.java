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
 */
public class ObstacleL extends Item{

    private Game game;
    
    public ObstacleL(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
        //movimiento
        
    }

    @Override
    public void render(Graphics g) {
        //imagen
        
    }
}
