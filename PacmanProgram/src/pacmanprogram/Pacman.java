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
    Walls walls;
//    public enum Dir{up, down, left, right}; 
    private static final double[] DIMENSIONS = {15,15};
    private static final double MAX_SPEED = 15;
    public static final double SIZE = 15;
    private double[] position = new double[2];
    private int currentTileIndex;
//    private Dir direction = Dir.right;
    private int lives = 3;
    
    //private Tiles currentTile;
   
    private double normSpeed;
    private double dotSpeed;
    private double frightSpeed;
    private double frightDotSpeed;
    
    public Pacman(Walls walls/*double normRatio, double dotRatio, double frightRatio, double frightDotRatio */){
       /* this.normSpeed = MAX_SPEED*normRatio;
        this.dotSpeed = MAX_SPEED*dotRatio;
        this.frightSpeed = MAX_SPEED*frightRatio;
        this.frightDotSpeed = MAX_SPEED*frightDotRatio;*/
        this.walls = walls;
        pacControl.getPosition(position);
        currentTileIndex = walls.getIndex(position[0], position[1]);
//        centerPoint[0] = position[0]+DIMENSIONS[0]/2;
//        centerPoint[1] = position[1]+DIMENSIONS[1]/2;
  //      currentTile = tiles.getTileOccupied(centerPoint);
    }
    
    public void draw(Graphics g){
        Graphics2D pac = (Graphics2D) g;
        pac.setColor(Color.YELLOW);
        pac.fill(new Ellipse2D.Double(position[0], position[1], DIMENSIONS[0], DIMENSIONS[1]));
    }
    
    public void refresh(ActionEvent e){ 
       pacControl.refresh(e);
       pacControl.getPosition(position);
       currentTileIndex = walls.getIndex(position[0], position[1]);
       walls.changeType(currentTileIndex,2,1);
       walls.changeType(currentTileIndex,3,1);
       
    }
    
    public void setSpeeds(double norm, double dot, double fright, double frightDot){
        this.normSpeed = MAX_SPEED*norm;
        this.dotSpeed = MAX_SPEED*dot;
        this.frightSpeed = MAX_SPEED*fright;
        this.frightDotSpeed = MAX_SPEED*frightDot;
    }
    

    public void controlPacman(KeyEvent ke){
        pacControl.control(ke);
    }
    
    public int getCurrentTileIndex(){
        return currentTileIndex;
    }  

}