/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.game;

/**
 *
 * @author NamiDW
 */
public class Score {
    Game game;
    
    //int
    private int size;
    private int score[];
    
    //boolean
    private boolean empty;
    
    public Score(Game game, int maxSize){
        this.game = game;
        
        score = new int[maxSize];
        size = 0;
        empty = true;
        
        if(Files.existsFile(game)){
            Files.loadFile(game);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return empty;
    }
    
    public void add(int num){
        
    }
    
    public int getAt(int num){
        return score[num];
    }
    
    public int getMaxSize(){
        return score.length;
    }
}
