/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *
 * @author Kevin
 */


public class Pacman{
    PacmanControl pacControl = new PacmanControl();
    Walls walls;
    private static final double[] DIMENSIONS = {15,15};
    private static final double MAX_SPEED = 1;
    public static final double SIZE = 15;
    private double[] position = new double[2];
    private int currentTileIndex;
    private int lives = 3;
    private int mode;
   
    private double speed;
    private double normSpeed;
    private double dotSpeed;
    private double frightSpeed;
    private double frightDotSpeed;
    
    public Pacman(Walls walls){
        this.walls = walls;
        mode = 2;
        pacControl.getPosition(position);
        currentTileIndex = walls.getIndex(position[0], position[1]);
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
       setMode(mode);
       walls.changeType(currentTileIndex,2,1);
       walls.changeType(currentTileIndex,3,1); 
    }
    
    public void reset(){
        pacControl.reset();
    }
    
    public void assignSpeeds(double norm, double dot, double fright, double frightDot){
        normSpeed = MAX_SPEED*norm;
        dotSpeed = MAX_SPEED*dot;
        frightSpeed = MAX_SPEED*fright;
        frightDotSpeed = MAX_SPEED*frightDot;
    }
    

    public void controlPacman(KeyEvent ke){
        pacControl.control(ke);
    }
    
    public int getCurrentTileIndex(){
        return currentTileIndex;
    }  
    
    public void setMode(int mode){
        this.mode = mode;
        setSpeed();
    }

    private void setSpeed() {
        if(mode==1||mode==2){
            if(walls.getType(getCurrentTileIndex())==2 || walls.getType(getCurrentTileIndex())==3){
                speed = dotSpeed;
            }
            else{
                speed = normSpeed;
            }
        }
        else if(mode==3){
            if(walls.getType(getCurrentTileIndex())==2 || walls.getType(getCurrentTileIndex())==3){
                speed = frightDotSpeed;
            }
            else{
                speed = frightSpeed;    
            }
        }
        pacControl.setPacmanSpeed(0.83);
    }

}