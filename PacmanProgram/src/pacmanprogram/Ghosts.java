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
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Kevin
 */
public class Ghosts extends JPanel implements ActionListener{
    
    Timer t = new Timer(10, this);
    MazeDimensions mazeDimensions = new MazeDimensions();
    java.util.Timer everySecond = new java.util.Timer();
    double blinkyXPosition=96,blinkyYPosition=512,pinkyXPosition=0, pinkyYPosition=0, inkyXposition=0, inkyYPosition=0,
            clydeXPosition=0,clydeYPosition=0;
    double blinkyVelX=0,blinkyVelY=0,pinkyVelX=0,pinkyVelY=0,inkyVelX=0,inkyVelY=0,clydeVelX=0,clydeVelY=0;
    double blinkyPreVelX=0,blinkyPreVelY=0;
    double sizeOfCharacters=mazeDimensions.getSizeOfPacman();
    
    int code=1, doRandNum=1, hitWall;
    int keyStrokeRemember=0;
    WallCollisionChecker walls = new WallCollisionChecker();
    Random randNum = new Random();
    
    public Ghosts(){
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(new Rectangle.Double(blinkyXPosition,blinkyYPosition,sizeOfCharacters,sizeOfCharacters));
        //g2.setColor(Color.PINK);
        //g2.fill(new Rectangle.Double(pinkyXPosition,pinkyYPosition,sizeOfCharacters,sizeOfCharacters));
        //g2.setColor(Color.CYAN);
       // g2.fill(new Rectangle.Double(inkyXposition,inkyYPosition,sizeOfCharacters,sizeOfCharacters));
       // g2.setColor(Color.ORANGE);
       // g2.fill(new Rectangle.Double(clydeXPosition,clydeYPosition,sizeOfCharacters,sizeOfCharacters));
    t.start();
    }
        
    public void actionPerformed(ActionEvent e){
        
        
        
        if(keyStrokeRemember==1){
            if(!walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)&&(code>=50&&code<100)){
            blinkyVelX=-1;
            blinkyVelY=0;
            blinkyPreVelX=0;
            blinkyPreVelY=0;
            keyStrokeRemember=0;
            doRandNum=1;
            }
            
            else if(!walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)&&(code<50&&code>=1)){
            blinkyVelX=1;
            blinkyVelY=0;
            blinkyPreVelX=0;
            blinkyPreVelY=0;
            keyStrokeRemember=0;
            doRandNum=1;
            }
            
            else if(!walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)&&(code>=150&&code<=200)){
            blinkyVelY=-1;
            blinkyVelX=0;
            blinkyPreVelX=0;
            blinkyPreVelY=0;
            keyStrokeRemember=0;
            doRandNum=1;
            }
            
            else if(!walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)&&(code>=100&&code<150)){
            blinkyVelY=1;
            blinkyVelX=0;
            blinkyPreVelX=0;
            blinkyPreVelY=0;
            keyStrokeRemember=0;
            doRandNum=1;
            }  
            
        }
        
        
        //Left checkers
        if(walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)&&
                                blinkyPreVelY==-1&&(code>=50&&code<100)){
                blinkyVelY=-1;
                blinkyVelX=0;
                keyStrokeRemember=1;
                doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)&&
                                blinkyPreVelY==1&&(code>=50&&code<100)){
                blinkyVelY=1;
                blinkyVelX=0;
                keyStrokeRemember=1;
                doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)){
            blinkyVelX=0;
            blinkyXPosition=blinkyXPosition+1;
            doRandNum=1;
        }
        
        
        
        
        //Right checkers
        if(walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)&&blinkyPreVelY==-1&&(code<50&&code>=1)){
            blinkyVelY=-1;
            blinkyVelX=0;
            keyStrokeRemember=1;
            doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)&&blinkyPreVelY==1&&(code<50&&code>=1)){
            blinkyVelY=1;
            blinkyVelX=0;
            keyStrokeRemember=1;
            doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)){
            blinkyVelX=0;
            blinkyXPosition=blinkyXPosition-1;
            doRandNum=1;
        }
        
        
        
        
        
        //Up checkers
        if(walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)&&blinkyPreVelX==-1&&(code>=150&&code<=200)){
            blinkyVelY=0;
            blinkyVelX=-1;
            keyStrokeRemember=1;
            doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)&&blinkyPreVelX==1&&(code>=150&&code<=200)){
            blinkyVelY=0;
            blinkyVelX=1;
            keyStrokeRemember=1;
            doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingUp(blinkyXPosition,blinkyYPosition)){
            blinkyVelY=0;
            blinkyYPosition=blinkyYPosition+1;
            doRandNum=1;
        }
        
        
        
        
        //Down checkers
        if(walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingLeft(blinkyXPosition,blinkyYPosition)&&blinkyPreVelX==-1&&(code>=100&&code<150)){
            blinkyVelY=0;
            blinkyVelX=-1;
            keyStrokeRemember=1;
            doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)&&!walls.isOccupiedByWallMovingRight(blinkyXPosition,blinkyYPosition)&&blinkyPreVelX==1&&(code>=100&&code<150)){
            blinkyVelY=0;
            blinkyVelX=1;
            keyStrokeRemember=1;
            doRandNum=0;
        }
        
        else if(walls.isOccupiedByWallMovingDown(blinkyXPosition,blinkyYPosition)){
            blinkyVelY=0;
            blinkyYPosition=blinkyYPosition-1;
            doRandNum=1;
        }
        
        
       
        blinkyPreVelX=blinkyVelX;
        blinkyPreVelY=blinkyVelY;
        blinkyXPosition+=blinkyVelX;
        blinkyYPosition+=blinkyVelY;
       
        
        
       
        
        if(doRandNum==1){
            if(code==200){
            code=1;
            }
            else if(code<200){
            code++;
            }    
        }
        
         if(code<50&&code>=1){
        blinkyVelX=1; 
        }
        if(code>=50&&code<100){
            blinkyVelX=-1;
        }
        if(code>=100&&code<150){
            blinkyVelY=1;
        }
        if(code>=150&&code<=200){
            blinkyVelY=-1;
        }
        
        
        
        
        
        
         /*everySecond = new java.util.Timer();
	    everySecond.schedule(new TimerTask()
		
		{
			public void run(){
			code=randNum.nextInt(4)+1;
			}
		}
		,0,5*1000);*/
        
        
        
        repaint();
        
        
       
        
     
        
        
    }
        
        
	    
        
        
        
    }
    
   
