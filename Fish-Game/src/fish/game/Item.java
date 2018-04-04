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
 * @author aguir
 */
public abstract class Item {
    protected int x;        // to store x position
    protected int y;        // to store y position
    protected Rectangle rectangle; // to manage collisions
    protected int width;
    protected int height;
    protected Game game;

    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y, int width, int height, Game game) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;
        
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
    
    /**
     * Returns width of the object
     * @return Width of the item
     */
    
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns height
     * @return Height of the item
     */
    
    public int getHeight() {
        return height;
    }
    
    /**
     * Sets width of the object
     * @param width 
     */
    
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets height of the object
     * @param height 
     */
    
    public void setHeight(int height) {
        this.height = height;
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
     * Creates rectangle of the item
     */
    
    public abstract void setRectangle();
    
    
    public abstract void updateRectangle();
    
    /**
     * Returns rectangle of the object
     * @return Rectangle to manage collisions
     */
    public Rectangle getRectangle(){
        return rectangle;
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
    
    /**
     * Checks collision between this item and the one received
     * @param item Item to check collision
     * @return True if the two items are colliding
     */
    public boolean collision(Item item){
        return getRectangle().intersects(item.getRectangle().getBounds());
    }
}
