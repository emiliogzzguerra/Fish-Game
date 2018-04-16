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
    private MouseManager mouseManager; //to manage the mouse
    
    //Objetos
    private Pez pez;          // to use a player
    
    //Arreglos
    private ArrayList<Background> backgrounds; //to store background collection
    private ArrayList<ObstacleL> obstaclesL; //to store enemies collection
    private ArrayList<ObstacleM> obstaclesM; //to store enemies collection
    private ArrayList<ObstacleR> obstaclesR; //to store enemies collection
    private ArrayList<Stalker> stalkers; //to store stalkers collection
    private ArrayList<Decoracion> decoraciones; //to store decoraciones collection
    private ArrayList<Pez> players; //to store players collection
    
    //ints
    private int puntuacion;
    private int contador;
    
    //extras
    String tituloPuntos;
    String letPuntos;
    
    //boolean
    private boolean running;        // to set the game
    private boolean gameover;       // to end the game
    private boolean blinking;
    private boolean beginning;
    
    /**
     * to create title, width and height and set the game is still not running
     * @param width to set the width of the window
     * @param height to set the height of the window
     * @param title to set the title of the window
     */
    public Game(int width, int height, String title) {
        //obligatorio
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        
        //boolean
        running = false;
        gameover = false;
        blinking = false;
        beginning = true;
        
        //int
        puntuacion = 0;
        contador = 0;
        
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

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public Pez getPlayer() {
        return pez;
    }
    
    public int getVel(){
        if(puntuacion/10==0)
            return 1;
        if(puntuacion/20<2)
            return 2;
        return puntuacion/20;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
        contador = 0;
    }

    public void setBeginning(boolean beginning) {
        this.beginning = beginning;
    }

    public boolean isBeginning() {
        return beginning;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        //no modificar
        display = new Display(title, getWidth(), getHeight());  
        Assets.init();
        
        //create player
        pez = new Pez(getWidth()/2,getHeight()-100, 40, 40, false, 1, this);
         
        //create Array of backgrounds
        backgrounds = new ArrayList<>();
        crearBackground();
        
        //create Array of stalkers
        stalkers = new ArrayList<>();
        crearStalkers();
        
        //create Array of obstacles
        obstaclesL = new ArrayList<>();
        obstaclesM = new ArrayList<>();
        obstaclesR = new ArrayList<>();
        crearObstacles();
        
        //create Array of decoraciones
        decoraciones = new ArrayList<>();         
        crearDecoraciones();
        
        //create Array of players
        players = new ArrayList<>();
        crearPlayers();
        
        //no modificar
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
    }
    
    private void crearBackground(){
        for(int cont=0; cont<2; cont++){
            //Primera fila de backgrounds
            backgrounds.add(new Background(0,-cont*getHeight()+getHeight()/2,getWidth()/4,getHeight()/2,1,this));
            backgrounds.add(new Background(getWidth()/4,-cont*getHeight()+getHeight()/2,getWidth()/4,getHeight()/2,2,this));
            backgrounds.add(new Background(getWidth()/2,-cont*getHeight()+getHeight()/2,getWidth()/4,getHeight()/2,3,this));
            backgrounds.add(new Background(3*getWidth()/4,-cont*getHeight()+getHeight()/2,getWidth()/4,getHeight()/2,4,this));
            
            //segunda fila de backgrounds
            backgrounds.add(new Background(0,-cont*getHeight(),getWidth()/4,getHeight()/2,1,this));
            backgrounds.add(new Background(getWidth()/4,-cont*getHeight(),getWidth()/4,getHeight()/2,2,this));
            backgrounds.add(new Background(getWidth()/2,-cont*getHeight(),getWidth()/4,getHeight()/2,3,this));
            backgrounds.add(new Background(3*getWidth()/4,-cont*getHeight(),getWidth()/4,getHeight()/2,4,this));
        }
    }
    
    private void crearObstacles(){
         for(int i = 0; i < 4; i++){
            if((int) (Math.random()*2)==0)
                obstaclesR.add(new ObstacleR(getWidth()-200,i*(getHeight()/5)-30,140,40,this));
            else
                obstaclesL.add(new ObstacleL(50,i*(getHeight()/5)-30,140,40,this));
         }
    }
    
    private void crearStalkers(){
        for(int i=0; i<9; i++){
            stalkers.add(new Stalker(pez.getX()-pez.getWidth()/2,pez.getY(),(9-i)*(pez.getWidth()/10),-(pez.getWidth()/2),(pez.getWidth()/2),(i+1)*(pez.getWidth()/5),(pez.getHeight()/2),(pez.getHeight()/2),this));
        }
    }
    
    private void crearDecoraciones(){
        for(int i=0; i<6; i++){
            //decoraciones= izquierda
            decoraciones.add(new Decoracion(0,-120+i*100,100,100, (int) (Math.random()*3),this));
            
            //decoraciones= derecha
            decoraciones.add(new Decoracion(getWidth()-100,-20+i*100,100,100, (int) (Math.random()*3),this));
        }
    }
    
    private void crearPlayers(){
        for(int i=0; i<4; i++){
            players.add(new Pez(150+i*150,250,70,70,true,i+1,this));
        }
    }
    
    private void agregarObstacle(){
        if((int) (Math.random()*2)==0)
            obstaclesR.add(new ObstacleR(getWidth()-200,-50,140,40,this));
        else
            obstaclesL.add(new ObstacleL(50,-50,140,40,this));
        if(puntuacion>=30){
            if((int) (Math.random()*4)==0)
                obstaclesM.add(new ObstacleM((getWidth()/2)-50,-120,120,100,this));
        }
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
        itr = obstaclesM.iterator();
        while(itr.hasNext()){
            obstaclesM.remove((ObstacleM) itr.next());
            itr = obstaclesM.iterator();
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
    
    private void eliminarDecoraciones(){
        Iterator itr = decoraciones.iterator();
        while(itr.hasNext()){
            decoraciones.remove((Decoracion) itr.next());
            itr = decoraciones.iterator();
        }
    }
    
    private void reordenar(){
        //eliminar objetos de arreglos
        eliminarBackground();
        eliminarObstacles();
        eliminarStalkers();
        eliminarDecoraciones();
        
        //reposicionar player
        pez.setX(getWidth()/2);
        
        //reiniciar conteo
        setPuntuacion(0);
        
        //recrear objetos de arreglos
        crearBackground();
        crearObstacles();
        crearStalkers();
        crearDecoraciones();
    }
    
    private void textoBeginning(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //color de la letra
        g2d.setColor(Color.BLACK);
        
        //Titulo del juego
        //Otra opcion: AR CENA
        g2d.setFont(new Font("Eras Bold ITC", Font.BOLD, 80));
        g2d.drawString("Fish-Game", (getWidth()/2)-150, getHeight()/6);
        
        //Creadores
        g2d.setFont(new Font("AR DESTINE", Font.BOLD, 40));
        g2d.drawString("By NirvanaGamming", (getWidth()/2)-230, getHeight()-100);
    }
    
    private void textoGameover(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //color de la letra
        g2d.setColor(Color.black);
        
        //Gameover Abajo
        g2d.setFont(new Font("AR CHRISTY", Font.BOLD, 80));
        g2d.drawString("Gameover", (getWidth()/2)-187, getHeight()/6-2);
        
        //Gameover Abajo
        g2d.setFont(new Font("AR CHRISTY", Font.BOLD, 80));
        g2d.drawString("Gameover", (getWidth()/2)-193, getHeight()/6+2);
        
        //color de la letra
        g2d.setColor(Color.red);
        
        //Gameover Arriba
        g2d.setFont(new Font("AR CHRISTY", Font.BOLD, 80));
        g2d.drawString("Gameover", (getWidth()/2)-190, getHeight()/6);
        
        //color de la letra
        g2d.setColor(Color.black);
        
        //Score Abajo
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 60));
        g2d.drawString("Score", 103, getHeight()/3-2);
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 100));
        g2d.drawString("" + puntuacion, 103, getHeight()/2-2);
        
        //Score Abajo
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 60));
        g2d.drawString("Score", 97, getHeight()/3+2);
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 100));
        g2d.drawString("" + puntuacion, 97, getHeight()/2+2);
        
        //color de la letra
        g2d.setColor(Color.YELLOW);
        
        //Score Arriba
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 61));
        g2d.drawString("Score", 100, getHeight()/3);
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 101));
        g2d.drawString("" + puntuacion, 100, getHeight()/2);
        
        //Mensajes para volver a jugar o al menu
        if(blinking){
            //color de la letra
            g2d.setColor(Color.black);
            
            //abajo
            g2d.setFont(new Font("Verdana", Font.BOLD, 20));
            g2d.drawString("Presiona 'R' para volver a jugar!!", 197, getHeight()-52);
            g2d.drawString("Presiona 'M' para volver al menu", 200, getHeight()-22);
            
            //abajo
            g2d.setFont(new Font("Verdana", Font.BOLD, 20));
            g2d.drawString("Presiona 'R' para volver a jugar!!", 203, getHeight()-48);
            g2d.drawString("Presiona 'M' para volver al menu", 203, getHeight()-18);
            
            //color de la letra
            g2d.setColor(Color.CYAN);
            
            //arriba
            g2d.setFont(new Font("Verdana", Font.BOLD, 20));
            g2d.drawString("Presiona 'R' para volver a jugar!!", 200, getHeight()-50);
            g2d.drawString("Presiona 'M' para volver al menu", 204, getHeight()-20);
            
            contador++;
            if(contador >= 20){
                blinking = false;
                contador = 0;
            }
        }
        else{
            contador++;
            if(contador >= 20){
                blinking = true;
                contador = 0;
            }
        }
    }
    
    public void textoJuego(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //color del texto
        g2d.setColor(Color.black);
        
        //score abajo
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 30));
        g2d.drawString(tituloPuntos, 7, 32);
        
        //score abajo
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 30));
        g2d.drawString(tituloPuntos, 13, 28);
        
        //velocidad abajo
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 20));
        g2d.drawString("Velocidad x" + getVel(), 603, 28);
        
        //velocidad abajo
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 20));
        g2d.drawString("Velocidad x" + getVel(), 597, 32);
        
        //color del texto
        g2d.setColor(Color.white);
        
        //score arriba
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 30));
        g2d.drawString(tituloPuntos, 10, 30);
        
        //velocidad arriba
        g2d.setFont(new Font("Segoe Print", Font.BOLD, 20));
        g2d.drawString("Velocidad x" + getVel(), 600, 30);
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
        if(!gameover && !beginning){
            if(keyManager.space){
                contador+=getVel();
                if(contador>=150){
                    puntuacion++;
                    contador=0;
                    agregarObstacle();
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
                    background.setY(-getHeight());
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
            itr = obstaclesM.iterator();
            while(itr.hasNext()){
                ObstacleM obstacle = (ObstacleM) itr.next();
                obstacle.tick();
                //si sale del juego
                if(obstacle.getY() >= getHeight()){
                    obstaclesM.remove(obstacle);
                    itr = obstaclesM.iterator();
                }
                //si choca con player
                if(obstacle.intersects(pez))
                    gameover= true;
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
            
            //decoraciones
            itr = decoraciones.iterator();
            while(itr.hasNext()){
                Decoracion decoracion = (Decoracion) itr.next();
                decoracion.tick();
                //si sale del juego
                if(decoracion.getY() >= getHeight()){
                    decoracion.setY(-120);
                    decoracion.setDecora((int) (Math.random()*3));
                }
            }
            
            //actualizar score
            tituloPuntos = letPuntos + puntuacion;
        }
        else if(gameover){
            if(keyManager.R){
                gameover = false;
                reordenar();
            }
            if(keyManager.M){
                gameover = false;
                beginning = true;
                reordenar();
            }
        }
        else if(beginning){
            Iterator itr = players.iterator();
            while(itr.hasNext()){
                ((Pez) itr.next()).tick();
            }
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
            itr = obstaclesM.iterator();
            while (itr.hasNext())
                ((ObstacleM) itr.next()).render(g);
            itr = obstaclesR.iterator();
            while (itr.hasNext())
                ((ObstacleR) itr.next()).render(g);
            
            //decoraciones
            itr = decoraciones.iterator();
            while (itr.hasNext())
                ((Decoracion) itr.next()).render(g);
            
            //player
            pez.render(g);
            
            //score y velocidad
            textoJuego(g);
            
            //gameover (IMPORTANTE: mantener el gameove.render por debajo de los otros renders)
            if(gameover){
                g.drawImage(Assets.gameover, 0, 0, width, height, null);
                
                //texto
                textoGameover(g);
            }
            
            //start
            else if(beginning){
                g.drawImage(Assets.start, 0, 0, width, height, null);
                
                //texto
                textoBeginning(g);
                
                //player
                itr = players.iterator();
                while(itr.hasNext()){
                    ((Pez) itr.next()).render(g);
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