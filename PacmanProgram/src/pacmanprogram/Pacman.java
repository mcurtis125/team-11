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
    double x=160,y=400,velx=0,vely=0;
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
        if(walls.isOccupiedByWallMovingLeft(x,y)&&code==KeyEvent.VK_LEFT){
            velx=0;
            x=x+1;
        }
        
        if(walls.isOccupiedByWallMovingRight(x,y)&&code==KeyEvent.VK_RIGHT){
            velx=0;
            x=x-1;
        }
        
        if(walls.isOccupiedByWallMovingUp(x,y)&&code==KeyEvent.VK_UP){
            vely=0;
            y=y+1;
        }
        
        if(walls.isOccupiedByWallMovingDown(x,y)&&code==KeyEvent.VK_DOWN){
            vely=0;
            y=y-1;
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
    public void keyReleased(KeyEvent e){
    
    
    }
    
}
