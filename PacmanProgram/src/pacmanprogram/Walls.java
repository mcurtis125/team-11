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
    static double sizeOfPacman=10;
    static double sizeOfWalls=16;
    

    
    
    
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
        
        
        //Top left quadrant
        coordinatesArray.add(new Coordinates(32,80,48,32)); //Far top-left block
        coordinatesArray.add(new Coordinates(80,80,16,32)); //Far top-left block
        coordinatesArray.add(new Coordinates(32,112,48,16));//Far top-left block
        coordinatesArray.add(new Coordinates(80,112,16,16));//Far top-left block
         
        coordinatesArray.add(new Coordinates(112,80,64,32));//Top center-left block
        coordinatesArray.add(new Coordinates(176,80,16,32));//Top center-left block
        coordinatesArray.add(new Coordinates(112,112,64,16));//Top center-left block
        coordinatesArray.add(new Coordinates(176,112,16,16));//Top center-left block
        
        coordinatesArray.add(new Coordinates(32,144,48,16)); //Far 2nd top-left block
        coordinatesArray.add(new Coordinates(80,144,16,16)); //Far 2nd top-left block
        coordinatesArray.add(new Coordinates(32,160,48,16)); //Far 2nd top-left block
        coordinatesArray.add(new Coordinates(80,160,16,16)); //Far 2nd top-left block
        
        coordinatesArray.add(new Coordinates(112,144,16,112)); //Top left 3 way block
        coordinatesArray.add(new Coordinates(112,256,16,16)); //Top left 3 way block
        coordinatesArray.add(new Coordinates(128,144,16,112)); //Top left 3 way block
        coordinatesArray.add(new Coordinates(128,256,16,16)); //Top left 3 way block
        coordinatesArray.add(new Coordinates(144,192,32,16)); //Top left 3 way block
        coordinatesArray.add(new Coordinates(144,208,32,16)); //Top left 3 way block
        coordinatesArray.add(new Coordinates(176,192,16,16)); //Top left 3 way block
        coordinatesArray.add(new Coordinates(176,208,16,16)); //Top left 3 way block
        
        
        
        //Top Center
        coordinatesArray.add(new Coordinates(208,64,16,48));//Top center
        coordinatesArray.add(new Coordinates(224,64,16,48));//Top center
        coordinatesArray.add(new Coordinates(208,112,16,16));//Top center
        coordinatesArray.add(new Coordinates(224,112,16,16));//Top center
        
        coordinatesArray.add(new Coordinates(160,144,112,16)); //Top center block
        coordinatesArray.add(new Coordinates(272,144,16,16)); //Top center block
        coordinatesArray.add(new Coordinates(160,160,112,16)); //Top center block
        coordinatesArray.add(new Coordinates(272,160,16,16));//Top center block
        coordinatesArray.add(new Coordinates(208,176,16,32));//Top center block
        coordinatesArray.add(new Coordinates(224,176,16,32));//Top center block
        coordinatesArray.add(new Coordinates(208,208,16,16));//Top center block
        coordinatesArray.add(new Coordinates(224,208,16,16));//Top center block
        
        
        
        //Top Right Quadrant
        coordinatesArray.add(new Coordinates(256,80,64,32));//Top center-right block
        coordinatesArray.add(new Coordinates(320,80,16,32));//Top center-right block
        coordinatesArray.add(new Coordinates(256,112,64,16));//Top center-right block
        coordinatesArray.add(new Coordinates(320,112,16,16));//Top center-right block
        
        coordinatesArray.add(new Coordinates(352,80,48,32)); //Far top-right block
        coordinatesArray.add(new Coordinates(400,80,16,32)); //Far top-right block
        coordinatesArray.add(new Coordinates(352,112,48,16));//Far top-right block
        coordinatesArray.add(new Coordinates(400,112,16,16));//Far top-right block
        
        coordinatesArray.add(new Coordinates(304,144,16,112)); //Top right 3 way block
        coordinatesArray.add(new Coordinates(304,256,16,16)); //Top right 3 way block
        coordinatesArray.add(new Coordinates(320,144,16,112)); //Top right 3 way block
        coordinatesArray.add(new Coordinates(320,256,16,16)); //Top right 3 way block
        coordinatesArray.add(new Coordinates(256,192,48,16));//Top right 3 way block
        coordinatesArray.add(new Coordinates(256,208,48,16));//Top right 3 way block
        
        coordinatesArray.add(new Coordinates(352,144,48,16)); //Far 2nd top-right block
        coordinatesArray.add(new Coordinates(400,144,16,16)); //Far 2nd top-right block
        coordinatesArray.add(new Coordinates(352,160,48,16)); //Far 2nd top-right block
        coordinatesArray.add(new Coordinates(400,160,16,16)); //Far 2nd top-right block
        
        
        
        coordinatesArray.add(new Coordinates(160,240,16,64)); //Ghost home
        coordinatesArray.add(new Coordinates(160,304,16,16));//Ghost home
        coordinatesArray.add(new Coordinates(176,240,32,16));//Ghost home
        coordinatesArray.add(new Coordinates(240,240,32,16));//Ghost home
        coordinatesArray.add(new Coordinates(272,240,16,64));//Ghost home
        coordinatesArray.add(new Coordinates(272,304,16,16));//Ghost home
        coordinatesArray.add(new Coordinates(176,304,96,16));//Ghost home
        
        
        //Bottom Left Quadrant
        coordinatesArray.add(new Coordinates(112,288,16,64)); //Center-left close to ghost-home block
        coordinatesArray.add(new Coordinates(112,352,16,16));//Center-left close to ghost-home block
        coordinatesArray.add(new Coordinates(128,288,16,64));//Center-left close to ghost-home block
        coordinatesArray.add(new Coordinates(128,352,16,16));//Center-left close to ghost-home block
        
        coordinatesArray.add(new Coordinates(112,384,64,16));//Center-left near 3-way block
        coordinatesArray.add(new Coordinates(176,384,16,16));//Center-left near 3-way block
        coordinatesArray.add(new Coordinates(112,400,64,16));//Center-left near 3-way block
        coordinatesArray.add(new Coordinates(176,400,16,16));//Center-left near 3-way block
        
        coordinatesArray.add(new Coordinates(32,384,48,16));//Bottom left hook
        coordinatesArray.add(new Coordinates(80,384,16,64));//Bottom left hook
        coordinatesArray.add(new Coordinates(32,400,48,16));//Bottom left hook
        coordinatesArray.add(new Coordinates(64,416,16,32));//Bottom left hook
        coordinatesArray.add(new Coordinates(64,448,16,16));//Bottom left hook
        coordinatesArray.add(new Coordinates(80,448,16,16));//Bottom left hook
        
        coordinatesArray.add(new Coordinates(32,480,144,16));//Bottom left 3-way block
        coordinatesArray.add(new Coordinates(32,496,144,16));//Bottom left 3-way block
        coordinatesArray.add(new Coordinates(176,480,16,16));//Bottom left 3-way block
        coordinatesArray.add(new Coordinates(176,496,16,16));//Bottom left 3-way block
        coordinatesArray.add(new Coordinates(112,432,16,48));//Bottom left 3-way block
        coordinatesArray.add(new Coordinates(128,432,16,48));//Bottom left 3-way block
        
        
        //Bottom Right Quadrant
        coordinatesArray.add(new Coordinates(304,288,16,64)); //Center-right close to ghost-home block
        coordinatesArray.add(new Coordinates(304,352,16,16));//Center-right close to ghost-home block
        coordinatesArray.add(new Coordinates(320,288,16,64));//Center-right close to ghost-home block
        coordinatesArray.add(new Coordinates(320,352,16,16));//Center-right close to ghost-home block
        
        coordinatesArray.add(new Coordinates(256,384,64,16));//Center-right near 3-way block
        coordinatesArray.add(new Coordinates(320,384,16,16));//Center-right near 3-way block
        coordinatesArray.add(new Coordinates(256,400,64,16));//Center-right near 3-way block
        coordinatesArray.add(new Coordinates(320,400,16,16));//Center-right near 3-way block
        
        coordinatesArray.add(new Coordinates(352,384,16,64));//Bottom right hook
        coordinatesArray.add(new Coordinates(352,448,16,16));//Bottom right hook
        coordinatesArray.add(new Coordinates(368,384,16,64));//Bottom right hook
        coordinatesArray.add(new Coordinates(368,448,16,16));//Bottom right hook
        coordinatesArray.add(new Coordinates(384,384,16,16));//Bottom right hook
        coordinatesArray.add(new Coordinates(384,400,16,16));//Bottom right hook
        coordinatesArray.add(new Coordinates(400,384,16,16));//Bottom right hook
        coordinatesArray.add(new Coordinates(400,400,16,16));//Bottom right hook
        
        coordinatesArray.add(new Coordinates(256,480,144,16));//Bottom right 3-way block
        coordinatesArray.add(new Coordinates(256,496,144,16));//Bottom right 3-way block
        coordinatesArray.add(new Coordinates(400,480,16,16));//Bottom right 3-way block
        coordinatesArray.add(new Coordinates(400,496,16,16));//Bottom right 3-way block
        coordinatesArray.add(new Coordinates(304,432,16,48));//Bottom right 3-way block
        coordinatesArray.add(new Coordinates(320,432,16,48));//Bottom right 3-way block
        
        //Bottom Center
        coordinatesArray.add(new Coordinates(160,336,112,16));//Center 3-way block close to ghost-home
        coordinatesArray.add(new Coordinates(272,336,16,16));//Center 3-way block close to ghost-home
        coordinatesArray.add(new Coordinates(160,352,112,16));//Center 3-way block close to ghost-home
        coordinatesArray.add(new Coordinates(272,352,16,16));//Center 3-way block close to ghost-home
        coordinatesArray.add(new Coordinates(208,368,16,32));//Center 3-way block close to ghost-home
        coordinatesArray.add(new Coordinates(208,400,16,16));//Center 3-way block close to ghost-home
        coordinatesArray.add(new Coordinates(224,368,16,32));//Center 3-way block close to ghost-home
        coordinatesArray.add(new Coordinates(224,400,16,16));//Center 3-way block close to ghost-home
        
        
        coordinatesArray.add(new Coordinates(160,432,112,16));//Center-bottom 3-way block
        coordinatesArray.add(new Coordinates(272,432,16,16));//Center-bottom 3-way block
        coordinatesArray.add(new Coordinates(160,448,112,16));//Center-bottom 3-way block
        coordinatesArray.add(new Coordinates(272,448,16,16));//Center-bottom 3-way block
        coordinatesArray.add(new Coordinates(208,464,16,32));//Center-bottom 3-way block
        coordinatesArray.add(new Coordinates(208,496,16,16));//Center-bottom 3-way block
        coordinatesArray.add(new Coordinates(224,464,16,32));//Center-bottom 3-way block
        coordinatesArray.add(new Coordinates(224,496,16,16));//Center-bottom 3-way block
     
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
            if(xCoordinate-sizeOfWalls==coordinatesArray.get(loopCounter).getXCoordinate()){
                if(yCoordinate+sizeOfPacman>coordinatesArray.get(loopCounter).getYCoordinate()&&
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
            if(xCoordinate+sizeOfPacman==coordinatesArray.get(loopCounter).getXCoordinate()){
                if(yCoordinate+sizeOfPacman>coordinatesArray.get(loopCounter).getYCoordinate()&&
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
            if(yCoordinate-sizeOfWalls==coordinatesArray.get(loopCounter).getYCoordinate()){
                if(xCoordinate+sizeOfPacman>coordinatesArray.get(loopCounter).getXCoordinate()&&
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
            if(yCoordinate+sizeOfPacman==coordinatesArray.get(loopCounter).getYCoordinate()){
                if(xCoordinate+sizeOfPacman>coordinatesArray.get(loopCounter).getXCoordinate()&&
                        xCoordinate<coordinatesArray.get(loopCounter).getXCoordinate()+
                        coordinatesArray.get(loopCounter).getWidth()){
                    return true;
                }
            }
        }
        return false; 
    }
        
    
}