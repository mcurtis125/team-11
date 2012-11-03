/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.HashMap;

/**
 *
 * @author Kevin
 */


public class Pacman{
    PacmanControl pacControl = new PacmanControl();
    public enum Dir{up, down, left, right}; 
    private static final double[] DIMENSIONS = {15,15};
    private static final double MAX_SPEED = 15;
    private double[] position = new double[2];
    private double[] centerPoint = new double[2];
    private Dir direction = Dir.right;
    private int lives = 3;
    
    //private Tiles currentTile;
   
    private double normSpeed;
    private double dotSpeed;
    private double frightSpeed;
    private double frightDotSpeed;
    
    public Pacman(/*double normRatio, double dotRatio, double frightRatio, double frightDotRatio */){
       /* this.normSpeed = MAX_SPEED*normRatio;
        this.dotSpeed = MAX_SPEED*dotRatio;
        this.frightSpeed = MAX_SPEED*frightRatio;
        this.frightDotSpeed = MAX_SPEED*frightDotRatio;*/
        pacControl.getPosition(position);
        centerPoint[0] = position[0]+DIMENSIONS[0]/2;
        centerPoint[1] = position[1]+DIMENSIONS[1]/2;
  //      currentTile = tiles.getTileOccupied(centerPoint);
    }
    
    public void draw(Graphics g){
        Graphics2D pac = (Graphics2D) g;
        pac.setColor(Color.YELLOW);
        pac.fill(new Rectangle.Double(position[0], position[1], DIMENSIONS[0], DIMENSIONS[1]));
    }
    
    
   public void setDirection(String direction){
       if(direction.equals("up")){
           this.direction = Dir.up;
       }
       else if(direction.equals("down")){
           this.direction = Dir.down;
       }
       else if(direction.equals("left")){
           this.direction = Dir.left;
       }
       else if(direction.equals("right")){
           this.direction = Dir.right;
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
  //     currentTile = tiles.getTileOccupied(centerPoint);
   }
}