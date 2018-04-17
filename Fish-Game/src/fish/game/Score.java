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
    
    public Score(Game game, int maxSize){
        this.game = game;
        
        init(maxSize);
        size = 0;
        
        if(Files.existsFile()){
            Files.loadFile(this);
        }
    }
    
    private void init(int maxSize){
        score = new int[maxSize];
    }

    public int getSize() {
        return size;
    }
    
    public int getAt(int num){
        return score[num];
    }
    
    public int getMaxSize(){
        return score.length;
    }

    public boolean isEmpty() {
        if(size==0)
            return true;
        return false;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void setMaxSize(int maxSize){
        init(maxSize);
    }
    
    public void setAt(int x, int num){
        score[x]=num;
    }
    
    public void add(int num){
        if(isEmpty()){
            score[size]=num;
            size++;
        }
        else{
            int cont=size;
            while(cont>0 && score[cont-1]<num)
                cont--;
            
            int ant;
            while(cont<=size && cont<getMaxSize()){
                ant= score[cont];
                score[cont]=num;
                num = ant;
                cont++;
            }
            if(size!=getMaxSize())
                size++;
        }
    }
    
    public void save(){
        Files.saveFile(this);
    }
}
