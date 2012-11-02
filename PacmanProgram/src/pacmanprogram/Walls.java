/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class Walls{
    

    
    ArrayList<Coordinates> coordinatesArray = new ArrayList<Coordinates>();
    WallCoordinates wallCoordinates = new WallCoordinates();
    

    public Walls(){
        coordinatesArray=wallCoordinates.getWallCoordinatesArray();
    }
   
    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
     
        int loopCounter;
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            g2.fill(new Rectangle.Double(coordinatesArray.get(loopCounter).getXCoordinate(),
                    coordinatesArray.get(loopCounter).getYCoordinate(),coordinatesArray.get(loopCounter).getWidth(),
                    coordinatesArray.get(loopCounter).getHeight()));
        }
    }
    
    
}