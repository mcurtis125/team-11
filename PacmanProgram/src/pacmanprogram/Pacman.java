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
    double x=219,y=419,velx=0,vely=0,preVelX=0,preVelY=0;
    double sizeOfPacman=10;
    int code;
    Walls walls = new Walls();
    
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
        g2.fill(new Rectangle.Double(x,y,10,10));
    }
    
    public void actionPerformed(ActionEvent e){
     
        //Left checkers
        if(walls.isOccupiedByWallMovingLeft(x,y)&&!walls.isOccupiedByWallMovingUp(x,y)&&preVelY==-1&&code==KeyEvent.VK_LEFT){
                vely=-1;
                velx=0;
            }
        
        else if(walls.isOccupiedByWallMovingLeft(x,y)&&!walls.isOccupiedByWallMovingDown(x,y)&&preVelY==1&&code==KeyEvent.VK_LEFT){
            vely=1;
            velx=0;
        }
        
        else if(walls.isOccupiedByWallMovingLeft(x,y)){
            velx=0;
            x=x+1;
        }
        
        else if(!walls.isOccupiedByWallMovingLeft(x,y)&&code==KeyEvent.VK_LEFT){
            velx=-1;
            vely=0;
        }
        
        
        //Right checkers
        if(walls.isOccupiedByWallMovingRight(x,y)&&!walls.isOccupiedByWallMovingUp(x,y)&&preVelY==-1&&code==KeyEvent.VK_RIGHT){
            vely=-1;
            velx=0;
        }
        
        else if(walls.isOccupiedByWallMovingRight(x,y)&&!walls.isOccupiedByWallMovingDown(x,y)&&preVelY==1&&code==KeyEvent.VK_RIGHT){
            vely=1;
            velx=0;
        }
        
        else if(walls.isOccupiedByWallMovingRight(x,y)){
            velx=0;
            x=x-1;
        }
        
        else if(!walls.isOccupiedByWallMovingRight(x,y)&&code==KeyEvent.VK_RIGHT){
            velx=1;
            vely=0;
        }
        
        
        
        //Up checkers
        if(walls.isOccupiedByWallMovingUp(x,y)&&!walls.isOccupiedByWallMovingLeft(x,y)&&preVelX==-1&&code==KeyEvent.VK_UP){
            vely=0;
            velx=-1;
        }
        
        else if(walls.isOccupiedByWallMovingUp(x,y)&&!walls.isOccupiedByWallMovingRight(x,y)&&preVelX==1&&code==KeyEvent.VK_UP){
            vely=0;
            velx=1;
        }
        
        else if(walls.isOccupiedByWallMovingUp(x,y)){
            vely=0;
            y=y+1;
        }
        
        else if(!walls.isOccupiedByWallMovingUp(x,y)&&code==KeyEvent.VK_UP){
            vely=-1;
            velx=0;
        }
        
        
        //Down checkers
        if(walls.isOccupiedByWallMovingDown(x,y)&&!walls.isOccupiedByWallMovingLeft(x,y)&&preVelX==-1&&code==KeyEvent.VK_DOWN){
            vely=0;
            velx=-1;
        }
        
        else if(walls.isOccupiedByWallMovingDown(x,y)&&!walls.isOccupiedByWallMovingRight(x,y)&&preVelX==1&&code==KeyEvent.VK_DOWN){
            vely=0;
            velx=1;
        }
        
        else if(walls.isOccupiedByWallMovingDown(x,y)){
            vely=0;
            y=y-1;
        }
        
        else if(!walls.isOccupiedByWallMovingDown(x,y)&&code==KeyEvent.VK_DOWN){
            vely=1;
            velx=0;
        }
        
         
         
        if(x==-10){
            x=447;
        }
        
        if(x==448){
            x=-9;
        }
        
        repaint();
        x+=velx;
        y+=vely;
    }
    
    public void up(){
        vely=-1;
        velx=0;
    }
    
    public void down(){
        vely=1;
        velx=0;
    }
    
    public void left(){
        vely=0;
        velx=-1;
    }
    
    public void right(){
        vely=0;
        velx=1;
    }
    
    
    public void keyPressed(KeyEvent e){
        code = e.getKeyCode();
        preVelX=velx;
        preVelY=vely;
        if(code==KeyEvent.VK_UP){
            up();
        }
            
        if(code==KeyEvent.VK_DOWN){
            down();
        }
            
        if(code==KeyEvent.VK_LEFT){
            left();
        }
        
        if(code==KeyEvent.VK_RIGHT){
            right();
        }
        
    }
    
    
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    
    public double getSizeOfPacman(){
        return sizeOfPacman;
    }
    
}
