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
public class Wall extends Item{

    private Game game;
    private Animation animationUp;
    private int vida;
    private int antvida;
    
    public Wall(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        vida = 4;
        this.animationUp = new Animation(Assets.bird4Up,100);
        antvida=vida;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    public void setAnimation(){
        if(vida==1){
            this.animationUp = new Animation(Assets.bird1Up,100);
        }
        else if(vida==2){
            this.animationUp = new Animation(Assets.bird2Up,100);
        }
        else if(vida==3){
            this.animationUp = new Animation(Assets.bird3Up,100);
        }
        else if(vida==4){
            this.animationUp = new Animation(Assets.bird4Up,100);
        }
    }

    @Override
    public void tick() {
        if(antvida!=vida){
            setAnimation();
            antvida=vida;
        }
        else
            animationUp.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
