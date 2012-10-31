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
public class Pacman extends JPanel implements ActionListener, KeyListener{
    
    Timer t = new Timer(10, this);
    MazeDimensions mazeDimensions = new MazeDimensions();
    double x=219,y=417,velx=0,vely=0,preVelX=0,preVelY=0, prePreVelX=0, prePreVelY=0;
    double sizeOfPacman=mazeDimensions.getSizeOfPacman();
    int code;
    double keyStrokeRemember=0;
    boolean tightSpace=false;
    WallCollisionChecker walls = new WallCollisionChecker();
    ValidVelocityChecker velChecker = new ValidVelocityChecker();
    HashMap<ValidVelocityChecker.typeVel,Double> validVelocityMap = new HashMap<>();
    
    public Pacman(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.fill(new Rectangle.Double(x,y,sizeOfPacman,sizeOfPacman));
    }
    
    public void actionPerformed(ActionEvent e){
    
        validVelocityMap=velChecker.velocityCheck(x, y, velx, vely, preVelX, preVelY, keyStrokeRemember, code);
        
        keyStrokeRemember=validVelocityMap.get(ValidVelocityChecker.typeVel.keyStrokeRemember);
        preVelX=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelX);
        preVelY=validVelocityMap.get(ValidVelocityChecker.typeVel.preVelY);
        velx=validVelocityMap.get(ValidVelocityChecker.typeVel.xVelocity);
        vely=validVelocityMap.get(ValidVelocityChecker.typeVel.yVelocity);
        x=validVelocityMap.get(ValidVelocityChecker.typeVel.xCoordinate);
        y=validVelocityMap.get(ValidVelocityChecker.typeVel.yCoordinate);
        
        
        x+=velx;
        y+=vely;
        
        
        repaint();
       
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
    
    
    public void keyPressed(KeyEvent e){
        code = e.getKeyCode();
        
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
    
    
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    
    public double getSizeOfPacman(){
        return sizeOfPacman;
    }
    
}
