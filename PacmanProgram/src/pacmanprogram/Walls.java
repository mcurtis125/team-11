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
public class Walls extends JPanel {
    
    
    ArrayList<Coordinates> coordinatesArray = new ArrayList<>();
    Coordinates firstCoordinate = new Coordinates(0,528,448,16);
    

    
    
    
    public Walls(){
        //Bottom, top, and left edges of maze
        coordinatesArray.add(0, new Coordinates(0,528,448,16)); //bottom wall
        coordinatesArray.add(new Coordinates(0,352,16,192)); //bottom left vertical
        coordinatesArray.add(new Coordinates(16,448,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(32,448,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(16,432,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(32,432,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(16,352,64,16));
        coordinatesArray.add(new Coordinates(80,352,16,16));
        coordinatesArray.add(new Coordinates(80,288,16,64));
        coordinatesArray.add(new Coordinates(0,288,80,16)); //bottom of left tunnel
        coordinatesArray.add(new Coordinates(0,256,80,16)); //top of left tunnel
        coordinatesArray.add(new Coordinates(80,256,16,16)); //top of left tunnel, corner
        coordinatesArray.add(new Coordinates(80,192,16,64));
        coordinatesArray.add(new Coordinates(16,192,64,16));
        coordinatesArray.add(new Coordinates(0,48,16,160));
        coordinatesArray.add(new Coordinates(0,48,448,16)); //top wall
        
        //Right edge of maze
        coordinatesArray.add(new Coordinates(432,352,16,192));
        coordinatesArray.add(new Coordinates(416,448,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(400,448,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(416,432,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(400,432,16,16));//bump in wall
        coordinatesArray.add(new Coordinates(368,352,64,16));
        coordinatesArray.add(new Coordinates(352,352,16,16));
        coordinatesArray.add(new Coordinates(352,288,16,64));
        coordinatesArray.add(new Coordinates(368,288,80,16)); //bottom of right tunnel
        coordinatesArray.add(new Coordinates(368,256,80,16)); //top of right tunnel
        coordinatesArray.add(new Coordinates(352,256,16,16)); //top of right tunnel, corner
        coordinatesArray.add(new Coordinates(352,192,16,64));
        coordinatesArray.add(new Coordinates(368,192,64,16));
        coordinatesArray.add(new Coordinates(432,48,16,160));
        
        
        
    }
   
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
     
        int loopCounter;
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            g2.fill(new Rectangle.Double(coordinatesArray.get(loopCounter).getXCoordinate(),
                    coordinatesArray.get(loopCounter).getYCoordinate(),coordinatesArray.get(loopCounter).getWidth(),
                    coordinatesArray.get(loopCounter).getHeight()));
        }
    }
    
    
    
    public boolean isOccupiedByWallMovingLeft(double xCoordinate, double yCoordinate){
        int loopCounter;
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            if(xCoordinate-16==coordinatesArray.get(loopCounter).getXCoordinate()){
                if(yCoordinate+10>coordinatesArray.get(loopCounter).getYCoordinate()&&
                        yCoordinate<coordinatesArray.get(loopCounter).getYCoordinate()+
                        coordinatesArray.get(loopCounter).getHeight()){
                    return true;
                }
            }   
        }
        
        return false;
    }



    public boolean isOccupiedByWallMovingRight(double xCoordinate, double yCoordinate){
        int loopCounter;
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            if(xCoordinate+10==coordinatesArray.get(loopCounter).getXCoordinate()){
                if(yCoordinate+10>coordinatesArray.get(loopCounter).getYCoordinate()&&
                        yCoordinate<coordinatesArray.get(loopCounter).getYCoordinate()+
                        coordinatesArray.get(loopCounter).getHeight()){
                    return true;
                }
            }

        }
        
        return false;
    }
    
    
    public boolean isOccupiedByWallMovingUp(double xCoordinate, double yCoordinate){
        int loopCounter;
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            if(yCoordinate-16==coordinatesArray.get(loopCounter).getYCoordinate()){
                if(xCoordinate+10>coordinatesArray.get(loopCounter).getXCoordinate()&&
                        xCoordinate<coordinatesArray.get(loopCounter).getXCoordinate()+
                        coordinatesArray.get(loopCounter).getWidth()){
                    return true;
                }
            }
        }
        return false; 
    }
    
    
    
    public boolean isOccupiedByWallMovingDown(double xCoordinate, double yCoordinate){
        int loopCounter;
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            if(yCoordinate+10==coordinatesArray.get(loopCounter).getYCoordinate()){
                if(xCoordinate+10>coordinatesArray.get(loopCounter).getXCoordinate()&&
                        xCoordinate<coordinatesArray.get(loopCounter).getXCoordinate()+
                        coordinatesArray.get(loopCounter).getWidth()){
                    return true;
                }
            }
        }
        return false; 
    }
        
    
}