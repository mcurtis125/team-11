package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stavy92
 */
public class Ghost {
    
//    public enum Dir{up, down, left, right}; 
    public enum Name{Blinky, Pinky, Inky, Clyde};
    private Name name;
    private Color color = Color.BLACK;
    private int homeCorner;
    private static final double[] DIMENSIONS = {15,15};
    private static final double MAX_SPEED = 15;
    private double[] position = new double[2];
    private int currentTileIndex;
    private int targetTileIndex;
   
    private double normSpeed;
    private double tunnelSpeed;
    private double frightSpeed;
    
    public Ghost(Name name){
        this.name = name;
        switch (name){
            case Blinky:
                homeCorner = 2;
                color = Color.RED;
                break;
            case Pinky:
                homeCorner = 1;
                color = Color.PINK;
                break;
            case Inky:
                homeCorner = 4;
                color = Color.CYAN;
                break;
            case Clyde:
                homeCorner = 3;
                color = Color.ORANGE;
                break;
        }
        GhostControl ghostControl = new GhostControl(this);    
        ghostControl.getPosition(position);
    }
    
    public void draw(Graphics g){
        Graphics2D ghost = (Graphics2D) g;
        ghost.setColor(color);
        ghost.fill(new Rectangle.Double(position[0],position[1],15,15));
        ghost.setColor(Color.BLACK);        
        ghost.fill(new Rectangle.Double(position[0]+4,position[1]+3,2,2));
        ghost.fill(new Rectangle.Double(position[0]+9,position[1]+3,2,2));
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
//       ghostControl.refresh(e);
   }
   
   public Name getName(){
       return name;
   }
   
   public int getHomeCorner(){
       return homeCorner;
   }
   
   
}
