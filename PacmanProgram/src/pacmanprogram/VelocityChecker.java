
package pacmanprogram;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**This class determines whether the movement specified by a key press if valid.
 * For example, if the player presses the right arrow key to move Pacman right,
 * this class will check to see if Pacman is actually allowed to move in that direction.
 * It will also "remember" key strokes so that Pacman will move in the specified direction
 * at the next available opportunity.
 *
 * @author Takeshi
 */
public class VelocityChecker {
    
    double pacmanSpeed;
    double xCoordinate,yCoordinate,xVelocity,yVelocity,preVelX,preVelY,keyStrokeRemember; 
    public enum typeVel{xCoordinate,yCoordinate,xVelocity,yVelocity,preVelX,preVelY,keyStrokeRemember};
    WallCollisionChecker walls = new WallCollisionChecker(0);
    
    /**The constructor does not set any variables or take any parameters*/
    public VelocityChecker(){
        
    }
    
    /**This returns information about what position and speed Pacman should have.
     * @return a hash map with the x and y position and velocity of Pacman, the previous x and y velocity of Pacman,
     * and keyStrokeRemember which determines whether or not a previous key stroke should be put in memory.
     * @param xCoordinate The current position of Pacman in the x direction.
     * @param yCoordinate The current position of Pacman in the y direction.
     * @param xVelocity The current velocity of Pacman in the x direction.
     * @param yVelocity The current velocity Pacman in the y direction.
     * @param preVelX The previous velocity of Pacman in the x direction.
     * @param preVelY The previous velocity of Pacman in the y direction.
     * @param keyStrokeRemember This determines whether a pressed key should be remembered for later use.
     * @param keyCode This is the code corresponding to the key that the user pressed.
     */
    public HashMap<typeVel,Double> velocityCheck(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double preVelX, 
                                                    double preVelY, double keyStrokeRemember, int keyCode){
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        this.xVelocity=xVelocity;
        this.yVelocity=yVelocity;
        this.preVelX=preVelX;
        this.preVelY=preVelY;
        this.keyStrokeRemember=keyStrokeRemember;
        this.pacmanSpeed=PacmanControl.pacmanSpeed;
        
        

        
        HashMap<typeVel, Double> validVelocityMap = new HashMap<typeVel, Double>();
        
        validVelocityMap=checkTunnel(this.xCoordinate,this.yCoordinate);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMemory(this.xCoordinate,this.yCoordinate,this.keyStrokeRemember,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveLeft(this.xCoordinate,this.yCoordinate,this.xVelocity,this.preVelY,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveRight(this.xCoordinate,this.yCoordinate,this.xVelocity,this.preVelY,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveUp(this.xCoordinate,this.yCoordinate,this.yVelocity,this.preVelX,keyCode);
        setVelocityCheckerVariables(validVelocityMap);
        
        validVelocityMap=checkMoveDown(this.xCoordinate,this.yCoordinate,this.yVelocity,this.preVelX,keyCode);
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
    
    private HashMap<typeVel,Double> checkTunnel(double xCoordinate, double yCoordinate){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        
        
        if(xCoordinate<-12){
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.xCoordinate,444.0);
        }
        
        else if(xCoordinate>444){
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.xCoordinate,-12.0);
        }
        
        return validVelocityMap;
    }
    
    
    private HashMap<typeVel,Double> checkMemory(double xCoordinate, double yCoordinate, double keyStrokeRemember, int keyCode){

        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();

        if(keyStrokeRemember!=1){
            return validVelocityMap;
        }
        else{
            if(!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_LEFT){
                validVelocityMap.put(typeVel.xVelocity,-pacmanSpeed);
                validVelocityMap.put(typeVel.yVelocity,0.0);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }
            
            else if(!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_RIGHT){
                validVelocityMap.put(typeVel.xVelocity,pacmanSpeed);
                validVelocityMap.put(typeVel.yVelocity,0.0);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }
            
            else if(!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_UP){
                validVelocityMap.put(typeVel.xVelocity,0.0);
                validVelocityMap.put(typeVel.yVelocity,-pacmanSpeed);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }
            
            else if(!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&keyCode==KeyEvent.VK_DOWN){
                validVelocityMap.put(typeVel.xVelocity,0.0);
                validVelocityMap.put(typeVel.yVelocity,pacmanSpeed);
                validVelocityMap.put(typeVel.preVelX,0.0);
                validVelocityMap.put(typeVel.preVelY,0.0);
                validVelocityMap.put(typeVel.keyStrokeRemember,0.0);
            }  
        }
        return validVelocityMap;
   
    }
    
    
    private HashMap<typeVel,Double> checkMoveLeft(double xCoordinate, double yCoordinate, double xVelocity, double preVelY, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        double tempVelocity;
        
        if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&
                                preVelY<0&&keyCode==KeyEvent.VK_LEFT){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.yVelocity,preVelY);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);      
        }
        
