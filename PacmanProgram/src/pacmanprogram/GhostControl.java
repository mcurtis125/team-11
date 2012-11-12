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
    private double x,y,velx,vely,preVelX,preVelY,prePreVelX,prePreVelY,pacmanX,pacmanY,pacmanVelX,pacmanVelY,blinkyX,blinkyY;
    Name name;
    double[] targetTile = new double[2];
    double[] position = new double[2];
    int direction;
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
                preVelX=0;
                preVelY=0;
                prePreVelX=0;
                prePreVelY=0;
                break;
            case Pinky:
                x=48;
                y=64;
                velx=0;
                vely=0;
                preVelX=0;
                preVelY=0;
                prePreVelX=0;
                prePreVelY=0;                
                break;
            case Inky:
                x=144;
                y=272;
                velx=0;
                vely=0;
                preVelX=0;
                preVelY=0;
                prePreVelX=0;
                prePreVelY=0;
                break;
            case Clyde:
                x=320;
                y=272;
                velx=0;
                vely=0;
                preVelX=0;
                preVelY=0;
                prePreVelX=0;
                prePreVelY=0;                
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
        direction=nextDirection.chooseNextRandomTile(x, y, velx, vely,preVelX,preVelY,prePreVelX,prePreVelY);
        move(direction);
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
        
        direction = nextDirection.chooseNextTile(x, y, targetTile[0], targetTile[1], velx, vely, preVelX, preVelY,prePreVelX,prePreVelY);
        
        move(direction); 
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
        
        direction = nextDirection.chooseNextTile(x, y, targetTile[0], targetTile[1], velx, vely,preVelX,preVelY,prePreVelX,prePreVelY);
                
        move(direction);        

    }

    
    
    public void checkTunnel(){
        if(x<-12){
            vely=0;
            x=444;
        }
        
        else if(x>444){
            vely=0;
            x=-12;
        } 
    }
    
    
    
    
    public void move(int direction){
        prePreVelX=preVelX;
        prePreVelY=preVelY;
        preVelX=velx;
        preVelY=vely;
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
    
    
    public void giveBlinkyPos(double[] blinkyPos){
        blinkyX=blinkyPos[0];
        blinkyY=blinkyPos[1];
    }
    
    public void algorithmTest(Graphics g){
        Graphics2D ghost = (Graphics2D) g;
        ghost.setColor(Color.GREEN);
        ghost.fill(new Rectangle.Double(targetTile[0],targetTile[1],16,16));
    }

    
}
