/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.charactercontrol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import pacmangameplay.charactercontrol.Ghost.Name;
import pacmangameplay.mazedisplay.Maze;

/**
 *
 * @author stavy92
 */
public class GhostControl {
    private double ghostSpeed;
    private double x,y,velx,vely,xNextTile,yNextTile,pacmanX,pacmanY,pacmanVelX,pacmanVelY,blinkyX,blinkyY;
    Name name;
    Maze maze;
    double[] targetTile = new double[2];
    double[] position = new double[2];
    double[] directionAndCoordinates = new double[3];
    double[] velocity = new double[2];
    double direction=1;
    double prevDirection;
    boolean randomSelection=false;
    boolean doDirectionCheck=true;
    boolean frightenedAndCaught=false;
    boolean leavePen=false;
    boolean existent=true;
    boolean blue = false;
    WallCollisionChecker walls;
    TargetTileFinder targetFinder;
    ShortestDistanceFinder nextDirection;
    
    
    public GhostControl(Ghost ghost, Maze maze){
        this.name=ghost.getName();
        this.maze = maze;
        this.walls = new WallCollisionChecker(1, maze); 
        this.targetFinder = new TargetTileFinder(maze);
        this.nextDirection = new ShortestDistanceFinder(maze);
        resetPosition();
    }

    public void checkTunnel(ActionEvent e) {
        checkTunnel();
    }
    
    public void resetPosition(){
        switch (name){
            case Blinky:
                x=208;
                y=224;
                velx=0;
                vely=0;
                break;
            case Pinky:
                x=216;
                y=272;
                velx=0;
                vely=0;               
                break;
            case Inky:
                x=192;
                y=272;
                velx=0;
                vely=0;
                break;
            case Clyde:
                x=240;
                y=272;
                velx=0;
                vely=0;                
                break;
        }
        frightenedAndCaught=false;
        blue = false;
        leavePen=false;
        existent=true;
        doDirectionCheck=true;
        direction=1;
        
    }
    
    public void getPosition(double[] pos){
        pos[0] = x;
        pos[1] = y;
    }
    
    public void getVelocity(double[] vel){
        vel[0]=velx;
        vel[1]=vely;
    }
    
    public void setMode(int mode) {
        try{
            if(frightenedAndCaught){
                goHome();
            }

            else if(leavePen){
                leaveHome();
            }

            else if(leavePen==false&&x>=176&&x<=256&&y>=240&&y<=288){
                velx=0;
                vely=0;
            }
            else if(mode==1){
                scatter();
            }
            else if(mode==2){
                chase();
            }
            else if(mode==3){
                frightened();
            }
            else if(mode==4 && name == Name.Blinky){
                chase();
            }
        }
        catch(Exception e){resetPosition();
            e.printStackTrace();
        }
    }
    
    public void setGhostSpeed(double ghostSpeed){
        this.ghostSpeed=ghostSpeed;
    }
    
    public void frightened(){
        randomSelection=true;
        getDirectionAndTileCoordinates(randomSelection); 
    }
    
    public void scatter(){
        
            switch(name){
                case Blinky:
                    targetTile=targetFinder.getBlinkyScatterTarget();
                    break;
                case Pinky: 
                    targetTile=targetFinder.getPinkyScatterTarget();
                    break;
                case Inky:
                    targetTile=targetFinder.getInkyScatterTarget();
                    break;
                case Clyde:
                    targetTile=targetFinder.getClydeScatterTarget();
                    break;
            }
        randomSelection=false;
        getDirectionAndTileCoordinates(randomSelection);

    }
    
    public void chase(){
        
            pacmanX=PacmanControl.x;
            pacmanY=PacmanControl.y;
            pacmanVelX=PacmanControl.velx;
            pacmanVelY=PacmanControl.vely;
        
            switch(name){
                case Blinky:
                    targetTile=targetFinder.getBlinkyChaseTarget(pacmanX,pacmanY);
                    break;
                case Pinky: 
                    targetTile=targetFinder.getPinkyChaseTarget(pacmanX,pacmanY,pacmanVelX,pacmanVelY);
                    break;
                case Inky:
                    targetTile=targetFinder.getInkyChaseTarget(pacmanX,pacmanY,pacmanVelX,pacmanVelY,blinkyX,blinkyY);
                    break;
                case Clyde:
                    targetTile=targetFinder.getClydeChaseTarget(pacmanX,pacmanY,x,y);
                    break;
            }
            randomSelection=false;
            getDirectionAndTileCoordinates(randomSelection);   

    }
    
