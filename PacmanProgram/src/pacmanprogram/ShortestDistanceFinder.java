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

    WallCollisionChecker wallCollsionChecker = new WallCollisionChecker(1);
    Walls walls = new Walls();
    double sizeOfGhost=Ghost.SIZE;
    double sizeOfTiles=Walls.sizeOfTiles;
    
    public ShortestDistanceFinder(){
    }
    
    
    public int chooseNextTileHelper(double xCoordinate, double yCoordinate, double targetXCoordinate, double targetYCoordinate, double velx, double vely){
        
        double nextTileX = xCoordinate;
        double nextTileY = yCoordinate;
        
        Double upTileDistance = Double.MAX_VALUE;
        Double leftTileDistance = Double.MAX_VALUE;
        Double downTileDistance = Double.MAX_VALUE;
        Double rightTileDistance = Double.MAX_VALUE;
        
        double minimum;
        
        ArrayList<Double> distanceArray = new ArrayList<Double>();
        
        

        if(!wallCollsionChecker.isOccupiedByWallMovingUp(xCoordinate, yCoordinate)&&vely<=0){
            nextTileY = getUpTile(yCoordinate);
            nextTileX = xCoordinate;
            upTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(upTileDistance);
        }
        

        if(!wallCollsionChecker.isOccupiedByWallMovingLeft(xCoordinate, yCoordinate)&&velx<=0){
            nextTileX = getLeftTile(xCoordinate);
            nextTileY = yCoordinate;
            leftTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(leftTileDistance);
        }
        
        
        if(!wallCollsionChecker.isOccupiedByWallMovingDown(xCoordinate, yCoordinate)&&vely>=0){
            nextTileY = getDownTile(yCoordinate);
            nextTileX = xCoordinate;
            downTileDistance = calculateDistance(nextTileX, nextTileY, targetXCoordinate, targetYCoordinate);
            distanceArray.add(downTileDistance);
        }
                
        
        if(!wallCollsionChecker.isOccupiedByWallMovingRight(xCoordinate, yCoordinate)&&velx>=0){
            nextTileX = getRightTile(xCoordinate);
            nextTileY = yCoordinate;
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
    
    
    
    
    public double[] chooseNextRandomTile(double ghostXCoordinate, double ghostYCoordinate, double velx, double vely){
        
        int randomNum;
        double xNextTile,yNextTile,nextDirection;
        double[] nextTileCoordinates = new double[2];
        double[] directionAndCoordinates = new double[3];
        HashMap<directionCheck,Boolean> movementArray = new HashMap<directionCheck,Boolean>();
        
        nextTileCoordinates=nextTileInDirectionOfVelocity(ghostXCoordinate,ghostYCoordinate,velx,vely);
        
        xNextTile=nextTileCoordinates[0];
        yNextTile=nextTileCoordinates[1];
        
        movementArray=isIntersection(xNextTile,yNextTile);
        
        if(movementArray.get(directionCheck.isIntersection)){
            Random randomGenerator = new Random(System.currentTimeMillis());
            randomNum = randomGenerator.nextInt(4)+1;
        }
        
        else{
            randomNum=1;
        }
        
        nextDirection=chooseNextRandomTileHelper(movementArray,randomNum,velx,vely);
        
        directionAndCoordinates[0]=nextDirection;
        directionAndCoordinates[1]=xNextTile;
        directionAndCoordinates[2]=yNextTile;
        
        return directionAndCoordinates; 
   
    }
    
    
    
    public HashMap<directionCheck,Boolean> isIntersection(double ghostXCoordinate, double ghostYCoordinate){
        boolean canMoveLeft=false;
        boolean canMoveRight=false;
        boolean canMoveUp=false;
        boolean canMoveDown=false;
        boolean isIntersection=false;
        HashMap<directionCheck,Boolean> movementArray = new HashMap<directionCheck,Boolean>();

        if(!wallCollsionChecker.isOccupiedByWallMovingUp(ghostXCoordinate, ghostYCoordinate-(sizeOfTiles-sizeOfGhost))){
            canMoveUp=true;
        }
        
        if(!wallCollsionChecker.isOccupiedByWallMovingLeft(ghostXCoordinate-(sizeOfTiles-sizeOfGhost), ghostYCoordinate)){
            canMoveLeft=true;
        }
        
        if(!wallCollsionChecker.isOccupiedByWallMovingDown(ghostXCoordinate, ghostYCoordinate+(sizeOfTiles-sizeOfGhost))){
            canMoveDown=true;
        }
        
        if(!wallCollsionChecker.isOccupiedByWallMovingRight(ghostXCoordinate+(sizeOfTiles-sizeOfGhost), ghostYCoordinate)){
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
    
    
    public double chooseNextRandomTileHelper(HashMap<directionCheck,Boolean> movementArray, int randomNum, double velx, double vely){
        int loopCounter;
        
        for(loopCounter=0;loopCounter<4;loopCounter++){
                if(randomNum==1&&movementArray.get(directionCheck.canMoveUp)&&vely<=0){
                    return 1;
                }
       
                if(randomNum==2&&movementArray.get(directionCheck.canMoveLeft)&&velx<=0){
                    return 2;
                }        
        
                if(randomNum==3&&movementArray.get(directionCheck.canMoveDown)&&vely>=0){
                    return 3;
                }
        
                if(randomNum==4&&movementArray.get(directionCheck.canMoveRight)&&velx>=0){
                    return 4;
                }
            
                if(randomNum==4){
                    randomNum=1;
                }
                else{
                    randomNum++;
                }
            }

        return 0;
    }
    
    
    public double[] chooseNextTile(double ghostXCoordinate, double ghostYCoordinate, double targetXCoordinate, double targetYCoordinate, double velx, double vely){
        

        double nextDirection;
        double xNextTile, yNextTile;
        double[] nextTileCoordinates = new double[2];
        double[] directionAndCoordinates = new double[3];
        
        if(velx==0&&vely==0){
            nextDirection=chooseNextTileHelper(ghostXCoordinate,ghostYCoordinate,targetXCoordinate,targetYCoordinate,velx,vely);
            
            directionAndCoordinates[0]=nextDirection;
            directionAndCoordinates[1]=ghostXCoordinate;
            directionAndCoordinates[2]=ghostYCoordinate;            
        }
        
        else{
            nextTileCoordinates=nextTileInDirectionOfVelocity(ghostXCoordinate,ghostYCoordinate,velx,vely);
        
            xNextTile=nextTileCoordinates[0];
            yNextTile=nextTileCoordinates[1];
        
            nextDirection=chooseNextTileHelper(xNextTile,yNextTile,targetXCoordinate,targetYCoordinate,velx,vely);
            
            directionAndCoordinates[0]=nextDirection;
            directionAndCoordinates[1]=xNextTile;
            directionAndCoordinates[2]=yNextTile;
        }

        return directionAndCoordinates;      
    }
    
    
    public double[] landOnTileEdge(double ghostXCoordinate, double ghostYCoordinate, double xTile, double yTile, double velx, double vely){
        
        double xDistanceFromEdge, yDistanceFromEdge;
        double[] velocity = new double[2];
        velocity[0]=0;
        velocity[1]=0;
        
        xDistanceFromEdge=xTile-ghostXCoordinate;
        yDistanceFromEdge=yTile-ghostYCoordinate;
        
        if(velx>0){
            if(xDistanceFromEdge>=0&&xDistanceFromEdge<velx){
                velocity[0]=xDistanceFromEdge;
            }
            else{
                velocity[0]=velx;
            }
        }
        
        if(velx<0){
            if(xDistanceFromEdge<=0&&xDistanceFromEdge>velx){
                velocity[0]=xDistanceFromEdge;
            }
            else{
                velocity[0]=velx;
            }
        }
        
        if(vely>0){
            if(yDistanceFromEdge>=0&&yDistanceFromEdge<vely){
                velocity[1]=yDistanceFromEdge;
            }
            else{
                velocity[1]=vely;
            }
        }
        
        if(vely<0){
            if(yDistanceFromEdge<=0&&yDistanceFromEdge>vely){
                velocity[1]=yDistanceFromEdge;
            }
            else{
                velocity[1]=vely;
            }
        }
        
        return velocity;
    }
    
    
    
    public double[] nextTileInDirectionOfVelocity(double ghostXCoordinate, double ghostYCoordinate, double velx, double vely){
        
        int intMultiplier;
        double xCurrentDirection = 0; 
        double yCurrentDirection = 0;
        double xNextTile=ghostXCoordinate;
        double yNextTile=ghostYCoordinate;
        double[] nextTileCoordinates = new double[2];
        
        
        if(velx!=0){
            xCurrentDirection=velx/(Math.abs(velx));
        }
        
        if(vely!=0){
            yCurrentDirection=vely/(Math.abs(vely));
        }
        
        intMultiplier = (int)((ghostXCoordinate+(sizeOfTiles*xCurrentDirection))/16);
        xNextTile = sizeOfTiles * intMultiplier;
        
        intMultiplier = (int)((ghostYCoordinate+(sizeOfTiles*yCurrentDirection))/16);
        yNextTile = sizeOfTiles * intMultiplier;
        
        nextTileCoordinates[0]=xNextTile;
        nextTileCoordinates[1]=yNextTile;
        
        return nextTileCoordinates;
        
    }
    
}



        

    



