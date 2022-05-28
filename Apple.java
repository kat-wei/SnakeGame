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
public class Apple extends GameElement implements Updateable, Renderable{

    public Apple(){
        super();
    }
    
    public Apple(double wid, double hei, double xVal, double yVal){
        super(wid, hei, xVal, yVal);
    }
    
   public void destroy(){
        this.setHeight(0);
        this.setWidth(0);
        this.setX(0);
        this.setY(0);
    }
    
    @Override
    public void onCollision(GameElement ge) {
        destroy();
        update();
    }
        
    
    
    @Override
    public void checkCollision(GameElement ge) {
      //X overlap
        double xOverlap = Math.min(this.getX() + this.getWidth() - ge.getX(), ge.getX() + ge.getWidth()-this.getX());
        double yOverlap = Math.min(this.getY() + this.getHeight() - ge.getY(), ge.getY() + ge.getHeight() - this.getY());
        if(xOverlap>0 && yOverlap>0){
            onCollision(ge);
        }
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(col);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight()); 
    }
    
}
