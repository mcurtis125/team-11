package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stavy92
 */
public class Ghost {
    
    PacmanControl pacControl = new PacmanControl();
    public enum Dir{up, down, left, right}; 
    private static final double[] DIMENSIONS = {15,15};
    private static final double MAX_SPEED = 15;
    private double[] position = new double[2];
    private double[] centerPoint = new double[2];
    Tile currentTile;
    Tiles tiles;
    private Pacman.Dir direction = Pacman.Dir.right;
    private int lives = 3;
    
    //private Tile currentTile;
    //private Tile targetTile;
   
    private double normSpeed;
    private double dotSpeed;
    private double frightSpeed;
    private double frightDotSpeed;
    
    public Ghost(/*Tiles tilesdouble normRatio, double dotRatio, double frightRatio, double frightDotRatio */){
       /* this.normSpeed = MAX_SPEED*normRatio;
        this.dotSpeed = MAX_SPEED*dotRatio;
        this.frightSpeed = MAX_SPEED*frightRatio;
        this.frightDotSpeed = MAX_SPEED*frightDotRatio;*/
        pacControl.getPosition(position);
        centerPoint[0] = position[0]+DIMENSIONS[0]/2;
        centerPoint[1] = position[1]+DIMENSIONS[1]/2;
        this.tiles = tiles; 
//        currentTile = tiles.getTileOccupied(centerPoint);
    }
    
    public void draw(Graphics g){
       
    }
    
   public void setPosition(double[] pos){
       position[0] = pos[0];
       position[1] = pos[1];
   }
    
   public void setDirection(String direction){
       if(direction.equals("up")){
           this.direction = Pacman.Dir.up;
       }
       else if(direction.equals("down")){
           this.direction = Pacman.Dir.down;
       }
       else if(direction.equals("left")){
           this.direction = Pacman.Dir.left;
       }
       else if(direction.equals("right")){
           this.direction = Pacman.Dir.right;
       }
   }
   
   private void setCenterPoint(){
       centerPoint[0] = position[0]+DIMENSIONS[0]/2;
       centerPoint[1] = position[1]+DIMENSIONS[1]/2;
   }
   
   public void controlPacman(KeyEvent ke){
       pacControl.control(ke);
   }
  
   public void refresh(ActionEvent e){ 
       pacControl.refresh(e);
       pacControl.getPosition(position);
       setCenterPoint();
 //      currentTile = tiles.getTileOccupied(centerPoint);
   }
    
}
