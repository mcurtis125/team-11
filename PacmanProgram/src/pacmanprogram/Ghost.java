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
    
    GhostControl ghostControl = new GhostControl(this);
//    public enum Dir{up, down, left, right}; 
    private static final double[] DIMENSIONS = {15,15};
    private static final double MAX_SPEED = 15;
    private double[] position = new double[2];
    Tiles currentTile;
    
    //private Tile currentTile;
    //private Tile targetTile;
   
    private double normSpeed;
    private double tunnelSpeed;
    private double frightSpeed;
    
    public Ghost(){
        
//        ghostControl.getPosition(position);
    }
    
    public void draw(Graphics g){
       
    }
   
   public void setSpeeds(double norm, double tunnel, double fright){
       this.normSpeed = MAX_SPEED*norm;
       this.tunnelSpeed = MAX_SPEED*tunnel;
       this.frightSpeed = MAX_SPEED*fright;
   }
           
//   public void setDirection(String direction){
//       if(direction.equals("up")){
//           this.direction = Pacman.Dir.up;
//       }
//       else if(direction.equals("down")){
//           this.direction = Pacman.Dir.down;
//       }
//       else if(direction.equals("left")){
//           this.direction = Pacman.Dir.left;
//       }
//       else if(direction.equals("right")){
//           this.direction = Pacman.Dir.right;
//       }
//   }
//   
//   private void setCenterPoint(){
//       centerPoint[0] = position[0]+DIMENSIONS[0]/2;
//       centerPoint[1] = position[1]+DIMENSIONS[1]/2;
//   }
//   
   public void refresh(ActionEvent e){ 
       ghostControl.refresh(e);
 //      currentTile = tiles.getTileOccupied(centerPoint);
   }
    
}
