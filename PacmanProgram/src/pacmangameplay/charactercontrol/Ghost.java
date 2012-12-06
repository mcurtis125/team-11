package pacmangameplay.charactercontrol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import pacmangameplay.gamelogic.DotCounter;
import pacmangameplay.gamelogic.DotCounter.Type;
import pacmangameplay.mazedisplay.Maze;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Character Ghost in the game. Can either be Blinky (the red one), Pinky (the pink one), Inky (the blue one) or Clyde (the orange one).
 * @author stavy92
 */
public class Ghost {
    public enum Name{Blinky, Pinky, Inky, Clyde};
    Maze maze;
    private int[] penTileIndexes;
    private int[] tunnelTileIndexes;
    private Name name;
    private Color color;
    public static final double SIZE = 16;
    private static final double MAX_SPEED = 1;
    private double speed;
    private double normSpeed;
    private double elroySpeed;
    private double[] elroySpeeds = new double[3];
    private double tunnelSpeed;
    private double frightSpeed;
    private double frightenedAndCaughtSpeed;
    private int frightTime;
    private double[] position = new double[2];
    public GhostControl ghostControl;
    public DotCounter dotCounter;
    private int mode;
    
    public Ghost(Name name, Maze maze){
        this.maze = maze;
        penTileIndexes = maze.getPenTileIndexes();
        tunnelTileIndexes = maze.getTunnelTileIndexes();
        this.name = name;
        switch (name){
            case Blinky:
                color = Color.RED;
                break;
            case Pinky:
                color = Color.PINK;
                break;
            case Inky:
                color = Color.CYAN;
                break;
            case Clyde:
                color = Color.ORANGE;              
                break;
        }
        mode = 2;
        GhostControl tempControl = new GhostControl(this, maze);
        ghostControl=tempControl;
        ghostControl.getPosition(position);
        if(name == Name.Blinky){
            dotCounter = new DotCounter(Type.elroy);
        }
        else{
            dotCounter = new DotCounter(Type.personal);
        }
    }
    
   /**
    * Paints the ghost either its original color or blue depending on the mode.
    * @param g 
    */
   public void draw(Graphics g){
        if(mode == 3 && ghostControl.blue){
            Graphics2D ghost = (Graphics2D) g;
            ghost.setColor(Color.BLUE);
            ghost.fill(new RoundRectangle2D.Double(position[0],position[1],SIZE,SIZE,8,6));
            ghost.setColor(Color.BLACK);        
            ghost.fill(new Rectangle.Double(position[0]+4,position[1]+3,2,2));
            ghost.fill(new Rectangle.Double(position[0]+9,position[1]+3,2,2));
            ghost.fill(new Rectangle.Double(position[0]+3,position[1]+9,10,1));
            ghost.fill(buildTriangle(new double[] {position[0],position[0]+2.75,position[0]+5.5}, new double[] {position[1]+SIZE,position[1]+SIZE-3,position[1]+SIZE}));
            ghost.fill(buildTriangle(new double[] {position[0]+5.5,position[0]+8,position[0]+10.5}, new double[] {position[1]+SIZE,position[1]+SIZE-3,position[1]+SIZE}));
            ghost.fill(buildTriangle(new double[] {position[0]+10.5,position[0]+13.25,position[0]+16}, new double[] {position[1]+SIZE,position[1]+SIZE-3,position[1]+SIZE}));
        }
        else{
            Graphics2D ghost = (Graphics2D) g;
            ghost.setColor(color);
            ghost.fill(new RoundRectangle2D.Double(position[0],position[1],SIZE,SIZE,8,6));
            ghost.setColor(Color.BLACK);        
            ghost.fill(new Rectangle.Double(position[0]+4,position[1]+4,2,2));
            ghost.fill(new Rectangle.Double(position[0]+9,position[1]+4,2,2));
            ghost.fill(buildTriangle(new double[] {position[0],position[0]+2.75,position[0]+5.5}, new double[] {position[1]+SIZE,position[1]+SIZE-3,position[1]+SIZE}));
            ghost.fill(buildTriangle(new double[] {position[0]+5.5,position[0]+8,position[0]+10.5}, new double[] {position[1]+SIZE,position[1]+SIZE-3,position[1]+SIZE}));
            ghost.fill(buildTriangle(new double[] {position[0]+10.5,position[0]+13.25,position[0]+16}, new double[] {position[1]+SIZE,position[1]+SIZE-3,position[1]+SIZE}));
        }
    }
   
