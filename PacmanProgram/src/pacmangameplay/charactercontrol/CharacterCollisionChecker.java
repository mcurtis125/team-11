/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmangameplay.charactercontrol;

import java.awt.event.ActionEvent;

/**
 * Checks collision between characters.
 * @author Kevin
 */
public class CharacterCollisionChecker {
    
    
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
    
    public CharacterCollisionChecker(Characters characters){
        this.pacControl=characters.pacman.pacControl;
        this.blinkyControl=characters.blinky.ghostControl;
        this.pinkyControl=characters.pinky.ghostControl;
        this.inkyControl=characters.inky.ghostControl;
        this.clydeControl=characters.clyde.ghostControl;
    }
    
    /**
     * Checks if Pacman is colliding with any of the ghosts.
     * @return The controller of the ghost that Pacman is colliding with.
     */
    public GhostControl pacmanGhostCollisionCheck(){
        updatePositions();

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
        

        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, blinkyX, blinkyY)&&blinkyControl.existent==true){
            return blinkyControl;
        }
        
        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, pinkyX, pinkyY)&&pinkyControl.existent==true){
            return pinkyControl;
        }
        
        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, inkyX, inkyY)&&inkyControl.existent==true){
            return inkyControl;
        }
        
        if(characterCollisionCheck(pacmanCenterX, pacmanCenterY, clydeX, clydeY)&&clydeControl.existent==true){
            return clydeControl;
        }
        
        return null;
    }
     
    /**
     * Gets all the characters' positions.  
     */
    private void updatePositions(){
        pacControl.getPosition(pacmanPos);
        pacControl.getVelocity(pacmanVel);
        
        blinkyControl.getPosition(blinkyPos);
        pinkyControl.getPosition(pinkyPos);
        inkyControl.getPosition(inkyPos);
        clydeControl.getPosition(clydePos);
        inkyControl.giveBlinkyPos(blinkyPos);
    }
    
    private boolean characterCollisionCheck(double pacmanCenterX, double pacmanCenterY, double ghostX, double ghostY){
        
        if(pacmanCenterX>=(ghostX)&&pacmanCenterX<=(ghostX+sizeOfGhost)&&pacmanCenterY>(ghostY+6)&&pacmanCenterY<(ghostY+10)){
            return true;
        }
        
        if(pacmanCenterY>=(ghostY)&&pacmanCenterY<=(ghostY+sizeOfGhost)&&pacmanCenterX>(ghostX+6)&&pacmanCenterX<(ghostX+10)){
            return true;
        }
        
        return false;
    }   
}