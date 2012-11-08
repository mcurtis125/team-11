/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;


/**
 *
 * @author stavy92
 */
public class PacmanControl  {
    
    private double x=219,y=417,velx=0,vely=0,preVelX=0,preVelY=0, prePreVelX=0, prePreVelY=0;
    private double sizeOfPacman=MazeDimensions.sizeOfPacman;
    int code;
    double keyStrokeRemember=0;
    boolean tightSpace=false;
    WallCollisionChecker walls = new WallCollisionChecker();
    newVelocityChecker velChecker = new newVelocityChecker();
    HashMap<newVelocityChecker.typeVel,Double> validVelocityMap = new HashMap<newVelocityChecker.typeVel,Double>();
    
    public PacmanControl(){
        
    }
    
    public void refresh(ActionEvent e){
        validVelocityMap=velChecker.velocityCheck(x, y, velx, vely, preVelX, preVelY, keyStrokeRemember, code);
        
        keyStrokeRemember=validVelocityMap.get(newVelocityChecker.typeVel.keyStrokeRemember);
        preVelX=validVelocityMap.get(newVelocityChecker.typeVel.preVelX);
        preVelY=validVelocityMap.get(newVelocityChecker.typeVel.preVelY);
        velx=validVelocityMap.get(newVelocityChecker.typeVel.xVelocity);
        vely=validVelocityMap.get(newVelocityChecker.typeVel.yVelocity);
        x=validVelocityMap.get(newVelocityChecker.typeVel.xCoordinate);
        y=validVelocityMap.get(newVelocityChecker.typeVel.yCoordinate);
        
        
        x+=velx;
        y+=vely;
        
    }
    
    public void up(int scaler){
        vely=-1*scaler;
        velx=0;
    }
    
    public void down(int scaler){
        vely=1*scaler;
        velx=0;
    }
    
    public void left(int scaler){
        vely=0;
        velx=-1*scaler;
    }
    
    public void right(int scaler){
        vely=0;
        velx=1*scaler;
    }

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
            up(1); 
            
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
            down(1);
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
            left(1);
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
            right(1);
        }
    }
    
    public void getPosition(double[] pos){
        pos[0] = x;
        pos[1] = y;
    }
    
}