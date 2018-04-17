/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fish.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author NirvanaGaming
 * 
 * No modificar
 * 
 */
public abstract class Item {
    protected int x;        // to store x position
    protected int y;        // to store y position
    protected int width;
    protected int height;
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Get x value
     * @return x 
     */
    public int getX() {
        return x;
    }

    /**
     * Get y value
     * @return y 
     */
    public int getY() {
        return y;
    }
    
    public int getXmid(){
        return getX()+getWidth()/2;
    }
    
    public int getYmid(){
        return getY()+getHeight()/2;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public boolean intersects (Object obj){
        return ( (obj instanceof Item) && this.getBounds().intersects(((Item) obj).getBounds()) );
    }
    
    private Rectangle getBounds(){
        return new Rectangle(getX()+(getWidth()/6),getY()+(getHeight()/6),getWidth()-(getWidth()/3),getHeight()-(getHeight()/3));
    }
    
    /**
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * To update positions of the item for every tick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
    
    public int getNumberedPosition(Game g){
        int auxInt = 0;
        int currPosition = getX();
        int gameWidthDivided = g.getWidth()/12;
            if(currPosition >= 0 && currPosition<gameWidthDivided){
                auxInt = 0;
            } else if(currPosition >= gameWidthDivided && currPosition<(gameWidthDivided*2)){
                auxInt = 1;
            } else if(currPosition >= (gameWidthDivided*2) && currPosition<(gameWidthDivided*3)){
                auxInt = 2;
            } else if(currPosition >= (gameWidthDivided*3) && currPosition<(gameWidthDivided*4)){
                auxInt = 3;
            } else if(currPosition >= (gameWidthDivided*4) && currPosition<(gameWidthDivided*5)){
                auxInt = 4;
            } else if(currPosition >= (gameWidthDivided*5) && currPosition<(gameWidthDivided*6)){
                auxInt = 5;
            } else if(currPosition >= (gameWidthDivided*6) && currPosition<(gameWidthDivided*7)){
                auxInt = 6;
            } else if(currPosition >= (gameWidthDivided*7) && currPosition<(gameWidthDivided*8)){
                auxInt = 7;
            } else if(currPosition >= (gameWidthDivided*8) && currPosition<(gameWidthDivided*9)){
                auxInt = 8;
            } else if(currPosition >= (gameWidthDivided*9) && currPosition<(gameWidthDivided*10)){
                auxInt = 9;
            } else if(currPosition >= (gameWidthDivided*10) && currPosition<(gameWidthDivided*11)){
                auxInt = 10;
            } else if(currPosition >= (gameWidthDivided*11) && currPosition<(gameWidthDivided*12)){
                auxInt = 11;
            }
        return auxInt;
    }
}
