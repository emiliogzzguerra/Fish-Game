/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author antoniomejorado
 */
public class KeyManager implements KeyListener {
    
    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean space;   //flag to shoot
    public boolean R;
    public boolean P;
    public boolean S;
    public boolean L;

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
        //set flag space of if need
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_L)
            keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
        //set flag space of if need
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_L)
            keys[e.getKeyCode()] = true;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        R = keys[KeyEvent.VK_R];
        keys[KeyEvent.VK_R] = false;
        P = keys[KeyEvent.VK_P];
        keys[KeyEvent.VK_P] = false;
        space = keys[KeyEvent.VK_SPACE];
        keys[KeyEvent.VK_SPACE] = false;
        S = keys[KeyEvent.VK_S];
        keys[KeyEvent.VK_S] = false;
        L = keys[KeyEvent.VK_L];
        keys[KeyEvent.VK_L] = false;
    }
}
