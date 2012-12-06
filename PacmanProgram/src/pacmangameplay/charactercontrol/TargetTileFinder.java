/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.charactercontrol;

import pacmangameplay.mazedisplay.Maze;

/**
 * Finds the target coordinates of the Ghosts in all modes.
 * @author Kevin
 */
public class TargetTileFinder {
    
    ShortestDistanceFinder distance;
    double sizeOfTiles=Maze.sizeOfTiles;
    
    public TargetTileFinder(Maze maze){
        this.distance = new ShortestDistanceFinder(maze);
    }
    
    /**
     * Returns Blinky's Chase target coordinates.
     * He targets Pacman's current tile.
     * @param pacmanX Pacman's x coordinate
     * @param pacmanY Pacman's y coordinate
     * @return 
     */
    public double[] getBlinkyChaseTarget(double pacmanX, double pacmanY){
        double[] position = new double[2];
        
        position[0]=pacmanX;
        position[1]=pacmanY;
        
        return position;
        
    }
    
    /**
     * Returns Pinky's Chase target coordinates.
     * He targets Pacman's current tile + 4 tiles up + 4 tiles left.
     * @param pacmanX Pacman's x coordinate
     * @param pacmanY Pacman's y coordinate
     * @param pacVelX Pacman's x velocity
     * @param pacVelY Pacman's y velocity
     * @return 
     */
    public double[] getPinkyChaseTarget(double pacmanX, double pacmanY, double pacVelX, double pacVelY){
            double x=pacmanX+sizeOfTiles*4;
            double y=pacmanY+sizeOfTiles*4;
            double[] position = new double[2];
            
            if(pacVelX>0){
                x=pacmanX+sizeOfTiles*4;
                y=pacmanY;
            }
            if(pacVelX<0){
                x=pacmanX-sizeOfTiles*4;
                y=pacmanY;
            }
            if(pacVelY>0){
                x=pacmanX;
                y=pacmanY+sizeOfTiles*4;
            }
            if(pacVelY<0){
                x=pacmanX-sizeOfTiles*4;
                y=pacmanY-sizeOfTiles*4;
            }
            
            position[0]=x;
            position[1]=y;
            
            return position;
        }

    /**
     * Returns Inky's Chase target coordinates.
     * He needs Pac-Man's current tile/orientation and Blinky's current tile to calculate his final target. 
     * To determine Inky's target, we must first establish an intermediate offset two tiles in front of Pac-Man in the direction he is moving. 
     * Now imagine drawing a vector from the center of the red ghost's current tile to the center of the offset tile, then double the vector length by extending it out just as far again beyond the offset tile. 
     * That new tile that the vector lands on is Inky's chase target tile.
     * @param pacmanX Pacman's x coordinate
     * @param pacmanY Pacman's y coordinate
     * @param pacVelX Pacman's x velocity
     * @param pacVelY Pacman's y velocity
     * @param blinkyX Blinky's x coordinate
     * @param blinkyY Blinky's y coordinate
     * @return 
     */
    public double[] getInkyChaseTarget(double pacmanX, double pacmanY, double pacVelX, double pacVelY, double blinkyX, double blinkyY){
        
        double offsetX;
        double offsetY;
        double xLine;
        double yLine;
        double x=pacmanX+(sizeOfTiles*2);
        double y=pacmanY+sizeOfTiles*2;
        double[] position = new double[2];
        
        if(pacVelX>0){
            offsetX=pacmanX+(sizeOfTiles*2);
            offsetY=pacmanY;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }
 
        if(pacVelX<0){
            offsetX=pacmanX-(sizeOfTiles*2);
            offsetY=pacmanY;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }

        if(pacVelY>0){
            offsetX=pacmanX;
            offsetY=pacmanY+(sizeOfTiles*2);
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        } 
        
        if(pacVelY<0){
            offsetX=pacmanX-(sizeOfTiles*2);
            offsetY=pacmanY-(sizeOfTiles*2);
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }
        
        if(pacVelY==0&&pacVelX==0){
            xLine=x-blinkyX;
            yLine=y-blinkyY;
            x=x+xLine;
            y=y+yLine;
        }
        
        position[0]=x;
        position[1]=y;
        
        return position;
        
    }
    
    /**
     * Returns Clyde's Chase target coordinates.
     * If the distance between his current tile and Pacman's current tile is eight tiles or more, then he targets Pacman's current tile.
     * Otherwise, he switches his target tile to the scatter target and heads back to his corner.
     * @param pacmanX Pacman's x coordinate
     * @param pacmanY Pacman's y coordinate
     * @param clydeX Clyde's x coordinate
     * @param clydeY Clyde's y coordinate
     * @return 
     */
    public double[] getClydeChaseTarget(double pacmanX, double pacmanY, double clydeX, double clydeY){
        
        double[] position = new double[2];
        
        if(distance.calculateDistance(clydeX, clydeY, pacmanX, pacmanY)<sizeOfTiles*8){
            position=getClydeScatterTarget();
        }
        
        else{
            position[0]=pacmanX;
            position[1]=pacmanY;
        }
        
        return position;
        
        
    }
    
    /**
     * Returns Blinky's Scatter target coordinates. 
     * @return top right corner of the maze 
     */
    public double[] getBlinkyScatterTarget(){
        double[] position = new double[2];
        position[0]=400.0;
        position[1]=0.0;
        return position;
    }
    
    
    /**
     * Returns Pinky's Scatter target coordinates.
     * @return top left corner of the maze
     */
    public double[] getPinkyScatterTarget(){
        double[] position = new double[2];
        position[0]=48.0;
        position[1]=0.0;
        return position;
    }
    
    /**
     * Returns Inky's Scatter target coordinates.
     * @return bottom right corner of the maze
     */
    public double[] getInkyScatterTarget(){
        double[] position = new double[2];
        position[0]=432.0;
        position[1]=544.0;
        return position;
    }
    
    /**
     * Returns Clyde's Scatter target coordinates.
     * @return bottom left corner of the maze
     */
    public double[] getClydeScatterTarget(){
        double[] position = new double[2];
        position[0]=0.0;
        position[1]=544.0;
        return position;
    }
    
    /**
     * Returns the target coordinates for returning to the ghost pen.
     * @return point right outside the pen door
     */
    public double[] getReturnToHomeTarget(){
        double[] position = new double[2];
        position[0]=208;
        position[1]=224;
        return position;
    }
    
    /**
     * Returns the target coordinates for exiting the ghost pen.
     * @return point right outside the pen door
     */
    public double[] getLeaveHomeTarget(){
        double[] position = new double[2];
        position[0]=208;
        position[1]=224;
        return position;
    }
}
