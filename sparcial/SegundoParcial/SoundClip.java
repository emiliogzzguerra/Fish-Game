/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.net.URL;

/**
 *
 * @author NamiDW
 */
public class SoundClip {
    
    private Clip clip;
    private String filename = "";
    private AudioInputStream sample;
    private boolean looping = false;
    private int repeat = 0;
    
    /**
     * Constructor default
     */
    public SoundClip() {
        try {
            //crea el Buffer de sonido
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) { 
        }
    }
    
    /** 
     * Constructor con parametros, que carga manda llamar a load
     * esto carga el archivo de sonido.
     * @param filename es el <code>String</code> del archivo.
     */
    public SoundClip(String filename) {
        //Llama al constructor default.
        this();
        //Carga el archivo de sonido.
        load(filename);
    }
    
    /** 
     * Metodo que carga el archivo de sonido.
     * @param audiofile es un <code>String</code> con el nombre del archivo de sonido.
     */
    public boolean load(String audiofile) {
        try {
            setFilename(audiofile);
            sample = AudioSystem.getAudioInputStream(getURL(filename));
            clip.open(sample);
            return true;

        } catch (IOException e) {
            return false;
        } catch (UnsupportedAudioFileException e) {
            return false;
        } catch (LineUnavailableException e) {
            return false;
        }
    }
    
    /**
     * Metodo que reproduce el sonido.
     */
    public void play() {
        //se sale si el sonido no a sido cargado
        if (!isLoaded()) 
            return;
        //vuelve a empezar el sound clip
        clip.setFramePosition(0);

        //Reproduce el sonido con repeticion opcional.
        if (looping)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            clip.loop(repeat);
    }
    
    /**
     * Metodo modificador que asigna un nombre al archivo.
     * @param _filename es un <code>String</code> con el nombre del archivo. 
     */
    public void setFilename(String filename) { 
        this.filename = filename; 
    }
    
    /**
     * Metodo que detiene el sonido.
     */
    public void stop() {
        clip.stop();
    }
    
    /** 
     * Metodo modificador usado para modificar si el sonido se repite.
     * @param _looping es un valor <code>boleano</code>. 
     */
    public void setLooping(boolean looping) {
        this.looping = looping; 
    }

    /** 
     * Metodo de acceso que regresa un booleano para ver si hay repeticion.
     * @return looping  es un valor <code>boleano</code>. 
     */
    public boolean getLooping() {
        return looping;
    }
    
    /** 
     * Metodo de acceso que regresa un objeto de tipo Clip
     * @return clip es un <code>objeto Clip</code>.
     */
    public Clip getClip() { 
        return clip; 
    }
    
    /** 
     * Metodo modificador usado para definir el numero de repeticiones.
     * @param _repeat es un <code>entero</code> que es el numero de repeticiones. 
     */
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
    
    /** 
     * Metodo de acceso que regresa el numero de repeticiones.
     * @return repeat es un valor <code>entero</code> con el numero de repeticiones. 
     */
    public int getRepeat() { 
        return repeat; 
    }
    
    /** 
     * Metodo de acceso que regresa el nombre del archivo.
     * @return filename es un <code>String</code> con el nombre del archivo. 
     */
    public String getFilename() { 
        return filename;
    }
    
    /**
     * Metodo que verifica si el archivo de audio esta cargado.
     * @return sample es un <code>objeto sample</code>.
     */
    public boolean isLoaded() {
        return (boolean)(sample != null);
    }
    
    /** 
     * Metodo de acceso que regresa el url del archivo
     * @param filename es un <code>String</code> con el nombre del archivo. 
     */
    private URL getURL(String filename) {
        URL url = null;
        try {
            url = this.getClass().getResource(filename);
        }
        catch (Exception e) { 
            System.out.println("" + filename + "does not exist");
        }
        return url;
    }
}
