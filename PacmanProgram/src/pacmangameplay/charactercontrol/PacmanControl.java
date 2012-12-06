/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.charactercontrol;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import pacmangameplay.mazedisplay.Maze;


/**
 * Controller for a Pacman's movement.
 * @author stavy92
 */
public class PacmanControl  {
    
    public static double pacmanSpeed;
    public static double x,y,velx,vely,preVelX,preVelY;
    public static int direction;
    private static final double sizeOfPacman=Pacman.SIZE;
    int code;
    double keyStrokeRemember;
    boolean tightSpace=false;
    WallCollisionChecker walls;
    VelocityChecker velChecker;
    HashMap<VelocityChecker.typeVel,Double> validVelocityMap = new HashMap<VelocityChecker.typeVel,Double>();
    
    public PacmanControl(Maze maze){
        this.walls = new WallCollisionChecker(0, maze);
        this.velChecker = new VelocityChecker(maze);
        resetPosition();
    }
    
    /**
     * Updates Pacman's position and velocities.
     * @param e 
     */
    public void refresh(ActionEvent e){
        validVelocityMap=velChecker.velocityCheck(x, y, velx, vely, preVelX, preVelY, keyStrokeRemember, code);
        
        keyStrokeRemember=validVelocityMap.get(VelocityChecker.typeVel.keyStrokeRemember);
        preVelX=validVelocityMap.get(VelocityChecker.typeVel.preVelX);
        preVelY=validVelocityMap.get(VelocityChecker.typeVel.preVelY);
        velx=validVelocityMap.get(VelocityChecker.typeVel.xVelocity);
        vely=validVelocityMap.get(VelocityChecker.typeVel.yVelocity);
        x=validVelocityMap.get(VelocityChecker.typeVel.xCoordinate);
        y=validVelocityMap.get(VelocityChecker.typeVel.yCoordinate);
        
        
        x+=velx;
        y+=vely;
        
    }
    
    /**
     * Moves Pacman up one pixel.
     */
    public void up(){
        vely=-pacmanSpeed;
        velx=0;
    }
    
    /**
     * Moves Pacman down one pixel.
     */
    public void down(){
        vely=pacmanSpeed;
        velx=0;
    }
    
    /**
     * Moves Pacman left one pixel.
     */
    public void left(){
        vely=0;
        velx=-pacmanSpeed;
    }
    
    /**
     * Moves Pacman right one pixel.
     */
    public void right(){
        vely=0;
        velx=pacmanSpeed;
    }

    /**
     * Gets arrow key strokes to control Pacman's movement.
     * @param ke 
     */
    public void control(KeyEvent ke) {
       code = ke.getKeyCode();
        
        if(code==KeyEvent.VK_UP){
            if(walls.isOccupiedByWallMovingDown(x,y)||walls.isOccupiedByWallMovingDown(x,y+1)){
                preVelX=velx;
                keyStrokeRemember=0;
                tightSpace=true;
            }
            
            else if(walls.isOccupiedByWallMovingUp(x,y)||walls.isOccupiedByWallMovingUp(x,y-1)){
               if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                   preVelX=velx;
                }
            } 
            up(); 
            
        }
            
        if(code==KeyEvent.VK_DOWN){
            if(walls.isOccupiedByWallMovingUp(x,y)||walls.isOccupiedByWallMovingUp(x,y-1)){
                preVelX=velx;
                keyStrokeRemember=0;
                tightSpace=true;
            }
            
            else if(walls.isOccupiedByWallMovingDown(x,y)||walls.isOccupiedByWallMovingDown(x,y+1)){
                if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                    preVelX=velx;
                }
            } 
            down();
        }
            
        if(code==KeyEvent.VK_LEFT){
            
            if(walls.isOccupiedByWallMovingRight(x,y)||walls.isOccupiedByWallMovingRight(x+1,y)){
                preVelY=vely;
                keyStrokeRemember=0;
                tightSpace=true;
            }
            
            else if(walls.isOccupiedByWallMovingLeft(x,y)||walls.isOccupiedByWallMovingLeft(x-1,y)){
                if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                    preVelY=vely;
                }
            }  
            left();
        }
        
        
        if(code==KeyEvent.VK_RIGHT){
            if(walls.isOccupiedByWallMovingLeft(x,y)||walls.isOccupiedByWallMovingLeft(x-1,y)){
               preVelY=vely;
               keyStrokeRemember=0;
               tightSpace=true; 
            }
            
            else if(walls.isOccupiedByWallMovingRight(x,y)||walls.isOccupiedByWallMovingRight(x+1,y)){
                if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                    preVelY=vely;
                }
            } 
            right();
        }
    }
    
    /**
     * Returns Pacman's position.
     * @param pos 
     */
    public void getPosition(double[] pos){
        pos[0] = x;
        pos[1] = y;
    }
    
    /**
     * Resets Pacman's position.
     */
    public void resetPosition(){
        x=217;
        y=417;
        velx=0;
        vely=0;
        preVelX=0;
        preVelY=0;
        direction=0;
        keyStrokeRemember=0;   
    }
    
    /**
     * Returns Pacman's x and y velocities.
     * @param vel 
     */
    public void getVelocity(double[] vel){
        vel[0]=velx;
        vel[1]=vely;
    }
    
    /**
     * Returns Pacman's direction.
     * @return direction as integer
     */
    public int getDirection(){
        
        if(vely>0&&!walls.isOccupiedByWallMovingUp(x,y)&&!walls.isOccupiedByWallMovingUp(x,y-1)){
            direction = 1;
        }
        
        else if(vely<0&&!walls.isOccupiedByWallMovingDown(x,y)&&!walls.isOccupiedByWallMovingDown(x,y+1)){
            direction = 2;
        }
        
        else if(velx>0&&!walls.isOccupiedByWallMovingLeft(x,y)&&!walls.isOccupiedByWallMovingLeft(x-1,y)){
            direction = 3;
        }
        
        else if(velx<0&&!walls.isOccupiedByWallMovingRight(x,y)&&!walls.isOccupiedByWallMovingRight(x+1,y)){
            direction = 4;
        }
        
        return direction;
         
    }
    
    /**
     * Returns Pacman's speed.
     * @return 
     */
    public double getPacmanSpeed(){
        return pacmanSpeed;
    }
    
    /**
     * Sets Pacman's speed.
     * @param pacmanSpeed 
     */
    public void setPacmanSpeed(double pacmanSpeed){
        this.pacmanSpeed=pacmanSpeed;
    }
}