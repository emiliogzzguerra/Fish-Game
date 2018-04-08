/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author NirvanaGaming
 * 
 * Por corregir
 * El sonido al salir de los limites de la ventana, esta actualmente dentro del
 * personaje... se queda asi o se cambia a game??
 * 
 * Nota
 * No modificar == Solo para quien sepa que hace
 * 
 */
public class Game implements Runnable {
    //obligatorios
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // largo <->
    private int height;             // alto
    private Thread thread;          // thread to create the game
    private KeyManager keyManager;  // to manage the keyboard
    
    //Objetos
    private Pez pez;          // to use a player
    //Arreglos
    private ArrayList<Background> backgrounds; //to store background collection
    private ArrayList<ObstacleL> obstaclesL; //to store enemies collection
    private ArrayList<ObstacleR> obstaclesR; //to store enemies collection
    private ArrayList<Stalker> stalkers; //to store stalkers collection
    //extras
    String titulo;
    //ints
    private int puntuacion;
    private int contador;
    private int contObstacle;
    //extras
    String tituloPuntos;
    String letPuntos;
    //boolean
    private boolean running;        // to set the game
    private boolean gameover;       // to end the game
    private boolean blinking;
    
    /**
     * to create title, width and height and set the game is still not running
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(int width, int height, String title) {
        //obligatorio
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        //boolean
        running = false;
        gameover = false;
        blinking = false;
        //int
        puntuacion = 0;
        contador = 0;
        contObstacle = 0;
        //extras
        letPuntos = "Score: ";
        tituloPuntos = letPuntos + puntuacion;
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Pez getPlayer() {
        return pez;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
        contador = 0;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        //no modificar
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         
         //create player
         pez = new Pez(getWidth()/2,getHeight()-100, 40, 40, this);
         
         //create Array of backgrounds
         backgrounds = new ArrayList<>();
         crearBackground();
         //create Array of stalkers
         stalkers = new ArrayList<>();
         crearStalkers();
         //create Array of obstacles
         obstaclesL = new ArrayList<>();
         obstaclesR = new ArrayList<>();
         crearObstacles();
         
         //no modificar
         display.getJframe().addKeyListener(keyManager);
    }
    
    private void crearBackground(){
        backgrounds.add(new Background(0,0,getWidth(),getHeight(),this));
        backgrounds.add(new Background(0,-getHeight(),getWidth(),getHeight(),this));
        backgrounds.add(new Background(0,-getHeight()*2,getWidth(),getHeight(),this));
    }
    
    private void crearObstacles(){
         for(int i = 0; i < 4; i++){
            if((int) (Math.random()*2)==0)
                obstaclesR.add(new ObstacleR(getWidth()-100,i*(getHeight()/5)-30,100,30,this));
            else
                obstaclesL.add(new ObstacleL(0,i*(getHeight()/5)-30,100,30,this));
         }
    }
    
    private void crearStalkers(){
        for(int i=0; i<9; i++){
            stalkers.add(new Stalker(pez.getX()-pez.getWidth()/2,pez.getY(),(9-i)*(pez.getWidth()/10),-(pez.getWidth()/2),(pez.getWidth()/2),(i+1)*(pez.getWidth()/5),(pez.getHeight()/2),(pez.getHeight()/2),this));
        }
    }
    
     private void agregarObstacle(){
        if((int) (Math.random()*2)==0)
            obstaclesR.add(new ObstacleR(getWidth()-100,-30,100,30,this));
        else
            obstaclesL.add(new ObstacleL(0,-30,100,30,this));
    }
    
    private void eliminarBackground(){
        Iterator itr = backgrounds.iterator();
        while(itr.hasNext()){
            backgrounds.remove((Background) itr.next());
            itr = backgrounds.iterator();
        }
    }
    
    private void eliminarObstacles(){
        Iterator itr = obstaclesL.iterator();
        while(itr.hasNext()){
            obstaclesL.remove((ObstacleL) itr.next());
            itr = obstaclesL.iterator();
        }
        itr = obstaclesR.iterator();
        while(itr.hasNext()){
            obstaclesR.remove((ObstacleR) itr.next());
            itr = obstaclesR.iterator();
        }
    }
    
   private void eliminarStalkers(){
        Iterator itr = stalkers.iterator();
        while(itr.hasNext()){
            stalkers.remove((Stalker) itr.next());
            itr = stalkers.iterator();
        }
    }
    
    private void reordenar(){
        //eliminar objetos de arreglos
        eliminarBackground();
        eliminarObstacles();
        eliminarStalkers();
        //reposicionar player
        pez.setX(getWidth()/2);
        //reiniciar conteo
        setPuntuacion(0);
        //recrear objetos de arreglos
        crearBackground();
        crearObstacles();
        crearStalkers();
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now; 
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }
    
    private void tick() {
        //KeyManager
        keyManager.tick();
        if(!gameover){
            if(keyManager.space){
                contador++;
                if(contador>=100){
                    puntuacion++;
                    contador=0;
                }
                contObstacle++;
                if(contObstacle>=(getHeight()/5)){
                    agregarObstacle();
                    contObstacle=0;
                }
            }
            //player
            pez.tick();
            //background
            Iterator itr = backgrounds.iterator();
            while(itr.hasNext()){
                Background background = (Background) itr.next();
                background.tick();
                if(background.getY()>=getHeight()){
                    background.setY(-getHeight()*2);
                }
            }
            //stalker
            itr = stalkers.iterator();
            while(itr.hasNext()){
                Stalker stalker = (Stalker) itr.next();
                stalker.tick();
            }
            //obstacles
            itr = obstaclesL.iterator();
            while(itr.hasNext()){
                ObstacleL obstacle = (ObstacleL) itr.next();
                obstacle.tick();
                //si sale del juego
                if(obstacle.getY() >= getHeight()){
                    obstaclesL.remove(obstacle);
                    itr = obstaclesL.iterator();
                }
                //si choca con player
                if(obstacle.intersects(pez))
                    gameover = true;
            }
            itr = obstaclesR.iterator();
            while(itr.hasNext()){
                ObstacleR obstacle = (ObstacleR) itr.next();
                obstacle.tick();
                //si sale del juego
                if(obstacle.getY() >= getHeight()){
                    obstaclesR.remove(obstacle);
                    itr = obstaclesR.iterator();
                }
                //si choca con player
                if(obstacle.intersects(pez))
                    gameover= true;
            }
            //actualizar score
            tituloPuntos = letPuntos + puntuacion;
        }
        else
            if(keyManager.R){
                gameover = false;
                reordenar();
            }
    }
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            
            //fondo de pantalla
            Iterator itr = backgrounds.iterator();
            while(itr.hasNext()){
                Background background = (Background) itr.next();
                background.render(g);
            }
            //stalker (IMPORTANTE: mantener el stalker.render arriba del pez.render)
            itr = stalkers.iterator();
            while(itr.hasNext())
                ((Stalker) itr.next()).render(g);
            //obstaculos
            itr = obstaclesL.iterator();
            while (itr.hasNext())
                ((ObstacleL) itr.next()).render(g);
            itr = obstaclesR.iterator();
            while (itr.hasNext())
                ((ObstacleR) itr.next()).render(g);
            //player
            pez.render(g);
            //score
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            g2d.drawString(tituloPuntos, 10, 30);
            //gameover (IMPORTANTE: mantener el gameove.render por debajo de los otros renders)
            if(gameover){
                g.drawImage(Assets.gameover, 0, 0, width, height, null);
                
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Verdana", Font.BOLD, 80));
                g2d.drawString("Gameover", (getWidth()/2)-200, getHeight()/6);
                
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Verdana", Font.BOLD, 60));
                g2d.drawString("Score", (getWidth()/2)-100, getHeight()/3);
                
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Verdana", Font.BOLD, 100));
                g2d.drawString("" + puntuacion, (getWidth()/2)-40, getHeight()/2);
                
                if(blinking){
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Verdana", Font.BOLD, 20));
                    g2d.drawString("Presiona 'R' para volver a jugar!!", 200, getHeight()-40);
                    contador++;
                    if(contador >= 17){
                        blinking = false;
                        contador = 0;
                    }
                }
                else{
                    contador++;
                    if(contador >= 17){
                        blinking = true;
                        contador = 0;
                    }
                }
            }
            
            //no modificar
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
}