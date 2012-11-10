/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.ActionEvent;

/**
 *
 * @author Kevin
 */
public class PositionVelocityGetter {
    
    
    PacmanControl pacControl;
    GhostControl blinkyControl;
    GhostControl pinkyControl;
    GhostControl inkyControl;
    GhostControl clydeControl;
    double pacmanCenterX, pacmanCenterY, blinkyX, blinkyY, pinkyX, pinkyY, inkyX, inkyY, clydeX, clydeY;
    double[] pacmanPos = new double[2]; 
    double[] pacmanVel = new double[2]; 
    double[] blinkyPos = new double[2];
    double[] pinkyPos = new double[2];
    double[] inkyPos = new double[2];
    double[] clydePos = new double[2];
    double sizeOfPacman=Pacman.SIZE;
    double sizeOfGhost=Ghost.SIZE;
    
    public PositionVelocityGetter(PacmanControl pacControl, GhostControl blinkyControl, GhostControl pinkyControl, 
                                                                    GhostControl inkyControl, GhostControl clydeControl){
        this.pacControl=pacControl;
        this.blinkyControl=blinkyControl;
        this.pinkyControl=pinkyControl;
        this.inkyControl=inkyControl;
        this.clydeControl=clydeControl;
    }
    
    
    
    public void refresh(ActionEvent e){
        pacControl.getPosition(pacmanPos);
        pacControl.getVelocity(pacmanVel);
        
        blinkyControl.getPosition(blinkyPos);
        pinkyControl.getPosition(pinkyPos);        
        inkyControl.getPosition(inkyPos);
        clydeControl.getPosition(clydePos);

        blinkyControl.givePacmanPos(pacmanPos);
        
        pinkyControl.givePacmanPos(pacmanPos);
        pinkyControl.givePacmanVel(pacmanVel);
        
        inkyControl.givePacmanPos(pacmanPos);
        inkyControl.givePacmanVel(pacmanVel);
        inkyControl.giveBlinkyPos(blinkyPos);
        
        clydeControl.givePacmanPos(pacmanPos);
        

    }
    
    
    public boolean pacmanGhostCollisionCheck(){

        pacmanCenterX=pacmanPos[0]+(sizeOfPacman/2);
        pacmanCenterY=pacmanPos[1]+(sizeOfPacman/2);
        blinkyX=blinkyPos[0];
        blinkyY=blinkyPos[1];        
        pinkyX=pinkyPos[0];
        pinkyY=pinkyPos[1];
        inkyX=inkyPos[0];
        inkyY=inkyPos[1];
        clydeX=clydePos[0];
        clydeY=clydePos[1];
        

        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, blinkyX, blinkyY)){
            return true;
        }
        
        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, pinkyX, pinkyY)){
            return true;
        }
        
        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, inkyX, inkyY)){
            return true;
        }
        
        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, clydeX, clydeY)){
            return true;
        }
        
        return false;
    }
    
    
    public boolean characterCollisionCheck(double pacmanCenterX, double pacmanCenterY, double ghostX, double ghostY){
        
        if(pacmanCenterX>=(ghostX)&&pacmanCenterX<=(ghostX+sizeOfGhost)&&pacmanCenterY>(ghostY+6)&&pacmanCenterY<(ghostY+10)){
            return true;
        }
        
        if(pacmanCenterY>=(ghostY)&&pacmanCenterY<=(ghostY+sizeOfGhost)&&pacmanCenterX>(ghostX+6)&&pacmanCenterX<(ghostX+10)){
            return true;
        }
        
        return false;
    }
    
    public void getBlinkyPos(double[] pos){
        pos=blinkyPos;
    }
    
    public void getPinkyPos(double[] pos){
        pos=pinkyPos;
    }
   
    public void getInkyPos(double[] pos){
        pos=inkyPos;
    }

    public void getClydePos(double[] pos){
        pos=clydePos;
    }
    
    
    
    
    
}
