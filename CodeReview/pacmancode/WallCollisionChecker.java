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
    
    
    public WallCollisionChecker(int pacmanOrGhost){
        coordinatesArray=wallCoordinates.getWallCoords();
        if(pacmanOrGhost==0){
            sizeOfCharacter=Pacman.SIZE;
        }
        else if(pacmanOrGhost==1){
            sizeOfCharacter=Ghost.SIZE;
        }
        sizeOfWalls=16;
    }
    
    
    
    
    public boolean isOccupiedByWallMovingLeft(double xCoordinate, double yCoordinate){
        int loopCounter;
        
        for(loopCounter=0;loopCounter<coordinatesArray.size();loopCounter++){
            if(xCoordinate-sizeOfWalls==coordinatesArray.get(loopCounter).getXCoordinate()){
                if(yCoordinate+sizeOfCharacter>coordinatesArray.get(loopCounter).getYCoordinate()&&
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
            if(xCoordinate+sizeOfCharacter==coordinatesArray.get(loopCounter).getXCoordinate()){
                if(yCoordinate+sizeOfCharacter>coordinatesArray.get(loopCounter).getYCoordinate()&&
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
                if(xCoordinate+sizeOfCharacter>coordinatesArray.get(loopCounter).getXCoordinate()&&
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
            if(yCoordinate+sizeOfCharacter==coordinatesArray.get(loopCounter).getYCoordinate()){
                if(xCoordinate+sizeOfCharacter>coordinatesArray.get(loopCounter).getXCoordinate()&&
                        xCoordinate<coordinatesArray.get(loopCounter).getXCoordinate()+
                        coordinatesArray.get(loopCounter).getWidth()){
                    return true;
                }
            }
        }
        return false; 
    }
    
    
 
    
}