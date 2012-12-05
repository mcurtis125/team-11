/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.charactercontrol;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import pacmangameplay.mazedisplay.Maze;

/**
 * 
 * @author Kevin
 */


public class Pacman{
    PacmanControl pacControl;
    Maze maze;
    private static final double[] DIMENSIONS = {15,15};
    private static final double MAX_SPEED = 1;
    public static final double SIZE = 15;
    private double[] position = new double[2];
    private int currentTileIndex;
    private int prevDirection;
    private int lives = 3;
    private int mode;
   
    private double speed;
    private double normSpeed;
    private double dotSpeed;
    private double frightSpeed;
    private double frightDotSpeed;
    
    public Pacman(Maze maze){
        this.maze = maze;
        this.pacControl = new PacmanControl(maze);
        mode = 2;
        pacControl.getPosition(position);
        currentTileIndex = maze.getIndex(position[0], position[1]);
        prevDirection = 0;
    }
    
    /**
     * Paints pacman with the mouth in the right direction.
     * @param g 
     */
    public void draw(Graphics g){
        Graphics2D pac = (Graphics2D) g;
        pac.setColor(Color.YELLOW);
        pac.fill(new Ellipse2D.Double(position[0], position[1], DIMENSIONS[0], DIMENSIONS[1]));
        
        
        pac.setColor(Color.BLACK);
        int direction = pacControl.getDirection();
        if(direction == 1){ //down
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0]+4, position[0]+11}, new double[] {position[1]+7.5, position[1]+15, position[1]+15}));
            //System.out.println("d");
        }
        else if(direction == 2){ //up
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0]+4, position[0]+11}, new double[] {position[1]+7.5, position[1], position[1]}));
            //System.out.println("u");
        }
        else if (direction == 3){ //right
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0]+15, position[0]+15}, new double[] {position[1]+7.5, position[1]+4, position[1]+11}));
            //System.out.println("r");
        }
        else if(direction == 4){ //left
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0], position[0]}, new double[] {position[1]+7.5, position[1]+4, position[1]+11}));
            //System.out.println("l");
        }
    }
    
    /**
     * Updates Pacman's position, mode and speed, while changing dots to paths.
     * @param e 
     */
    public void refresh(ActionEvent e){ 
       pacControl.refresh(e);
       pacControl.getPosition(position);
       currentTileIndex = maze.getIndex(position[0], position[1]);
       setMode(mode);
       setSpeed();
       maze.changeType(currentTileIndex,2,1);
    }
    
    /**
     * Resets Pacman's position.
     */
    public void resetPosition(){
        pacControl.resetPosition();
    }
    
    /**
     * Assigns all the speeds needed in one level.
     * @param norm
     * @param dot
     * @param fright
     * @param frightDot 
     */
    public void assignSpeeds(double norm, double dot, double fright, double frightDot){
        normSpeed = MAX_SPEED*norm;
        dotSpeed = MAX_SPEED*dot;
        frightSpeed = MAX_SPEED*fright;
        frightDotSpeed = MAX_SPEED*frightDot;
    }

    /**
     * Registers player's keystroke to control Pacman.
     * @param ke 
     */
    public void controlPacman(KeyEvent ke){
        pacControl.control(ke);
    }
    
    /**
     * Returns Pacman's current tile index.
     * @return index in the maze array
     */
    public int getCurrentTileIndex(){
        return currentTileIndex;
    }  
    
    /**
     * Sets Pacman's mode to mode.
     * @param mode mode as integer
     */
    public void setMode(int mode){
        this.mode = mode;
    }
    
    /**
     * Returns the number of lives remaining.
     * @return 
     */
    public int getLives() {
        return lives;
    }
    
    /**
     * Returns Pacman's current x position.
     * @return x coord
     */
    public double getX(){
        return position[0];
    }
    
    /**
     * Returns Pacman's current y position.
     * @return y coord
     */
    public double getY(){
        return position[1];
    }

    /**
     * Decrements number of lives remaining.
     */
    public void loseLife() {
        lives--;
    }

    /**
     * Resets lives to 3.
     */
    public void resetLives(){
       lives = 3;
    }
    
    //Sets Pacman's speed according to the mode
    private void setSpeed() {
        if(mode==1||mode==2){
            if(maze.getType(getCurrentTileIndex())==2 || maze.getType(getCurrentTileIndex())==3){
                speed = dotSpeed;
            }
            else{
                speed = normSpeed;
            }
        }
        else if(mode==3){
            if(maze.getType(getCurrentTileIndex())==2 || maze.getType(getCurrentTileIndex())==3){
                speed = frightDotSpeed;
            }
            else{
                speed = frightSpeed;    
            }
        }
        pacControl.setPacmanSpeed(speed);
    }
    
    private GeneralPath buildTriangle(double[] pointsX, double[] pointsY){
        GeneralPath triangle = new GeneralPath(GeneralPath.WIND_EVEN_ODD,pointsX.length);
        triangle.moveTo(pointsX[0], pointsY[0]);
        int i;
        for(i=1;i<pointsX.length;i++){
            triangle.lineTo(pointsX[i], pointsY[i]);
        }
        triangle.closePath();
        return triangle;
    }
}