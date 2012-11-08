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
public class newVelocityChecker {
    
    double xCoordinate,yCoordinate,xVelocity,yVelocity,preVelX,preVelY,keyStrokeRemember; 
    public enum typeVel{xCoordinate,yCoordinate,xVelocity,yVelocity,preVelX,preVelY,keyStrokeRemember};
    WallCollisionChecker walls = new WallCollisionChecker();
    
    public newVelocityChecker(){
        
    }
    
    
    public HashMap<typeVel,Double> velocityCheck(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double preVelX, 
                                                    double preVelY, double keyStrokeRemember, int keyCode){
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        this.xVelocity=xVelocity;
        this.yVelocity=yVelocity;
        this.preVelX=preVelX;
        this.preVelY=preVelY;
        this.keyStrokeRemember=keyStrokeRemember;
        
        HashMap<typeVel, Double> validVelocityMap = new HashMap<typeVel, Double>();
        
        
        validVelocityMap=checkMemory(this.xCoordinate,this.yCoordinate,this.keyStrokeRemember,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveLeft(this.xCoordinate,this.yCoordinate,this.preVelY,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveRight(this.xCoordinate,this.yCoordinate,this.preVelY,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveUp(this.xCoordinate,this.yCoordinate,this.preVelX,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveDown(this.xCoordinate,this.yCoordinate,this.preVelX,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap.put(typeVel.xCoordinate, this.xCoordinate);
        validVelocityMap.put(typeVel.yCoordinate, this.yCoordinate);
        validVelocityMap.put(typeVel.xVelocity, this.xVelocity);
        validVelocityMap.put(typeVel.yVelocity, this.yVelocity);
        validVelocityMap.put(typeVel.preVelX, this.preVelX);
        validVelocityMap.put(typeVel.preVelY, this.preVelY);
        validVelocityMap.put(typeVel.keyStrokeRemember, this.keyStrokeRemember);
        
        return validVelocityMap;
        
    }
    
    
    
    
    public HashMap<typeVel,Double> checkMemory(double xCoordinate, double yCoordinate, double keyStrokeRemember, int keyCode){

        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();

        if(keyStrokeRemember!=1){
            return validVelocityMap;
        }
        else{
            if(!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_LEFT){
                validVelocityMap.put(typeVel.xVelocity,-1.0);
                validVelocityMap.put(typeVel.yVelocity,0.0);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }
            
            else if(!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_RIGHT){
                validVelocityMap.put(typeVel.xVelocity,1.0);
                validVelocityMap.put(typeVel.yVelocity,0.0);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }
            
            else if(!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_UP){
                validVelocityMap.put(typeVel.xVelocity,0.0);
                validVelocityMap.put(typeVel.yVelocity,-1.0);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }
            
            else if(!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_DOWN){
                validVelocityMap.put(typeVel.xVelocity,0.0);
                validVelocityMap.put(typeVel.yVelocity,1.0);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }  
        }
        return validVelocityMap;
   
    }
    
    
    public HashMap<typeVel,Double> checkMoveLeft(double xCoordinate, double yCoordinate, double preVelY, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        
        if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&
                                preVelY==-1&&keyCode==KeyEvent.VK_LEFT){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.yVelocity,-1.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);      
        }
        
        else if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&
                                preVelY==1&&keyCode==KeyEvent.VK_LEFT){

                validVelocityMap.put(typeVel.xVelocity,0.0);
                validVelocityMap.put(typeVel.yVelocity,1.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,1.0);            
        }
        
        else if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)){
                validVelocityMap.put(typeVel.xCoordinate,xCoordinate+1);
                validVelocityMap.put(typeVel.xVelocity,0.0);           
        }
        
        return validVelocityMap;
   
    }
    
    
    
    public HashMap<typeVel,Double> checkMoveRight(double xCoordinate, double yCoordinate, double preVelY, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        
        if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&
                preVelY==-1&&keyCode==KeyEvent.VK_RIGHT){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.yVelocity,preVelY);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&
                preVelY==1&&keyCode==KeyEvent.VK_RIGHT){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.yVelocity,preVelY);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.xCoordinate,xCoordinate-1);
        }
        
        return validVelocityMap;

    }
    
    
    
    public HashMap<typeVel,Double> checkMoveUp(double xCoordinate, double yCoordinate, double preVelX, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        
        if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&
                 preVelX==-1&&keyCode==KeyEvent.VK_UP){
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
         }
        
        else if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&
                preVelX==1&&keyCode==KeyEvent.VK_UP){
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)){
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.yCoordinate,yCoordinate+1);
        }
         
         return validVelocityMap;        
   
    }
    
    
        
    public HashMap<typeVel,Double> checkMoveDown(double xCoordinate, double yCoordinate, double preVelX, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        
        if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&
            preVelX==-1&&keyCode==KeyEvent.VK_DOWN){
            
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&
            preVelX==1&&keyCode==KeyEvent.VK_DOWN){
            
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)){
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.yCoordinate,yCoordinate-1);
        }
        
        return validVelocityMap;            
    }
    
    
    public void setVelocityCheckerVariables(HashMap<typeVel,Double> validVelocityMap){
        if(validVelocityMap.get(typeVel.xCoordinate)!=null){
            this.xCoordinate=validVelocityMap.get(typeVel.xCoordinate);
        }
        if(validVelocityMap.get(typeVel.yCoordinate)!=null){
            this.yCoordinate=validVelocityMap.get(typeVel.yCoordinate);
        }
        if(validVelocityMap.get(typeVel.xVelocity)!=null){
            this.xVelocity=validVelocityMap.get(typeVel.xVelocity);
        }
        if(validVelocityMap.get(typeVel.yVelocity)!=null){
            this.yVelocity=validVelocityMap.get(typeVel.yVelocity);
        }
        if(validVelocityMap.get(typeVel.preVelX)!=null){
            this.preVelX=validVelocityMap.get(typeVel.preVelX);
        }
        if(validVelocityMap.get(typeVel.preVelY)!=null){
            this.preVelY=validVelocityMap.get(typeVel.preVelY);
        }
        if(validVelocityMap.get(typeVel.keyStrokeRemember)!=null){
            this.keyStrokeRemember=validVelocityMap.get(typeVel.keyStrokeRemember);
        }
        
    }
    
    
}