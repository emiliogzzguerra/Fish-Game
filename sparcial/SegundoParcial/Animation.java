/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author NamiDW
 */
public class Animation {
    private int speed;  //for the speed of every frame
    private int index;  //to get the index of next frame to paint
    private long lastTime;  //to save the prevoius time of the animation
    private long timer; //to acumulate the time of the animation
    private BufferedImage[] frames; //to store every image - frame
    
    public Animation(BufferedImage[] frames, int speed){
        this.frames = frames;   //storing frames
        this.speed = speed;     //storing speed
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();  //getting the initial time
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public BufferedImage getFrames(int n) {
        return frames[n];
    }
    
    public void tick(){
        //acumulating time from the prevoius tick to this one
        timer += System.currentTimeMillis() - lastTime;
        //updating the lastTime for the next tick
        lastTime = System.currentTimeMillis();
        //check the timer to increase the index
        if(timer>speed){
            index++;
            timer = 0;
            //check index not to get out of the bounds
            if(index>=frames.length){
                index=0;
            }
        }
    }
}
