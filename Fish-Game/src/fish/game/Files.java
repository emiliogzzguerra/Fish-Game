/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author NirvanaGaming
 * 
 * Archivo ejemplo
 * 
 * Size,
 * Score 1,
 * Score 2,
 * Score 3,
 * ...
 * Score Size,
 * 
 */
public class Files {
    
    public static void saveFile(Score score){
        //define objects
        PrintWriter printWriter;
        try{
            //creating file object
            printWriter = new PrintWriter(new FileWriter("data.txt"));
            //writing the game
            //size
            printWriter.println("" + score.getSize() + ",");
            for(int cont=0; cont<score.getSize(); cont++){
                printWriter.println("" + score.getAt(cont)+ ",");
            }
            //closing file objet
            printWriter.close();
        }catch(IOException ioe){
            System.out.println("Disco duro lleno " + ioe.toString());
        }
    }
    
    public static void loadFile(Score score){
        BufferedReader bufferedReader;
        try{
            //abriendo archivo
            bufferedReader = new BufferedReader( new FileReader("data.txt"));
            //leyendo archivo
            String line = bufferedReader.readLine();
            String[] tokens = line.split(",");
            score.setSize(Integer.parseInt(tokens[0]));
            for(int cont=0; cont<score.getSize(); cont++){
                line = bufferedReader.readLine();
                tokens = line.split(",");
                score.setAt(cont,Integer.parseInt(tokens[0]));
            }
        }catch (IOException ioe){
            System.out.println("Juego no ha sido guardado " + ioe.toString());
        }
    }
    
    public static boolean existsFile(){
        BufferedReader bufferedReader;
        try{
            //abriendo archivo
            bufferedReader = new BufferedReader( new FileReader("data.txt"));
            
            //si logro abrirlo, significa que existe
            return true;
        }catch (IOException ioe){
            return false;
        }
    }
}