        else if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&
                                preVelY>0&&keyCode==KeyEvent.VK_LEFT){

                validVelocityMap.put(typeVel.xVelocity,0.0);
                validVelocityMap.put(typeVel.yVelocity,preVelY);
                validVelocityMap.put(typeVel.keyStrokeRemember,1.0);            
        }
        
        else if(walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)){
                validVelocityMap.put(typeVel.xCoordinate,xCoordinate+1);
                validVelocityMap.put(typeVel.xVelocity,0.0);           
        }
        
        else {
                tempVelocity=walls.leftCollisionInAdvance(xCoordinate, yCoordinate, xVelocity);
                if(tempVelocity!=xVelocity){
                validVelocityMap.put(typeVel.xVelocity,tempVelocity);
                }
        }
        
        return validVelocityMap;
   
    }
    
    
    
    private HashMap<typeVel,Double> checkMoveRight(double xCoordinate, double yCoordinate, double xVelocity, double preVelY, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        double tempVelocity;
        
        if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&
                preVelY<0&&keyCode==KeyEvent.VK_RIGHT){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.yVelocity,preVelY);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&
                preVelY>0&&keyCode==KeyEvent.VK_RIGHT){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.yVelocity,preVelY);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)){
            validVelocityMap.put(typeVel.xVelocity,0.0);
            validVelocityMap.put(typeVel.xCoordinate,xCoordinate-1);
        }
        
        else {
            tempVelocity=walls.rightCollisionInAdvance(xCoordinate, yCoordinate, xVelocity);
            if(tempVelocity!=xVelocity){
                validVelocityMap.put(typeVel.xVelocity,tempVelocity);
            }
        }
        
        return validVelocityMap;

    }
    
    
    
    private HashMap<typeVel,Double> checkMoveUp(double xCoordinate, double yCoordinate, double yVelocity, double preVelX, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        double tempVelocity;
        
        if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&
                 preVelX<0&&keyCode==KeyEvent.VK_UP){
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
         }
        
        else if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&
                preVelX>0&&keyCode==KeyEvent.VK_UP){
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingUp(xCoordinate,yCoordinate)){
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.yCoordinate,yCoordinate+1);
        }
        
        else{
            tempVelocity=walls.upCollisionInAdvance(xCoordinate, yCoordinate, yVelocity);
            if(tempVelocity!=yVelocity){
                validVelocityMap.put(typeVel.yVelocity,tempVelocity);
            }
        }
         
         return validVelocityMap;        
   
    }
    
    
        
    private HashMap<typeVel,Double> checkMoveDown(double xCoordinate, double yCoordinate, double yVelocity, double preVelX, int keyCode){
        
        HashMap<typeVel,Double> validVelocityMap = new HashMap<typeVel,Double>();
        double tempVelocity;
        
        if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingLeft(xCoordinate,yCoordinate)&&
            preVelX<0&&keyCode==KeyEvent.VK_DOWN){
            
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)&&!walls.isOccupiedByWallMovingRight(xCoordinate,yCoordinate)&&
            preVelX>0&&keyCode==KeyEvent.VK_DOWN){
            
            validVelocityMap.put(typeVel.xVelocity,preVelX);
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.keyStrokeRemember,1.0);
        }
        
        else if(walls.isOccupiedByWallMovingDown(xCoordinate,yCoordinate)){
            validVelocityMap.put(typeVel.yVelocity,0.0);
            validVelocityMap.put(typeVel.yCoordinate,yCoordinate-1);
        }
        
        else{
            tempVelocity=walls.downCollisionInAdvance(xCoordinate, yCoordinate, yVelocity);
            if(tempVelocity!=yVelocity){
                validVelocityMap.put(typeVel.yVelocity,tempVelocity);
            }
        }
        
        return validVelocityMap;            
    }
    
    
    
    
    
    
    private void setVelocityCheckerVariables(HashMap<typeVel,Double> validVelocityMap){
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