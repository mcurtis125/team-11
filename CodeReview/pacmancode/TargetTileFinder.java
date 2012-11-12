/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 *
 * @author Kevin
 */

/** this class will let the four ghosts find the tile they are targeting
* 	therefore they can decide their moving direction and path
* 	Note that the return value of all following methods in this class is an array of two doubles
* 	which represents a tile that the ghost is aiming.
*/

public class TargetTileFinder {
    
    private static double pacmanSpeed = 1;
    
    static ShortestDistanceFinder distance = new ShortestDistanceFinder();
    
    public TargetTileFinder(){
        
    }
    
   
    
    
    /** This is the method of calculating Blinky's target tile;
    * 	As stated in Pacman Dossir:
    * 
    * 	 "Blinky's is the most simple and direct, using Pac-Man's current tile as his target." 
    * 
    * 	The pacman's coordinates are input by the variables of this method.
    */
    
    public static double[] getBlinkyChaseTarget(double pacmanX, double pacmanY){
        double[] position = new double[2];
        
        position[0]=pacmanX;
        position[1]=pacmanY;
        
        return position;
        
    }
    
    
    
    /** This is the method of calculating Pinky's target tile;
    * 	As stated in Pacman Dossir:
    * 
    * 	 "Pinky behaves as he does because he does not target Pac-Man's tile directly,
    *   Instead, he selects an offset four tiles away from Pac-Man in the direction 
    * 	Pac-Man is currently moving (with one exception)."
    * 
    * 
	*	Therefore we need to know the direction of the PACMAN,
	* 	which is indicated by two variables of this function: PacVelX, PalVelY;
	* 	After deciding the direction,
	* 	shift the PACMAN's coordinates by 64,which is the size of 4 tiles,
	* 	and this tile is Pinky's Target Tile.
	* 
    */
    
    public static double[] getPinkyChaseTarget(double pacmanX, double pacmanY, double pacVelX, double pacVelY){
            double x=pacmanX+64;
            double y=pacmanY+64;
            double[] position = new double[2];
            
            if(pacVelX>0){
                x=pacmanX+64;
                y=pacmanY;
            }
            if(pacVelX<0){
                x=pacmanX-64;
                y=pacmanY;
            }
            if(pacVelY>0){
                x=pacmanX;
                y=pacmanY+64;
            }
            if(pacVelY<0){
                x=pacmanX-64;
                y=pacmanY-64;
            }
            
            position[0]=x;
            position[1]=y;
            
            return position;
        }

    
    /**	This is the method of calculating Inky's target tile;
     * 	As stated in Pacman Dossir:
     * 
     *	 "Inky uses the most complex targeting scheme of the four ghosts in chase mode
     * 	He needs Pac-Man's current tile/orientation and Blinky's current tile to calculate his final target
     *	To determine Inky's target, we must first establish an intermediate offset two tiles 
     * 	in front of Pac-Man in the direction he is moving (represented by the tile bracketed in green above)
     * 	Drawing a vector from the center of the red ghost's current tile to the center of the offset tile, 
     * 	then double the vector length by extending it out just as far again beyond the offset tile."
     * 
     *  We need to know the direction of the PACMAN, Blinky's coordinates,
	 * 	which is input from the variables in getInkyChaseTarget()
	 * 	After deciding the direction,
	 * 	shift the PACMAN's coordinates by 32,which is the size of 2 tiles, 
	 * 	and shift this new tile coordinates again by Blinky's position to get Inky's target tile.
	 * 	
     */
    
    public static double[] getInkyChaseTarget(double pacmanX, double pacmanY, double pacVelX, double pacVelY, double blinkyX, double blinkyY){
        
        double offsetX;
        double offsetY;
        double xLine;
        double yLine;
        double x=pacmanX+32;
        double y=pacmanY+32;
        double[] position = new double[2];
        
        if(pacVelX>0){
            offsetX=pacmanX+32;
            offsetY=pacmanY;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }
 
        if(pacVelX<0){
            offsetX=pacmanX-32;
            offsetY=pacmanY;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }

        if(pacVelY>0){
            offsetX=pacmanX;
            offsetY=pacmanY+32;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        } 
        
        if(pacVelY<0){
            offsetX=pacmanX-32;
            offsetY=pacmanY-32;
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
    
    
    /** This is the method of calculating Clyde's target tile;
     * 	As stated in Pacman Dossir:
     * 
     * 	 "Clyde is the last ghost to leave the pen and tends to separate himself from the other ghosts 
     * 	by shying away from Pac-Man and doing his own thing when he isn't patrolling his corner of the maze."
     * 
        * 	We set that if the distance between PACMAN and Clyde is smaller than 128, then Make Clyde into scatter mode;
        *  Otherwise, make the PACMAN's current tile as his target tile.
     */
    
    public static double[] getClydeChaseTarget(double pacmanX, double pacmanY, double clydeX, double clydeY){
        
        double[] position = new double[2];
        
        if(distance.calculateDistance(clydeX, clydeY, pacmanX, pacmanY)<128.0){
            position=getClydeScatterTarget();
        }
        
        else{
            position[0]=pacmanX;
            position[1]=pacmanY;
        }
        
        return position;
        
        
    }
    
    
    /** This is the method of calculating Blinky's target tile while in Scatter Mode;
     * 	As stated in Pacman Dossir:
     * 
     *	 "Whenever the ghosts scatter to the corners of the maze, 
     * 	for example, each ghost is striving to reach a fixed target tile located somewhere near its home corner." 
     *  
     * 	In this point, we choose a tile near bottom-right corner,(400,0), to be Blink's target tile in scatter mode.
     */
    
    public static double[] getBlinkyScatterTarget(){
        double[] position = new double[2];
        position[0]=400.0;
        position[1]=0.0;
        return position;
    }
    
    
    /** This is the method of calculating Blinky's target tile while in Scatter Mode;
     * 	As stated in Pacman Dossir:
     * 
     *	 "Whenever the ghosts scatter to the corners of the maze, 
     * 	for example, each ghost is striving to reach a fixed target tile located somewhere near its home corner." 
     *  
     * 	In this point, we choose a tile near bottom-left corner,(48,0), to be Blink's target tile in scatter mode.
     */
    
    public static double[] getPinkyScatterTarget(){
        double[] position = new double[2];
        position[0]=48.0;
        position[1]=0.0;
        return position;
    }
    
   /** This is the method of calculating Inky's target tile while in Scatter Mode;
     * 	As stated in Pacman Dossir:
     * 
     *	 "Whenever the ghosts scatter to the corners of the maze, 
     * 	for example, each ghost is striving to reach a fixed target tile located somewhere near its home corner." 
     *  
     * 	In this point, we choose a tile near top-right corner,(432,544), to be Inky's target tile in scatter mode.
     */
    public static double[] getInkyScatterTarget(){
        double[] position = new double[2];
        position[0]=432.0;
        position[1]=544.0;
        return position;
    }
    
    /** This is the method of calculating Clyde's target tile while in Scatter Mode;
     * 	As stated in Pacman Dossir:
     * 
     *	 "Whenever the ghosts scatter to the corners of the maze, 
     * 	for example, each ghost is striving to reach a fixed target tile located somewhere near its home corner." 
     *  
     * 	In this point, we choose a tile near top-left corner,(0,544), to be Clyde's target tile in scatter mode.
     */
    public static double[] getClydeScatterTarget(){
        double[] position = new double[2];
        position[0]=0.0;
        position[1]=544.0;
        return position;
    }
    
}
