/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

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
    int keyStrokeRemember=0;
    boolean tightSpace=false;
    WallCollisionChecker walls = new WallCollisionChecker();
    
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
     
        if(x==-10){
            vely=0;
            x=447;
        }
        
        if(x==448){
            vely=0;
            x=-9;
        }
        
        //Left checkers
        if(keyStrokeRemember==1){
            if(!walls.isOccupiedByWallMovingLeft(x,y)&&code==KeyEvent.VK_LEFT){
            velx=-1;
            vely=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }
            
            else if(!walls.isOccupiedByWallMovingRight(x,y)&&code==KeyEvent.VK_RIGHT){
            velx=1;
            vely=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }
            
            else if(!walls.isOccupiedByWallMovingUp(x,y)&&code==KeyEvent.VK_UP){
            vely=-1;
            velx=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }
            
            else if(!walls.isOccupiedByWallMovingDown(x,y)&&code==KeyEvent.VK_DOWN){
            vely=1;
            velx=0;
            preVelX=0;
            preVelY=0;
            keyStrokeRemember=0;
            }  
            
        }
        if(walls.isOccupiedByWallMovingLeft(x,y)&&!walls.isOccupiedByWallMovingUp(x,y)&&
                                preVelY==-1&&code==KeyEvent.VK_LEFT){
                vely=-1;
                velx=0;
                keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingLeft(x,y)&&!walls.isOccupiedByWallMovingDown(x,y)&&
                                preVelY==1&&code==KeyEvent.VK_LEFT){
                vely=1;
                velx=0;
                keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingLeft(x,y)){
            velx=0;
            x=x+1;
        }
        
        
        
        
        //Right checkers
        if(walls.isOccupiedByWallMovingRight(x,y)&&!walls.isOccupiedByWallMovingUp(x,y)&&preVelY==-1&&code==KeyEvent.VK_RIGHT){
            vely=-1;
            velx=0;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingRight(x,y)&&!walls.isOccupiedByWallMovingDown(x,y)&&preVelY==1&&code==KeyEvent.VK_RIGHT){
            vely=1;
            velx=0;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingRight(x,y)){
            velx=0;
            x=x-1;
        }
        
        
        
        
        
        //Up checkers
        if(walls.isOccupiedByWallMovingUp(x,y)&&!walls.isOccupiedByWallMovingLeft(x,y)&&preVelX==-1&&code==KeyEvent.VK_UP){
            vely=0;
            velx=-1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingUp(x,y)&&!walls.isOccupiedByWallMovingRight(x,y)&&preVelX==1&&code==KeyEvent.VK_UP){
            vely=0;
            velx=1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingUp(x,y)){
            vely=0;
            y=y+1;
        }
        
        
        
        
        //Down checkers
        if(walls.isOccupiedByWallMovingDown(x,y)&&!walls.isOccupiedByWallMovingLeft(x,y)&&preVelX==-1&&code==KeyEvent.VK_DOWN){
            vely=0;
            velx=-1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingDown(x,y)&&!walls.isOccupiedByWallMovingRight(x,y)&&preVelX==1&&code==KeyEvent.VK_DOWN){
            vely=0;
            velx=1;
            keyStrokeRemember=1;
        }
        
        else if(walls.isOccupiedByWallMovingDown(x,y)){
            vely=0;
            y=y-1;
        }
        
        
         
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
