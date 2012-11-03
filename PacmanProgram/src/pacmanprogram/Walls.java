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
    

    
    ArrayList<Tiles> coordinatesArray = new ArrayList<Tiles>();
    WallCoordinates wallCoordinates = new WallCoordinates();
    

    public Walls(){
        coordinatesArray=wallCoordinates.getWallCoordinatesArray();
    }
   
    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        //g2.setColor(Color.BLUE);
     
        int loopCounter;
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
        	if (coordinatesArray.get(loopCounter).getType()==0){
        	g2.setColor(Color.BLUE );
            g2.fill(new Rectangle.Double(coordinatesArray.get(loopCounter).getXCoordinate(),
                    coordinatesArray.get(loopCounter).getYCoordinate(),coordinatesArray.get(loopCounter).getWidth(),
                    coordinatesArray.get(loopCounter).getHeight()));}
        	else if (coordinatesArray.get(loopCounter).getType()==2){
        		g2.setColor(Color.LIGHT_GRAY );
        		g2.fill(new Ellipse2D.Double(coordinatesArray.get(loopCounter).getXCoordinate(),
                        coordinatesArray.get(loopCounter).getYCoordinate(),coordinatesArray.get(loopCounter).getWidth(),
                        coordinatesArray.get(loopCounter).getHeight()));}
        	else if (coordinatesArray.get(loopCounter).getType()==3){
        		g2.setColor(Color.LIGHT_GRAY );
        		g2.fill(new Ellipse2D.Double(coordinatesArray.get(loopCounter).getXCoordinate(),
                        coordinatesArray.get(loopCounter).getYCoordinate(),coordinatesArray.get(loopCounter).getWidth(),
                        coordinatesArray.get(loopCounter).getHeight()));}

        	}
        }
    
        public void refresh(ActionEvent ae){
            
        }
    }
