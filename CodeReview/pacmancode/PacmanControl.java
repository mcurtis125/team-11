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
    
    private double pacmanSpeed = 1;
    private double x,y,velx,vely,preVelX,preVelY;
    private static final double sizeOfPacman=Pacman.SIZE;
    int code;
    double keyStrokeRemember;
    boolean tightSpace=false;
    WallCollisionChecker walls = new WallCollisionChecker(0);
    VelocityChecker velChecker = new VelocityChecker();
    HashMap<VelocityChecker.typeVel,Double> validVelocityMap = new HashMap<VelocityChecker.typeVel,Double>();
    
    public PacmanControl(){
        reset();
    }
    
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
    
    public void up(){
        vely=-pacmanSpeed;
        velx=0;
    }
    
    public void down(){
        vely=pacmanSpeed;
        velx=0;
    }
    
    public void left(){
        vely=0;
        velx=-pacmanSpeed;
    }
    
    public void right(){
        vely=0;
        velx=pacmanSpeed;
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
    
    public void getPosition(double[] pos){
        pos[0] = x;
        pos[1] = y;
    }
    
    public void getVelocity(double[] vel){
        vel[0]=velx;
        vel[1]=vely;
    }
    
    
    public void setPacmanSpeed(double pacmanSpeed){
        this.pacmanSpeed=pacmanSpeed;
    }
    
    
    public void reset(){
        x=217;
        y=417;
        velx=0;
        vely=0;
        preVelX=0;
        preVelY=0;
        keyStrokeRemember=0;   
    }
    
}