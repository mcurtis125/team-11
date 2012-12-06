/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.charactercontrol;

import java.util.ArrayList;
import pacmangameplay.mazedisplay.Maze;
import pacmangameplay.mazedisplay.Tile;

/**
 * Prevents any character from going through walls.
 * @author Kevin
 */
public class WallCollisionChecker {
    
    ArrayList<Tile> wallCoordinatesArray = new ArrayList<Tile>();
    ArrayList<Tile> ghostPenDoor = new ArrayList<Tile>();
    

    
    
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
    
    
    public WallCollisionChecker(int pacmanOrGhost, Maze maze){
        wallCoordinatesArray=maze.getWallCoords();
        if(pacmanOrGhost==0){
            sizeOfCharacter=Pacman.SIZE;
        }
        else if(pacmanOrGhost==1){
            sizeOfCharacter=Ghost.SIZE;
        }
        sizeOfWalls=Maze.sizeOfTiles;
        
        ghostPenDoor.add(new Tile(208,240,sizeOfWalls,sizeOfWalls,1));
        ghostPenDoor.add(new Tile(224,240,sizeOfWalls,sizeOfWalls,1));
    }
    
    
    
    /**
     * Checks if a character is moving left and has a wall on its left.
     * @param xCoordinate 
     * @param yCoordinate
     * @return 
     */
    public boolean isOccupiedByWallMovingLeft(double xCoordinate, double yCoordinate){
        
        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            topEdgeOfCharacter=yCoordinate;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            
            if(leftEdgeOfCharacter>=leftEdgeOfWall&&leftEdgeOfCharacter<=rightEdgeOfWall){
                if(bottomEdgeOfCharacter>topEdgeOfWall&&topEdgeOfCharacter<bottomEdgeOfWall){
                    return true;
                }
            }   
        }
        
        return false;
    }


    /**
     * Checks if a character is moving right and has a wall on its right.
     * @param xCoordinate 
     * @param yCoordinate
     * @return 
     */
    public boolean isOccupiedByWallMovingRight(double xCoordinate, double yCoordinate){
        
        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
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
    
    /**
     * Checks if a character is moving up and has a wall on its up.
     * @param xCoordinate 
     * @param yCoordinate
     * @return 
     */
    public boolean isOccupiedByWallMovingUp(double xCoordinate, double yCoordinate){
        

        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
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
    
    
    /**
     * Checks if a character is moving down and has a wall on its down.
     * @param xCoordinate 
     * @param yCoordinate
     * @return 
     */
    public boolean isOccupiedByWallMovingDown(double xCoordinate, double yCoordinate){
        
        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;

            if(bottomEdgeOfCharacter>=topEdgeOfWall&&bottomEdgeOfCharacter<=bottomEdgeOfWall){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return true;
                }
            }
        }
        
        for(loopCounter=0;loopCounter<ghostPenDoor.size();loopCounter++){
            
            leftEdgeOfWall=ghostPenDoor.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=ghostPenDoor.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=ghostPenDoor.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=ghostPenDoor.get(loopCounter).getYCoordinate()+sizeOfWalls;
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
    
    /**
     * Tells a character how many pixels to move in the next time frame without entering a wall while moving left.
     * @param xCoordinate current x position
     * @param yCoordinate current y position
     * @param velx x velocity
     * @return 
     */
    public double leftCollisionInAdvance(double xCoordinate, double yCoordinate, double velx){
        
        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){
             
            rightEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            topEdgeOfCharacter=yCoordinate;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            distanceFromWall=rightEdgeOfWall-leftEdgeOfCharacter;
            
            if(distanceFromWall<=0&&distanceFromWall>velx){
                if(bottomEdgeOfCharacter>topEdgeOfWall&&topEdgeOfCharacter<bottomEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }
        
        return velx;
    }
    
    
    /**
     * Tells a character how many pixels to move in the next time frame without entering a wall while moving right.
     * @param xCoordinate current x position
     * @param yCoordinate current y position
     * @param velx x velocity
     * @return 
     */
    public double rightCollisionInAdvance(double xCoordinate, double yCoordinate, double velx){
        
        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate();
            topEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate();
            bottomEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            topEdgeOfCharacter=yCoordinate;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            distanceFromWall=leftEdgeOfWall-rightEdgeOfCharacter;
            
            if(distanceFromWall>=0&&distanceFromWall<velx){
                if(bottomEdgeOfCharacter>topEdgeOfWall&&topEdgeOfCharacter<bottomEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }
        
        return velx;
    }
    
    
    /**
     * Tells a character how many pixels to move in the next time frame without entering a wall while moving up.
     * @param xCoordinate current x position
     * @param yCoordinate current y position
     * @param velx x velocity
     * @return 
     */
    public double upCollisionInAdvance(double xCoordinate, double yCoordinate, double vely){
        
        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){
            
            leftEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            bottomEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate()+sizeOfWalls;
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            topEdgeOfCharacter=yCoordinate;
            distanceFromWall=bottomEdgeOfWall-topEdgeOfCharacter;
            
            if(distanceFromWall<=0&&distanceFromWall>vely){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }
        
        return vely;
        
    }
    
    /**
     * Tells a character how many pixels to move in the next time frame without entering a wall while moving down.
     * @param xCoordinate current x position
     * @param yCoordinate current y position
     * @param velx x velocity
     * @return 
     */
    public double downCollisionInAdvance(double xCoordinate, double yCoordinate, double vely){

        for(loopCounter=0;loopCounter<wallCoordinatesArray.size();loopCounter++){

            leftEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=wallCoordinatesArray.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=wallCoordinatesArray.get(loopCounter).getYCoordinate();
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            distanceFromWall=topEdgeOfWall-bottomEdgeOfCharacter;

            if(distanceFromWall>=0&&distanceFromWall<vely){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return distanceFromWall;
                }
            }
        }

        for(loopCounter=0;loopCounter<ghostPenDoor.size();loopCounter++){
            leftEdgeOfWall=ghostPenDoor.get(loopCounter).getXCoordinate();
            rightEdgeOfWall=ghostPenDoor.get(loopCounter).getXCoordinate()+sizeOfWalls;
            topEdgeOfWall=ghostPenDoor.get(loopCounter).getYCoordinate();
            leftEdgeOfCharacter=xCoordinate;
            rightEdgeOfCharacter = xCoordinate+sizeOfCharacter;
            bottomEdgeOfCharacter=yCoordinate+sizeOfCharacter;
            distanceFromWall=topEdgeOfWall-bottomEdgeOfCharacter;

            if(distanceFromWall>=0&&distanceFromWall<vely){
                if(rightEdgeOfCharacter>leftEdgeOfWall&&leftEdgeOfCharacter<rightEdgeOfWall){
                    return distanceFromWall;
                }
            } 
        }


        return vely;

    }
     
}