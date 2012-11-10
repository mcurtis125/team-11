/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.util.ArrayList;



/**
 *
 * @author Kevin
 */
public class ShortestDistanceFinder {
    
    WallCollisionChecker wallCollsionChecker = new WallCollisionChecker(1);
    double sizeOfPacman=Pacman.SIZE;
    
    public ShortestDistanceFinder(){
    }
    
    
    public int chooseNextTile(double ghostXCoordinate, double ghostYCoordinate, double targetXCoordinate, double targetYCoordinate, double velx, double vely){
        
        double nextTileX = ghostXCoordinate;
        double nextTileY = ghostYCoordinate;
        
        Double upTileDistance = Double.MAX_VALUE;
        Double leftTileDistance = Double.MAX_VALUE;
        Double downTileDistance = Double.MAX_VALUE;
        Double rightTileDistance = Double.MAX_VALUE;
        
        double minimum;
        
        ArrayList<Double> distanceArray = new ArrayList<Double>();
        
        

        if(!wallCollsionChecker.isOccupiedByWallMovingUp(ghostXCoordinate, ghostYCoordinate)&&vely!=1){
            nextTileY = getUpTile(ghostYCoordinate);
            nextTileX = ghostXCoordinate;
            upTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(upTileDistance);
        }
        

        if(!wallCollsionChecker.isOccupiedByWallMovingLeft(ghostXCoordinate, ghostYCoordinate)&&velx!=1){
            nextTileX = getLeftTile(ghostXCoordinate);
            nextTileY = ghostYCoordinate;
            leftTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(leftTileDistance);
        }
        
        
        if(!wallCollsionChecker.isOccupiedByWallMovingDown(ghostXCoordinate, ghostYCoordinate)&&vely!=-1){
            nextTileY = getDownTile(ghostYCoordinate);
            nextTileX = ghostXCoordinate;
            downTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(downTileDistance);
        }
                
        
        if(!wallCollsionChecker.isOccupiedByWallMovingRight(ghostXCoordinate, ghostYCoordinate)&&velx!=-1){
            nextTileX = getRightTile(ghostXCoordinate);
            nextTileY = ghostYCoordinate;
            rightTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(rightTileDistance);
        }
        
        
        minimum = findMinimumDistance(distanceArray);
        
        if(upTileDistance==minimum){
            return 1;
        }
        
        if(leftTileDistance==minimum){
            return 2;
        }
        
        if(downTileDistance==minimum){
            return 3;
        }
        
        if(rightTileDistance==minimum){
            return 4;
        }
        
        return 0; 

    }
    
    
    public double getUpTile(double ghostYCoordinate){
        
        return ghostYCoordinate - sizeOfPacman;

    }
    
    public double getDownTile(double ghostYCoordinate){
        
        return ghostYCoordinate + sizeOfPacman;
        
    }
    
    public double getLeftTile(double ghostXCoordinate){
        
        return ghostXCoordinate - sizeOfPacman;
    }
    
    public double getRightTile(double ghostXCoordinate){
        
        return ghostXCoordinate + sizeOfPacman;
    }
    
    
    public double calculateDistance(double tileX, double tileY, double targetX, double targetY){
        double xDistance = Math.abs(targetX - tileX);
        double yDistance = Math.abs(targetY - tileY);
        
        xDistance = xDistance*xDistance;
        yDistance = yDistance*yDistance;
        
        double linearDistance = Math.sqrt(xDistance + yDistance);
        
        return linearDistance;
    }
    
    
    public double findMinimumDistance(ArrayList<Double> distanceArray){
        
        int loopCounter;
        double minimum=distanceArray.get(0);
        
        for(loopCounter=1;loopCounter<distanceArray.size();loopCounter++){
            if(distanceArray.get(loopCounter)<minimum){
                minimum=distanceArray.get(loopCounter);
            }
        }
        
        return minimum; 
    }
    
}
