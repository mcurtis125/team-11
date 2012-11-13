package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stavy92
 */
public class Ghost {
    
    
    public enum Name{Blinky, Pinky, Inky, Clyde};
    private Name name;
    private Color color;
    public static final double SIZE = 16;
    private static final double MAX_SPEED = 1;
    private double speed;
    private double normSpeed;
    private double tunnelSpeed;
    private double frightSpeed;
    private int frightTime;
    private int flashNumber;
    private double[] position = new double[2];
    GhostControl ghostControl;
    private int mode;
    
    public Ghost(Name name){
        this.name = name;
        switch (name){
            case Blinky:
                color = Color.RED;
                break;
            case Pinky:
                color = Color.PINK;
                break;
            case Inky:
                color = Color.CYAN;
                break;
            case Clyde:
                color = Color.ORANGE;              
                break;
        }
        mode = 2;
        GhostControl tempControl = new GhostControl(this);
        ghostControl=tempControl;
        ghostControl.getPosition(position);
    }
    
   public void draw(Graphics g){
        if(mode == 3){
            Graphics2D ghost = (Graphics2D) g;
            ghost.setColor(Color.BLUE);
            ghost.fill(new Rectangle.Double(position[0],position[1],15,15));
            ghost.setColor(Color.BLACK);        
            ghost.fill(new Rectangle.Double(position[0]+4,position[1]+3,2,2));
            ghost.fill(new Rectangle.Double(position[0]+9,position[1]+3,2,2));
        }
        else{
            Graphics2D ghost = (Graphics2D) g;
            ghost.setColor(color);
            ghost.fill(new Rectangle.Double(position[0],position[1],15,15));
            ghost.setColor(Color.BLACK);        
            ghost.fill(new Rectangle.Double(position[0]+4,position[1]+3,2,2));
            ghost.fill(new Rectangle.Double(position[0]+9,position[1]+3,2,2));
        }
    }
   
   public void assignSpeeds(double norm, double tunnel, double fright){
       this.normSpeed = MAX_SPEED*norm;
       this.tunnelSpeed = MAX_SPEED*tunnel;
       this.frightSpeed = MAX_SPEED*fright;
   }         
 
   public void refresh(ActionEvent e){
        ghostControl.refresh(e);
        ghostControl.getPosition(position);
        setMode(mode);
   }
   
   public void reset(){
       ghostControl.reset();
   }
   
   public Name getName(){
       return name;
   }
   
   public void setMode(int mode){
       this.mode = mode;
       ghostControl.setMode(mode);
       setSpeed();
   }
   
   public void flash(){
       
   }
   
   public void setSpeed(){
       if(mode==1||mode==2){
           speed = normSpeed;
       }
       else if(mode==3){
           speed = frightSpeed;
       }
       else if(isInTunnel()){
           speed = tunnelSpeed;
           System.out.println("tunnel");
       }
       ghostControl.setGhostSpeed(speed);
   }
   
   public boolean isInTunnel(){
       if( position[1]==272 && (position[0]<96||position[0]>352) ){
           return true;
       }
       else{
           return false;
       }
   }
}