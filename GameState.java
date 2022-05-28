/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author 
 */
public class GameState {

    public List<Renderable> renderables;
    public List<Updateable> updateable;
    public List<GameElement> gameElements;

    //you need a constructor
    public GameState(){
        renderables = new ArrayList<Renderable>();
        updateable = new ArrayList<Updateable>();
        gameElements= new ArrayList<GameElement>();
    }
    
//You need an add method
    public void add(GameElement ge){
        if(ge instanceof Renderable){
          renderables.add((Renderable)ge);
        }
         //Do the same with Updateable          
        if(ge instanceof Updateable){          
          updateable.add((Updateable)ge);     
        }       
        
        gameElements.add(ge);
     
    }
    public void UpdateAll(Canvas canvas) {
        //implement later
        for(Updateable item : updateable){
            item.update();
        }
    }

    public void DrawAll(Canvas canvas) {
        //implement later
         for(Renderable item : renderables){
            item.draw(canvas, Color.CORAL);
        }
 
    }

    public void CollideAll() {
        //implement later
        for(GameElement item : gameElements){           
            for(GameElement item2 : gameElements)
                if(!item.equals(item2)){                
                  if(item instanceof Head){//look over this part
                    item.checkCollision(item2);                     
                  }
                }             
        }
        for(GameElement item : gameElements){           
            for(GameElement item2 : gameElements)
                if(!item.equals(item2)){   
                  //debatable
                  if(!(item instanceof Head)){
                    item.checkCollision(item2);                     
                  }
                }             
        }        
    }
}
