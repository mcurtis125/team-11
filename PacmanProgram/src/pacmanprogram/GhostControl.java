/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import pacmanprogram.Ghost.Name;

/**
 *
 * @author stavy92
 */
class GhostControl {
    private double ghostSpeed;
    private double x,y,velx,vely,xNextTile,yNextTile,pacmanX,pacmanY,pacmanVelX,pacmanVelY,blinkyX,blinkyY;
    Name name;
    double[] targetTile = new double[2];
    double[] position = new double[2];
    double[] directionAndCoordinates = new double[3];
    double[] velocity = new double[2];
    double direction=1;
    double prevDirection;
    boolean doDirectionCheck=true;
    TargetTileFinder targetFinder = new TargetTileFinder();
    ShortestDistanceFinder nextDirection = new ShortestDistanceFinder();
    WallCollisionChecker walls = new WallCollisionChecker(1);
    
    public GhostControl(Ghost ghost){
        this.name=ghost.getName();
        reset();
    }

    public void refresh(ActionEvent e) {
        checkTunnel();
    }
    
    public void reset(){
        switch (name){
            case Blinky:
                x=399;
                y=64;
                velx=0;
                vely=0;
                break;
            case Pinky:
                x=48;
                y=64;
                velx=0;
                vely=0;               
                break;
            case Inky:
                x=144;
                y=272;
                velx=0;
                vely=0;
                break;
            case Clyde:
                x=320;
                y=272;
                velx=0;
                vely=0;                
                break;
        }
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
        if(mode==1){
            scatter();
        }
        else if(mode==2){
            chase();
        }
        else if(mode==3){
            frightened();
        }
    }
    
    public void setGhostSpeed(double ghostSpeed){
        this.ghostSpeed=ghostSpeed;
    }
    
    public void frightened(){
        
        getDirectionAndTileCoordinates(true); 
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
        
        getDirectionAndTileCoordinates(false);

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
        
        getDirectionAndTileCoordinates(false);     

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
        
            if(prevDirection==direction){
                move(direction,xNextTile,yNextTile);
            }
        
            else{
                doDirectionCheck=false;
                move(0,xNextTile,yNextTile);
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
        
            if(prevDirection==direction){
                move(direction,xNextTile,yNextTile);
            }
        
            else{
                doDirectionCheck=false;
                move(0,xNextTile,yNextTile);
            }
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
