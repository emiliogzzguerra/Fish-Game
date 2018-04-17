/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

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
    //animation
    private Animation pezUp;
    private Animation pezLeft;
    private Animation pezRight;
    //boolean
    private boolean moveToRight;
    //ints
    private int velocityBase;

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
        //animation
        pezUp = new Animation(Assets.player1Up,100);
        pezLeft = new Animation(Assets.player1Left,100);
        pezRight = new Animation(Assets.player1Right,100);
        //boolean
        moveToRight = true;
        //int
        velocityBase = 6;
    }

    @Override
    public void tick() {
        //moviendo al personaje de derecha a izquierda
        if(moveToRight){
            if(getX()+getWidth() >= game.getWidth()-width){
                moveToRight = false;
            } else {
              switch(getNumberedPosition(this.game)){
                  case 0:
                      setX(getX() + velocityBase);
                      break;
                  case 1:
                      setX(getX() + (velocityBase + 1));
                      break;
                  case 2:
                      setX(getX() + (velocityBase + 2));
                      break;
                  case 3:
                      setX(getX() + (velocityBase + 3));
                      break;
                  case 4:
                      setX(getX() + (velocityBase + 4));
                      break;
                  case 5:
                      setX(getX() + (velocityBase + 6));
                      break;
                  case 6:
                      setX(getX() + (velocityBase + 6));
                      break;
                  case 7:
                      setX(getX() + (velocityBase + 4));
                      break;
                  case 8:
                      setX(getX() + (velocityBase + 3));
                      break;
                  case 9:
                      setX(getX() + (velocityBase + 2));
                      break;
                  case 10:
                      setX(getX() + (velocityBase + 1));
                      break;
                  case 11:
                      setX(getX() + velocityBase);
                      break;
              }
            }
        } else {
            if(getX() <= width){
                moveToRight = true;
            } else {
              switch(getNumberedPosition(this.game)){
                  case 0:
                      setX(getX() - velocityBase);
                      break;
                  case 1:
                      setX(getX() - (velocityBase + 1));
                      break;
                  case 2:
                      setX(getX() - (velocityBase + 2));
                      break;
                  case 3:
                      setX(getX() - (velocityBase + 3));
                      break;
                  case 4:
                      setX(getX() - (velocityBase + 4));
                      break;
                  case 5:
                      setX(getX() - (velocityBase + 6));
                      break;
                  case 6:
                      setX(getX() - (velocityBase + 6));
                      break;
                  case 7:
                      setX(getX() - (velocityBase + 4));
                      break;
                  case 8:
                      setX(getX() - (velocityBase + 3));
                      break;
                  case 9:
                      setX(getX() - (velocityBase + 2));
                      break;
                  case 10:
                      setX(getX() - (velocityBase + 1));
                      break;
                  case 11:
                      setX(getX() - velocityBase);
                      break;
              }
          }
        }
        pezUp.tick();
        pezLeft.tick();
        pezRight.tick();
    }

    @Override
    public void render(Graphics g) {
        //imagenes segun el movimiento
        if(game.getKeyManager().space){
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform at = AffineTransform.getTranslateInstance(getX(), getY());
            if(moveToRight)
                at.rotate(Math.toRadians(45),30,20);
            else
                at.rotate(Math.toRadians(-45),10,30);
            at.scale(1.2, 1.2);
            g2d.drawImage(pezUp.getCurrentFrame(), at,null);
        }
        else if(moveToRight)
            g.drawImage(pezRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(pezLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
