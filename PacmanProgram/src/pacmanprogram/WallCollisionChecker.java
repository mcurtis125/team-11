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
public class WallCollisionChecker {
    
    ArrayList<Tiles> coordinatesArray = new ArrayList<Tiles>();
    Walls wallCoordinates = new Walls();
    double sizeOfCharacter, sizeOfWalls;
    
        int loopCounter;
        double leftEdgeOfWall;
        double rightEdgeOfWall;
        double topEdgeOfWall;
        double bottomEdgeOfWall;
        double leftEdgeOfCharacter;
        double rightEdgeOfCharacter;
        double topEdgeOfCharacter;
        double bottomEdgeOfCharacter;
        double distanceFromWall;
    
    
    public WallCollisionChecker(int pacmanOrGhost){
        coordinatesArray=wallCoordinates.getWallCoords();
        if(pacmanOrGhost==0){
            sizeOfCharacter=Pacman.SIZE;
        }
        else if(pacmanOrGhost==1){
            sizeOfCharacter=Ghost.SIZE;
        }
        sizeOfWalls=Walls.sizeOfTiles;
    }
    
    
    
    
    public boolean isOccupiedByWallMovingLeft(double xCoordinate, double yCoordinate){
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            topEdgeOfCharacter=yCoordinate;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            
            if(leftEdgeOfCharacter>=leftEdgeOfWall&&xCoordinate<=rightEdgeOfWall){
                if(bottomEdgeOfCharacter>topEdgeOfWall&&topEdgeOfCharacter<bottomEdgeOfWall){
                    return true;
                }
            }   
        }
        
        return false;
    }



    public boolean isOccupiedByWallMovingRight(double xCoordinate, double yCoordinate){
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            topEdgeOfCharacter=yCoordinate;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            
            if(rightEdgeOfCharacter>=leftEdgeOfWall&&rightEdgeOfCharacter<=rightEdgeOfWall){
                if(bottomEdgeOfCharacter>topEdgeOfWall&&topEdgeOfCharacter<bottomEdgeOfWall){
                    return true;
                }
            }

        }
        
        return false;
    }
    
    
    public boolean isOccupiedByWallMovingUp(double xCoordinate, double yCoordinate){
        

        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            topEdgeOfCharacter=yCoordinate;
            
            if(topEdgeOfCharacter>=topEdgeOfWall&&topEdgeOfCharacter<=bottomEdgeOfWall){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return true;
                }
            }
        }
        return false; 
    }
    
    
    
    public boolean isOccupiedByWallMovingDown(double xCoordinate, double yCoordinate){
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;

            if(bottomEdgeOfCharacter>=topEdgeOfWall&&bottomEdgeOfCharacter<=bottomEdgeOfWall){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return true;
                }
            }
        }
        return false; 
    }
    
    
    public double leftCollisionInAdvance(double xCoordinate, double yCoordinate, double velx){
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
             
            rightEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            topEdgeOfCharacter=yCoordinate;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            distanceFromWall=rightEdgeOfWall-leftEdgeOfCharacter;
            
            if(distanceFromWall<0&&distanceFromWall>velx){
                if(bottomEdgeOfCharacter>topEdgeOfWall&&topEdgeOfCharacter<bottomEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }
        
        return velx;
    }
    
    
    
    public double rightCollisionInAdvance(double xCoordinate, double yCoordinate, double velx){
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate();
            topEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            topEdgeOfCharacter=yCoordinate;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            distanceFromWall=leftEdgeOfWall-rightEdgeOfCharacter;
            
            if(distanceFromWall>0&&distanceFromWall<velx){
                if(bottomEdgeOfCharacter>topEdgeOfWall&&topEdgeOfCharacter<bottomEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }
        
        return velx;
    }
    
    
    
    public double upCollisionInAdvance(double xCoordinate, double yCoordinate, double vely){
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            bottomEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            topEdgeOfCharacter=yCoordinate;
            distanceFromWall=bottomEdgeOfWall-topEdgeOfCharacter;
            
            if(distanceFromWall<0&&distanceFromWall>vely){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }
        
        return vely;
        
    }
    
    
     public double downCollisionInAdvance(double xCoordinate, double yCoordinate, double vely){
         
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=coordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=coordinatesArray.get(loopCounter).getYCoordinate();
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            distanceFromWall=topEdgeOfWall-bottomEdgeOfCharacter;
            
            if(distanceFromWall>0&&distanceFromWall<vely){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }
        
        return vely;
        
    }
 
    
}