/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *
 * @author Kevin
 */


public class Pacman{
    PacmanControl pacControl = new PacmanControl();
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
        mode = 2;
        pacControl.getPosition(position);
        currentTileIndex = maze.getIndex(position[0], position[1]);
        prevDirection = 0;
    }
    
    public void draw(Graphics g){
        Graphics2D pac = (Graphics2D) g;
        pac.setColor(Color.YELLOW);
        pac.fill(new Ellipse2D.Double(position[0], position[1], DIMENSIONS[0], DIMENSIONS[1]));
        
        
        pac.setColor(Color.BLACK);
        int direction = pacControl.getDirection();
        if(direction == 1){ //up
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0]+4, position[0]+11}, new double[] {position[1]+7.5, position[1]+15, position[1]+15}));
            //System.out.println("u");
        }
        else if(direction == 2){ //down
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0]+4, position[0]+11}, new double[] {position[1]+7.5, position[1], position[1]}));
            //System.out.println("d");
        }
        else if (direction == 3){ //right
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0]+15, position[0]+15}, new double[] {position[1]+7.5, position[1]+4, position[1]+11}));
            //System.out.println("r");
        }
        else if(direction == 4){ //left
            pac.fill(buildTriangle(new double[] {position[0]+7.5, position[0], position[0]}, new double[] {position[1]+7.5, position[1]+4, position[1]+11}));
            //System.out.println("d");
        }
    }
    
    public void refresh(ActionEvent e){ 
       pacControl.refresh(e);
       pacControl.getPosition(position);
       currentTileIndex = maze.getIndex(position[0], position[1]);
       setMode(mode);
       setSpeed();
       maze.changeType(currentTileIndex,2,1);
    }
    
    public void resetPosition(){
        pacControl.resetPosition();
    }
    
    public void assignSpeeds(double norm, double dot, double fright, double frightDot){
        normSpeed = MAX_SPEED*norm;
        dotSpeed = MAX_SPEED*dot;
        frightSpeed = MAX_SPEED*fright;
        frightDotSpeed = MAX_SPEED*frightDot;
    }
    

    public void controlPacman(KeyEvent ke){
        pacControl.control(ke);
    }
    
    public int getCurrentTileIndex(){
        return currentTileIndex;
    }  
    
    public void setMode(int mode){
        this.mode = mode;
    }

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
    
    
    public int getLives() {
        return lives;
    }
    
    public double getX(){
        return position[0];
    }
    public double getY(){
        return position[1];
    }

    public void loseLife() {
        lives--;
    }

    public void resetLives(){
       lives = 3;
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