   /**
    * Assigns all the ghost speeds needed for one level.
    * @param norm 
    * @param elroy1 
    * @param elroy2
    * @param tunnel
    * @param fright 
    */
   public void assignSpeeds(double norm, double elroy1, double elroy2, double tunnel, double fright){
       this.normSpeed = MAX_SPEED*norm;
       this.elroySpeeds[0] = MAX_SPEED*elroy1;
       this.elroySpeeds[1] = MAX_SPEED*elroy2;
       this.elroySpeeds[2] = MAX_SPEED*elroy2;;
       this.elroySpeed = elroySpeeds[0];
       this.tunnelSpeed = MAX_SPEED*tunnel;
       this.frightSpeed = MAX_SPEED*fright;
       this.frightenedAndCaughtSpeed = MAX_SPEED*3;
   }         
 
   /**
    * Updates ghost's mode, position and speed.
    * @param e 
    */
   public void refresh(ActionEvent e){
        ghostControl.setTargetTile(mode);
        ghostControl.checkTunnel(e);
        ghostControl.getPosition(position);
        setSpeed();
   }
   
   /**
    * Resets the ghost's position.
    */
   public void resetPosition(){
       ghostControl.resetPosition();
       ghostControl.getPosition(position);
   }
   
   /**
    * Returns the name of the ghost.
    * @return 
    */
   public Name getName(){
       return name;
   }
   
   /**
    * Sets the mode of the ghost.
    * @param mode mode as integer
    */
   public void setMode(int mode){
       this.mode = mode;
   }

   /**
    * Sets the speed of the ghost depending on the mode and position.
    */
   public void setSpeed(){
       
       if(ghostControl.frightenedAndCaught){
           speed = frightenedAndCaughtSpeed;
       }
       
       else if(isInTunnel()){
           speed = tunnelSpeed;
       }
       else if(mode==1||mode==2){
           speed = normSpeed;
       }
       else if(mode==3){
           speed = frightSpeed;
       }
       else if(mode==4){
           speed = elroySpeed;
       }
       ghostControl.setGhostSpeed(speed);
   }
   
   /**
    * Sets the elroy's speed to elroyspeed1 or elroyspeed2
    * @param i 0 for elroyspeed1, 1 for elroyspeed2
    */
   public void setElroySpeed(int i){
       elroySpeed = elroySpeeds[i];
   }
   
   /**
    * Sets the elroy's limit to elroylimit1 or elroylimit2
    * @param limit  0 for elroylimit1, 1 for elroylimit2
    */
   public void setElroyLimit(int limit){
       dotCounter.setLimit(limit);
   }
   
   /**
    * Checks if the ghost is in the tunnel.
    * @return true or false
    */
   public boolean isInTunnel(){
       int i;
       for(i=0;i<tunnelTileIndexes.length;i++){
          if( maze.getIndex(position[0], position[1]) == tunnelTileIndexes[i] ){
           return true;
          } 
       }
       
       return false;
   }
   
   /**
    * Checks if the ghost is in the pen.
    * @return true or false
    */
   public boolean isInPen(){
       int i;
       for(i=0;i<penTileIndexes.length;i++){
          if( maze.getIndex(position[0], position[1]) == penTileIndexes[i] ){
            return true;
          } 
       }
       
       return false;
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