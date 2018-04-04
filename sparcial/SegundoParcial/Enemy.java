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
public class Enemy extends Item{

    private Game game;
    private Animation animationLeft;    //to store the animation for going left
    private Animation animationRight;
    private Animation animationDown;
    private int color;
    
    public Enemy(int x, int y, int width, int height, int color, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.color=color;
        if(color==1){
            this.animationLeft = new Animation(Assets.enemy1Left,100);
            this.animationRight = new Animation(Assets.enemy1Right,100);
            this.animationDown = new Animation(Assets.enemy1Down,100);
        }
        else if(color==2){
            this.animationLeft = new Animation(Assets.enemy2Left,100);
            this.animationRight = new Animation(Assets.enemy2Right,100);
            this.animationDown = new Animation(Assets.enemy2Down,100);
        }
        else if(color==3){
            this.animationLeft = new Animation(Assets.enemy3Left,100);
            this.animationRight = new Animation(Assets.enemy3Right,100);
            this.animationDown = new Animation(Assets.enemy3Down,100);
        }
        else if(color==4){
            this.animationLeft = new Animation(Assets.enemy4Left,100);
            this.animationRight = new Animation(Assets.enemy4Right,100);
            this.animationDown = new Animation(Assets.enemy4Down,100);
        }
        else if(color==5){
            this.animationLeft = new Animation(Assets.enemy5Left,100);
            this.animationRight = new Animation(Assets.enemy5Right,100);
            this.animationDown = new Animation(Assets.enemy5Down,100);
        }
        else if(color==6){
            this.animationLeft = new Animation(Assets.enemy6Left,100);
            this.animationRight = new Animation(Assets.enemy6Right,100);
            this.animationDown = new Animation(Assets.enemy6Down,100);
        }
        else if(color==7){
            this.animationLeft = new Animation(Assets.enemy7Left,100);
            this.animationRight = new Animation(Assets.enemy7Right,100);
            this.animationDown = new Animation(Assets.enemy7Down,100);
        }
        else if(color==8){
            this.animationLeft = new Animation(Assets.enemy8Left,100);
            this.animationRight = new Animation(Assets.enemy8Right,100);
            this.animationDown = new Animation(Assets.enemy8Down,100);
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public void tick() {
        if(game.getDireccion()==0){
            setX(getX()+1);
            animationRight.tick();
        }
        else if(game.getDireccion()==1){
            setX(getX()-1);
            animationLeft.tick();
        }
        else{
            setY(getY()+1);
            animationDown.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        if(game.getDireccion()==0)
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        else if(game.getDireccion()==1)
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
