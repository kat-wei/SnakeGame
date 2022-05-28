/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 *
 */
public class SnakeRunner extends Application {

    /**
     * @param args the command line arguments
     */
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private static Canvas canvas;

    //DECLARE a static GameState object here (used in the timer)
    RedrawTimer timer = new RedrawTimer();
    GameState tester;
     
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        StackPane root = new StackPane();
          
        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        //instantiate your GameState object
        tester = new GameState();
        
        //create walls, ball, paddle, and blocks
        Wall wall = new Wall(WIDTH, 10, 0, 0); //top
        Wall wall2 = new Wall(10, HEIGHT-10, 0,0); //left
        Wall wall3 = new Wall(10, HEIGHT, WIDTH-10,0); //right
        Wall wall4 = new Wall(WIDTH, 10, 0, HEIGHT-10); //bottom
        
        //creat snake head and apple
        
        BodyPart BP = new BodyPart(15, 15, 500, 500, 5);
        BP.setIfHead(true);   
        Apple apple = new Apple(10, 10, 300, 300);
        Snake mySnake = new Snake(BP);
        
        //add the game elements (walls, ball, paddle, and blocks) to the GameState object 
        tester.add(wall);
        tester.add(wall2);
        tester.add(wall3);
        tester.add(wall4);
        tester.add(mySnake);
        tester.add(apple);
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        timer.start();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    mySnake.getPart(0).setDirection("RIGHT");
                }
                if (event.getCode() == KeyCode.LEFT) {
                    mySnake.getPart(0).setDirection("LEFT");
                }
                if(event.getCode() == KeyCode.UP){
                    //action for head
                    mySnake.getPart(0).setDirection("UP");
                }
                if(event.getCode() == KeyCode.DOWN){
                    //action for head
                    mySnake.getPart(0).setDirection("DOWN");
                }
                
            }

        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                     mySnake.getPart(0).setDirection("RIGHT");
                }
                if (event.getCode() == KeyCode.LEFT) {
                    mySnake.getPart(0).setDirection("LEFT");
                }
                if(event.getCode() == KeyCode.UP){
                    //action for head
                    mySnake.getPart(0).setDirection("UP");
                }
                if(event.getCode() == KeyCode.DOWN){
                    //action for head
                    mySnake.getPart(0).setDirection("DOWN");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public class RedrawTimer extends AnimationTimer {

        public void handle(long now) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, WIDTH, HEIGHT);
            
            
            //update, draw and collide all of the Game Elements in the GameState object              
            tester.UpdateAll(canvas);
            tester.DrawAll(canvas);            
            tester.CollideAll();
           
        }
    }

}
