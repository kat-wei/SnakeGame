/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author 18_kat_18
 */
public class Head extends GameElement implements Updateable, Renderable{
    
    private double velocity;
    private String direction;
    
    public Head(){
        super();
        direction = "NEUTRAL	";
    }
    
    public Head(double wid, double hei, double xVal, double yVal, double vel){
        super(wid, hei, xVal, yVal);
        velocity = vel;
        direction = "NEUTRAL	";
    }
    
    public void setVel(double vel){
        velocity = vel;
    }
    
    public double getVel(){
        return velocity;
    }
    
    public void setDirection(String dir){
        direction = dir;
    }
    
    public String getDirection(){
        return direction;
    }

    @Override
    public void onCollision(GameElement ge) {
        
    }
    
    @Override
    public void checkCollision(GameElement ge) {
        
        double xOverlap = Math.min(this.getX() + this.getWidth() - ge.getX(), ge.getX() + ge.getWidth()-this.getX());
        double yOverlap = Math.min(this.getY() + this.getHeight() - ge.getY(), ge.getY() + ge.getHeight() - this.getY());

        if(xOverlap>0 && yOverlap>0){
            //if collide with a way then updata the travel direction of the snake
            
            //if collide with an apple update the length of the snake

        }        
             
    }

    @Override
    public void update() {
        if (direction.equals("LEFT")) {
            super.setX(super.getX() + -velocity);
        } else if (direction.equals("RIGHT")) {
            super.setX(super.getX() + velocity);
        } else if (direction.equals("UP")){
            super.setY(super.getY() + -velocity);
        } else if(direction.equals("DOWN")){
            super.setY(super.getY() + velocity);
        }
    }

    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(col);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight()); 

    }
}
