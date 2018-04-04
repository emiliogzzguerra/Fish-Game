/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author NamiDW
 * 
 * Juego al estilo de aliens que atacan una nave.
 * El player lanza balas y se puede ocultar tras obstaculos para evitar las balas enemigas.
 * Los obstaculos tienen 4 vidas.
 * Pausa.
 * Opcion de guardar y cargar partida.
 * 5 vidas.
 * Puntuacion.
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
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    
    String titulo;
    //Arreglos
    private ArrayList<Enemy> enemies; //to store enemies collection
    private ArrayList<BulletPlayer> bulletsPlayer;
    private ArrayList<BulletEnemy> bulletsEnemy;
    private ArrayList<Wall> bricks;
    private ArrayList<Vida> vidas;
    //sonido
    private SoundClip musica;
    private SoundClip enemigoPerdido;
    private SoundClip enemigoAtrapado;
    private SoundClip risaGameover;
    //ints
    private int puntuacion;
    private int direccion;          //for enemies
    private int antdireccion;
    private int contDireccion;
    private int velEnemigo;         //para disminuir velocidad del enemigo
    //boolean
    private boolean choque;
    private boolean pausa;
    private boolean running;        // to set the game
    private boolean ganar;
    private boolean enemigoCambiarDireccion;
    
    
    /**
     * to create title, width and height and set the game is still not running
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(int width, int height) {
        //obligatorio
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        //extras
        title = "Puntos: ";
        titulo = title + puntuacion;
        //boolean
        running = false;
        choque = false;
        pausa=false;
        ganar=false;
        enemigoCambiarDireccion = false;
        //int
        puntuacion = 0;
        direccion=0; //0 right. 1 left. 3 Down
        contDireccion = 0;
        velEnemigo=0;
        //agregar sonidos
        musica = new SoundClip("/sonido/ringtones-be-happy.wav");
        enemigoPerdido = new SoundClip("/sonido/laugh3.wav");
        enemigoAtrapado = new SoundClip("/sonido/los-animales_2.wav");
        risaGameover = new SoundClip("/sonido/gracioso_2.wav");
        musica.setLooping(true);
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
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    public int getDireccion() {
        return direccion;
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<BulletPlayer> getBulletsPlayer() {
        return bulletsPlayer;
    }

    public ArrayList<BulletEnemy> getBulletsEnemy() {
        return bulletsEnemy;
    }

    public ArrayList<Vida> getVidas() {
        return vidas;
    }

    public ArrayList<Wall> getBricks() {
        return bricks;
    }

    public Player getPlayer() {
        return player;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
    
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    private void agregarEnemigo(){
        for(int y=0; y<5; y++){
            for(int x=0; x<8; x++){
                enemies.add(new Enemy(x*60+10*x, y*60,60,60,y+1,this));
            }
        }
        setDireccion(0);
    }
    
    private void agregarBarrera(){
        for(int y=0; y<3; y++){
            bricks.add(new Wall(60,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(60,getHeight()-(getHeight()/3)+50,50,50,this));
            bricks.add(new Wall(60+50,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(60+100,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(60+100,getHeight()-(getHeight()/3)+50,50,50,this));
            
            bricks.add(new Wall(250+60,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(250+60,getHeight()-(getHeight()/3)+50,50,50,this));
            bricks.add(new Wall(250+60+50,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(250+60+100,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(250+60+100,getHeight()-(getHeight()/3)+50,50,50,this));
            
            bricks.add(new Wall(500+60,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(500+60,getHeight()-(getHeight()/3)+50,50,50,this));
            bricks.add(new Wall(500+60+50,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(500+60+100,getHeight()-(getHeight()/3),50,50,this));
            bricks.add(new Wall(500+60+100,getHeight()-(getHeight()/3)+50,50,50,this));
        }
    }
    
    private void reordenar(){
        //esta funcion tiene que eliminar a todos los enemigos existentes, 
        //      mandar a crear nuevos y reiniciar al jugador.
        
        //reiniciar al jugador
        player.setX((getWidth()/2)-35);
        player.setY(getHeight()-90);
        
        //eliminar a los enemigos y balas
        eleminarEnemigos();
        eleminarExtras();
        
        //agregarEnemigos
        agregarEnemigo();
        //agregarMurralla
        agregarBarrera();
    }
    
    private void eleminarEnemigos(){
        Iterator itr = enemies.iterator();
        while(itr.hasNext()){
            Enemy enemy = (Enemy)itr.next();
            enemies.remove(enemy);
            itr = enemies.iterator();
        }
    }
    
    private void eleminarExtras(){
        Iterator itr = bulletsPlayer.iterator();
        while(itr.hasNext()){
            BulletPlayer bullet = (BulletPlayer)itr.next();
            bulletsPlayer.remove(bullet);
            itr = bulletsPlayer.iterator();
        }
        itr = bulletsEnemy.iterator();
        while(itr.hasNext()){
            BulletEnemy bullet = (BulletEnemy)itr.next();
            bulletsEnemy.remove(bullet);
            itr = bulletsEnemy.iterator();
        }
        itr = bricks.iterator();
        while(itr.hasNext()){
            Wall wall = (Wall)itr.next();
            bricks.remove(wall);
            itr = bricks.iterator();
        }
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(titulo, getWidth(), getHeight());  
         Assets.init();
         //inicia el jugador en la parte baja de la pantalla en el medio.
         player = new Player((getWidth()/2)-35, getHeight()-90, 70, 70, this);
         //create Array of enemies
         enemies = new ArrayList<>();
         //create Array of BulletPlayers
         bulletsPlayer = new ArrayList<>(); 
         //create Array of BulletEnemy
         bulletsEnemy = new ArrayList<>();
         //create Array of Bricks
         bricks = new ArrayList<>();
         //create Array of vidas
         vidas = new ArrayList<>();
         //adding lives
         for(int cont=0; cont<5; cont++){
             vidas.add(new Vida(cont*20,0,20,20,this));
         }
         //adding enemies to the colection
         agregarEnemigo();
         //adding bricks
         agregarBarrera();
         //agregando las vidas
         display.getJframe().addKeyListener(keyManager);
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
            // acumulating to delta the difference between times in timeTick
            //      units
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
        keyManager.tick();
        
        if(!vidas.isEmpty() && !choque && !pausa && !ganar){
            //avancing player
            player.tick();
            //moving the enemies
            Iterator itr = enemies.iterator();
            velEnemigo++;
            while(itr.hasNext() && velEnemigo>=3){
                Enemy enemy = (Enemy)itr.next();
                enemy.tick();
                //check if colision with player
                if(player.intersects(enemy)){
                    enemies.remove(enemy);
                    itr = enemies.iterator();
                    enemigoPerdido.play();
                    //condiciones para perder
                    choque=true;
                }
                
                //reset position if it get out of the screen
                if((enemy.getX() <= 0 || enemy.getX()>=getWidth()-60) && !enemigoCambiarDireccion){
                    enemigoCambiarDireccion = true;
                    antdireccion = direccion;
                    contDireccion = 0;
                }
                
                //enemy create a bullet
                if((int) (Math.random()*(enemy.getColor()*750+100))==0)
                    bulletsEnemy.add(new BulletEnemy(enemy.getX()+(enemy.getWidth()/2)-10,enemy.getY()+25,20,20,this));
            }
            //evento en caso de tener que cambiar de direccion, primero baja un poco y luego cambia.
            if(enemigoCambiarDireccion && velEnemigo>=3){
                contDireccion++;
                if(contDireccion>=5){
                    if(antdireccion == 1)
                        direccion = 0;
                    else
                        direccion = 1;
                    enemigoCambiarDireccion = false;
                    contDireccion = 0;
                }
                else
                    direccion = 2;
            }
            if(velEnemigo>=3)
                velEnemigo=0;
            
            //moving bullets
            itr = bulletsPlayer.iterator();
            while(itr.hasNext()){
                BulletPlayer bullet = (BulletPlayer)itr.next();
                bullet.tick();
            
                //check if the bullets crashes
                Iterator itr2 = enemies.iterator();
                while(itr2.hasNext()){
                    Enemy enemy = (Enemy) itr2.next();
                    if(bullet.intersects(enemy)){
                        enemies.remove(enemy);
                        bulletsPlayer.remove(bullet);
                        //due to problems with iterator, reset the iterator
                        itr = bulletsPlayer.iterator();
                        itr2 = enemies.iterator();
                        puntuacion+=(100*enemy.getColor());
                    }
                }
            
                //reset position if it get out of the screen at the top
                if(bullet.getY() <= -20){
                    bulletsPlayer.remove(bullet);
                    itr = bulletsPlayer.iterator();
                }
                
                //checar choque contra murralla
                itr2 = bricks.iterator();
                while(itr2.hasNext()){
                    Wall wall = (Wall) itr2.next();
                    if(bullet.intersects(wall)){
                        wall.setVida(wall.getVida()-1);
                        bulletsPlayer.remove(bullet);
                        //due to problems with iterator, reset the iterator
                        itr = bulletsPlayer.iterator();
                    }
                }
            }
            
            //balas enemigas
            itr = bulletsEnemy.iterator();
            while(itr.hasNext()){
                BulletEnemy bullet = (BulletEnemy)itr.next();
                bullet.tick();
                if(bullet.intersects(player)){
                    bulletsEnemy.remove(bullet);
                    enemigoPerdido.play();
                    itr = bulletsEnemy.iterator();
                    //condiciones para perder
                    choque = true;
                }
                
                //checar choque contra murralla
                Iterator itr2 = bricks.iterator();
                while(itr2.hasNext()){
                    Wall wall = (Wall) itr2.next();
                    if(bullet.intersects(wall)){
                        wall.setVida(wall.getVida()-1);
                        bulletsEnemy.remove(bullet);
                        //due to problems with iterator, reset the iterator
                        itr = bulletsEnemy.iterator();
                    }
                }
            }
            
            //muralla
            itr = bricks.iterator();
            while(itr.hasNext()){
                Wall wall = (Wall)itr.next();
                if(wall.getVida()<=0){
                    bricks.remove(wall);
                    itr = bricks.iterator();
                }
                else
                    wall.tick();
            }
            
            //ganaste el juego
            if(enemies.isEmpty())
                ganar=true;
            //pausar el juego
            if(keyManager.P){
                pausa=true;
            }
            if(keyManager.space && bulletsPlayer.isEmpty()){
                bulletsPlayer.add(new BulletPlayer(player.getX()+35-10,player.getY()-25,20,20,this));
            }
            if(choque && !vidas.isEmpty()){
                vidas.remove(vidas.size()-1);
            }
            
        //las condicines de lo que pasa cuando se detiene
        }
        else if(choque){
            if(keyManager.R){
                reordenar();
                choque = false;
            }
        }
        else if(pausa){
            if(keyManager.S)
                Files.saveFile(this);
            if(keyManager.L)
                Files.loadFile(this);
            if(keyManager.P)
                pausa=false;
        }
        
        if(vidas.isEmpty()){
            risaGameover.play();
            musica.stop();
            render();
            running = false;
        }
        
        //checa puntuacion;
        if(puntuacion<0)
            puntuacion=0;
        titulo = title+puntuacion;
        display.changJframe(titulo);
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
            g.drawImage(Assets.background, 0, 0, width, height, null);
            //player
            player.render(g);
            //enemigos
            Iterator itr = enemies.iterator();
            while(itr.hasNext()){
                ((Enemy)itr.next()).render(g);
            }
            //balas
            itr = bulletsPlayer.iterator();
            while(itr.hasNext()){
                ((BulletPlayer)itr.next()).render(g);
            }
            itr = bulletsEnemy.iterator();
            while(itr.hasNext()){
                ((BulletEnemy)itr.next()).render(g);
            }
            //barrera
            itr = bricks.iterator();
            while(itr.hasNext()){
                ((Wall)itr.next()).render(g);
            }
            //vidas
            itr = vidas.iterator();
            while(itr.hasNext()){
                ((Vida)itr.next()).render(g);
            }
            //imagen segun el estado del juego
            if(pausa)
                g.drawImage(Assets.backgroundPausa, 0, 0, width, height, null);
            if(vidas.isEmpty())
                g.drawImage(Assets.gameover, 0, 0, width, height, null);
            if(ganar)
                g.drawImage(Assets.ganaste, 0, 0, width, height, null);
            
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
            musica.play();
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
