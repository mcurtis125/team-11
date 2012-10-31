/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *
 * @author Kevin
 */
public class ValidVelocityChecker {
    
    public enum typeVel{xCoordinate,yCoordinate,xVelocity,yVelocity,preVelX,preVelY,keyStrokeRemember};
    WallCollisionChecker walls = new WallCollisionChecker();
    
    public ValidVelocityChecker(){
    }
    
    
    
    public HashMap<typeVel,Double> velocityCheck(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double preVelX, double preVelY, double keyStrokeRemember, int keyCode){
    

    HashMap<typeVel, Double> validVelocityMap = new HashMap<>();
    
        //Tunnel checkers
        if(xCoordinate==-10){
            yVelocity=0;
            xCoordinate=447;
        }
        
        if(xCoordinate==448){
            yVelocity=0;
            xCoordinate=-9;
        }
        
        
        
        //Memory checkers
        if(keyStrokeRemember==1){
            if(!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_LEFT){
            xVelocity=-1;
            yVelocity=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }
            
            else if(!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_RIGHT){
            xVelocity=1;
            yVelocity=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }
            
            else if(!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_UP){
            yVelocity=-1;
            xVelocity=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }
            
            else if(!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_DOWN){
            yVelocity=1;
            xVelocity=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }  
            
        }
        
        
        
        
        //Left checkers
        if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&
                                preVelY==-1&&keyCode==KeyEvent.VK_LEFT){
                yVelocity=-1;
                xVelocity=0;
                keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&
                                preVelY==1&&keyCode==KeyEvent.VK_LEFT){
                yVelocity=1;
                xVelocity=0;
                keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)){
            xVelocity=0;
            xCoordinate=xCoordinate+1;
        }
        
        
        
        
        //Right checkers
        if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&preVelY==-1&&keyCode==KeyEvent.VK_RIGHT){
            yVelocity=-1;
            xVelocity=0;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&preVelY==1&&keyCode==KeyEvent.VK_RIGHT){
            yVelocity=1;
            xVelocity=0;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)){
            xVelocity=0;
            xCoordinate=xCoordinate-1;
        }
        
        
        
        
        
        //Up checkers
        if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&preVelX==-1&&keyCode==KeyEvent.VK_UP){
            yVelocity=0;
            xVelocity=-1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&preVelX==1&&keyCode==KeyEvent.VK_UP){
            yVelocity=0;
            xVelocity=1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)){
            yVelocity=0;
            yCoordinate=yCoordinate+1;
        }
        
        
        
        
        //Down checkers
        if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&preVelX==-1&&keyCode==KeyEvent.VK_DOWN){
            yVelocity=0;
            xVelocity=-1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&preVelX==1&&keyCode==KeyEvent.VK_DOWN){
            yVelocity=0;
            xVelocity=1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)){
            yVelocity=0;
            yCoordinate=yCoordinate-1;
        }
    
    
                validVelocityMap.put(typeVel.xVelocity,xVelocity);
                validVelocityMap.put(typeVel.yVelocity,yVelocity);
                validVelocityMap.put(typeVel.preVelX,preVelX);
                validVelocityMap.put(typeVel.preVelY,preVelY);
                validVelocityMap.put(typeVel.keyStrokeRemember,keyStrokeRemember);
                validVelocityMap.put(typeVel.xCoordinate,xCoordinate);
                validVelocityMap.put(typeVel.yCoordinate,yCoordinate);
    
                return validVelocityMap;
    
}

                            
}