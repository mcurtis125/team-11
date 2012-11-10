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
    private static final double MAX_SPEED = 15;
    private double[] position = new double[2];
    GhostControl ghostControl;
    
    private double normSpeed;
    private double tunnelSpeed;
    private double frightSpeed;
    
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
        GhostControl tempControl = new GhostControl(this);
        ghostControl=tempControl;
    }
    
   public void draw(Graphics g){
        Graphics2D ghost = (Graphics2D) g;
        ghost.setColor(color);
        ghost.fill(new Rectangle.Double(position[0],position[1],SIZE,SIZE));
        ghost.setColor(Color.BLACK);        
        ghost.fill(new Rectangle.Double(position[0]+4,position[1]+3,2,2));
        ghost.fill(new Rectangle.Double(position[0]+9,position[1]+3,2,2));
    }
   
   public void setSpeeds(double norm, double tunnel, double fright){
       this.normSpeed = MAX_SPEED*norm;
       this.tunnelSpeed = MAX_SPEED*tunnel;
       this.frightSpeed = MAX_SPEED*fright;
   }
           
 
   public void refresh(ActionEvent e){
        
        ghostControl.refresh(e);
        ghostControl.getPosition(position);
   }
   
   public Name getName(){
       return name;
   }

}
