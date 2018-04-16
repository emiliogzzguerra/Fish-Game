/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author NirvanaGamming
 * 
 * No modificar
 * 
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean izquierdo;  //to check if left has been pushed
    private boolean derecho;    //to check if right has been pushed
    private int x;              //to get x position of the mouse
    private int y;              //to get y position of the mouse
    
    @Override
    public void mouseClicked(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        izquierdo = true;
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseExited(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    public void setDerecho(boolean derecho) {
        this.derecho = derecho;
    }

    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }

    public boolean isDerecho() {
        return derecho;
    }

    public boolean isIzquierdo() {
        return izquierdo;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
}