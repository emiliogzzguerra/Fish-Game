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
 * Mostrar score en pantalla
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
    //extras
    String titulo;
    //sonido
    private SoundClip musica;
    //ints
    private int puntuacion;
    //extras
    String tituloPuntos;
    String letPuntos;
    //boolean
    private boolean running;        // to set the game
    
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
        //int
        puntuacion = 0;
        //extras
        letPuntos = "Score: ";
        tituloPuntos = letPuntos + puntuacion;
        //agregar sonidos (por definir, para version 2)
        //musica = new SoundClip("/sonido/ringtones-be-happy.wav");
        //musica.setLooping(true);
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

    public ArrayList<ObstacleL> getObstacle() {
        return obstaclesL;
    }

    public Pez getPlayer() {
        return pez;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         //inicia el jugador donde??
         //pez = new Pez();


         //create Array of backgrounds
         backgrounds = new ArrayList<>();
         crearBackground();
         //create Array of obstacles left
         obstaclesL = new ArrayList<>();
         //create Array of obstacles right
         obstaclesR = new ArrayList<>();
         //adding enemies to the colection
         int widthObstacle = 50;
         int heightObstacle = 30;
         for(int i = 0; i < 4; i++)
         {
             ObstacleL obstacleL = new ObstacleL(0 ,i*100,widthObstacle, heightObstacle, this); 
             obstaclesL.add(obstacleL);
         }
         for(int i = 0; i < 4; i++)
         {
             ObstacleL obstacleL = new ObstacleL(750 ,i*100,widthObstacle, heightObstacle, this); 
             obstaclesL.add(obstacleL);
         }
         //no modificar
         display.getJframe().addKeyListener(keyManager);
    }
    
    private void crearBackground(){
        for(int cont=0; cont<3; cont++){
            backgrounds.add(new Background(0,0,getWidth(),getHeight(),this));
            backgrounds.add(new Background(0,-getHeight(),getWidth(),getHeight(),this));
            backgrounds.add(new Background(0,-getHeight()*2,getWidth(),getHeight(),this));
        }
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
        //player
        //pez.tick();
        //background
        Iterator itr = backgrounds.iterator();
        while(itr.hasNext()){
            Background background = (Background) itr.next();
            background.tick();
            if(background.getY()>=getHeight()){
                background.setY(-getHeight()*2);
            }
        }
        //actualizar score
        tituloPuntos = letPuntos + puntuacion;
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
            //player
            //pez.render(g);
            //score
            Graphics2D g2d = (Graphics2D) g;
            //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            g2d.drawString(tituloPuntos, 10, 30);
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