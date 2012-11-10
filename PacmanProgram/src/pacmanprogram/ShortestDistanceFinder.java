/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;



/**
 *
 * @author Kevin
 */
public class ShortestDistanceFinder {
    
    public enum directionCheck{canMoveUp,canMoveLeft,canMoveDown,canMoveRight,isIntersection};
    
    private double ghostSpeed=1;

    WallCollisionChecker wallCollsionChecker = new WallCollisionChecker(1);
    double sizeOfGhost=Ghost.SIZE;
    
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
        
        

        if(!wallCollsionChecker.isOccupiedByWallMovingUp(ghostXCoordinate, ghostYCoordinate)&&vely!=ghostSpeed){
            nextTileY = getUpTile(ghostYCoordinate);
            nextTileX = ghostXCoordinate;
            upTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(upTileDistance);
        }
        

        if(!wallCollsionChecker.isOccupiedByWallMovingLeft(ghostXCoordinate, ghostYCoordinate)&&velx!=ghostSpeed){
            nextTileX = getLeftTile(ghostXCoordinate);
            nextTileY = ghostYCoordinate;
            leftTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(leftTileDistance);
        }
        
        
        if(!wallCollsionChecker.isOccupiedByWallMovingDown(ghostXCoordinate, ghostYCoordinate)&&vely!=-ghostSpeed){
            nextTileY = getDownTile(ghostYCoordinate);
            nextTileX = ghostXCoordinate;
            downTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(downTileDistance);
        }
                
        
        if(!wallCollsionChecker.isOccupiedByWallMovingRight(ghostXCoordinate, ghostYCoordinate)&&velx!=-ghostSpeed){
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
        
        return ghostYCoordinate - sizeOfGhost;

    }
    
    public double getDownTile(double ghostYCoordinate){
        
        return ghostYCoordinate + sizeOfGhost;
        
    }
    
    public double getLeftTile(double ghostXCoordinate){
        
        return ghostXCoordinate - sizeOfGhost;
    }
    
    public double getRightTile(double ghostXCoordinate){
        
        return ghostXCoordinate + sizeOfGhost;
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
    
    
    
    
    public int chooseNextRandomTile(double ghostXCoordinate, double ghostYCoordinate, double velx, double vely){
        
        int randomNum=0;
        int loopCounter;
        HashMap<directionCheck,Boolean> movementArray = new HashMap<directionCheck,Boolean>();
        movementArray=isIntersection(ghostXCoordinate,ghostYCoordinate);
        
        if(movementArray.get(directionCheck.isIntersection)){
            Random randomGenerator = new Random(System.currentTimeMillis());
            randomNum = randomGenerator.nextInt(4)+1;
        
            for(loopCounter=0;loopCounter<4;loopCounter++){
                if(randomNum==1&&movementArray.get(directionCheck.canMoveUp)&&vely!=ghostSpeed){
                    return 1;
                }
       
                if(randomNum==2&&movementArray.get(directionCheck.canMoveLeft)&&velx!=ghostSpeed){
                    return 2;
                }        
        
                if(randomNum==3&&movementArray.get(directionCheck.canMoveDown)&&vely!=-ghostSpeed){
                    return 3;
                }
        
                if(randomNum==4&&movementArray.get(directionCheck.canMoveRight)&&velx!=-ghostSpeed){
                    return 4;
                }
            
                if(randomNum==4){
                    randomNum=1;
                }
                else{
                    randomNum++;
                }
            }
        
        }
        
        else{
            randomNum=1;
            for(loopCounter=0;loopCounter<4;loopCounter++){
                if(randomNum==1&&movementArray.get(directionCheck.canMoveUp)&&vely!=ghostSpeed){
                    return 1;
                }
       
                if(randomNum==2&&movementArray.get(directionCheck.canMoveLeft)&&velx!=ghostSpeed){
                    return 2;
                }        
        
                if(randomNum==3&&movementArray.get(directionCheck.canMoveDown)&&vely!=-ghostSpeed){
                    return 3;
                }
        
                if(randomNum==4&&movementArray.get(directionCheck.canMoveRight)&&velx!=-ghostSpeed){
                    return 4;
                }
                    randomNum++;
                }
        }
        
        return 0;
        
    }
    
    
    
    public HashMap<directionCheck,Boolean> isIntersection(double ghostXCoordinate, double ghostYCoordinate){
        boolean canMoveLeft=false;
        boolean canMoveRight=false;
        boolean canMoveUp=false;
        boolean canMoveDown=false;
        boolean isIntersection=false;
        HashMap<directionCheck,Boolean> movementArray = new HashMap<directionCheck,Boolean>();

        if(!wallCollsionChecker.isOccupiedByWallMovingUp(ghostXCoordinate, ghostYCoordinate)){
            canMoveUp=true;
        }
        
        if(!wallCollsionChecker.isOccupiedByWallMovingLeft(ghostXCoordinate, ghostYCoordinate)){
            canMoveLeft=true;
        }
        
        if(!wallCollsionChecker.isOccupiedByWallMovingDown(ghostXCoordinate, ghostYCoordinate)){
            canMoveDown=true;
        }
        
        if(!wallCollsionChecker.isOccupiedByWallMovingRight(ghostXCoordinate, ghostYCoordinate)){
            canMoveRight=true;
        }
        
        if((canMoveLeft&&canMoveRight)&&(canMoveUp||canMoveDown)){
            isIntersection=true;
        }
        
        if((canMoveUp&&canMoveDown)&&(canMoveLeft||canMoveRight)){
            isIntersection=true;
        }
        
        movementArray.put(directionCheck.canMoveUp,canMoveUp);
        movementArray.put(directionCheck.canMoveLeft,canMoveLeft);
        movementArray.put(directionCheck.canMoveDown,canMoveDown);
        movementArray.put(directionCheck.canMoveRight,canMoveRight);
        movementArray.put(directionCheck.isIntersection,isIntersection);
        
        return movementArray;
        
    }
    
    
}
