/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.image.BufferedImage;

/**
 *
 * @author NirvanaGaming
 * 
 * No modificar
 * 
 */
public class SpreadSheet {
    private BufferedImage sheet;    //to store the spritesheet
    
    public SpreadSheet(BufferedImage sheet){
        this.sheet=sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
