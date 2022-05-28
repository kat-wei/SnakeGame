/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;


/**
 *
 * @author wei.katherine22
 */
public abstract class GameElement {

    //public or private?
    private double width, height, x, y;
    private String direction;
    
    public GameElement(){
        width = 0.0; 
        height = 0.0;
        x = 0.0;
        y = 0.0;
    }

    public GameElement(double wid, double hei, double xVal, double yVal){
        setWidth(wid);
        setHeight(hei);
        setX(xVal);
        setY(yVal);
    }
    
    public void setWidth(double wid){
        width = wid;
    }
    
    public void setHeight(double hei){
        height = hei;
    }
    
    public void setX(double xVal){
        x = xVal;
    }
    
    public void setY(double yVal){
        y = yVal;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setDirection(String dir){
        direction = dir;
    }
    
    public String getDirection(){
        return direction;
    }
    
    public abstract void onCollision(GameElement ge);
   
    //return type
    public void checkCollision(GameElement ge){
        double xOverlap = Math.min(this.getX() + this.getWidth() - ge.getX(), ge.getX() + ge.getWidth()-this.getX());
        double yOverlap = Math.min(this.getY() + this.getHeight() - ge.getY(), ge.getY() + ge.getHeight() - this.getY());
        if(xOverlap>0 && yOverlap>0){
            if(yOverlap > xOverlap){
                ge.setX(-ge.getX());
            }else{
                ge.setY(-ge.getY());
            }
        }
         
    }
    
    
    
    public String toString(){
        return width + height + x + y + "";
    }
}
