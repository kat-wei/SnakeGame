/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author 18_kat_18
 */

// Snake is just a container used to save the bodyparts and tracking the score

public class Snake extends GameElement implements Updateable, Renderable{

    //Arraylist for body parts
    ArrayList<BodyPart> bodyParts = new ArrayList<BodyPart>();
    int score; // total score

    public Snake(){
        super();
    }
    
    //if this is the first body part added to the arraylist
    public Snake(BodyPart BP){
        bodyParts.add(BP);
    }
    
    public void addPart(BodyPart BP){
        bodyParts.add(BP);
    }
    
    public BodyPart getPart(int indexPart){
        return bodyParts.get(indexPart);
    }
        
    public void setScore(int newScore){
        score=newScore;
    }
    
    public int getScore(){
        return score;
    }
 
    @Override
    public void onCollision(GameElement ge) {
        if (ge instanceof Wall) {
             updateCollisionWall();
        } 
        if (ge instanceof Apple) {
             updateCollisionApple();
        } 
    }
    
    @Override
    public void checkCollision(GameElement ge) {
        //only need to check if there is a head
        BodyPart sHead = new BodyPart();
        sHead=bodyParts.get(0);
        double xOverlap = Math.min(sHead.getX() + sHead.getWidth() - ge.getX(), ge.getX() + ge.getWidth()-sHead.getX());
        double yOverlap = Math.min(sHead.getY() + sHead.getHeight() - ge.getY(), ge.getY() + ge.getHeight() - sHead.getY());

        if(xOverlap>0 && yOverlap>0){            
            onCollision(ge);
        }                           

    }

    @Override
    public void update() {
    
        double newX=0;
        double newY=0;
        String newDirection = "";
        //the other bodypart will follow the head
        for(int idxParts =bodyParts.size()-1; idxParts>0; idxParts--){
        
            bodyParts.get(idxParts).setX(bodyParts.get(idxParts-1).getX());
            bodyParts.get(idxParts).setY(bodyParts.get(idxParts-1).getY());
        
        }
        
        //set the movement of the head
        if (bodyParts.get(0).getDirection().equals("LEFT")) {
        
            newX= bodyParts.get(0).getX() - bodyParts.get(0).getVel();
            newY= bodyParts.get(0).getY();
            bodyParts.get(0).setX(newX);
            bodyParts.get(0).setY(newY);
            bodyParts.get(0).setDirection("LEFT");
            
        } 
        if (bodyParts.get(0).getDirection().equals("RIGHT")) {
                
            newX= bodyParts.get(0).getX() + bodyParts.get(0).getVel();      
            newY= bodyParts.get(0).getY();
           
            bodyParts.get(0).setX(newX);
            bodyParts.get(0).setY(newY);
            bodyParts.get(0).setDirection("RIGHT");
            
        } 
        if (bodyParts.get(0).getDirection().equals("UP")){
                
            newY=bodyParts.get(0).getY() + bodyParts.get(0).getVel();  
            newX= bodyParts.get(0).getX();                
            bodyParts.get(0).setX(newX);
            bodyParts.get(0).setY(newY);
            bodyParts.get(0).setDirection("UP");
            
        }
        if(bodyParts.get(0).getDirection().equals("DOWN")){
            newY= bodyParts.get(0).getY() - bodyParts.get(0).getVel();
            newX= bodyParts.get(0).getX();
             
            bodyParts.get(0).setX(newX);
            bodyParts.get(0).setY(newY);
            bodyParts.get(0).setDirection("DOWN");
        } 
        
       }
    
    public void updateCollisionWall() {
 
        for(int idxParts =0; idxParts<bodyParts.size(); idxParts++){
                if (bodyParts.get(idxParts).getDirection().equals("LEFT")) {
                    bodyParts.get(idxParts).setX(bodyParts.get(idxParts).getX() + 2*bodyParts.get(idxParts).getVel());
                    bodyParts.get(idxParts).setDirection("RIGHT");
                } else if (bodyParts.get(idxParts).getDirection().equals("RIGHT")) {
                    bodyParts.get(idxParts).setX(bodyParts.get(idxParts).getX() - 2*bodyParts.get(idxParts).getVel());
                    bodyParts.get(idxParts).setDirection("LEFT");
                } else if (bodyParts.get(idxParts).getDirection().equals("UP")){
                    bodyParts.get(idxParts).setY(bodyParts.get(idxParts).getY() + 2*bodyParts.get(idxParts).getVel());
                    bodyParts.get(idxParts).setDirection("DOWN");
                } else if(bodyParts.get(idxParts).getDirection().equals("DOWN")){
                    bodyParts.get(idxParts).setY(bodyParts.get(idxParts).getY() - 2*bodyParts.get(idxParts).getVel());
                    bodyParts.get(idxParts).setDirection("UP");
                }                 
        }         
    }
 
    public void updateCollisionApple() {         
        
        double newX=0;
        double newY=0;
        
        //the other bodypart will follow the head
        for(int idxParts =bodyParts.size()-1; idxParts>0; idxParts--){
            bodyParts.get(idxParts).setX(bodyParts.get(idxParts-1).getX());
            bodyParts.get(idxParts).setY(bodyParts.get(idxParts-1).getY());
            bodyParts.get(idxParts).setIfHead(false);
        }
        
        //set the movement of the head
        if (bodyParts.get(0).getDirection().equals("LEFT")) {
                newX= bodyParts.get(0).getX() - bodyParts.get(0).getVel();
                newY= bodyParts.get(0).getY();
           } else if (bodyParts.get(0).getDirection().equals("RIGHT")) {
                newX=bodyParts.get(0).getX() + bodyParts.get(0).getVel();
                newY= bodyParts.get(0).getY();
           } else if (bodyParts.get(0).getDirection().equals("UP")){
                newY=bodyParts.get(0).getY() + bodyParts.get(0).getVel();  
                newX= bodyParts.get(0).getX();                
           } else if(bodyParts.get(0).getDirection().equals("DOWN")){
                newY=bodyParts.get(0).getY() - bodyParts.get(0).getVel();
                newX= bodyParts.get(0).getX();
        } 
       
        //add the new head        
        BodyPart newHead = new BodyPart(bodyParts.get(0).getWidth(), 
                              bodyParts.get(0).getHeight(), newX, 
                              newY, bodyParts.get(0).getVel());
        newHead.setIfHead(true);
        bodyParts.add(newHead);
        
    }
    
    @Override
    public void draw(Canvas canvas, Color col) {        
       for(int idxParts =0; idxParts<bodyParts.size(); idxParts++){
            GraphicsContext graphics = canvas.getGraphicsContext2D();
            graphics.setFill(col);
            graphics.fillRect(bodyParts.get(idxParts).getX(), bodyParts.get(idxParts).getY(), 
            bodyParts.get(idxParts).getWidth(), bodyParts.get(idxParts).getHeight());    
       }
    }
}
