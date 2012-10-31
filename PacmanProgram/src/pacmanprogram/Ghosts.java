/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Kevin
 */
public class Ghosts extends JPanel implements ActionListener{
    
    
    //public enum DirectionCode{UP, DOWN, LEFT, RIGHT};
    
    //DirectionCode code;
    
    Timer t = new Timer(10, this);
    MazeDimensions mazeDimensions = new MazeDimensions();
    double blinkyXPosition=416,blinkyYPosition=512,pinkyXPosition=416, pinkyYPosition=128, inkyXPosition=160, inkyYPosition=272,
            clydeXPosition=288,clydeYPosition=417;
    double blinkyVelX=0,blinkyVelY=0,pinkyVelX=0,pinkyVelY=0,inkyVelX=0,inkyVelY=0,clydeVelX=0,clydeVelY=0;
    double blinkyPreVelX=0,blinkyPreVelY=0, pinkyPreVelX=0, pinkyPreVelY=0, inkyPreVelX=0, inkyPreVelY=0, clydePreVelX=0, clydePreVelY=0;
    double blinkyKeyRemember=0, pinkyKeyRemember=0, inkyKeyRemember=0, clydeKeyRemember=0;
    double sizeOfCharacters=mazeDimensions.getSizeOfPacman();
    int code;
    int blinkyLoopCounter=0, pinkyLoopCounter=0, inkyLoopCounter=0, clydeLoopCounter=0;
    double keyStrokeRemember=0;
    boolean tightSpace=false;
    WallCollisionChecker walls = new WallCollisionChecker();
    ValidVelocityChecker velChecker = new ValidVelocityChecker();
    HashMap<ValidVelocityChecker.typeVel,Double> validVelocityMap = new HashMap<>();
    
    public Ghosts(){
        
    }
    
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(new Rectangle.Double(blinkyXPosition,blinkyYPosition,sizeOfCharacters,sizeOfCharacters));
        g2.setColor(Color.PINK);
        g2.fill(new Rectangle.Double(pinkyXPosition,pinkyYPosition,sizeOfCharacters,sizeOfCharacters));
        g2.setColor(Color.CYAN);
        g2.fill(new Rectangle.Double(inkyXPosition,inkyYPosition,sizeOfCharacters,sizeOfCharacters));
        g2.setColor(Color.getHSBColor(45,54,93));
        g2.fill(new Rectangle.Double(clydeXPosition,clydeYPosition,sizeOfCharacters,sizeOfCharacters));
        t.start();
    }
        
    public void actionPerformed(ActionEvent e){
        
        validVelocityMap=velChecker.velocityCheck(blinkyXPosition, blinkyYPosition, blinkyVelX, blinkyVelY, blinkyPreVelX, blinkyPreVelY, keyStrokeRemember, code);
        
        //keyStrokeRemember=validVelocityMap.get(ValidVelocityChecker.typeVel.keyStrokeRemember);
        blinkyPreVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelX);
        blinkyPreVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelY);
        blinkyVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.xVelocity);
        blinkyVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.yVelocity);
        blinkyXPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.xCoordinate);
        blinkyYPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.yCoordinate);
        
        blinkyXPosition+=blinkyVelX;
        blinkyYPosition+=blinkyVelY;
        
        validVelocityMap=velChecker.velocityCheck(pinkyXPosition, pinkyYPosition, pinkyVelX, pinkyVelY, pinkyPreVelX, pinkyPreVelY, keyStrokeRemember, code);
        
        pinkyPreVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelX);
        pinkyPreVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelY);
        pinkyVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.xVelocity);
        pinkyVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.yVelocity);
        pinkyXPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.xCoordinate);
        pinkyYPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.yCoordinate);
        
        pinkyXPosition+=blinkyVelX;
        pinkyYPosition+=blinkyVelY;
        
        
        validVelocityMap=velChecker.velocityCheck(inkyXPosition, inkyYPosition, inkyVelX, inkyVelY, inkyPreVelX, inkyPreVelY, keyStrokeRemember, code);
        
        inkyPreVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelX);
        inkyPreVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelY);
        inkyVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.xVelocity);
        inkyVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.yVelocity);
        inkyXPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.xCoordinate);
        inkyYPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.yCoordinate);
        
        inkyXPosition+=blinkyVelX;
        inkyYPosition+=blinkyVelY;
        
        validVelocityMap=velChecker.velocityCheck(clydeXPosition, clydeYPosition, clydeVelX, clydeVelY, clydePreVelX, clydePreVelY, keyStrokeRemember, code);
        
        clydePreVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelX);
        clydePreVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelY);
        clydeVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.xVelocity);
        clydeVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.yVelocity);
        clydeXPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.xCoordinate);
        clydeYPosition=validVelocityMap.get(ValidVelocityChecker.typeVel.yCoordinate);
        
        clydeXPosition+=blinkyVelX;
        clydeYPosition+=blinkyVelY;
        
        
        
            if(blinkyLoopCounter==800){
                blinkyLoopCounter=0;
            }
        
            if(blinkyLoopCounter>=0&&blinkyLoopCounter<=399){
                code=KeyEvent.VK_LEFT;   
            }
            if(blinkyLoopCounter>=400&&blinkyLoopCounter<=799){
                code=KeyEvent.VK_RIGHT;
            }
            
            blinkyLoopCounter++;

        repaint();
        
        
        
        
        if(code==KeyEvent.VK_UP){
            if(walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition+1)){
                blinkyPreVelX=blinkyVelX;
                keyStrokeRemember=0;
                tightSpace=true;
            }
            
            else if(walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition-1)){
               if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                blinkyPreVelX=blinkyVelX;
                }
            } 
            up(1); 
            
        }
            
        if(code==KeyEvent.VK_DOWN){
            if(walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition-1)){
                blinkyPreVelX=blinkyVelX;
                keyStrokeRemember=0;
                tightSpace=true;
            }
            
            else if(walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition+1)){
                if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                blinkyPreVelX=blinkyVelX;
                }
            } 
            down(1);
        }
            
        if(code==KeyEvent.VK_LEFT){
            
            if(walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingRight(blinkyXPosition+1,blinkyYPosition)){
                blinkyPreVelY=blinkyVelY;
                keyStrokeRemember=0;
                tightSpace=true;
            }
            
            else if(walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingLeft(blinkyXPosition-1,blinkyYPosition)){
                if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                blinkyPreVelY=blinkyVelY;
                }
            }  
            left(1);
        }
        
        
        if(code==KeyEvent.VK_RIGHT){
            if(walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingLeft(blinkyXPosition-1,blinkyYPosition)){
               blinkyPreVelY=blinkyVelY;
               keyStrokeRemember=0;
               tightSpace=true; 
            }
            
            else if(walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)||walls.isOccupiedByWallMovingRight(blinkyXPosition+1,blinkyYPosition)){
                if(tightSpace==true){
                    tightSpace=false;
                }
                else{
                blinkyPreVelY=blinkyVelY;
                }
            } 
            right(1);
        }
        
       
        
     
        
        
    }
        
        

    
    public void up(int scaler){
    blinkyVelY=-1*scaler;
    blinkyVelX=0;
    }
    
    public void down(int scaler){
        blinkyVelY=1*scaler;
        blinkyVelX=0;
    }
    
    public void left(int scaler){
        blinkyVelY=0;
        blinkyVelX=-1*scaler;
    }
    
    public void right(int scaler){
        blinkyVelY=0;
        blinkyVelX=1*scaler;
    }
    
    
        
        
        
    }
    
   
