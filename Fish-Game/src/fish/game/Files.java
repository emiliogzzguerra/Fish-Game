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
 * ... (por crear, para la version 2)
 * 
 */
public class Files {
    
    public static void saveFile(Game game){
        //define objects
        PrintWriter printWriter;
        try{
            //creating file object
            printWriter = new PrintWriter(new FileWriter("data.txt"));
            //writing the game
            
            //closing file objet
            printWriter.close();
        }catch(IOException ioe){
            System.out.println("Disco duro lleno " + ioe.toString());
        }
    }
    
    public static void loadFile(Game game){
        BufferedReader bufferedReader;
        try{
            //abriendo archivo
            bufferedReader = new BufferedReader( new FileReader("data.txt"));
            //leyendo archivo
            
        }catch (IOException ioe){
            System.out.println("Juego no ha sido guardado " + ioe.toString());
        }
    }
}