    public void becomeBlue(){
        blue = true;
    }
    
    public void setFrightenedAndCaughtTrue(){
        frightenedAndCaught = true;
    }
    
    public void setLeavePenTrue(){
        leavePen = true;
        frightenedAndCaught = false;
    }
    
    private void goHome(){
        targetTile=targetFinder.getReturnToHomeTarget();
        
        if(x>=targetTile[0]-16&&x<=targetTile[0]+16&&y>=targetTile[1]-0.5&&y<=targetTile[1]+0.5){
            resetPosition();
        }
        else{
            getDirectionAndTileCoordinates(false);
        }
    }
    
    private void leaveHome(){
        targetTile=targetFinder.getLeaveHomeTarget();
        
        if(x>=targetTile[0]-16&&x<=targetTile[0]+16&&y>=targetTile[1]-0.5&&y<=targetTile[1]+0.5){
            leavePen=false;
        }
        else{
            getDirectionAndTileCoordinates(false);
        }
    }
    
    public void becomeNonExistent(){
        existent=false;
    }

    
    
    public void checkTunnel(){
        if(x<-12){
            vely=0;
            x=444;
            xNextTile=432;
            yNextTile=272;
        }
        
        else if(x>444){
            vely=0;
            x=-12;
            xNextTile=0;
            yNextTile=272;
        } 
    }
    
    
    
    
    public void move(double direction, double xNextTile, double yNextTile){

       if(direction==0){
            velocity=nextDirection.landOnTileEdge(x, y, xNextTile, yNextTile, velx, vely);
            velx=velocity[0];
            vely=velocity[1];
            if(velx==0&&vely==0){
                direction=this.direction;
                this.doDirectionCheck=true;
            }
        }
        
        if(direction==1){
            velx=0;
            vely=walls.upCollisionInAdvance(x, y, -ghostSpeed);
        }
        if(direction==2){
            velx=walls.leftCollisionInAdvance(x, y, -ghostSpeed);
            vely=0;
        }
        if(direction==3){
            velx=0;
            vely=walls.downCollisionInAdvance(x, y, ghostSpeed);
        }
        if(direction==4){
            velx=walls.rightCollisionInAdvance(x, y, ghostSpeed);
            vely=0;
        }
            
        x=x+velx;
        y=y+vely;
    }
    
    public void getDirectionAndTileCoordinates(boolean isRandom){
        
        if(isRandom){
            if(doDirectionCheck){
                prevDirection=direction;
                directionAndCoordinates = nextDirection.chooseNextRandomTile(x, y, velx, vely);
                direction=directionAndCoordinates[0];
                xNextTile=directionAndCoordinates[1];
                yNextTile=directionAndCoordinates[2];
            }
        }

        else{
            if(doDirectionCheck){
                prevDirection=direction;
                directionAndCoordinates = nextDirection.chooseNextTile(x, y, targetTile[0], targetTile[1], velx, vely);
                direction=directionAndCoordinates[0];
                xNextTile=directionAndCoordinates[1];
                yNextTile=directionAndCoordinates[2];
            }
        }
        
        if(prevDirection==direction){
            move(direction,xNextTile,yNextTile);
        }
        
        else{
            doDirectionCheck=false;
            move(0,xNextTile,yNextTile);
        }
    }
    
    
    
    
    public void giveBlinkyPos(double[] blinkyPos){
        blinkyX=blinkyPos[0];
        blinkyY=blinkyPos[1];
    }
    
    public void movementAlgorithmTest(Graphics g){
        Graphics2D ghost = (Graphics2D) g;
        if(direction==0){
            ghost.setColor(Color.RED);
        }
        else if(direction==1){
            ghost.setColor(Color.GREEN);
        }
        else if(direction==2){
            ghost.setColor(Color.BLUE);
        }
        else if(direction==3){
            ghost.setColor(Color.YELLOW);
        }
        else if(direction==4){
            ghost.setColor(Color.WHITE);
        }

        ghost.fill(new Rectangle.Double(xNextTile,yNextTile,16,16));
    }
    
    public void targetAlgorithmTest(Graphics g){
        Graphics2D ghost = (Graphics2D) g;
        ghost.setColor(Color.GREEN);
        ghost.fill(new Rectangle.Double(targetTile[0],targetTile[1],16,16));
    }

    
}
