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
    
    WallCollisionChecker wallCollsionChecker = new WallCollisionChecker();
    public enum nextDirection{up, down, left, right, none};
    
    public ShortestDistanceFinder(){
    }
    
    
    public ShortestDistanceFinder.nextDirection chooseNextTile(double ghostXCoordinate, double ghostYCoordinate, double targetXCoordinate, double targetYCoordinate, double velx, double vely){
        
        double nextTileX = ghostXCoordinate;
        double nextTileY = ghostYCoordinate;
        
        Double upTileDistance = Double.MAX_VALUE;
        Double leftTileDistance = Double.MAX_VALUE;
        Double downTileDistance = Double.MAX_VALUE;
        Double rightTileDistance = Double.MAX_VALUE;
        
        double minimum;
        
        ArrayList<Double> distanceArray = new ArrayList<Double>();
        
        
        nextTileY = getUpTile(ghostYCoordinate);
        if(!wallCollsionChecker.isOccupiedByWallMovingUp(nextTileX, nextTileY)&&vely!=1){
            upTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(upTileDistance);
        }
        
        nextTileX = getLeftTile(ghostXCoordinate);
        if(!wallCollsionChecker.isOccupiedByWallMovingLeft(nextTileX, nextTileY)&&velx!=1){
            leftTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(leftTileDistance);
        }
        
        nextTileY = getDownTile(ghostYCoordinate);
        if(!wallCollsionChecker.isOccupiedByWallMovingDown(nextTileX, nextTileY)&&vely!=-1){
            downTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(downTileDistance);
        }
                
        nextTileX = getRightTile(ghostXCoordinate);
        if(!wallCollsionChecker.isOccupiedByWallMovingRight(nextTileX, nextTileY)&&velx!=-1){
            rightTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(rightTileDistance);
        }
        
        
        minimum = findMinimumDistance(distanceArray);
        
        if(upTileDistance==minimum){
            return nextDirection.up;
        }
        
        if(leftTileDistance==minimum){
            return nextDirection.left;
        }
        
        if(downTileDistance==minimum){
            return nextDirection.down;
        }
        
        if(rightTileDistance==minimum){
            return nextDirection.right;
        }
        
        return nextDirection.none; 

    }
    
    
    public double getUpTile(double ghostYCoordinate){
        
        return ghostYCoordinate - MazeDimensions.sizeOfPacman;

    }
    
    public double getDownTile(double ghostYCoordinate){
        
        return ghostYCoordinate + MazeDimensions.sizeOfPacman;
        
    }
    
    public double getLeftTile(double ghostXCoordinate){
        
        return ghostXCoordinate - MazeDimensions.sizeOfPacman;
    }
    
    public double getRightTile(double ghostXCoordinate){
        
        return ghostXCoordinate + MazeDimensions.sizeOfPacman;
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
