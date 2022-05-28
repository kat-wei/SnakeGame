/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
/**
 *
 * @author 18_kat_18
 */
public class BodyPart extends GameElement implements Updateable, Renderable{
    
    private boolean isHead; 
    private double velocity;
    private String direction;
    int score; // total score
    
    
    public BodyPart(){
        super();
        direction = "NEUTRAL	";
        isHead = false;
    }
    
    public BodyPart(double wid, double hei, double xVal, double yVal, double vel){
        super(wid, hei, xVal, yVal);
        velocity = vel;
        direction = "NEUTRAL	";
        isHead =false;
    }
    
    public void setVel(double vel){
        velocity = vel;
    }
    
    public double getVel(){
        return velocity;
    }
    
    public void setIfHead(boolean inputIsHead){
        isHead = inputIsHead;
    }
    
    public boolean getIfHead(){
        return isHead;
    }
    
    public void setDirection(String dir){
        direction = dir;
    }
    
    public String getDirection(){
        return direction;
    }

    @Override
    public void onCollision(GameElement ge) {
        if (this.isHead == true && ge instanceof Wall) {
             updateCollisionWall();
        } 
        if (this.isHead == true && ge instanceof Apple) {
             updateCollisionApple();
        } 
    }
    
    @Override
    public void checkCollision(GameElement ge) {
        
        double xOverlap = Math.min(this.getX() + this.getWidth() - ge.getX(), ge.getX() + ge.getWidth()-this.getX());
        double yOverlap = Math.min(this.getY() + this.getHeight() - ge.getY(), ge.getY() + ge.getHeight() - this.getY());

        if(xOverlap>0 && yOverlap>0){            
          onCollision(ge);
        }              
    }

    @Override
    public void update() {
                 
           if (direction.equals("LEFT")) {
                this.setX(super.getX()  -velocity);
            } else if (direction.equals("RIGHT")) {
                this.setX(super.getX() + velocity);
            } else if (direction.equals("UP")){
                this.setY(super.getY() - velocity);
            } else if(direction.equals("DOWN")){
                this.setY(super.getY() + velocity);
            }
    
    }

       public void updateCollisionWall() {
                 
           if (direction.equals("LEFT")) {
                this.setX(super.getX() + 2*velocity);
                this.direction="RIGHT";
            } else if (direction.equals("RIGHT")) {
                this.setX(super.getX() - 2*velocity);
                this.direction="LEFT";
            } else if (direction.equals("UP")){
                this.setY(super.getY() + 2*velocity);
                this.direction="DOWN";
            } else if(direction.equals("DOWN")){
                this.setY(super.getY() - 2*velocity);
                this.direction="UP";
            }
    
    }
 
    public void updateCollisionApple() {             
           isHead= false;
    }
       
    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(col);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight()); 

    }
}
    
    

