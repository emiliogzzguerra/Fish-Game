/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author NamiDW
 * 
 * Archivo ejemplo
 * 
 * PosicionX,PosicionY
 * BoolBala
 * BalaPosX,BalaPosY
 * Puntuacion,CantVidas
 * CantEnemigos
 * X,Y,Color
 * CantMurralla
 * X,Y,Vida
 * CantBalasEnemigas
 * X,Y
 */
public class Files {
    public static void saveFile(Game game){
        //define objects
        PrintWriter printWriter;
        try{
            //creatubg file object
            printWriter = new PrintWriter(new FileWriter("data.txt"));
            //writing the game
            printWriter.println("" + game.getPlayer().getX() + "," + 
                    game.getPlayer().getY());
            printWriter.println("" + game.getBulletsPlayer().size());
            if(game.getBulletsPlayer().size()==1)
                for(BulletPlayer bullet : game.getBulletsPlayer()){
                    printWriter.println("" + bullet.getX() + "," + bullet.getY());
                }
            printWriter.println("" + game.getPuntuacion() + "," + 
                    game.getVidas().size());
            printWriter.println("" + game.getEnemies().size());
            for(Enemy enemy : game.getEnemies()){
                printWriter.println("" + enemy.getX() + "," + enemy.getY()
                        + "," + enemy.getColor());
            }
            printWriter.println("" + game.getBricks().size());
            for(Wall wall : game.getBricks()){
                printWriter.println("" + wall.getX() + "," + wall.getY() + 
                        "," + wall.getVida());
            }
            printWriter.println("" + game.getBulletsEnemy().size());
            for(BulletEnemy bullet : game.getBulletsEnemy()){
                printWriter.println("" + bullet.getX() + "," + bullet.getY());
            }
            printWriter.close();
        }catch(IOException ioe){
            System.out.println("Disco duro lleno " + ioe.toString());
        }
    }
    
    public static void loadFile(Game game){
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader( new FileReader("data.txt"));
            //PosicionX,PosicionY
            String line = bufferedReader.readLine();
            String[] tokens = line.split(",");
            game.getPlayer().setX(Integer.parseInt(tokens[0]));
            game.getPlayer().setY(Integer.parseInt(tokens[1]));
            // BoolBala
            line = bufferedReader.readLine();
            tokens = line.split(",");
            game.getBulletsPlayer().clear();
            if(Integer.parseInt(tokens[0])==1){
                //BalaPosX,BalaPosY
                line = bufferedReader.readLine();
                tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                BulletPlayer bullet = new BulletPlayer(x,y,20,20,game);
                game.getBulletsPlayer().add(bullet);
            }
            //Puntuacion,CantVidas
            line = bufferedReader.readLine();
            tokens = line.split(",");
            game.setPuntuacion(Integer.parseInt(tokens[0]));
            game.getVidas().clear();
            for(int cont=0; cont<Integer.parseInt(tokens[1]); cont++){
                game.getVidas().add(new Vida(cont*20,0,20,20,game));
            }
            //CantEnemigos
            int enemies = Integer.parseInt(bufferedReader.readLine());
            game.getEnemies().clear();
            for(int i=0; i<enemies; i++){
                //X,Y,Color
                line = bufferedReader.readLine();
                tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                int num = Integer.parseInt(tokens[2]);
                Enemy enemy = new Enemy(x,y,60,60,num,game);
                game.getEnemies().add(enemy);
            }
            //CantMurralla
            int murralla = Integer.parseInt(bufferedReader.readLine());
            game.getBricks().clear();
            for(int i=0; i<murralla; i++){
                //X,Y,Vida
                line = bufferedReader.readLine();
                tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                int num = Integer.parseInt(tokens[2]);
                Wall wall = new Wall(x,y,50,50,game);
                wall.setVida(num);
                game.getBricks().add(wall);
            }
            //CantBalasEnemigas
            int balas = Integer.parseInt(bufferedReader.readLine());
            game.getBulletsEnemy().clear();
            for(int i=0; i<balas; i++){
                //X,Y
                line = bufferedReader.readLine();
                tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                BulletEnemy bullet = new BulletEnemy(x,y,20,20,game);
                game.getBulletsEnemy().add(bullet);
            }
        }catch (IOException ioe){
            System.out.println(" Juego no ha sido guardado " + ioe.toString());
        }
    }
}
