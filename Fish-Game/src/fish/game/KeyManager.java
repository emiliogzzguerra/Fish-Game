/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author NirvanaGaming
 * 
 * Teclas que se usan:
 *  Space
 *  P (... pausa?)
 * 
 * Nota:
 * las teclas que entran en la condicion if, solo funcionaran una vez cada vez que se oprima.
 * En caso de que uno quiera que se mantenga encendido hasta que suelte la tecla
 * Quitar la tecla de la condicion if, si esta, y guardar su estado directamente.
 * 
 */
public class KeyManager implements KeyListener {
    
    public boolean space;   //flag to shoot
    public boolean P;
    
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_P)
            keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
        //set flag space of if need
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_P)
            keys[e.getKeyCode()] = true;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        space = keys[KeyEvent.VK_SPACE];
        keys[KeyEvent.VK_SPACE] = false;
        P = keys[KeyEvent.VK_P];
        keys[KeyEvent.VK_P] = false;
    }
}
