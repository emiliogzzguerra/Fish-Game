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
 * Persigue al Pez, dando ilusion de movimiento.
 */
public class Stalker extends Item{
    private Game game;
    //boolean
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;
    //int internos
    private int centRight;
    private int centLeft;
    private int centUp;
    private int centDown;
    private int centDef;
    //int recibe
    private int centRightDef;
    private int centLeftDef;
    private int centUpOpu;
    private int centDownOpu;
    private int centOpu;
    
    /**
     * To build a Player object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game an <code>int</code> value to get outside elements
     */
    public Stalker(int x, int y, int size, int centRight, int centLeft, int centOpu, int centUpOpu, int centDownOpu, Game game) {
        super(x - centOpu, y, size, size);
        this.game = game;
        this.centRight = centRightDef = centRight - centOpu + 10;
        this.centLeft = centLeftDef = centLeft + centOpu - 10;
        centUp = centDown = centDef = 0;
        this.centOpu = centOpu;
        this.centUpOpu = centUpOpu + centOpu - 15;
        this.centDownOpu = centDownOpu - centOpu;
        //boolean
        right = false;
        left = false;
        up = false;
        down = false;
    }
    
    public boolean chUp() {
        if(game.getPlayer().getYmid()+centUp-3<getYmid() && game.getPlayer().getYmid()+centUp+3>getYmid())
            return false;
        else if(game.getPlayer().getYmid()+centUp-3<getYmid() && !(game.getPlayer().getYmid()+centUp+3>getYmid()))
            return true;
        return false;
    }

    public boolean chDown() {
        if(game.getPlayer().getYmid()+centDown-3<getYmid() && game.getPlayer().getYmid()+centDown+3>getYmid())
            return false;
        else if(!(game.getPlayer().getYmid()+centDown-3<getYmid()) && game.getPlayer().getYmid()+centDown+3>getYmid())
            return true;
        return false;
    }

    public boolean chLeft() {
        if(game.getPlayer().getXmid()+centLeft-3<getXmid() && game.getPlayer().getXmid()+centLeft+3>getXmid())
            return false;
        else if(game.getPlayer().getXmid()+centLeft-3<getXmid() && !(game.getPlayer().getXmid()+centLeft+3>getXmid()))
            return true;
        return false;
    }

    public boolean chRight() {
        if(game.getPlayer().getXmid()+centRight-3<getXmid() && game.getPlayer().getXmid()+centRight+3>getXmid())
            return false;
        else if(!(game.getPlayer().getXmid()+centRight-3<getXmid()) && game.getPlayer().getXmid()+centRight+3>getXmid())
            return true;
        return false;
    }
    
    public void voltearVar(){
        centRight = centDef-centOpu;
        centLeft = centDef+centOpu;
        centUp = centUpOpu;
        centDown = centUpOpu;
    }
    
    public void defaultVar(){
        centRight = centRightDef;
        centLeft = centLeftDef;
        centUp = centDown = centDef;
    }

    @Override
    public void tick() {
        if(game.getKeyManager().space)
            voltearVar();
        else
            defaultVar();
        //moviendo al personaje de derecha a izquierda
        left = chLeft();
        right = chRight();
        up = chUp();
        down = chDown();
        
        if(left)
            setX(getX()-7);
        if(right){
            setX(getX()+7);
        }
        if(up){
            setY(getY()-1);
        }
        if(down){
            setY(getY()+1);
        }
    }

    @Override
    public void render(Graphics g) {
        //imagenes segun el movimiento
        g.setColor(Color.RED);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
